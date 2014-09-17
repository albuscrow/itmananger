package com.itmanapp;

import com.itmanapp.util.AppManager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * @date 2014-7-10
 * @author wangpeng
 * @class description 启动页面
 * 
 */
public class StartActivity extends Activity{
	
	/**本地保存*/
	private SharedPreferences spf = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		AppManager.getAppManager().addActivity(this);
		spf = getSharedPreferences("user",Context.MODE_PRIVATE);
		
		//加载2秒
		getThread();
	}
	
	/**
	 * 启动加载画面 2秒
	 */
	private void getThread(){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
					handler.sendEmptyMessage(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}).start();
	}
	
	/**
	 * 消息处理
	 **/
	Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				//判断是否已登录 否：进入登录页面  是：进入主页面
				if (spf.getBoolean("loginFlag", false)) {
					Intent intentLogin=new Intent(StartActivity.this,MainActivity.class);
					startActivity(intentLogin);
					StartActivity.this.finish();
				}else{
					Intent intentLogin=new Intent(StartActivity.this,LoginActivity.class);
					startActivity(intentLogin);
					StartActivity.this.finish();
				}
				
				break;

			default:
				break;
			}
		}
	};
	
}
