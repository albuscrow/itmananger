package com.itmanapp;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.itmanapp.entity.CabinetEntity;
import com.itmanapp.entity.DeviceNeedToCheck;
import com.itmanapp.entity.EquipmentEntity;
import com.itmanapp.entity.RelatedDeviceEntity;
import com.itmanapp.json.GetCabinetDetailJson;
import com.itmanapp.json.GetCheckDetailJson;
import com.itmanapp.json.GetDeviceDetailJson;
import com.itmanapp.util.AppManager;
import com.itmanapp.util.CommonUtil;

public class GetOrderActivity extends BaseActivity implements OnClickListener{
	
	/** 返回按钮 */
	private ImageView backBtn;
	
	/**所属系统*/
	private TextView planName;
	
	/**设备编号*/
	private TextView time;
	
	/**设备类型*/
	private TextView deviceName;
	
	/**设备配置*/
	private TextView roomName;
    	
    private String key;
	
	private String base;

	private String code;
	
	/** 进度框 */
	private ProgressDialog mDialog = null;
	
	private DeviceNeedToCheck entity=null;
	
	private Long id;

	private TextView cabinetName;

	private TextView deviceCode;

	private TextView devicePosition;

	private TextView deviceType;

	private TextView project;

	private int status;


	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_get_order);
		AppManager.getAppManager().addActivity(this);
		
		getView();
		setPhone();
	}
	

	boolean canEdit = false;

	private long userId;
	
	/**
	 * 控件显示
	 */
	private void getView() {
		Intent intent=getIntent();
		id=intent.getLongExtra("id", -1);
		status = intent.getIntExtra("status", -1);
		canEdit = intent.getBooleanExtra("canEdit", false);
		userId = getSharedPreferences("user", Context.MODE_PRIVATE).getInt("Id", -1);
		
		backBtn=(ImageView)findViewById(R.id.backBtn);
		backBtn.setOnClickListener(this);
		
		planName=(TextView)findViewById(R.id.plan);
		time=(TextView)findViewById(R.id.time);
		deviceName=(TextView)findViewById(R.id.device_name);
		roomName=(TextView)findViewById(R.id.room);
		cabinetName=(TextView)findViewById(R.id.cabinet);
		deviceCode=(TextView)findViewById(R.id.code);
		devicePosition=(TextView)findViewById(R.id.position);
		deviceType=(TextView)findViewById(R.id.type);
		project=(TextView)findViewById(R.id.project);

		Button button = (Button) findViewById(R.id.get_Btn);
		if (canEdit) {
			button.setOnClickListener(this);
			if (status == 1) {
				button.setBackgroundResource(R.drawable.glsb);
				button.setText("填写检查结果");
			}else{
				button.setVisibility(View.GONE);
			}
		}else{
			button.setVisibility(View.GONE);
		}
		mDialog = new ProgressDialog(this);
		mDialog.setMessage(getString(R.string.login_msg));
		
		key = getRandomString(5);
		String kb = key + "ASSET-HJTECH";
		base = md5(kb);
		code = Base64.encodeToString(base.getBytes(), Base64.DEFAULT);

		mDialog.show();
		mDialog.setCanceledOnTouchOutside(false);
		
		getResult();
		
	}
	
	
	/**
	 * description 解析数据
	 * 
	 * @param accountType
	 *            单位用户，账号类型为2
	 * @return void
	 */
	private void getResult() {

		// tencent 123456
		String url = "http://121.40.188.122:8080/assetapi2/xj/detail?"
				+ "key=z1zky&code=M0U3Q0IwQzE0RDMwNzUwQTI3MTZFNTc5NjIxMzJENzE="
				+ "&txrId="+id;
		System.out.println(url);

		HashMap<String, String> params = new HashMap<String, String>();

		JsonObjectRequest req = new JsonObjectRequest(Method.GET, url,
				new JSONObject(params), new Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {

						System.out.println("@@" + response.toString());
						
						int result = GetCheckDetailJson.getJson(response.toString());
						if (result == 1) {
							entity=GetCheckDetailJson.entity;
							handler.sendEmptyMessage(1);
						} else if (result == -1) {
							handler.sendEmptyMessage(-1);
						} else if (result == 0) {
							handler.sendEmptyMessage(0);
						} else if (result == 101) {
							handler.sendEmptyMessage(101);
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
	
	@Override
	protected void onResume() {
		Button button = (Button) findViewById(R.id.get_Btn);
		if (canEdit) {
			button.setOnClickListener(this);
			if (status == 1) {
				button.setBackgroundResource(R.drawable.glsb);
				button.setText("填写检查结果");
			}
		}else{
			button.setVisibility(View.GONE);
		}

		super.onResume();
	}
	
	private void getOrder() {

		// tencent 123456
		String url = "http://121.40.188.122:8080/assetapi2/xj/sure?"
				+ "key=z1zky&code=M0U3Q0IwQzE0RDMwNzUwQTI3MTZFNTc5NjIxMzJENzE="
				+ "&txrId="+id
				+ "&userId=" + userId;
		System.out.println(url);
		if (status == 1) {
			handler.sendEmptyMessage(10086);
			return;
		}

		HashMap<String, String> params = new HashMap<String, String>();

		JsonObjectRequest req = new JsonObjectRequest(Method.GET, url,
				new JSONObject(params), new Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {

						System.out.println("@@" + response.toString());
						
						int result = -1;
						try {
							result = response.getInt("result");
						} catch (JSONException e) {
							e.printStackTrace();
						}
						if (result == 1) {
							handler.sendEmptyMessage(10086);
						} else if (result == -1) {
							handler.sendEmptyMessage(-1);
						} else if (result == 0) {
							handler.sendEmptyMessage(0);
						} else if (result == 101) {
							handler.sendEmptyMessage(101);
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
				//Toast.makeText(EquipmentSearchDetailActivity.this, "获取成功", 1000).show();
				planName.setText(entity.getTxpName()+"");
				time.setText(entity.getPlanDate()+"");
				deviceName.setText(entity.getTdName()+""); 
				roomName.setText(entity.getTmrName()+"");
				cabinetName.setText(entity.getTcName()+"");
				deviceCode.setText(entity.getTdCode()+"");
				devicePosition.setText(entity.getTdPosition()+"");
				deviceType.setText(entity.getTxcName()+"");
				project.setText(entity.getTxpNames());
				break;
			case -1:
				Toast.makeText(GetOrderActivity.this, "验证不通过，非法用户", 1000).show();
				break;
			case 0:
				Toast.makeText(GetOrderActivity.this, "获取失败", 1000).show();
				break;
			case 101:
				Toast.makeText(GetOrderActivity.this, "设备不存在", 1000).show();
				break;
			case 103:
				Toast.makeText(GetOrderActivity.this, "参数错误", 1000).show();
				break;
			case 10086:
				if (status == 0) {
					Toast.makeText(GetOrderActivity.this, "领取成功", 1000).show();
				}
//				findViewById(R.id.get_Btn).setEnabled(false);
				Intent intent1=new Intent(GetOrderActivity.this, CheckDeviceActivity.class);
				intent1.putExtra("id", entity.getTxrId());
				intent1.putExtra("planName", entity.getTxpName());
				intent1.putExtra("name", entity.getTdName());
				intent1.putExtra("code", entity.getTdCode());
				intent1.putExtra("cabName", entity.getTcName());
				intent1.putExtra("roomName", entity.getTmrName());
				status = 1;
				startActivityForResult(intent1, 1);
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

	/**点击事件*/
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.backBtn:
			finish();
			break;
		case R.id.get_Btn:
//			TODO
			getOrder();
			
			break;
		}
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == 20) {
			Intent intent=new Intent();  
			//请求代码可以自己设置，这里设置成20  
			setResult(20, intent);  
			finish();
		}
	}
}
