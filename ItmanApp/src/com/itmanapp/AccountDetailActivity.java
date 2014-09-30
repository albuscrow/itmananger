package com.itmanapp;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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
import com.itmanapp.entity.LoginEntity;
import com.itmanapp.entity.UserDetailEntity;
import com.itmanapp.json.GetLoginJson;
import com.itmanapp.json.GetUserDetailJson;
import com.itmanapp.util.AppManager;

/**
 * @date 2014-7-16
 * @author wangpeng
 * @class description 设置页面
 * 
 */
public class AccountDetailActivity extends Activity implements OnClickListener{

	/** 返回按钮 */
	private ImageView backBtn;
	
	/**账号名称 */
	private TextView accountNameTv;
	
	/**姓名 */
	private TextView nameTv;
	
	/**邮件地址 */
	private TextView mailAdrressTv;
	
	private String key;
	
	private String base;

	private String code;
	
	private String pwd;
	
	/**修改密码按钮*/
	private Button changePwdBtn;
	
	/**退出登录按钮*/
	private Button signInBtn;

	/** 进度框 */
	private ProgressDialog mDialog = null;
	
	/**用户实体类*/
	private UserDetailEntity entity = null; 
	
	/**本地保存*/
	private SharedPreferences spf = null;
	
	/**用户名*/
	private String loginUser;
	
	/**用户登录密码*/
	private String loginPass;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_account_detail);
		AppManager.getAppManager().addActivity(this);
		spf = getSharedPreferences("user",Context.MODE_PRIVATE);
		loginUser=spf.getString("LoginUser", "");
		loginPass=spf.getString("pwd", "");
		System.out.println(loginUser+loginPass);
		getView();
		
		getResult();
	}
	
	/**
	 * 控件显示
	 */
	private void getView() {
		backBtn=(ImageView)findViewById(R.id.backBtn);
		backBtn.setOnClickListener(this);
		accountNameTv=(TextView)findViewById(R.id.accountNameTv);
		nameTv=(TextView)findViewById(R.id.nameTv);
		mailAdrressTv=(TextView)findViewById(R.id.mailAdrressTv);
		changePwdBtn=(Button)findViewById(R.id.changePwdBtn);
		changePwdBtn.setOnClickListener(this);
		signInBtn=(Button)findViewById(R.id.signInBtn);
		signInBtn.setOnClickListener(this);
		
		mDialog = new ProgressDialog(AccountDetailActivity.this);
		mDialog.setMessage(getString(R.string.login_msg));
		
		key = getRandomString(5);
		String kb = key + "ASSET-HJTECH";
		base = md5(kb);
		code = Base64.encodeToString(base.getBytes(), Base64.DEFAULT);
		pwd = md5(loginPass);

		mDialog.show();
		mDialog.setCanceledOnTouchOutside(false);
	}
	
	/**
	 * description 解析数据
	 * 
	 * @param accountType 账号类型为1
	 * @return void
	 */
	private void getResult() {

		System.out.println("loginUser:"+loginUser);
		// tencent 123456
		String url = "http://121.40.188.122:8080/assetapi2/unituser/detail?"
				+ "key=z1zky&code=M0U3Q0IwQzE0RDMwNzUwQTI3MTZFNTc5NjIxMzJENzE="
				+ "&loginUser=" + loginUser + "&loginPass=" + pwd
				+ "&accountType=1";
		System.out.println(url);

		HashMap<String, String> params = new HashMap<String, String>();

		JsonObjectRequest req = new JsonObjectRequest(Method.GET, url,
				new JSONObject(params), new Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {

						System.out.println("@@" + response.toString());
						entity=GetUserDetailJson.getJson(response.toString());
						int result = GetUserDetailJson.result; 
						
						try {
							result = response.getInt("result");
						} catch (JSONException e) {
							e.printStackTrace();
						}
						
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
				//Toast.makeText(AccountDetailActivity.this, "成功获取数据", 1000).show();
				if(entity!=null){
					accountNameTv.setText(entity.getTuiAccount()+"");
					nameTv.setText(entity.getTuiName()+"");
					mailAdrressTv.setText(entity.getTuiEmail()+"");
				}else{
					Toast.makeText(AccountDetailActivity.this, "获取失败", 1000).show();
				}
				
				break;
			case -1:
				Toast.makeText(AccountDetailActivity.this, "验证不通过，非法用户", 1000).show();
				break;
			case 0:
				Toast.makeText(AccountDetailActivity.this, "获取失败", 1000).show();
				break;
			case 101:
				Toast.makeText(AccountDetailActivity.this, "账号不存在", 1000).show();
				break;
			case 102:
				Toast.makeText(AccountDetailActivity.this, "账号密码不正确", 1000).show();
				break;
			case 103:
				Toast.makeText(AccountDetailActivity.this, "参数错误", 1000).show();
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
	 *  点击事件
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.backBtn:
			finish();
			break;
			
		case R.id.changePwdBtn:
			Intent intent=new Intent(AccountDetailActivity.this,ChangePasswordActivity.class);
			startActivity(intent);
			break;
			
		case R.id.signInBtn:
			new AlertDialog.Builder(AccountDetailActivity.this).setTitle("提示")
			.setMessage("是否退出登录")
			.setPositiveButton("是", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					//本地保存登录用户信息
					SharedPreferences sharedPreferences = getSharedPreferences(
							"user", MODE_PRIVATE);
					Editor editor = sharedPreferences.edit();
					editor.putInt("Id", -1);
					editor.putString("Name", "");
					editor.putString("LoginUser", "");
					editor.putString("Email", "");
					editor.putString("pwd", "");
					editor.putBoolean("loginFlag", false);
					editor.commit();
					Intent intent=new Intent(AccountDetailActivity.this,LoginActivity.class);
					startActivity(intent);
					AppManager.getAppManager().finishAllActivity();
				}
			}).setNegativeButton("否", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					
				}
			}).show();
			break;
		}
	}

	
}
