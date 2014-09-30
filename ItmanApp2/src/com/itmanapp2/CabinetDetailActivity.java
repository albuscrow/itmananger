package com.itmanapp2;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.itmanapp2.entity.CabinetEntity;
import com.itmanapp2.entity.EquipmentEntity;
import com.itmanapp2.entity.RelatedDeviceEntity;
import com.itmanapp2.json.GetCabinetDetailJson;
import com.itmanapp2.json.GetDeviceDetailJson;
import com.itmanapp2.util.AppManager;
import com.itmanapp2.util.CommonUtil;

public class CabinetDetailActivity extends Activity implements OnClickListener{
	
	/** 返回按钮 */
	private ImageView backBtn;
	
	/**所属系统*/
	private TextView belongsRoomTv;
	
	/**设备编号*/
	private TextView deviceCodeTv;
	
	/**设备类型*/
	private TextView deviceNameTv;
	
	/**设备配置*/
	private TextView deviceUnitTv;
    	
    private String key;
	
	private String base;

	private String code;
	
	/** 进度框 */
	private ProgressDialog mDialog = null;
	
	private CabinetEntity entity=null;
	
	private Long cabinetId;

	private TextView deviceDepartmentTv;

	private TextView devicePositionTv;

	private TextView deviceTime;

	private TextView deviceDesp;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cabinet_detail);
		AppManager.getAppManager().addActivity(this);
		getView();
	}
	
	/**
	 * 控件显示
	 */
	private void getView() {
		Intent intent=getIntent();
		cabinetId=intent.getLongExtra("cabinetId", -1);
		
		backBtn=(ImageView)findViewById(R.id.backBtn);
		backBtn.setOnClickListener(this);
		
		belongsRoomTv=(TextView)findViewById(R.id.belongsRoomTv);
		deviceCodeTv=(TextView)findViewById(R.id.code);
		deviceNameTv=(TextView)findViewById(R.id.name);
		deviceUnitTv=(TextView)findViewById(R.id.unit);
		deviceDepartmentTv=(TextView)findViewById(R.id.department);
		devicePositionTv=(TextView)findViewById(R.id.position);
		deviceTime=(TextView)findViewById(R.id.time);
		deviceDesp=(TextView)findViewById(R.id.desp);
		
		findViewById(R.id.related_file_Btn).setOnClickListener(this);
		
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
		String url = "http://121.40.188.122:8080/assetapi2/cabinet/detail?"
				+ "key=z1zky&code=M0U3Q0IwQzE0RDMwNzUwQTI3MTZFNTc5NjIxMzJENzE="
				+ "&tcId="+cabinetId;
		System.out.println(url);

		HashMap<String, String> params = new HashMap<String, String>();

		JsonObjectRequest req = new JsonObjectRequest(Method.GET, url,
				new JSONObject(params), new Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {

						System.out.println("@@" + response.toString());
						
						int result = GetCabinetDetailJson.getJson(response.toString());
						if (result == 1) {
							entity=GetCabinetDetailJson.entity;
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
				belongsRoomTv.setText(entity.getRoomName()+"");
				deviceCodeTv.setText(entity.getTcCode()+"");
				deviceNameTv.setText(entity.getTcName()+""); 
				deviceUnitTv.setText(entity.getUnitName()+"");
				deviceDepartmentTv.setText(entity.getDepName()+"");
				devicePositionTv.setText(entity.getTcPosition()+"");
				deviceTime.setText(entity.getTcLayDate()+"");
				deviceDesp.setText(entity.getTcDescription()+"");

				break;
			case -1:
				Toast.makeText(CabinetDetailActivity.this, "验证不通过，非法用户", 1000).show();
				break;
			case 0:
				Toast.makeText(CabinetDetailActivity.this, "获取失败", 1000).show();
				break;
			case 101:
				Toast.makeText(CabinetDetailActivity.this, "设备不存在", 1000).show();
				break;
			case 103:
				Toast.makeText(CabinetDetailActivity.this, "参数错误", 1000).show();
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
			
		case R.id.related_file_Btn:
			Intent intent1=new Intent(CabinetDetailActivity.this,RelatedFileActivity.class);
			if(entity.getTcId()>-1){
				intent1.putExtra("id", entity.getTcId());
			}

			intent1.putExtra("type", RelatedFileActivity.TYPE_CABINET);

			if(entity.getTcName()!=null&&!entity.getTcName().equals("")){
				intent1.putExtra("name", entity.getTcName());
			}
			startActivity(intent1);
			break;		}
		
	}
}
