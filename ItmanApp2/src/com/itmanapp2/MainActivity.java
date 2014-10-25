package com.itmanapp2;

import java.util.Timer;
import java.util.TimerTask;

import com.itmanapp2.util.AppManager;
import com.umeng.update.UmengUpdateAgent;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @date 2014-7-10
 * @author wangpeng
 * @class description 主框架页面
 * 
 */
public class MainActivity extends Activity implements OnClickListener{

	/** 退出程序标示 */
	private static Boolean isExit = false;
	
	/** 系统查询 */
	private LinearLayout socialSearchLayout;
	
	/** 系统设置 */
	private LinearLayout socialSecurityLayout;
	
	/** 设备查询 */
	private LinearLayout deviceSearchLayout;
	
	/**填写报修*/
	private LinearLayout fillRepairLayout;
	
	/**我的报修*/
	private LinearLayout myRepairLayout;
	
	/**待验收工单*/
	private LinearLayout repairAcceptanceLayout;
	
	private TextView phoneTv;
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		UmengUpdateAgent.update(this);
		setContentView(R.layout.activity_main);
		AppManager.getAppManager().addActivity(this);
		getView();
	}
	

	/**
	 * 控件显示
	 */
	private void getView() {
		socialSearchLayout=(LinearLayout)findViewById(R.id.social_searchLayout);
		socialSearchLayout.setOnClickListener(this);
		socialSecurityLayout=(LinearLayout)findViewById(R.id.social_securityLayout);
		socialSecurityLayout.setOnClickListener(this);
		deviceSearchLayout=(LinearLayout)findViewById(R.id.deviceSearchLayout);
		deviceSearchLayout.setOnClickListener(this);
		fillRepairLayout=(LinearLayout)findViewById(R.id.fillRepairLayout);
		fillRepairLayout.setOnClickListener(this);
		myRepairLayout=(LinearLayout)findViewById(R.id.myRepairLayout);
		myRepairLayout.setOnClickListener(this);
//		repairAcceptanceLayout=(LinearLayout)findViewById(R.id.repairAcceptanceLayout);
//		repairAcceptanceLayout.setOnClickListener(this);
		phoneTv=(TextView)findViewById(R.id.phoneTv);
		phoneTv.setOnClickListener(this);
	}

	/**
	 * description 菜单、返回键响应
	 * 
	 * @return void
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) { // 调用双击退出函数
			exitBy2Click();
		}
		return false;
	}

	/**
	 * description 退出程序
	 * 
	 * @return void
	 */
	private void exitBy2Click() {
		Timer tExit = null;
		if (isExit == false) {
			isExit = true; // 准备退出
			Toast.makeText(this, R.string.exit_app, Toast.LENGTH_SHORT).show();
			tExit = new Timer();
			tExit.schedule(new TimerTask() {
				@Override
				public void run() {
					isExit = false; // 取消退出
				}
			}, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务
		} else {
			SharedPreferences setting = getSharedPreferences("LOGINFO", 0);
			SharedPreferences.Editor editor = setting.edit();
			editor.putInt("LOGINSTATE", 0);
			editor.commit();
			finish();
			System.exit(0);
		}
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		//系统查询
		case R.id.social_searchLayout:
			Intent intent=new Intent(MainActivity.this, RoomSearchActivity.class);
			startActivity(intent);
			break;
			
		//设备查询
		case R.id.deviceSearchLayout:
			Intent intent2=new Intent(MainActivity.this,EquipmentSearchActivity.class);
			startActivity(intent2);
			break;
			
		//填写报修
		case R.id.fillRepairLayout:
			Intent intent3=new Intent(MainActivity.this,FillRepairActivity.class);
			startActivity(intent3);
			break;
			
		//待验收工单
//		case R.id.repairAcceptanceLayout:
//			Intent intent4=new Intent(MainActivity.this,RepairAcceptanceActivity.class);
//			startActivity(intent4);
//			break;
			
		//我的报修
		case R.id.myRepairLayout:
			Intent intent5=new Intent(MainActivity.this,MyRepairActivity.class);
			startActivity(intent5);
			break;
			
		//系统设置
		case R.id.social_securityLayout:
			Intent intent6=new Intent(MainActivity.this,AccountDetailActivity.class);
			startActivity(intent6);
			break;
			
		//拨打电话
		case R.id.phoneTv:
			  Intent intent9 = new Intent(Intent.ACTION_CALL,Uri.parse("tel:400-800-8003"));
			  startActivity(intent9);
			break;
		}
		
	}

}
