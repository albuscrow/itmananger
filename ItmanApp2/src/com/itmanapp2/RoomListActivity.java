package com.itmanapp2;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.itmanapp2.adapter.FileListAdatper;
import com.itmanapp2.adapter.RelatedDeviceAdatper;
import com.itmanapp2.adapter.RoomListAdatper;
import com.itmanapp2.entity.RelatedDeviceEntity;
import com.itmanapp2.entity.RoomEntity;
import com.itmanapp2.json.GetModifyInfoJson;
import com.itmanapp2.json.GetRelatedDeviceJson;
import com.itmanapp2.util.AppManager;
/**
 * 
 * @author albuscrow
 * 机房查询
 */
public class RoomListActivity extends BaseActivity implements OnClickListener, OnItemClickListener{/** 返回按钮 */
	private ImageView backBtn;

	/** 进度框 */
	private ProgressDialog mDialog = null;
	
	/**列表控件 */
	private ListView relatedDeviceLv;
	
	/**相关设备列表适配器*/
	private RoomListAdatper adapter;
	
	private Intent intent;
	
	private List<RoomEntity> rooms;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_room_list);
		AppManager.getAppManager().addActivity(this);
		intent=getIntent();
		rooms=(ArrayList<RoomEntity>) intent.getSerializableExtra("rooms");
		getView();
		setPhone();
	}
	
	/**
	 * 控件显示
	 */
	private void getView() {
		mDialog = new ProgressDialog(RoomListActivity.this);
		mDialog.setMessage(getString(R.string.login_msg));
		
		backBtn=(ImageView)findViewById(R.id.backBtn);
		backBtn.setOnClickListener(this);
		
		relatedDeviceLv=(ListView)findViewById(R.id.relatedDeviceLv);
		relatedDeviceLv.setOnItemClickListener(this);
		
		adapter=new RoomListAdatper(RoomListActivity.this, rooms);
		relatedDeviceLv.setAdapter(adapter);
		
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.backBtn:
			finish();
			break;
		}
		
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Intent resultIntent = new Intent(RoomListActivity.this,RoomDetailActivity.class);
		resultIntent.putExtra("room", rooms.get(arg2));
		startActivity(resultIntent);
		
	}
	

}
