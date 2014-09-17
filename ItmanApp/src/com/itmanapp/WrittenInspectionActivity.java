package com.itmanapp;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
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
import com.itmanapp.adapter.PendingInspectionEquipmentAdatper;
import com.itmanapp.entity.PendingInspectionEquipmentEntity;
import com.itmanapp.json.GetWrittenInspectionJson;
import com.itmanapp.util.AppManager;

/**
 * @date 2014-7-15
 * @author wangpeng
 * @class description 待巡检工单页面
 * 
 */
public class WrittenInspectionActivity extends Activity implements
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
	private PendingInspectionEquipmentAdatper adapter;

	/** 服务端解析数据 */
	private List<PendingInspectionEquipmentEntity> list = new ArrayList<PendingInspectionEquipmentEntity>();

	/**巡检系统计划Id*/
	private int id;
	
	private Intent intent;
	
	private int position;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_written_inspection);
		AppManager.getAppManager().addActivity(this);
		intent=getIntent();
		id=intent.getIntExtra("id", 0);
		getView();
	}

	/**
	 * 控件显示
	 */
	private void getView() {
		mDialog = new ProgressDialog(WrittenInspectionActivity.this);
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

		getResult();

	}

	/**
	 * description 解析数据
	 * 
	 * @return void
	 */
	private void getResult() {

			String url = "http://211.155.229.136:8080/assetapi/xj/record?"
					+ "key=z1zky&code=M0U3Q0IwQzE0RDMwNzUwQTI3MTZFNTc5NjIxMzJENzE="
					+ "&planId="+id + "&status=0" ;
			System.out.println(url);

			HashMap<String, String> params = new HashMap<String, String>();

			JsonObjectRequest req = new JsonObjectRequest(Method.GET, url,
					new JSONObject(params), new Listener<JSONObject>() {

						@Override
						public void onResponse(JSONObject response) {
							
							System.out.println("@@" + response.toString());
							list = GetWrittenInspectionJson.getJson(response.toString());
							int result = GetWrittenInspectionJson.result;
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
				adapter = new PendingInspectionEquipmentAdatper(WrittenInspectionActivity.this, list);
				writtenInspectionLv.setAdapter(adapter);
				break;
			case -1:
				Toast.makeText(WrittenInspectionActivity.this, "验证不通过，非法用户",
						1000).show();
				break;
			case 0:
				Toast.makeText(WrittenInspectionActivity.this, "获取失败", 1000)
						.show();
				break;
			case 103:
				Toast.makeText(WrittenInspectionActivity.this, "参数错误", 1000)
						.show();
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

	/**
	 * 列表点击事件 
	 */
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Intent intent=new Intent(WrittenInspectionActivity.this,WrittenInspectionDetailActivity.class);
		intent.putExtra("asName", list.get(arg2).getAsName()+"");
		intent.putExtra("adName", list.get(arg2).getAdName()+"");
		intent.putExtra("adCode", list.get(arg2).getAdCode()+"");
		intent.putExtra("id", list.get(arg2).getId());
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
		}
		
	}

}
