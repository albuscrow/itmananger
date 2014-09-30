package com.itmanapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.itmanapp.entity.RoomEntity;
import com.itmanapp.util.AppManager;
import com.itmanapp.util.CommonUtil;


/**
 * @date 2014-7-11
 * @author wangpeng
 * @class description 系统查询页面
 * 
 */
public class RoomDetailActivity extends Activity implements OnClickListener{
	
	/** 返回按钮 */
	private ImageView backBtn;
	
	/**系统编号显示*/
	private TextView roomNumberTv;
	
	/**系统名称显示*/
	private TextView roomNameTv;
	
	/**系统类型显示*/
	private TextView systemTypeTv;
	
	/**所在地址显示*/
	private TextView addressTv;
	
	/**使用单位显示*/
	private TextView useUnitTv;
	
	/**系统管理联系人显示*/
	private TextView roomContactTv;
	
	/**系统管理联系电话显示*/
	private TextView roomPhoneTv;
	
	/**客户单位联系人显示*/
	private TextView clientContactTv;
	
	/**客户单位联系电话显示*/
	private TextView clientPhoneTv;
	
	/**查阅整改记录按钮*/
	private Button cxzgjlBtn;
	
	/**关联设备按钮*/
	private Button glsbBtn;
	
	/**系统查询结果实体类*/
	private RoomEntity entity = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_room_detail);
		AppManager.getAppManager().addActivity(this);
		getView();
	}
	
	/**
	 * 控件显示
	 */
	private void getView() {
		Intent intent = this.getIntent(); 
		entity=(RoomEntity)intent.getSerializableExtra("room");
		System.out.println("数据："+entity);
		
		backBtn=(ImageView)findViewById(R.id.backBtn);
		backBtn.setOnClickListener(this);
		
		roomNumberTv=(TextView)findViewById(R.id.related_type);
		roomNameTv=(TextView)findViewById(R.id.related_name);
		systemTypeTv=(TextView)findViewById(R.id.roomTypeTv);
		addressTv=(TextView)findViewById(R.id.addressTv);
		useUnitTv=(TextView)findViewById(R.id.useUnitTv);
		roomContactTv=(TextView)findViewById(R.id.roomContactTv);
		roomPhoneTv=(TextView)findViewById(R.id.roomPhoneTv);
		clientContactTv=(TextView)findViewById(R.id.clientContactTv);
		clientPhoneTv=(TextView)findViewById(R.id.clientPhoneTv);
		if(entity!=null){
			roomNumberTv.setText(entity.getTmrCode()+"");
			roomNameTv.setText(entity.getTmrName()+"");
			addressTv.setText(entity.getTmrPosition()+"");
			useUnitTv.setText(entity.getUnitName()+"");
			roomContactTv.setText(entity.getRoomManager()+"");
			String roomManagerPhone = entity.getRoomManagerPhone();
			System.out.println(roomManagerPhone);
			if (roomManagerPhone.length() == 0) {
				roomPhoneTv.setText("末录入");
			}else{
				roomPhoneTv.setText(CommonUtil.decorateStringWithUnderlineAndColor(roomManagerPhone + ""));//modify by albuscrow
			}
			clientContactTv.setText(entity.getUnitManager() + ""); 
			
			String unitManagerPhone = entity.getUnitManagerPhone();
			if (unitManagerPhone.length() == 0) {
				clientPhoneTv.setText("末录入");
			}else{
				clientPhoneTv.setText(CommonUtil.decorateStringWithUnderlineAndColor(unitManagerPhone + ""));//modify by albuscrow
			}
		}
		
		//add by albuscrow
		roomPhoneTv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:" + ((TextView)v).getText()));
				startActivity(intent);
			}
		});
		clientPhoneTv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:" + ((TextView)v).getText()));
				startActivity(intent);
			}
		});
		findViewById(R.id.phoneTv).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:400-800-8003"));
				startActivity(intent);
			}
		});;
		
		cxzgjlBtn=(Button)findViewById(R.id.related_file_Btn);
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
		case R.id.related_file_Btn:
			Intent intent1=new Intent(RoomDetailActivity.this,RelatedFileActivity.class);
			if(entity.getTmrId()>-1){
				intent1.putExtra("id", entity.getTmrId());
			}
			
			intent1.putExtra("type", RelatedFileActivity.TYPE_ROOM);
			
			if(entity.getTmrName()!=null&&!entity.getTmrName().equals("")){
				intent1.putExtra("name", entity.getTmrName());
			}
			startActivity(intent1);
			break;

		case R.id.glsbBtn:
			Intent intent2=new Intent(RoomDetailActivity.this,CabinetListActivity.class);
			if(entity.getTmrId()>-1){
				intent2.putExtra("roomId", entity.getTmrId());
			}
			intent2.putExtra("roomCode", entity.getTmrCode());
			
			intent2.putExtra("roomName", entity.getTmrName());
			startActivity(intent2);
			break;
		}
		
	}
	
}
