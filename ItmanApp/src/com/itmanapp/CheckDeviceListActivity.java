package com.itmanapp;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.itmanapp.adapter.NeedToCheckDeviceAdatper;
import com.itmanapp.adapter.RelatedDeviceAdatper;
import com.itmanapp.entity.DeviceNeedToCheck;
import com.itmanapp.entity.RelatedDeviceEntity;
import com.itmanapp.json.GetDeviceDetailJson;
import com.itmanapp.json.GetInspectionPlanJson;
import com.itmanapp.json.GetCheckDeviceListJson;
import com.itmanapp.json.GetRelatedDeviceForXJJson;
import com.itmanapp.json.GetRelatedDeviceJson;
import com.itmanapp.util.AppManager;

/**
 * @date 2014-7-15
 * @author wangpeng
 * @class description 待巡检工单页面
 * 
 */
public class CheckDeviceListActivity extends BaseActivity implements
		OnItemClickListener, OnClickListener {

	/** 返回按钮 */
	private ImageView backBtn;
	
	private String key;

	private String base;

	private String code;

	/** 进度框 */
	private ProgressDialog mDialog = null;

	/** 列表控件 */
	private ListView writtenInspectionLv = null;

	/**待处理工单适配器*/
	private NeedToCheckDeviceAdatper adapter;

	/** 服务端解析数据 */
	private List<DeviceNeedToCheck> list = new ArrayList<DeviceNeedToCheck>();

	/**巡检系统计划Id*/
	private long id;
	
	private Intent intent;
	
	private int position;

	private int userId;

	private SharedPreferences spf;

	private Long txrId;

	private String type;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_written_inspection);
		AppManager.getAppManager().addActivity(this);
		
		spf = getSharedPreferences("user",Context.MODE_PRIVATE);
		userId=spf.getInt("Id", 0);
		
		intent=getIntent();
		id=intent.getLongExtra("id", 0);
		txrId = intent.getLongExtra("txrId", -1);
		type = intent.getStringExtra("type");
		status = intent.getIntExtra("status", -1);
		getView();
		setPhone();
	}

	/**
	 * 控件显示
	 */
	private void getView() {
		mDialog = new ProgressDialog(CheckDeviceListActivity.this);
		mDialog.setMessage(getString(R.string.login_msg));
		
		backBtn=(ImageView)findViewById(R.id.backBtn);
		backBtn.setOnClickListener(this);

		writtenInspectionLv = (ListView) findViewById(R.id.writtenInspectionLv);
		writtenInspectionLv.setOnItemClickListener(this);
		

		key = getRandomString(5);
		String kb = key + "ASSET-HJTECH";
		base = md5(kb);
		code = Base64.encodeToString(base.getBytes(), Base64.DEFAULT);

		mDialog.show();
		mDialog.setCanceledOnTouchOutside(false);
		
		findViewById(R.id.get).setOnClickListener(this);

	}
	
	
	@Override
	protected void onResume() {
		getResult();
		super.onResume();
	}

	/**
	 * description 解析数据
	 * 
	 * @return void
	 */
	private void getResult() {

			String url = "http://121.40.188.122:8080/assetapi2/xj/listByXj?"
					+ "key=z1zky&code=M0U3Q0IwQzE0RDMwNzUwQTI3MTZFNTc5NjIxMzJENzE="
					+ "&txrId=" + txrId;
			
			System.out.println(url);

			HashMap<String, String> params = new HashMap<String, String>();

			JsonObjectRequest req = new JsonObjectRequest(Method.GET, url,
					new JSONObject(params), new Listener<JSONObject>() {

						@Override
						public void onResponse(JSONObject response) {
							
							System.out.println("@@" + response.toString());
							list = GetRelatedDeviceForXJJson.getJson(response.toString());
							int result = GetRelatedDeviceForXJJson.result;
							if (result == 1) {
								if (list != null && list.size() > 0) {
									// 适配数据
									handler.sendEmptyMessage(1);
								} else {
									handler.sendEmptyMessage(0);
								}
							} else if (result == -1) {
								handler.sendEmptyMessage(-1);
							} else if (result == 0) {
								handler.sendEmptyMessage(0);
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
				//Toast.makeText(WrittenInspectionActivity.this, "获取成功", 1000).show();
				adapter = new NeedToCheckDeviceAdatper(CheckDeviceListActivity.this, list);
				writtenInspectionLv.setAdapter(adapter);
				break;
			case -1:
				Toast.makeText(CheckDeviceListActivity.this, "验证不通过，非法用户",
						1000).show();
				break;
			case 0:
				Toast.makeText(CheckDeviceListActivity.this, "无数据", 1000)
						.show();
				break;
			case 103:
				Toast.makeText(CheckDeviceListActivity.this, "参数错误", 1000)
						.show();
				break;
				
			case 101:
				Toast.makeText(CheckDeviceListActivity.this, "系统出错", 1000)
				.show();
				break;
			
				
			case 10086:
				Toast.makeText(CheckDeviceListActivity.this, "领取成功", 1000)
						.show();
				adapter.get();
				break;
			}
			// 关闭ProgressDialog
			if (mDialog != null && mDialog.isShowing()) {
				mDialog.dismiss();
			}
		}
	};

	private int status;

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

	/**
	 * 列表点击事件 
	 */
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Intent intent=new Intent(CheckDeviceListActivity.this,GetOrderActivity.class);
		intent.putExtra("id", list.get(arg2).getTxrId());
		intent.putExtra("status", list.get(arg2).getTxrStatus());
		intent.putExtra("canEdit", true);
		position=arg2;
		startActivityForResult(intent, 100);
	}
	
	/**列表刷新*/
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(20==resultCode){
			list.remove(position);
			adapter.notifyDataSetChanged();
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	/**
	 * 点击事件
	 **/
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.backBtn:
			finish();
			break;
			
		case R.id.get:
			getOrder();
			break;
		}
		
	}
	
	private void getOrder() {

		// tencent 123456
		String url = "http://121.40.188.122:8080/assetapi2/xj/sure?"
				+ "key=z1zky&code=M0U3Q0IwQzE0RDMwNzUwQTI3MTZFNTc5NjIxMzJENzE="
				+ "&txrId="+txrId
				+ "&userId=" + userId;
		System.out.println(url);
//		if (status == 1) {
//			handler.sendEmptyMessage(10086);
//			return;
//		}

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

}
