package com.itmanapp;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.itmanapp.entity.WorkOrderEntity;
import com.itmanapp.json.GetWorkOrderDetailJson;
import com.itmanapp.util.AppManager;

/**
 * @date 2014-7-17
 * @author wangpeng
 * @class description 待维修工单详细页面
 * 
 */
public class PendingWorkOrderDetailActivity extends Activity implements OnClickListener{

	/** 返回按钮 */
	private ImageView backBtn;
	
	/**订单编号*/
	private TextView orderNumberTv;
	
	/**分配时间*/
	private TextView allocationTimeTv;
	
	/**维修项目*/
	private TextView projectTv;
	
	/**维修状态*/
	private TextView statusTv;
	
	/**维修描述编辑框*/
	private EditText despEdt;
	
	/**维修完成按钮*/
	private Button repairCompleteBtn;
	
	private String key;

	private String base;

	private String code;

	/** 进度框 */
	private ProgressDialog mDialog = null;
	
	/**维修明细Id*/
	private int detailId;
	
	/**工单实体类*/
	private WorkOrderEntity entity=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pending_work_order_detail);
		AppManager.getAppManager().addActivity(this);
		getView();
	}
	
	/**
	 * 控件显示
	 */
	private void getView() {
		detailId=getIntent().getIntExtra("id", 0);
		System.out.println("detailId==="+detailId);
		mDialog = new ProgressDialog(PendingWorkOrderDetailActivity.this);
		mDialog.setMessage(getString(R.string.login_msg));
		
		orderNumberTv=(TextView)findViewById(R.id.orderNumberTv);
		allocationTimeTv=(TextView)findViewById(R.id.allocationTimeTv);
		projectTv=(TextView)findViewById(R.id.projectTv);
		statusTv=(TextView)findViewById(R.id.statusTv);
		despEdt=(EditText)findViewById(R.id.despEdt);
		
		backBtn=(ImageView)findViewById(R.id.backBtn);
		backBtn.setOnClickListener(this);
		
		repairCompleteBtn=(Button)findViewById(R.id.repairCompleteBtn);
		repairCompleteBtn.setOnClickListener(this);

		key = getRandomString(5);
		System.out.println("key-->" + key);
		String kb = key + "ASSET-HJTECH";
		base = md5(kb);
		System.out.println("base-->" + base);
		code = Base64.encodeToString(base.getBytes(), Base64.DEFAULT);
		System.out.println("code-->" + code);

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

		// tencent 123456
		String url = "http://211.155.229.136:8080/assetapi/order/detail?"
				+ "key=z1zky&code=M0U3Q0IwQzE0RDMwNzUwQTI3MTZFNTc5NjIxMzJENzE="
				+ "&detailId=" + detailId;
		System.out.println(url);

		HashMap<String, String> params = new HashMap<String, String>();

		JsonObjectRequest req = new JsonObjectRequest(Method.GET, url,
				new JSONObject(params), new Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {

						System.out.println("@@" + response.toString());
					
						int result = GetWorkOrderDetailJson.getJson(response.toString());
						if (result == 1) {
							entity=GetWorkOrderDetailJson.entity;
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
				//Toast.makeText(PendingWorkOrderDetailActivity.this, "获取成功", 1000).show();
				if(entity!=null){
					orderNumberTv.setText(entity.getOrderNo()+"");
					allocationTimeTv.setText(entity.getAllocateDate()+"");
					projectTv.setText(entity.getTdName()+"");
					int status=entity.getOrderStatus();
					//1:提交报修 2:已经确认 3：已派工 4：待维修 5：已维修 6：已验收 0：审核失败 7：维修失败
					if(status==1){
						statusTv.setText("提交报修");
					}else if(status==2){
						statusTv.setText("已经确认");
					}else if(status==3){
						statusTv.setText("已派工");
					}else if(status==4){
						statusTv.setText("待维修");
					}else if(status==5){
						statusTv.setText("已维修");
					}else if(status==6){
						statusTv.setText("已验收");
					}else if(status==0){
						statusTv.setText("审核失败");
					}else if(status==7){
						statusTv.setText("维修失败");
					}
				}else{
					Toast.makeText(PendingWorkOrderDetailActivity.this, "获取失败", 1000).show();
				}
				break;
			case -1:
				Toast.makeText(PendingWorkOrderDetailActivity.this, "验证不通过，非法用户", 1000)
						.show();
				break;
			case 0:
				Toast.makeText(PendingWorkOrderDetailActivity.this, "获取失败", 1000).show();
				break;
			case 103:
				Toast.makeText(PendingWorkOrderDetailActivity.this, "参数错误", 1000).show();
				break;
			case 111:
				Toast.makeText(PendingWorkOrderDetailActivity.this, "提交成功", 1000).show();
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
			
		case R.id.repairCompleteBtn:
			String desp=despEdt.getText().toString().trim();
			if(isEmpty(desp)){
				Toast.makeText(PendingWorkOrderDetailActivity.this, "维修描述未填写", 1000).show();
				return;
			}
			submitData(desp);
			break;

		default:
			break;
		}
	}
	
	/**
	 * description 解析数据
	 * 
	 * @return void
	 */
	private void submitData(String desp) {

		try {
			desp=URLEncoder.encode(desp, "utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		// tencent 123456
		String url = "http://211.155.229.136:8080/assetapi/order/complete?"
				+ "key=z1zky&code=M0U3Q0IwQzE0RDMwNzUwQTI3MTZFNTc5NjIxMzJENzE="
				+ "&detailId="+detailId+"&desp="+desp;
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
								handler.sendEmptyMessage(111);
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
