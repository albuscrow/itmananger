package com.itmanapp;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Request.Method;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.itmanapp.entity.WorkOrderEntity;
import com.itmanapp.json.GetWorkOrderDetailJson;
import com.itmanapp.util.AppManager;

import android.app.Activity;
import android.app.ProgressDialog;
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
 * @date 2014-7-17
 * @author wangpeng
 * @class description 我的维修工单详细页面
 * 
 */
public class MyRepairOrderDetailActivity extends Activity implements OnClickListener{

	/** 返回按钮 */
	private ImageView backBtn;
	
	/**订单编号*/
	private TextView orderNumberTv;
	
	/**分配时间*/
	private TextView assignTimeTv;
	
	/**使用单位*/
	private TextView useNameTv;
	
	/**所属系统*/
	private TextView belongsSystemTv;
	
	/**设备类型*/
	private TextView deviceTypeTv;
	
	/**报修描述*/
	private TextView despTv;
	
	/**维修状态*/
	private TextView statusTv;
	
    private String key;
	
	private String base;

	private String code;
	
	/** 进度框 */
	private ProgressDialog mDialog = null;
	
	private long detailId;
	
	/**工单实体类*/
	private WorkOrderEntity entity=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_repair_order_detail);
		AppManager.getAppManager().addActivity(this);
		getView();
	}

	/**
	 * 控件显示
	 */
	private void getView() {
		detailId=getIntent().getLongExtra("id", 0);
		mDialog = new ProgressDialog(MyRepairOrderDetailActivity.this);
		mDialog.setMessage(getString(R.string.login_msg));
		
		backBtn=(ImageView)findViewById(R.id.backBtn);
		backBtn.setOnClickListener(this);

		orderNumberTv=(TextView)findViewById(R.id.orderNumberTv);
		assignTimeTv=(TextView)findViewById(R.id.assignTimeTv);
		useNameTv=(TextView)findViewById(R.id.useNameTv);
		belongsSystemTv=(TextView)findViewById(R.id.belongsSystemTv);
		deviceTypeTv=(TextView)findViewById(R.id.deviceTypeTv);
		despTv=(TextView)findViewById(R.id.despTv);
		statusTv=(TextView)findViewById(R.id.statusTv);
		
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
		String url = "http://121.40.188.122:8080/assetapi2/order/detail?"
				+ "key=z1zky&code=M0U3Q0IwQzE0RDMwNzUwQTI3MTZFNTc5NjIxMzJENzE="
				+ "&detailId="+detailId;
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
						} else if (result == 104) {
							handler.sendEmptyMessage(104);
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
				//Toast.makeText(MyRepairOrderDetailActivity.this, "获取成功", 1000).show();
				if(entity!=null){
					orderNumberTv.setText(entity.getOrderNo()+"");
					assignTimeTv.setText(entity.getAllocateDate()+"");
					useNameTv.setText(entity.getAuiName()+"");
					belongsSystemTv.setText(entity.getRoomName()+"");
					deviceTypeTv.setText(entity.getAdName()+"");
					despTv.setText(entity.getDesp()+"");
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
					}else if(status==8){
						statusTv.setText("维修已审核");
					}
				}else{
					Toast.makeText(MyRepairOrderDetailActivity.this, "获取失败", 1000).show();
				}
				
				break;
			case -1:
				Toast.makeText(MyRepairOrderDetailActivity.this, "验证不通过，非法用户", 1000).show();
				break;
			case 0:
				Toast.makeText(MyRepairOrderDetailActivity.this, "获取失败", 1000).show();
				break;
			case 103:
				Toast.makeText(MyRepairOrderDetailActivity.this, "参数错误", 1000).show();
				break;
			case 104:
				Toast.makeText(MyRepairOrderDetailActivity.this, "工单不存在", 1000).show();
				break;
			case 111:
				Toast.makeText(MyRepairOrderDetailActivity.this, "提交成功", 1000).show();
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

	/***
	 * 点击事件
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.backBtn:
			finish();
			break;
		}
	}
	
}
