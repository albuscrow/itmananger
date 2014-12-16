package com.itmanapp2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.itmanapp2.entity.SystemSearchEntity;
import com.itmanapp2.util.AppManager;


/**
 * @date 2014-7-11
 * @author wangpeng
 * @class description 系统查询页面
 * 
 */
public class SystemSeachDetailActivity extends BaseActivity implements OnClickListener{
	
	/** 返回按钮 */
	private ImageView backBtn;
	
	/**系统编号显示*/
	private TextView systemNumberTv;
	
	/**系统名称显示*/
	private TextView systemNameTv;
	
	/**系统类型显示*/
	private TextView systemTypeTv;
	
	/**所在地址显示*/
	private TextView addressTv;
	
	/**使用单位显示*/
	private TextView useUnitTv;
	
	/**系统管理联系人显示*/
	private TextView systemContactTv;
	
	/**系统管理联系电话显示*/
	private TextView systemPhoneTv;
	
	/**客户单位联系人显示*/
	private TextView clientContactTv;
	
	/**客户单位联系电话显示*/
	private TextView clientPhoneTv;
	
	/**查阅整改记录按钮*/
	private Button cxzgjlBtn;
	
	/**关联设备按钮*/
	private Button glsbBtn;
	
	/**系统查询结果实体类*/
	private SystemSearchEntity entity = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sys_search_detail);
		AppManager.getAppManager().addActivity(this);
		getView();
		setPhone();
	}
	
	/**
	 * 控件显示
	 */
	private void getView() {
		Intent intent = this.getIntent(); 
		entity=(SystemSearchEntity)intent.getSerializableExtra("entity");
		System.out.println("数据："+entity);
		
		backBtn=(ImageView)findViewById(R.id.backBtn);
		backBtn.setOnClickListener(this);
		
		systemNumberTv=(TextView)findViewById(R.id.systemNumberTv);
		systemNameTv=(TextView)findViewById(R.id.systemNameTv);
		systemTypeTv=(TextView)findViewById(R.id.systemTypeTv);
		addressTv=(TextView)findViewById(R.id.addressTv);
		useUnitTv=(TextView)findViewById(R.id.useUnitTv);
		systemContactTv=(TextView)findViewById(R.id.systemContactTv);
		systemPhoneTv=(TextView)findViewById(R.id.systemPhoneTv);
		clientContactTv=(TextView)findViewById(R.id.clientContactTv);
		clientPhoneTv=(TextView)findViewById(R.id.clientPhoneTv);
		if(entity!=null){
			systemNumberTv.setText(entity.getAsCode()+"");
			systemNameTv.setText(entity.getAsName()+"");
			systemTypeTv.setText(entity.getSwName()+"");
			addressTv.setText(entity.getAsAddress()+"");
			useUnitTv.setText(entity.getAuiName()+"");
			systemContactTv.setText(entity.getAsChargePerson()+"");
			systemPhoneTv.setText(entity.getAsChargePhone()+"");
			clientContactTv.setText(entity.getSystemPerspm()+"");
			clientPhoneTv.setText(entity.getSystemPhone()+"");
		}
		cxzgjlBtn=(Button)findViewById(R.id.cxzgjlBtn);
		cxzgjlBtn.setOnClickListener(this);
		glsbBtn=(Button)findViewById(R.id.glsbBtn);
		glsbBtn.setOnClickListener(this);
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
		case R.id.cxzgjlBtn:
			Intent intent1=new Intent(SystemSeachDetailActivity.this,SystemModifyInfoActivity.class);
			if(entity.getAsId()>-1){
				intent1.putExtra("AsId", entity.getAsId());
			}
			if(entity.getAsCode() != null){
				intent1.putExtra("AsCode", entity.getAsCode());
			}
			if(entity.getAsName()!=null&&!entity.getAsName().equals("")){
				intent1.putExtra("AsName", entity.getAsName());
			}
			startActivity(intent1);
			break;

		case R.id.glsbBtn:
			Intent intent2=new Intent(SystemSeachDetailActivity.this,RelatedDeviceActivity.class);
			if(entity.getAsId()>-1){
				intent2.putExtra("AsId", entity.getAsId());
			}
			if(entity.getAsCode() != null){
				intent2.putExtra("AsCode", entity.getAsCode());
			}
			if(entity.getAsName()!=null&&!entity.getAsName().equals("")){
				intent2.putExtra("AsName", entity.getAsName());
			}
			startActivity(intent2);
			break;
		}
		
	}
	
}
