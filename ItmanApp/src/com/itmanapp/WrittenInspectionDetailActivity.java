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
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.itmanapp.adapter.WrittenInspectionDetailAdatper;
import com.itmanapp.entity.CheckItemEntity;
import com.itmanapp.json.GetItemJson;
import com.itmanapp.util.AppManager;

/**
 * @date 2014-7-29
 * @author wangpeng
 * @class description 待巡检详细提交页面
 * 
 */
public class WrittenInspectionDetailActivity extends BaseActivity implements OnClickListener{
	
	/** 返回按钮 */
	private ImageView backBtn;
	
	/**巡检目标*/
	private TextView inspectionTargetTv;
	
	/**设备*/
	private TextView deviceNameTv;
	
	/**设备编号*/
	private TextView deviceNumberTv;
	
	private Button submitBtn;
        	
    private String key;
	
	private String base;

	private String code;
	
	/** 进度框 */
	private ProgressDialog mDialog = null;
	
	/**列表控件*/
	private ListView inspectionProjectLv;
	
	/**待巡检提交-巡检项目-适配器*/
	private WrittenInspectionDetailAdatper adapter;
	
	/**数据集合*/
	private List<CheckItemEntity> list = new ArrayList<CheckItemEntity>();
	
	/**id提交集合*/
	private StringBuffer sbId=new StringBuffer();
	
	/**审批参数*/
	private StringBuffer sbResult=new StringBuffer();
	
	private Intent intent;
	
	/**巡检记录id*/
	private int id;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_check_device);
		AppManager.getAppManager().addActivity(this);
		intent=getIntent();
		id=intent.getIntExtra("id", 0);
		getView();
		setPhone();
	}
	
	/**
	 * 控件显示
	 */
	private void getView() {
		mDialog = new ProgressDialog(WrittenInspectionDetailActivity.this);
		mDialog.setMessage(getString(R.string.login_msg));
		mDialog.show();
		mDialog.setCanceledOnTouchOutside(false);
		
		backBtn=(ImageView)findViewById(R.id.backBtn);
		backBtn.setOnClickListener(this);
		
		inspectionTargetTv=(TextView)findViewById(R.id.inspectionTargetTv);
		deviceNameTv=(TextView)findViewById(R.id.deviceNameTv);
		deviceNumberTv=(TextView)findViewById(R.id.deviceNumberTv);
		
		submitBtn=(Button)findViewById(R.id.submitBtn);
		submitBtn.setOnClickListener(this);
		
		String asName=intent.getStringExtra("asName");
		String adName=intent.getStringExtra("adName");
		String adCode=intent.getStringExtra("adCode");
		
		inspectionTargetTv.setText(asName+"");
		deviceNameTv.setText(adName+"");
		deviceNumberTv.setText(adCode+"");
		
		key = getRandomString(5);
		String kb = key + "ASSET-HJTECH";
		base = md5(kb);
		code = Base64.encodeToString(base.getBytes(), Base64.DEFAULT);

		inspectionProjectLv=(ListView)findViewById(R.id.inspectionProjectLv);
		
		getResult();
		
	}
	
	/**
	 * description 解析数据
	 * 
	 * @return void
	 */
	private void getResult() {

		// tencent 123456
		String url = "http://211.155.229.136:8080/assetapi/xj/record/detail?"
				+ "key=z1zky&code=M0U3Q0IwQzE0RDMwNzUwQTI3MTZFNTc5NjIxMzJENzE="
				+ "&recordId="+id;
		System.out.println(url);

		HashMap<String, String> params = new HashMap<String, String>();

		JsonObjectRequest req = new JsonObjectRequest(Method.GET, url,
				new JSONObject(params), new Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						System.out.println("@@" + response.toString());
						
						list=GetItemJson.getJson(response.toString());
						int result = GetItemJson.result;
						if (result == 1) {
							handler.sendEmptyMessage(1);
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
				//Toast.makeText(WrittenInspectionDetailActivity.this, "获取成功", 1000).show();
				adapter=new WrittenInspectionDetailAdatper(WrittenInspectionDetailActivity.this, list);
				inspectionProjectLv.setAdapter(adapter);
				
//				for(int i=0;i<list.size();i++){
//					if(i==(list.size()-1)){
//						sbId.append(list.get(i).getTxpId());
//					}else if(list.size()==1){
//						sbId.append(list.get(i).getTxpId());
//					}else{
//						sbId.append(list.get(i).getTxpId()+",");
//					}
//				}
				
				break;
			case -1:
				Toast.makeText(WrittenInspectionDetailActivity.this, "验证不通过，非法用户", 1000).show();
				break;
			case 0:
				Toast.makeText(WrittenInspectionDetailActivity.this, "获取失败", 1000).show();
				break;
			case 103:
				Toast.makeText(WrittenInspectionDetailActivity.this, "参数错误", 1000).show();
				break;
			case 2:
				Toast.makeText(WrittenInspectionDetailActivity.this, "提交成功", 1000).show();
				Intent data=new Intent();  
	            //请求代码可以自己设置，这里设置成20  
	            setResult(20, data);  
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
		case R.id.submitBtn:
			
			for(int i=0;i<list.size();i++){
				if(i==(list.size()-1)){
					sbResult.append(list.get(i).getTxpStatus());
				}else if(list.size()==1){
					sbResult.append(list.get(i).getTxpStatus());
				}else{
					sbResult.append(list.get(i).getTxpStatus()+",");
				}
			}
			System.out.println("id-->"+sbId.toString()+"--Result-->"+sbResult.toString());
			submitResult();
			break;
		case R.id.backBtn:
			finish();
			break;
		}
	}
	
	/**
	 * description 提交数据
	 * 
	 * @param accountType
	 *            单位用户，账号类型为2
	 * @return void
	 */
	private void submitResult() {

		// tencent 123456
		String url = "http://211.155.229.136:8080/assetapi/xj/commit_item_result?"
				+ "key=z1zky&code=M0U3Q0IwQzE0RDMwNzUwQTI3MTZFNTc5NjIxMzJENzE="
				+ "&recordIds="+id+"&detailIds="+ sbId.toString()
				+ "&resultIds=" + sbResult.toString() ;
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
								handler.sendEmptyMessage(2);
							} else if (result == -1) {
								handler.sendEmptyMessage(-1);
							} else if (result == 0) {
								handler.sendEmptyMessage(0);
							} else if (result == 103) {
								handler.sendEmptyMessage(103);
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


}

