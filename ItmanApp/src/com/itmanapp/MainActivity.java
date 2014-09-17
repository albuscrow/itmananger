package com.itmanapp;

import java.util.Timer;
import java.util.TimerTask;

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

import com.itmanapp.util.AppManager;

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
	
	/** 待巡检工单 */
	private LinearLayout writtenInspectionLayout;
	
	/** 我的巡检 */
	private LinearLayout myInspectionLayout;
	
	/** 待确认工单 */
	private LinearLayout pendingConfirmWorkOrdersLayout;
	
	/** 待维修工单 */
	private LinearLayout pendingWorkOrdersLayout;
	
	/** 我的维修工单 */
	private LinearLayout myRepairOrderLayout;
	
	private TextView phoneTv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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
		
		deviceSearchLayout=(LinearLayout)findViewById(R.id.deviceSearchLayout);
		deviceSearchLayout.setOnClickListener(this);
		
		writtenInspectionLayout=(LinearLayout)findViewById(R.id.writtenInspectionLayout);
		writtenInspectionLayout.setOnClickListener(this);
		
		myInspectionLayout=(LinearLayout)findViewById(R.id.myInspectionLayout);
		myInspectionLayout.setOnClickListener(this);
		
		pendingConfirmWorkOrdersLayout=(LinearLayout)findViewById(R.id.pendingConfirmWorkOrdersLayout);
		pendingConfirmWorkOrdersLayout.setOnClickListener(this);
		
		pendingWorkOrdersLayout=(LinearLayout)findViewById(R.id.pendingWorkOrdersLayout);
		pendingWorkOrdersLayout.setOnClickListener(this);
		
		myRepairOrderLayout=(LinearLayout)findViewById(R.id.myRepairOrderLayout);
		myRepairOrderLayout.setOnClickListener(this);
		
		socialSecurityLayout=(LinearLayout)findViewById(R.id.social_securityLayout);
		socialSecurityLayout.setOnClickListener(this);
		
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


	/**
	 * 点击事件
	 * */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		//系统查询
		case R.id.social_searchLayout:
			Intent intent=new Intent(MainActivity.this,SystemSearchActivity.class);
			startActivity(intent);
			break;
			
		//设备查询
		case R.id.deviceSearchLayout:
			Intent intent2=new Intent(MainActivity.this,EquipmentSearchActivity.class);
			startActivity(intent2);
			break;
			
		//待巡检工单
		case R.id.writtenInspectionLayout:
			Intent intent4=new Intent(MainActivity.this,InspectionSystemPlanActivity.class);
			startActivity(intent4);
			break;
			
		//我的巡检
		case R.id.myInspectionLayout:
			Intent intent3=new Intent(MainActivity.this,MyInspectionActivity.class);
			startActivity(intent3);
			break;	
			
		//待确认工单
		case R.id.pendingConfirmWorkOrdersLayout:
			Intent intent5=new Intent(MainActivity.this,PendingConfirmWorkOrdersActivity.class);
			startActivity(intent5);
			break;
			
		//待维修工单
		case R.id.pendingWorkOrdersLayout:
			Intent intent6=new Intent(MainActivity.this,PendingWorkOrdersActivity.class);
			startActivity(intent6);
			break;
			
		//我的工单
		case R.id.myRepairOrderLayout:
			Intent intent7=new Intent(MainActivity.this,MyRepairOrderActivity.class);
			startActivity(intent7);
			break;

		//系统设置
		case R.id.social_securityLayout:
			Intent intent8=new Intent(MainActivity.this,AccountDetailActivity.class);
			startActivity(intent8);
			break;
	
		//拨打电话
		case R.id.phoneTv:
			  Intent intent9 = new Intent(Intent.ACTION_CALL,Uri.parse("tel:400-800-8003"));
			  startActivity(intent9);
			break;
			
		}
		
	}

}
