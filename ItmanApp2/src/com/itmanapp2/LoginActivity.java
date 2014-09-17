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
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.itmanapp2.entity.LoginEntity;
import com.itmanapp2.json.GetLoginJson;
import com.itmanapp2.util.AppManager;

/**
 * @date 2014-7-10
 * @author wangpeng
 * @class description 登录页面
 * 
 */
public class LoginActivity extends Activity implements OnClickListener {

	/** 账号编辑框 */
	private EditText acountEdt;

	/** 密码编辑框 */
	private EditText pwdEdt;

	/** 登录按钮 */
	private Button loginBtn;

	/** 账号 */
	private String acountStr;

	/** 密码 */
	private String pwdStr;

	private String key;

	private String base;

	private String code;

	private String pwd;

	/** 进度框 */
	private ProgressDialog mDialog = null;
	
	/**登录用户实体类*/
	private LoginEntity entity = null; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		AppManager.getAppManager().addActivity(this);
		//获取控件显示
		getView();
	}

	/**
	 * 控件显示
	 */
	private void getView() {
		acountEdt = (EditText) this.findViewById(R.id.acountEdt);
		pwdEdt = (EditText) this.findViewById(R.id.pwdEdt);
		loginBtn = (Button) this.findViewById(R.id.loginBtn);
		loginBtn.setOnClickListener(this);
		mDialog = new ProgressDialog(LoginActivity.this);
		mDialog.setMessage(getString(R.string.login_msg));
	}

	/**
	 *登录按钮点击事件
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.loginBtn:
			acountStr = acountEdt.getText().toString().trim();
			pwdStr = pwdEdt.getText().toString().trim();
			if (isEmpty(acountStr)) {
				Toast.makeText(LoginActivity.this,
						getString(R.string.acountStr), 1000).show();
				return;
			}
			if (isEmpty(pwdStr)) {
				Toast.makeText(LoginActivity.this, getString(R.string.pwdStr),
						1000).show();
				return;
			}
			
			key = getRandomString(5);
			System.out.println("key-->" + key);
			String kb = key + "ASSET-HJTECH";
			base = md5(kb);
			System.out.println("base-->" + base);
			code = Base64.encodeToString(base.getBytes(), Base64.DEFAULT);
			System.out.println("code-->" + code);
			pwd = md5(pwdStr);
			System.out.println("pwd-->" + pwd);

			mDialog.show();
			mDialog.setCanceledOnTouchOutside(false);

			getLoginResult();

			break;

		}
	}

	/**
	 * description 解析登录数据
	 * 
	 * @param accountType 合作单位，账号类型为2
	 * @return void
	 */
	private void getLoginResult() {

		// tencent 123456
		String url = "http://211.155.229.136:8080/assetapi/unituser/login?"
				+ "key=z1zky&code=M0U3Q0IwQzE0RDMwNzUwQTI3MTZFNTc5NjIxMzJENzE="
				+ "&loginUser=" + acountStr + "&loginPass=" + pwd
				+ "&accountType=2";
		System.out.println(url);

		HashMap<String, String> params = new HashMap<String, String>();

		JsonObjectRequest req = new JsonObjectRequest(Method.GET, url,
				new JSONObject(params), new Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {

						System.out.println("@@" + response.toString());
						entity=GetLoginJson.getJson(response.toString());
						int result = GetLoginJson.result;
						if (result == 1) {
							if(entity!=null){
								//本地保存登录用户信息
								SharedPreferences sharedPreferences = getSharedPreferences(
										"user", MODE_PRIVATE);
								Editor editor = sharedPreferences.edit();
								editor.putInt("auuId", entity.getAuuId());
								editor.putString("auiName", entity.getAuiName());
								editor.putString("auuName", entity.getAuuName());
								editor.putString("auuLoginUser", entity.getAuuLoginUser());
								editor.putString("auuEmail", entity.getAuuMail());
								editor.putString("auuPhone", entity.getAuuPhone());
								editor.putString("pwd", pwdStr);
								editor.putBoolean("loginFlag", true);
								editor.commit();
							}
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
				Toast.makeText(LoginActivity.this, "登录成功", 1000).show();
				Intent intent=new Intent(LoginActivity.this,MainActivity.class);
				startActivity(intent);
				LoginActivity.this.finish();
				break;
			case -1:
				Toast.makeText(LoginActivity.this, "验证不通过，非法用户", 1000).show();
				break;
			case 0:
				Toast.makeText(LoginActivity.this, "登录失败", 1000).show();
				break;
			case 101:
				Toast.makeText(LoginActivity.this, "账号不存在", 1000).show();
				break;
			case 102:
				Toast.makeText(LoginActivity.this, "账号密码不正确", 1000).show();
				break;
			case 103:
				Toast.makeText(LoginActivity.this, "参数错误", 1000).show();
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

}
