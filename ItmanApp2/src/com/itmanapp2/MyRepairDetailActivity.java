package com.itmanapp2;

import com.itmanapp2.util.AppManager;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @date 2014-7-27
 * @author wangpeng
 * @class description 我的报修详细页面
 * 
 */
public class MyRepairDetailActivity extends BaseActivity implements OnClickListener{
	
	/** 返回按钮 */
	private ImageView backBtn;
	
	/**系       统*/
	private TextView systemTv;
	
	/**设       备*/
	private TextView equipmentTv;
	
	/**报修类型*/
	private TextView repairTypeTv;
	
	/**故障描述*/
	private TextView errorDespTv;
	
	/**维修状态*/
	private TextView statusTv;
	
	/** 进度框 */
	private ProgressDialog mDialog = null;
	
	private Intent intent;
	
	/**明细Id*/
	private int detailId;
	
	private String wxName;
	
	private String asName;
	
	private String desp;
	
	private String adName;
	
	private int status;

	private String cabName;

	private String itemName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_repair_detail);
		AppManager.getAppManager().addActivity(this);
		intent=getIntent();
		getView();
		
		setPhone();
	}

	/**
	 * 控件显示
	 */
	private void getView() {
		detailId=intent.getIntExtra("id", 0);
		System.out.println("detailId==="+detailId);
		wxName=intent.getStringExtra("wxName");
		asName=intent.getStringExtra("asName");
		adName=intent.getStringExtra("adName");
		desp=intent.getStringExtra("desp");
		status=intent.getIntExtra("status", 0);
		cabName = intent.getStringExtra("cabName");
		itemName = intent.getStringExtra("itemName");
		
				
		backBtn=(ImageView)findViewById(R.id.backBtn);
		backBtn.setOnClickListener(this);
		mDialog = new ProgressDialog(MyRepairDetailActivity.this);
		mDialog.setMessage(getString(R.string.login_msg));
		systemTv=(TextView)findViewById(R.id.systemTv);
		equipmentTv=(TextView)findViewById(R.id.equipmentTv);
		repairTypeTv=(TextView)findViewById(R.id.repairTypeTv);
		errorDespTv=(TextView)findViewById(R.id.errorDespTv);
		statusTv=(TextView)findViewById(R.id.statusTv);
		
		mDialog.show();
		mDialog.setCanceledOnTouchOutside(false);
		
		systemTv.setText(cabName+"");
		equipmentTv.setText(adName+"");
		repairTypeTv.setText(itemName+"");
		errorDespTv.setText(desp+"");
		
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
		
		// 关闭ProgressDialog
		if (mDialog != null && mDialog.isShowing()) {
			mDialog.dismiss();
		}

	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.backBtn:
			finish();
			break;
		}
	}


}

