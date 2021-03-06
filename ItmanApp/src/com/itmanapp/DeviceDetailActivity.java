package com.itmanapp;

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
import com.itmanapp.entity.EquipmentEntity;
import com.itmanapp.entity.RelatedDeviceEntity;
import com.itmanapp.json.GetDeviceDetailJson;
import com.itmanapp.util.AppManager;
import com.itmanapp.util.CommonUtil;

/**
 * @date 2014-7-15
 * @author wangpeng
 * @class description 设备查询结果页面
 * 
 */
public class DeviceDetailActivity extends BaseActivity implements OnClickListener{

	/** 返回按钮 */
	private ImageView backBtn;
	
	/**所属系统*/
	private TextView roomTv;
	
	/**设备编号*/
	private TextView deviceIdTv;
	
	/**设备类型*/
	private TextView deviceTypeTv;
	
	/**设备配置*/
	private TextView deviceConfigurationTv;
    	
    private String key;
	
	private String base;

	private String code;
	
	/** 进度框 */
	private ProgressDialog mDialog = null;
	
	private RelatedDeviceEntity entity=null;
	
	private String deviceCode;

	private TextView belongsCabinet;

	private TextView devicePosition;

	private TextView supplyName;

	private TextView model;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_device_detail);
		AppManager.getAppManager().addActivity(this);
		getView();
		setPhone();
	}
	
	/**
	 * 控件显示
	 */
	private void getView() {
		Intent intent=getIntent();
		deviceCode=intent.getStringExtra("deviceCode");
		
		backBtn=(ImageView)findViewById(R.id.backBtn);
		backBtn.setOnClickListener(this);
		
		roomTv=(TextView)findViewById(R.id.belongsRoomTv);
		deviceIdTv=(TextView)findViewById(R.id.deviceIdTv);
		deviceTypeTv=(TextView)findViewById(R.id.deviceTypeTv);
		deviceConfigurationTv=(TextView)findViewById(R.id.deviceConfigurationTv);
		devicePosition = (TextView)findViewById(R.id.deviceLocationTv);
		supplyName = (TextView)findViewById(R.id.deviceSupplyTv);
		model = (TextView)findViewById(R.id.deviceModelTv);
		
		//add by albuscrow
		belongsCabinet = (TextView) findViewById(R.id.belongsCabinetTv);
		findViewById(R.id.related_file_Btn).setOnClickListener(this);
		
		mDialog = new ProgressDialog(DeviceDetailActivity.this);
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
				roomTv.setText(CommonUtil.decorateStringWithUnderlineAndColor(entity.getTmrName()));
				roomTv.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(DeviceDetailActivity.this, RoomDetailActivity.class);
						intent.putExtra("roomId", entity.getRoomId());
						intent.putExtra("from", "device");
						startActivity(intent);
					}
				});
				deviceIdTv.setText(entity.getAdCode()+"");
				deviceTypeTv.setText(entity.getTdcName()+"");
				deviceConfigurationTv.setText(entity.getAdDesp()+"");
				devicePosition.setText(entity.getAdPosition());
				supplyName.setText(entity.getSupplyName());
				model.setText(entity.getModelName());
				((TextView)findViewById(R.id.name)).setText(entity.getAdName());
				
				belongsCabinet.setText(CommonUtil.decorateStringWithUnderlineAndColor(entity.getAsName()));
				belongsCabinet.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(DeviceDetailActivity.this, CabinetDetailActivity.class);
						intent.putExtra("cabinetId", entity.getCabinetId());
						startActivity(intent);
					}
				});
				break;
			case -1:
				Toast.makeText(DeviceDetailActivity.this, "验证不通过，非法用户", 1000).show();
				break;
			case 0:
				Toast.makeText(DeviceDetailActivity.this, "获取失败", 1000).show();
				break;
			case 101:
				Toast.makeText(DeviceDetailActivity.this, "设备不存在", 1000).show();
				break;
			case 103:
				Toast.makeText(DeviceDetailActivity.this, "参数错误", 1000).show();
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
			Intent intent1=new Intent(DeviceDetailActivity.this,RelatedFileActivity.class);
			if(entity.getAdId()>-1){
				intent1.putExtra("id", entity.getAdId());
			}

			intent1.putExtra("type", RelatedFileActivity.TYPE_DEVICE);

			if(entity.getAdName()!=null&&!entity.getAdName().equals("")){
				intent1.putExtra("name", entity.getAdName());
			}
			startActivity(intent1);
			break;		}
		
	}

	
}
