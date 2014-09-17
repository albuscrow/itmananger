package com.itmanapp2;

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
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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
import com.itmanapp2.util.AppManager;

/**
 * 
 * @author WANGPENG
 * @create date: 2014/7/15
 * @class description 修改密码页面
 */
public class ChangePasswordActivity extends Activity implements OnClickListener {

	/**返回按钮*/
	private ImageView backBtn;
	
	/** 原密码 */
	private EditText oldPwd = null;
	private String oldPwdStr = null;

	/** 新密码 */
	private EditText newPwd = null;
	private String newPwdStr = null;

	/** 确认密码 */
	private EditText surePwd = null;
	private String surePwdStr = null;

	/** 确定按钮 */
	private Button sureBtn = null;
	
	private TextView nameTv;

	/** 进度框 */
	private ProgressDialog mDialog = null;
	
    private String key;
	
	private String base;

	private String code;
	
	private String oldPass;
	
	/**本地保存*/
	private SharedPreferences spf = null;
	
	/**用户名*/
	private String loginUser;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_password);
		AppManager.getAppManager().addActivity(this);
		spf = getSharedPreferences("user",Context.MODE_PRIVATE);
		loginUser=spf.getString("auuLoginUser", "");
		// 调用根据控件id声明控件对象的方法
		getViews();
	}

	/**
	 * description 根据控件id声明控件对象的方法
	 * 
	 * @return void
	 */
	private void getViews() {
		nameTv=(TextView)findViewById(R.id.nameTv);
		nameTv.setText(loginUser+"");
		backBtn=(ImageView) findViewById(R.id.backBtn);
		oldPwd = (EditText) findViewById(R.id.oldPassword);
		newPwd = (EditText) findViewById(R.id.newPassword);
		surePwd = (EditText) findViewById(R.id.surePassword);
		sureBtn = (Button) findViewById(R.id.sureBtn);
		sureBtn.setOnClickListener(this);
		backBtn.setOnClickListener(this);
		mDialog = new ProgressDialog(ChangePasswordActivity.this);
		mDialog.setMessage(getString(R.string.loading));
	}

	/**
	 * description 判断新密码和旧密码是否一样
	 * 
	 * @return void
	 */
	private boolean comparePwd(String surePwdStr, String newPwd) {

		if (newPwd.equals(surePwdStr)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * description 非空判断
	 * 
	 * @return void
	 */
	private boolean isNull(String oldPwd, String newPwd, String surePwd) {

		if (null == oldPwd || "".equals(oldPwd) || null == newPwd
				|| "".equals(newPwd) || null == surePwd || "".equals(surePwd)) {
			return false;
		} else {
			return true;
		}

	}

	/**
	 * description 控件监听事件
	 * 
	 * @return void
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// 确定按钮
		case R.id.sureBtn:
			// 获值
			oldPwdStr = oldPwd.getText().toString().trim();
			newPwdStr = newPwd.getText().toString().trim();
			surePwdStr = surePwd.getText().toString().trim();
			// 非空判断
			if (!isNull(oldPwdStr, newPwdStr, surePwdStr)) {
				Toast.makeText(ChangePasswordActivity.this, "不能为空",
						Toast.LENGTH_SHORT).show();
				return;
			}

			// 密码是否一致
			if (!comparePwd(surePwdStr, newPwdStr)) {
				Toast.makeText(ChangePasswordActivity.this, "密码不一致",
						Toast.LENGTH_SHORT).show();
				return;
			}

			// 判断密码长度是否大于6
			if (newPwdStr.length() < 6) {
				Toast.makeText(ChangePasswordActivity.this,
						"密码需大于6位", Toast.LENGTH_SHORT).show();
				return;
			}
			// 显示对话框
			mDialog.show();
			mDialog.setCanceledOnTouchOutside(false);
			sureBtn.setEnabled(false);
			
			key = getRandomString(5);
			System.out.println("key-->" + key);
			String kb = key + "ASSET-HJTECH";
			base = md5(kb);
			System.out.println("base-->" + base);
			code = Base64.encodeToString(base.getBytes(), Base64.DEFAULT);
			System.out.println("code-->" + code);
			oldPass = md5(oldPwdStr);
			System.out.println("oldPass-->" + oldPass);
			
			getResult();
			break;
			
			//返回	
		case R.id.backBtn:
			ChangePasswordActivity.this.finish();
			break;

		}
	}

	/**
	 * description 解析数据
	 * 
	 * @return void
	 */
	private void getResult() {
		// tencent 123456
		String url = "http://211.155.229.136:8080/assetapi/unituser/modifyPassword?"
				+ "key=z1zky&code=M0U3Q0IwQzE0RDMwNzUwQTI3MTZFNTc5NjIxMzJENzE="
				+ "&loginUser=" + loginUser + "&oldPass=" + oldPass
				+ "&accountType=2" + "&newPass=" + newPwdStr;
		System.out.println(url);

		HashMap<String, String> params = new HashMap<String, String>();

		JsonObjectRequest req = new JsonObjectRequest(Method.GET, url,
				new JSONObject(params), new Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {

						System.out.println("@@" + response.toString());
						try {
							int result = response.getInt("result");
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

	/**
	 * description handler数据操作
	 * 
	 * @return void
	 */
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				Toast.makeText(ChangePasswordActivity.this, "修改成功", 1000).show();
				//本地保存登录用户信息
				SharedPreferences sharedPreferences = getSharedPreferences(
						"user", MODE_PRIVATE);
				Editor editor = sharedPreferences.edit();
				editor.putString("pwd", newPwdStr);
				editor.commit();
				break;
			case -1:
				Toast.makeText(ChangePasswordActivity.this, "验证不通过，非法用户", 1000).show();
				break;
			case 0:
				Toast.makeText(ChangePasswordActivity.this, "修改失败", 1000).show();
				break;
			case 101:
				Toast.makeText(ChangePasswordActivity.this, "账号不存在", 1000).show();
				break;
			case 102:
				Toast.makeText(ChangePasswordActivity.this, "账号密码不正确", 1000).show();
				break;
			case 103:
				Toast.makeText(ChangePasswordActivity.this, "参数错误", 1000).show();
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

}
