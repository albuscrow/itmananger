package com.itmanapp2;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.itmanapp2.entity.RoomEntity;
import com.itmanapp2.json.GetRoomSearchJson;
import com.itmanapp2.util.AppManager;
import com.itmanapp2.util.CommonUtil;


/**
 * @date 2014-7-11
 * @author wangpeng
 * @
 * class description 系统查询页面
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

	private ProgressDialog mDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_room_detail);
		AppManager.getAppManager().addActivity(this);
	}
	@Override
	protected void onResume() {
		getView();
		super.onResume();
	}

	protected void onNewIntent(Intent intent) {
		setIntent(intent);
		super.onNewIntent(intent);
	}
	
	static String baseurl = "http://121.40.188.122:8080/assetapi2/room/detail?"
			+ "key=z1zky&code=M0U3Q0IwQzE0RDMwNzUwQTI3MTZFNTc5NjIxMzJENzE=";
	

	/**
	 * description 解析数据
	 * 
	 * @param accountType
	 *            单位用户，账号类型为2
	 * @return void
	 */
	private void getResult(long id) {
		
		mDialog = new ProgressDialog(RoomDetailActivity.this);
		mDialog.setMessage(getString(R.string.login_msg));
		String url = baseurl + "&roomId=" + id;
		System.out.println(url);

		HashMap<String, String> params = new HashMap<String, String>();
		JsonObjectRequest req = new JsonObjectRequest(Method.GET, url, new JSONObject(), new Listener<JSONObject>() {


			public void onResponse(JSONObject response) {
				System.out.println("@@" + response.toString());
				try {
					entity = new Gson().fromJson(response.toString(), RoomEntity.class);
					
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
				} catch (Exception e) {
					handler.sendEmptyMessage(100086);
				}
			}
		}, new ErrorListener() {

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
				//Toast.makeText(SystemSearchActivity.this, "获取成功", 1000).show();
				Intent intent=new Intent(RoomDetailActivity.this, RoomListActivity.class);
				intent.putExtra("rooms", entity);
				startActivity(intent);
				break;
			case -1:
				Toast.makeText(RoomDetailActivity.this, "验证不通过，非法用户", 1000).show();
				break;
			case 0:
				Toast.makeText(RoomDetailActivity.this, "获取失败", 1000).show();
				break;
			case 101:
				Toast.makeText(RoomDetailActivity.this, "系统信息不存在", 1000).show();
				break;
			case 103:
				Toast.makeText(RoomDetailActivity.this, "参数错误", 1000).show();
				break;
			case 100086:
				Toast.makeText(RoomDetailActivity.this, "获取机房信息出错", 1000).show();
				break;
			}
			// 关闭ProgressDialog
			if (mDialog != null && mDialog.isShowing()) {
				mDialog.dismiss();
			}
		}
	};
	
	
	/**
	 * 控件显示
	 */
	private void getView() {
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

		Intent intent = this.getIntent(); 
		entity=(RoomEntity)intent.getSerializableExtra("room");
		String from = intent.getStringExtra("from");
		if (from != null && from.equals("device")) {
			long roomId = intent.getLongExtra("roomId", -1);
			getResult(roomId);
		}else{
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
		});
		
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
			intent2.putExtra("room", entity);
			intent2.putExtra("roomName", entity.getTmrName());
			startActivity(intent2);
			break;
		}
		
	}
	
}
