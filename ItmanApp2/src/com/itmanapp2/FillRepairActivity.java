package com.itmanapp2;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.itmanapp2.entity.CabinetEntity;
import com.itmanapp2.entity.DepEntity;
import com.itmanapp2.entity.DeviceTypeEntity;
import com.itmanapp2.entity.EquipmentEntity;
import com.itmanapp2.entity.FillRepairEntity;
import com.itmanapp2.entity.RelatedDeviceEntity;
import com.itmanapp2.entity.RoomEntity;
import com.itmanapp2.entity.UnitEntity;
import com.itmanapp2.json.GetCabinetDetailJson;
import com.itmanapp2.json.GetDepJson;
import com.itmanapp2.json.GetDeviceDetailJson;
import com.itmanapp2.json.GetDeviceTypeJson;
import com.itmanapp2.json.GetEptJson;
import com.itmanapp2.json.GetFillRepairJson;
import com.itmanapp2.json.GetRelatedCabinetListJson;
import com.itmanapp2.json.GetRelatedDeviceJson;
import com.itmanapp2.json.GetRoomSearchJson;
import com.itmanapp2.json.GetUnitJson;
import com.itmanapp2.util.AppManager;
import com.itmanapp2.util.CustomRequest;
import com.itmanapp2.widget.spiner.AbstractSpinerAdapter.IOnItemSelectListener;
import com.itmanapp2.widget.spiner.SpinerPopWindow;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


/**
 * @date 2014-7-18
 * @author wangpeng
 * @class description 填写报修页面
 * 
 */
public class FillRepairActivity extends BaseActivity implements OnClickListener, IOnItemSelectListener{
	
	/** 返回按钮 */
	private ImageView backBtn;
	
    private String key;
	
	private String base;

	private String code;
	
	/** 进度框 */
	private ProgressDialog mDialog = null;
	
	private List<UnitEntity> unitList;
	
	private List<DepEntity> depList;
	
	/**所属系统数据集合*/
	private List<RoomEntity> roomList = new ArrayList<RoomEntity>();
	
	/**所属系统名称集合*/
	private List<String> roomNameList = new ArrayList<String>();
	
	
	/**所属系统数据集合*/
	private List<CabinetEntity> cabList = new ArrayList<CabinetEntity>();
	
	/**所属系统名称集合*/
	private List<String> cabNameList = new ArrayList<String>();
	
	
	/**设备数据集合*/
    private List<RelatedDeviceEntity> eptList = new ArrayList<RelatedDeviceEntity>();
	
    /**设备名称集合*/
	private List<String> eptStrList = new ArrayList<String>();
	
	/**报修类型数据集合*/
	private List<DeviceTypeEntity> deviceTypeList = new ArrayList<DeviceTypeEntity>();
	
	/**报修类型名称集合*/
	private List<String> deviceTypeStrList = new ArrayList<String>();
	
	//所属系统
	private ImageView roomsIgv;
	private TextView roomsTv;
	
	private ImageView cabsIgv;
	private TextView cabsTv;
	//所属系统
	private ImageView depsIgv;
	private TextView depsTv;
	
	//设       备
	private ImageView equipmentIgv;
	private TextView equipmentTv;
	
	//报修类型
	private ImageView typeRepairIgv;
	private TextView typeRepairTv;
	
	//问题描述编辑框
	private EditText descriptionTv;
	private String descriptionStr;
	
	//提交按钮
	private Button submitBtn;
	
	//选择标示
	private int first=1;
	
	/**本地保存*/
	private SharedPreferences spf = null;
	
	/**用户Id*/
	private int userId;
	
	/**系统Id*/
	private long roomId = -1l;
	private long unitId;
	private long depId = -1l;
	private long cabId = -1l;
	
	/**类型Id*/
	private int typeId;
	
	/**设备Id*/
	private long adId = -1l;
	
//	/**维修类型Id*/
//	private long wxId;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fill_repair);
		AppManager.getAppManager().addActivity(this);
		spf = getSharedPreferences("user",Context.MODE_PRIVATE);
		userId = spf.getInt("auuId", -1);
		unitId = spf.getLong("unitId", -1);
		getView();
		setPhone();
	}
	
	/**
	 * 控件显示
	 */
	private void getView() {
		backBtn=(ImageView)findViewById(R.id.backBtn);
		backBtn.setOnClickListener(this);
		depsIgv=(ImageView)findViewById(R.id.depsIgv);
		depsIgv.setOnClickListener(this);
		depsTv=(TextView)findViewById(R.id.depsTv);
		roomsIgv=(ImageView)findViewById(R.id.roomsIgv);
		roomsIgv.setOnClickListener(this);
		roomsTv=(TextView)findViewById(R.id.roomsTv);
		cabsIgv = (ImageView)findViewById(R.id.cabIgv);
		cabsIgv.setOnClickListener(this);
		cabsTv = (TextView)findViewById(R.id.cabTv);
		equipmentIgv=(ImageView)findViewById(R.id.equipmentIgv);
		equipmentIgv.setOnClickListener(this);
		equipmentTv=(TextView)findViewById(R.id.equipmentTv);
		typeRepairIgv=(ImageView)findViewById(R.id.typeRepairIgv);
		typeRepairIgv.setOnClickListener(this);
		typeRepairTv=(TextView)findViewById(R.id.typeRepairTv);
		descriptionTv=(EditText)findViewById(R.id.descriptionTv);
		submitBtn=(Button)findViewById(R.id.submitBtn);
		submitBtn.setOnClickListener(this);
		
		mDialog = new ProgressDialog(FillRepairActivity.this);
		mDialog.setMessage(getString(R.string.login_msg));
		key = getRandomString(5);
		System.out.println("key-->" + key);
		String kb = key + "ASSET-HJTECH";
		base = md5(kb);
		System.out.println("base-->" + base);
		code = Base64.encodeToString(base.getBytes(), Base64.DEFAULT);
		System.out.println("code-->" + code);
		
		findViewById(R.id.scan).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent2=new Intent(FillRepairActivity.this,CaptureActivity.class);
				startActivityForResult(intent2, 10086);
			}
		});
		
		

		mDialog.show();
		mDialog.setCanceledOnTouchOutside(false);
		
		getResult05();
		
	}
	
	private RelatedDeviceEntity entity;

	private String deviceCode;
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK) {
			deviceCode = data.getStringExtra("deviceCode");
			getResult();
		}
	}
	
	private void fillUI() {
		roomId = entity.getRoomId();
		depId = entity.getDepId();
		cabId = entity.getCabinetId();
		adId = entity.getAdId();
		
		getResult1();
		getResult15();
		getResult2();
		getResult3();
		
		roomsTv.setText(entity.getTmrName());
		depsTv.setText(entity.getDepName());
		cabsTv.setText(entity.getAsName());
		equipmentTv.setText(entity.getAdName());
	}

	/**
	 * description 解析数据
	 * 
	 * @param accountType
	 *            单位用户，账号类型为2
	 * @return void
	 */
	private void getResult() {
//		deviceCode = "011504030001010010101000";
		// tencent 123456
		String url = "http://121.40.188.122:8080/assetapi2/device/detail?"
				+ "key=z1zky&code=M0U3Q0IwQzE0RDMwNzUwQTI3MTZFNTc5NjIxMzJENzE="
				+ "&deviceCode="+deviceCode;
		System.out.println(url);

		HashMap<String, String> params = new HashMap<String, String>();

		JsonObjectRequest req = new JsonObjectRequest(Method.GET, url,
				new JSONObject(params), new Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {

						System.out.println("@@" + response.toString());
						
						int result = GetDeviceDetailJson.getJson(response.toString());
						if (result == 1) {
							entity=GetDeviceDetailJson.entity;
							handler.sendEmptyMessage(37);
						} else if (result == -1) {
							handler.sendEmptyMessage(-1);
						} else if (result == 0) {
							handler.sendEmptyMessage(0);
						} else if (result == 101) {
							handler.sendEmptyMessage(1001);
						} else if (result == 103) {
							handler.sendEmptyMessage(103);
						}

					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {

						System.out.println("##" + error.toString());
						handler.sendEmptyMessage(0);

					}
				});
		DemoApplication.getInstance().getRequestQueue().add(req);
	}
	
	/**
	 * description 解析数据-获取单位下的系统列表
	 * 
	 * @param accountType
	 *            单位用户，账号类型为2
	 * @return void
	 */
	private void getResult05() {

		String url = "http://121.40.188.122:8080/assetapi2/room/getDepListByUnitId?"
				+ "key=z1zky&code=M0U3Q0IwQzE0RDMwNzUwQTI3MTZFNTc5NjIxMzJENzE="
				+ "&unitId=" + unitId;
		System.out.println(url);

		HashMap<String, String> params = new HashMap<String, String>();

		JsonObjectRequest req = new JsonObjectRequest(Method.GET, url,
				new JSONObject(params), new Listener<JSONObject>() {


					@Override
					public void onResponse(JSONObject response) {

						System.out.println("@@" + response.toString());
						depList=GetDepJson.getJson(response.toString());
						int result = GetDepJson.result;
						if (result == 1) {
							handler.sendEmptyMessage(10087);
						} else if (result == -1) {
							handler.sendEmptyMessage(-1);
						} else if (result == 0) {
							handler.sendEmptyMessage(0);
						} else if (result == 101) {
							handler.sendEmptyMessage(101);
						} else if (result == 102) {
							handler.sendEmptyMessage(102);
						} else if (result == 103) {
							handler.sendEmptyMessage(103);
						}

					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {

						System.out.println("##" + error.toString());
						handler.sendEmptyMessage(0);

					}
				});
		DemoApplication.getInstance().getRequestQueue().add(req);
	}
	
	/**
	 * description 解析数据-获取单位下的系统列表
	 * 
	 * @param accountType
	 *            单位用户，账号类型为2
	 * @return void
	 */
	private void getResult1() {

		String url = "http://121.40.188.122:8080/assetapi2/room/getRoomListByDepId?"
				+ "key=z1zky&code=M0U3Q0IwQzE0RDMwNzUwQTI3MTZFNTc5NjIxMzJENzE="
				+ "&depId="+depId;
		System.out.println(url);

		HashMap<String, String> params = new HashMap<String, String>();

		JsonObjectRequest req = new JsonObjectRequest(Method.GET, url,
				new JSONObject(params), new Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {

						System.out.println("@@" + response.toString());
						int result = GetRoomSearchJson.getJson(response.toString());
						roomList=GetRoomSearchJson.entity;
						if (result == 1) {
							handler.sendEmptyMessage(1);
						} else if (result == -1) {
							handler.sendEmptyMessage(-1);
						} else if (result == 0) {
							handler.sendEmptyMessage(0);
						} else if (result == 101) {
							handler.sendEmptyMessage(101);
						} else if (result == 102) {
							handler.sendEmptyMessage(102);
						} else if (result == 103) {
							handler.sendEmptyMessage(103);
						}

					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {

						System.out.println("##" + error.toString());
						handler.sendEmptyMessage(0);

					}
				});
		DemoApplication.getInstance().getRequestQueue().add(req);
	}
	/**
	 * description 解析数据-获取单位下的系统列表
	 * 
	 * @param accountType
	 *            单位用户，账号类型为2
	 * @return void
	 */
	private void getResult15() {

		String url = "http://121.40.188.122:8080/assetapi2/room/getCabinetListByRoomId?"
				+ "key=z1zky&code=M0U3Q0IwQzE0RDMwNzUwQTI3MTZFNTc5NjIxMzJENzE="
				+ "&roomId="+roomId;
		System.out.println(url);

		HashMap<String, String> params = new HashMap<String, String>();

		JsonObjectRequest req = new JsonObjectRequest(Method.GET, url,
				new JSONObject(params), new Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {

						System.out.println("@@" + response.toString());
						cabList = GetRelatedCabinetListJson.getJson(response.toString());
						int result=GetRelatedCabinetListJson.result;
						if (result == 1) {
							handler.sendEmptyMessage(10088);
						} else if (result == -1) {
							handler.sendEmptyMessage(-1);
						} else if (result == 0) {
							handler.sendEmptyMessage(0);
						} else if (result == 101) {
							handler.sendEmptyMessage(101);
						} else if (result == 102) {
							handler.sendEmptyMessage(102);
						} else if (result == 103) {
							handler.sendEmptyMessage(103);
						}

					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {

						System.out.println("##" + error.toString());
						handler.sendEmptyMessage(0);

					}
				});
		DemoApplication.getInstance().getRequestQueue().add(req);
	}
	/**
	 * description 解析数据-获取某个系统下的设备列表
	 * 
	 * @param accountType
	 *            单位用户，账号类型为2
	 * @return void
	 */
	private void getResult2() {

		String url = "http://121.40.188.122:8080/assetapi2/room/getDeviceListByRoomId?"
				+ "key=z1zky&code=M0U3Q0IwQzE0RDMwNzUwQTI3MTZFNTc5NjIxMzJENzE="
				+ "&cabinetId="+cabId;
		System.out.println(url);

		HashMap<String, String> params = new HashMap<String, String>();

		JsonObjectRequest req = new JsonObjectRequest(Method.GET, url,
				new JSONObject(params), new Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {

						System.out.println("@@" + response.toString());
						eptList=GetRelatedDeviceJson.getJson(response.toString());
						int result = GetRelatedDeviceJson.result;
						if (result == 1) {
							handler.sendEmptyMessage(2);
						} else if (result == -1) {
							handler.sendEmptyMessage(-1);
						} else if (result == 0) {
							handler.sendEmptyMessage(0);
						} else if (result == 101) {
							handler.sendEmptyMessage(101);
						} else if (result == 102) {
							handler.sendEmptyMessage(102);
						} else if (result == 103) {
							handler.sendEmptyMessage(103);
						}

					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {

						System.out.println("##" + error.toString());
						handler.sendEmptyMessage(0);

					}
				});
		DemoApplication.getInstance().getRequestQueue().add(req);
	}
	
	/**
	 * description 解析数据-获取维修子项目
	 * 
	 * @param accountType
	 *            单位用户，账号类型为2
	 * @return void
	 */
	private void getResult3() {

		String url = "http://121.40.188.122:8080/assetapi2/room/getWxCategoryListByDeviceId?"
				+ "key=z1zky&code=M0U3Q0IwQzE0RDMwNzUwQTI3MTZFNTc5NjIxMzJENzE="
				+ "&deviceId="+adId;
		System.out.println(url);

		HashMap<String, String> params = new HashMap<String, String>();

		JsonObjectRequest req = new JsonObjectRequest(Method.GET, url,
				new JSONObject(params), new Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {

						System.out.println("@@" + response.toString());
						deviceTypeList=GetDeviceTypeJson.getJson(response.toString());
						int result = GetDeviceTypeJson.result;
						if (result == 1) {
							handler.sendEmptyMessage(3);
						} else if (result == -1) {
							handler.sendEmptyMessage(-1);
						} else if (result == 0) {
							handler.sendEmptyMessage(0);
						} else if (result == 101) {
							handler.sendEmptyMessage(101);
						} else if (result == 102) {
							handler.sendEmptyMessage(102);
						} else if (result == 103) {
							handler.sendEmptyMessage(103);
						}

					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {

						System.out.println("##" + error.toString());
						handler.sendEmptyMessage(0);

					}
				});
		DemoApplication.getInstance().getRequestQueue().add(req);
	}
	
	/**
	 * description 解析数据-提交
	 * 
	 * @param accountType
	 *            单位用户，账号类型为2
	 * @return void
	 */
	private void submitResult() {

//		try {
//			descriptionStr = URLEncoder.encode(descriptionStr, "UTF-8");
//		} catch (UnsupportedEncodingException e1) {
//			e1.printStackTrace();
//		}
		
//		String url = "http://211.155.229.136:8080/assetapi2/order/add?"
//				+ "key=z1zky&code=M0U3Q0IwQzE0RDMwNzUwQTI3MTZFNTc5NjIxMzJENzE="
//				+ "&userId="+userId+"&roomId="+roomId+"&deviceId="+adId + "&cabinetId=" + cabId
//				+ "&unitId=" + unitId + "&depId="+depId 
//				+"&wxId="+getWxIdStr()+"&desp="+descriptionStr;
		String url = "http://121.40.188.122:8080/assetapi2/order/add?"
				+ "key=z1zky&code=M0U3Q0IwQzE0RDMwNzUwQTI3MTZFNTc5NjIxMzJENzE=";
		
		System.out.println(url);
		
		if (depId == -1 || roomId == -1 || cabId == -1 || adId == -1) {
			Toast.makeText(this, "请输入完整数据！", Toast.LENGTH_SHORT);
			return;
		}

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("userId", String.valueOf(userId));
		params.put("unitId", String.valueOf(unitId));
		params.put("depId", String.valueOf(depId));
		params.put("roomId", String.valueOf(roomId));
		params.put("cabinetId", String.valueOf(cabId));
		params.put("deviceId", String.valueOf(adId));
		params.put("wxIds", getWxIdStr());
		params.put("desp", descriptionStr);

		CustomRequest req = new CustomRequest(Method.POST, url,
				params, new Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {

						System.out.println("@@" + response.toString());
						try {
							int result = response.getInt("result");
							if (result == 1) {
								handler.sendEmptyMessage(4);
							} else if (result == -1) {
								handler.sendEmptyMessage(-1);
							} else if (result == 0) {
								handler.sendEmptyMessage(0);
							} else if (result == 103) {
								handler.sendEmptyMessage(103);
							} else if (result == 105) {
								handler.sendEmptyMessage(105);
							}
						} catch (JSONException e) {
							e.printStackTrace();
						}

					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {

						System.out.println("##" + error.toString());
						handler.sendEmptyMessage(0);

					}
				});
		DemoApplication.getInstance().getRequestQueue().add(req);
	}

	private String getWxIdStr() {
		String result = "";
		for (Long i : item) {
			result += (i.toString() + ",");
		}
		if (result.length() > 0) {
			result = result.substring(0, result.length() - 1);
		}
		return result;
	}

	private List<String> unitNameList = new ArrayList<String>();
	private List<String> depNameList = new ArrayList<String>();
	/**
	 * description 消息处理
	 * 
	 * @return void
	 */
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
		
			case 10086:
				//Toast.makeText(FillRepairActivity.this, "获取成功", 1000).show();
				for(int i = 0; i < unitList.size(); i++){
					unitNameList.add(unitList.get(i).getTuName());
				}
				mSpinerPopWindow0 = new SpinerPopWindow(FillRepairActivity.this);
				mSpinerPopWindow0.refreshData(unitNameList, 0);
				mSpinerPopWindow0.setItemListener(FillRepairActivity.this);
				break;
				
			case 10087:
				//Toast.makeText(FillRepairActivity.this, "获取成功", 1000).show();
				for(int i = 0; i < depList.size(); i++){
					depNameList.add(depList.get(i).getTdName());
				}
				mSpinerPopWindow05 = new SpinerPopWindow(FillRepairActivity.this);
				mSpinerPopWindow05.refreshData(depNameList, 0);
				mSpinerPopWindow05.setItemListener(FillRepairActivity.this);
				break;
			case 10088:
				//Toast.makeText(FillRepairActivity.this, "获取成功", 1000).show();
				for(int i = 0; i < cabList.size(); i++){
					cabNameList.add(cabList.get(i).getTcName());
				}
				mSpinerPopWindow15 = new SpinerPopWindow(FillRepairActivity.this);
				mSpinerPopWindow15.refreshData(cabNameList, 0);
				mSpinerPopWindow15.setItemListener(FillRepairActivity.this);
				break;			case 1:
				//Toast.makeText(FillRepairActivity.this, "获取成功", 1000).show();
				for(int i = 0; i < roomList.size(); i++){
					roomNameList.add(roomList.get(i).getTmrName());
				}
				mSpinerPopWindow1 = new SpinerPopWindow(FillRepairActivity.this);
				mSpinerPopWindow1.refreshData(roomNameList, 0);
				mSpinerPopWindow1.setItemListener(FillRepairActivity.this);
				break;
				
			case 2:
				//Toast.makeText(FillRepairActivity.this, "获取成功", 1000).show();
				for(int i = 0; i < eptList.size(); i++){
					eptStrList.add(eptList.get(i).getAdName());
				}
				mSpinerPopWindow2 = new SpinerPopWindow(FillRepairActivity.this);
				mSpinerPopWindow2.refreshData(eptStrList, 0);
				mSpinerPopWindow2.setItemListener(FillRepairActivity.this);
				break;
				
			case 3:
				//Toast.makeText(FillRepairActivity.this, "获取成功", 1000).show();
				for(int i = 0; i < deviceTypeList.size(); i++){
					deviceTypeStrList.add(deviceTypeList.get(i).getTwcName());
					deviceTypeNames.put(deviceTypeList.get(i).getTwcId(), deviceTypeList.get(i).getTwcName());
				}
				mSpinerPopWindow3 = new SpinerPopWindow(FillRepairActivity.this);
				mSpinerPopWindow3.setNeedDismiss(false);
				mSpinerPopWindow3.refreshData(deviceTypeStrList, 0);
				mSpinerPopWindow3.setItemListener(FillRepairActivity.this);
				
				break;
				
			case -1:
				Toast.makeText(FillRepairActivity.this, "验证不通过，非法用户", 1000).show();
				break;
			case 0:
				Toast.makeText(FillRepairActivity.this, "获取失败", 1000).show();
				break;
			case 103:
				Toast.makeText(FillRepairActivity.this, "参数错误", 1000).show();
				break;
			case 105:
				Toast.makeText(FillRepairActivity.this, "设备不存在", 1000).show();
				break;
			case 4:
				Toast.makeText(FillRepairActivity.this, "添加成功", 1000).show();
				setResult(20);
				finish();
				break;
			case 37:
				fillUI();
				break;
			case 1001:
				Toast.makeText(FillRepairActivity.this, "未查询到该设备", Toast.LENGTH_SHORT).show();
				break;
			}
			// 关闭ProgressDialog
			if (mDialog != null && mDialog.isShowing()) {
				mDialog.dismiss();
			}
		}
	};

	/**
	 * description 随机生成5位字符串
	 * 
	 * @return void
	 */
	public String getRandomString(int length) { // length表示生成字符串的长度
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 判断是否为空
	 */
	private boolean isEmpty(String result) {
		if (result == null || result.equals("")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * description 对密码进行加密
	 * 
	 * @param password2
	 * @return
	 */
	private String md5(String password) {
		byte[] hash;
		try {
			hash = MessageDigest.getInstance("MD5").digest(
					password.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Huh, MD5 should be supported?", e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Huh, UTF-8 should be supported?", e);
		}
		StringBuilder hex = new StringBuilder(hash.length * 2);
		for (byte b : hash) {
			if ((b & 0xFF) < 0x10)
				hex.append("0");
			hex.append(Integer.toHexString(b & 0xFF));
		}
		return hex.toString().toUpperCase();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.backBtn:
			finish();
			break;

		case R.id.depsIgv:
			if(depNameList.size()>0 && depNameList!=null){
				depId = -1;
				roomId = -1;
				cabId = -1;
				adId = -1;
				roomsTv.setText("");
				cabsTv.setText("");
				equipmentTv.setText("");
				typeRepairTv.setText("");
				roomNameList.clear();
				cabNameList.clear();
				eptStrList.clear();
				deviceTypeStrList.clear();
				deviceTypeNames.clear();
				showSpinWindow05();
				first=15;
			}
			break;
					case R.id.cabIgv:
			if(cabNameList.size()>0 && cabNameList!=null){
				cabId = -1;
				adId = -1;
				equipmentTv.setText("");
				typeRepairTv.setText("");
				eptStrList.clear();
				deviceTypeStrList.clear();
				deviceTypeNames.clear();
				showSpinWindow15();
				first=16;
			}
			break;		case R.id.roomsIgv:
			if(roomNameList.size()>0 && roomNameList!=null){
				roomId = -1;
				cabId = -1;
				adId = -1;
				cabsTv.setText("");
				equipmentTv.setText("");
				typeRepairTv.setText("");
				cabNameList.clear();
				eptStrList.clear();
				deviceTypeStrList.clear();
				deviceTypeNames.clear();
				showSpinWindow1();
				first=1;
			}
			
			break;

		case R.id.equipmentIgv:
			if(eptStrList.size()>0&&eptStrList!=null){
				adId = -1;
				deviceTypeNames.clear();
				deviceTypeStrList.clear();
				typeRepairTv.setText("");
				showSpinWindow2();
				first=2;
			}
			
			break;
			
		case R.id.typeRepairIgv:
			System.out.println("systemsList:"+roomNameList);
			System.out.println("eptStrList:"+eptStrList);
			System.out.println("deviceTypeStrList"+deviceTypeStrList);
			if(deviceTypeStrList.size()>0&&deviceTypeStrList!=null){
				System.out.println("333333333");
				showSpinWindow3();
				first=3;
			}
			
			break;
			
		case R.id.submitBtn:
			descriptionStr=descriptionTv.getText().toString().trim();
			if(isEmpty(descriptionStr)){
				Toast.makeText(FillRepairActivity.this, "别忘了填写问题描述", 1000).show();
				return;
			}
			submitResult();
			break;
		}
	}
	

//	@Override
//	public void onItemClick(int pos) {
//		if(first==1){
//			setHero1(pos);
//		}else if(first==2){
//			setHero2(pos);
//		}else if(first==3){
//			setHero3(pos);
//		}
//		
//	}

	private void setHero05(int pos){
		if (pos >= 0 && pos <= depNameList.size()){
			String value = depNameList.get(pos);
			depId=depList.get(pos).getTdId();
			getResult1();
			depsTv.setText(value);
		}
	}
	
	private void setHero1(int pos){
		if (pos >= 0 && pos <= roomNameList.size()){
			String value = roomNameList.get(pos);
			roomId=roomList.get(pos).getTmrId();
			getResult15();
			roomsTv.setText(value);
		}
	}
	
	private void setHero2(int pos){
		if (pos >= 0 && pos <= eptStrList.size()){
			String value = eptStrList.get(pos);
//			typeId=eptList.get(pos).getTypeId();
			adId=eptList.get(pos).getAdId();
			getResult3();
			equipmentTv.setText(value);
		}
	}
	
	Set<Long> item = new HashSet<Long>();
	
	private void setHero3(int pos){
		if (pos >= 0 && pos <= deviceTypeStrList.size()){
			
			long wxId=deviceTypeList.get(pos).getTwcId();
			if (item.contains(wxId)) {
				item.remove(wxId);
			}else{
				item.add(wxId);
			}
			String value = getItemStr();
			typeRepairTv.setText(value);
		}
	}
	

	Map<Long, String> deviceTypeNames = new HashMap<Long, String>();
	private String getItemStr() {
		String result = "";
		for (Long i : item) {
			result += (deviceTypeNames.get(i) + ",");
		}
		if (result.length() > 0) {
			result = result.substring(0, result.length() - 1);
		}
		return result;
	}

	private void setHero15(int pos){
		if (pos >= 0 && pos <= cabList.size()){
			String value = cabNameList.get(pos);
			cabId=cabList.get(pos).getTcId();
			getResult2();
			cabsTv.setText(value);
		}
	}
	private SpinerPopWindow mSpinerPopWindow1;
	private SpinerPopWindow mSpinerPopWindow0;
	private SpinerPopWindow mSpinerPopWindow05;
	private SpinerPopWindow mSpinerPopWindow15;
	
	private void showSpinWindow05(){
		mSpinerPopWindow05.setWidth(depsTv.getWidth());
		mSpinerPopWindow05.showAsDropDown(depsTv);
	}
	
	private void showSpinWindow15(){
		mSpinerPopWindow15.setWidth(cabsTv.getWidth());
		mSpinerPopWindow15.showAsDropDown(cabsTv);
	}		private void showSpinWindow1(){
		mSpinerPopWindow1.setWidth(roomsTv.getWidth());
		mSpinerPopWindow1.showAsDropDown(roomsTv);
	}
	private SpinerPopWindow mSpinerPopWindow2;
	private void showSpinWindow2(){
		mSpinerPopWindow2.setWidth(equipmentTv.getWidth());
		mSpinerPopWindow2.showAsDropDown(equipmentTv);
	}
	private SpinerPopWindow mSpinerPopWindow3;
	private void showSpinWindow3(){
		mSpinerPopWindow3.setWidth(typeRepairTv.getWidth());
		mSpinerPopWindow3.showAsDropDown(typeRepairTv);
	}

	@Override
	public void onItemClick(int pos) {
		if(first==15){
			setHero05(pos);
		}else if(first==1){
			setHero1(pos);
		}else if(first==2){
			setHero2(pos);
		}else if(first==3){
			setHero3(pos);
		}else if(first == 16){
			setHero15(pos);
		}
	}
	
}
