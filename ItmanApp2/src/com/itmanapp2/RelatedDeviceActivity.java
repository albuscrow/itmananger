package com.itmanapp2;

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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.itmanapp2.adapter.RelatedDeviceAdatper;
import com.itmanapp2.entity.RelatedDeviceEntity;
import com.itmanapp2.json.GetRelatedDeviceJson;
import com.itmanapp2.util.AppManager;

/**
 * @date 2014-7-17
 * @author wangpeng
 * @class description 关联设备页面
 * 
 */
public class RelatedDeviceActivity extends Activity implements OnClickListener,OnItemClickListener{
	
	/** 返回按钮 */
	private ImageView backBtn;
	
    private String key;
	
	private String base;

	private String code;
	
	/** 进度框 */
	private ProgressDialog mDialog = null;
	
	/**列表控件 */
	private ListView relatedDeviceLv;
	
	/**相关设备列表适配器*/
	private RelatedDeviceAdatper adapter;
	
	/**数据集合*/
	private List<RelatedDeviceEntity> modifyInfoList = new ArrayList<RelatedDeviceEntity>();
	
	/**系统编号*/
    private TextView systemNumberTv;
	
    /**系统名称*/
	private TextView systemNameTv;
	
	private Intent intent;
	
	private int systemNumber;
	
    private String asCode;
	
	private String systemName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sys_related_device);
		AppManager.getAppManager().addActivity(this);
		intent=getIntent();
		systemNumber=intent.getIntExtra("AsId", -1);
		asCode=intent.getStringExtra("AsCode");
		systemName=intent.getStringExtra("AsName");
		getView();
	}
	
	/**
	 * 控件显示
	 */
	private void getView() {
		mDialog = new ProgressDialog(RelatedDeviceActivity.this);
		mDialog.setMessage(getString(R.string.login_msg));
		
		backBtn=(ImageView)findViewById(R.id.backBtn);
		backBtn.setOnClickListener(this);
		
		systemNumberTv=(TextView)findViewById(R.id.systemNumberTv);
		systemNameTv=(TextView)findViewById(R.id.systemNameTv);
		
		systemNumberTv.setText(asCode+"");
		systemNameTv.setText(systemName+"");
		
		relatedDeviceLv=(ListView)findViewById(R.id.relatedDeviceLv);
		relatedDeviceLv.setOnItemClickListener(this);
		
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
		String url = "http://211.155.229.136:8080/assetapi/system/related?"
				+ "key=z1zky&code=M0U3Q0IwQzE0RDMwNzUwQTI3MTZFNTc5NjIxMzJENzE="
				+ "&systemId="+systemNumber;
		System.out.println(url);

		HashMap<String, String> params = new HashMap<String, String>();

		JsonObjectRequest req = new JsonObjectRequest(Method.GET, url,
				new JSONObject(params), new Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {

						System.out.println("@@" + response.toString());
						modifyInfoList=GetRelatedDeviceJson.getJson(response.toString());
						int result = GetRelatedDeviceJson.result;
						if (result == 1) {
							System.out.println("List:"+modifyInfoList);
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
				//Toast.makeText(RelatedDeviceActivity.this, "获取成功", 1000).show();
				adapter=new RelatedDeviceAdatper(RelatedDeviceActivity.this, modifyInfoList);
				relatedDeviceLv.setAdapter(adapter);
				break;
			case -1:
				Toast.makeText(RelatedDeviceActivity.this, "验证不通过，非法用户", 1000).show();
				break;
			case 0:
				Toast.makeText(RelatedDeviceActivity.this, "获取失败", 1000).show();
				break;
			case 103:
				Toast.makeText(RelatedDeviceActivity.this, "参数错误", 1000).show();
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
		}
		
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Intent resultIntent = new Intent(RelatedDeviceActivity.this,EquipmentSearchDetailActivity.class);
		resultIntent.putExtra("deviceCode", modifyInfoList.get(arg2).getAdCode());
		startActivity(resultIntent);
		
	}

}
