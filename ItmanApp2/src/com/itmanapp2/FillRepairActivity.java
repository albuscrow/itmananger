package com.itmanapp2;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.itmanapp2.entity.DeviceTypeEntity;
import com.itmanapp2.entity.EquipmentEntity;
import com.itmanapp2.entity.FillRepairEntity;
import com.itmanapp2.json.GetDeviceTypeJson;
import com.itmanapp2.json.GetEptJson;
import com.itmanapp2.json.GetFillRepairJson;
import com.itmanapp2.util.AppManager;
import com.itmanapp2.widget.spiner.AbstractSpinerAdapter.IOnItemSelectListener;
import com.itmanapp2.widget.spiner.SpinerPopWindow;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
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
public class FillRepairActivity extends Activity implements OnClickListener, IOnItemSelectListener{
	
	/** 返回按钮 */
	private ImageView backBtn;
	
    private String key;
	
	private String base;

	private String code;
	
	/** 进度框 */
	private ProgressDialog mDialog = null;
	
	/**所属系统数据集合*/
	private List<FillRepairEntity> fillRepairList = new ArrayList<FillRepairEntity>();
	
	/**所属系统名称集合*/
	private List<String> systemsList = new ArrayList<String>();
	
	/**设备数据集合*/
    private List<EquipmentEntity> eptList = new ArrayList<EquipmentEntity>();
	
    /**设备名称集合*/
	private List<String> eptStrList = new ArrayList<String>();
	
	/**报修类型数据集合*/
	private List<DeviceTypeEntity> deviceTypeList = new ArrayList<DeviceTypeEntity>();
	
	/**报修类型名称集合*/
	private List<String> deviceTypeStrList = new ArrayList<String>();
	
	//所属系统
	private ImageView systemsIgv;
	private TextView systemsTv;
	
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
	private int systemId;
	
	/**类型Id*/
	private int typeId;
	
	/**设备Id*/
	private int adId;
	
	/**维修类型Id*/
	private int wxId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fill_repair);
		AppManager.getAppManager().addActivity(this);
		spf = getSharedPreferences("user",Context.MODE_PRIVATE);
		userId=spf.getInt("auuId", 0);
		getView();
	}
	
	/**
	 * 控件显示
	 */
	private void getView() {
		backBtn=(ImageView)findViewById(R.id.backBtn);
		backBtn.setOnClickListener(this);
		systemsIgv=(ImageView)findViewById(R.id.systemsIgv);
		systemsIgv.setOnClickListener(this);
		systemsTv=(TextView)findViewById(R.id.systemsTv);
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

		mDialog.show();
		mDialog.setCanceledOnTouchOutside(false);
		
		getResult1();
		
	}
	
	/**
	 * description 解析数据-获取单位下的系统列表
	 * 
	 * @param accountType
	 *            单位用户，账号类型为2
	 * @return void
	 */
	private void getResult1() {

		String url = "http://211.155.229.136:8080/assetapi/system/list?"
				+ "key=z1zky&code=M0U3Q0IwQzE0RDMwNzUwQTI3MTZFNTc5NjIxMzJENzE="
				+ "&userId="+userId;
		System.out.println(url);

		HashMap<String, String> params = new HashMap<String, String>();

		JsonObjectRequest req = new JsonObjectRequest(Method.GET, url,
				new JSONObject(params), new Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {

						System.out.println("@@" + response.toString());
						fillRepairList=GetFillRepairJson.getJson(response.toString());
						int result = GetFillRepairJson.result;
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
	 * description 解析数据-获取某个系统下的设备列表
	 * 
	 * @param accountType
	 *            单位用户，账号类型为2
	 * @return void
	 */
	private void getResult2() {

		String url = "http://211.155.229.136:8080/assetapi/device/list?"
				+ "key=z1zky&code=M0U3Q0IwQzE0RDMwNzUwQTI3MTZFNTc5NjIxMzJENzE="
				+ "&systemId="+systemId;
		System.out.println(url);

		HashMap<String, String> params = new HashMap<String, String>();

		JsonObjectRequest req = new JsonObjectRequest(Method.GET, url,
				new JSONObject(params), new Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {

						System.out.println("@@" + response.toString());
						eptList=GetEptJson.getJson(response.toString());
						int result = GetEptJson.result;
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

		String url = "http://211.155.229.136:8080/assetapi/wx/items?"
				+ "key=z1zky&code=M0U3Q0IwQzE0RDMwNzUwQTI3MTZFNTc5NjIxMzJENzE="
				+ "&typeId="+typeId;
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

		try {
			descriptionStr = URLEncoder.encode(descriptionStr, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		String url = "http://211.155.229.136:8080/assetapi/order/add?"
				+ "key=z1zky&code=M0U3Q0IwQzE0RDMwNzUwQTI3MTZFNTc5NjIxMzJENzE="
				+ "&userId="+userId+"&systemId="+systemId+"&deviceId="+adId
				+"&wxId="+wxId+"&desp="+descriptionStr;
		System.out.println(url);

		HashMap<String, String> params = new HashMap<String, String>();

		JsonObjectRequest req = new JsonObjectRequest(Method.POST, url,
				new JSONObject(params), new Listener<JSONObject>() {

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
			case 1:
				//Toast.makeText(FillRepairActivity.this, "获取成功", 1000).show();
				for(int i = 0; i < fillRepairList.size(); i++){
					systemsList.add(fillRepairList.get(i).getAsName());
				}
				mSpinerPopWindow1 = new SpinerPopWindow(FillRepairActivity.this);
				mSpinerPopWindow1.refreshData(systemsList, 0);
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
					deviceTypeStrList.add(deviceTypeList.get(i).getAdvItemName());
				}
				mSpinerPopWindow3 = new SpinerPopWindow(FillRepairActivity.this);
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
				finish();
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
			
		case R.id.systemsIgv:
			System.out.println("systemsList:"+systemsList);
			System.out.println("eptStrList:"+eptStrList);
			System.out.println("deviceTypeStrList"+deviceTypeStrList);
			if(systemsList.size()>0 && systemsList!=null){
				eptStrList.clear();
				deviceTypeStrList.clear();
				showSpinWindow1();
				first=1;
			}
			
			break;

		case R.id.equipmentIgv:
			System.out.println("systemsList:"+systemsList);
			System.out.println("eptStrList:"+eptStrList);
			System.out.println("deviceTypeStrList"+deviceTypeStrList);
			if(eptStrList.size()>0&&eptStrList!=null){
				System.out.println("222222222");
				deviceTypeStrList.clear();
				showSpinWindow2();
				first=2;
			}
			
			break;
			
		case R.id.typeRepairIgv:
			System.out.println("systemsList:"+systemsList);
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
	
	private void setHero1(int pos){
		if (pos >= 0 && pos <= systemsList.size()){
			String value = systemsList.get(pos);
			systemId=fillRepairList.get(pos).getAsId();
			getResult2();
			systemsTv.setText(value);
		}
	}
	
	private void setHero2(int pos){
		if (pos >= 0 && pos <= eptStrList.size()){
			String value = eptStrList.get(pos);
			typeId=eptList.get(pos).getTypeId();
			adId=eptList.get(pos).getAdId();
			getResult3();
			equipmentTv.setText(value);
		}
	}
	
	private void setHero3(int pos){
		if (pos >= 0 && pos <= deviceTypeStrList.size()){
			String value = deviceTypeStrList.get(pos);
			wxId=deviceTypeList.get(pos).getAdvId();
			typeRepairTv.setText(value);
		}
	}

	private SpinerPopWindow mSpinerPopWindow1;
	private void showSpinWindow1(){
		mSpinerPopWindow1.setWidth(systemsTv.getWidth());
		mSpinerPopWindow1.showAsDropDown(systemsTv);
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
		// TODO Auto-generated method stub
		if(first==1){
			setHero1(pos);
		}else if(first==2){
			setHero2(pos);
		}else if(first==3){
			setHero3(pos);
		}
	}
	
}
