package com.itmanapp;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.itmanapp.entity.RoomEntity;
import com.itmanapp.json.GetRoomSearchJson;
import com.itmanapp.util.AppManager;
import com.itmanapp.widget.spiner.AbstractSpinerAdapter;
import com.itmanapp.widget.spiner.SpinerPopWindow;

/**
 * @date 2014-7-11
 * @author wangpeng
 * @class description 系统查询页面
 * 
 */
public class RoomSearchActivity extends Activity implements OnClickListener, AbstractSpinerAdapter.IOnItemSelectListener{
	
	/** 返回按钮 */
	private ImageView backBtn;
	
	/** 账号编辑框 */
	private EditText searchEdt;
	
	/**0编码查询102*/
	private String systemCode="";
	
	/**1系统名称*/
	private String systemName="";
	
	/**2单位名称*/
	private String unitName="";
	
	/** 登录按钮 */
	private ImageView searchBtn;
	
	private String key;
	
	private String base;

	private String code;
	
	/** 进度框 */
	private ProgressDialog mDialog = null;
	
	/**下拉列表选择模块*/
	private TextView mTView;
	private LinearLayout ll_lowwarn;
	private List<String> nameList = new ArrayList<String>();

	/**系统查询结果实体类*/
	private ArrayList<RoomEntity> entity = null;
	
	private int flag=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_room_search);
		AppManager.getAppManager().addActivity(this);
		getView();
	}
	
	/**
	 * 控件显示
	 */
	private void getView() {
		searchEdt=(EditText)findViewById(R.id.searchEdt);
		searchBtn=(ImageView)findViewById(R.id.searchBtn);
		
		backBtn=(ImageView)findViewById(R.id.backBtn);
		backBtn.setOnClickListener(this);
		
		mTView = (TextView) findViewById(R.id.tv_value);
		ll_lowwarn=(LinearLayout)findViewById(R.id.ll_lowwarn);
		ll_lowwarn.setOnClickListener(this);
		
		String[] names = getResources().getStringArray(R.array.name);
		for(int i = 0; i < names.length; i++){
			nameList.add(names[i]);
		}

		mSpinerPopWindow = new SpinerPopWindow(this);
		mSpinerPopWindow.refreshData(nameList, 0);
		mSpinerPopWindow.setItemListener(this);
		
		searchBtn.setOnClickListener(new OnClickListener( ) {
			
			@Override
			public void onClick(View v) {
				
				if(flag==0){
					systemCode=searchEdt.getText().toString().trim();
					url = baseurl + "&systemCode="+systemCode;
					systemName="";
					unitName="";
					if (isEmpty(systemCode)) {
						Toast.makeText(RoomSearchActivity.this, "不能为空",
								1000).show();
						return;
					}
				}else if(flag==1){
					systemName=searchEdt.getText().toString().trim();
					try {
						url = baseurl + "&roomName="+URLEncoder.encode(systemName, "UTF-8");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					systemCode="";
					unitName="";
					if (isEmpty(systemName)) {
						Toast.makeText(RoomSearchActivity.this, "不能为空",
								1000).show();
						return;
					}
					try {
						systemName=URLEncoder.encode(systemName, "utf-8");
					} catch (UnsupportedEncodingException e1) {
						e1.printStackTrace();
					}
				}else if(flag==2){
					unitName=searchEdt.getText().toString().trim();
					try {
						url = baseurl + "&unitName=" + URLEncoder.encode(unitName, "UTF-8");
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
							
					systemCode="";
					systemName="";
					if (isEmpty(unitName)) {
						Toast.makeText(RoomSearchActivity.this, "不能为空",
								1000).show();
						return;
					}
					try {
						unitName=URLEncoder.encode(unitName, "utf-8");
					} catch (UnsupportedEncodingException e1) {
						e1.printStackTrace();
					}
				}
				key = getRandomString(5);
				String kb = key + "ASSET-HJTECH";
				base = md5(kb);
				code = Base64.encodeToString(base.getBytes(), Base64.DEFAULT);

				mDialog.show();
				mDialog.setCanceledOnTouchOutside(false);
				
				getResult();
			}
		});
		
		mDialog = new ProgressDialog(RoomSearchActivity.this);
		mDialog.setMessage(getString(R.string.login_msg));
	}
	
	
	static String baseurl = "http://121.40.188.122:8080/assetapi2/room/roomList?"
			+ "key=z1zky&code=M0U3Q0IwQzE0RDMwNzUwQTI3MTZFNTc5NjIxMzJENzE=";
	
	static String url = null;

	/**
	 * description 解析数据
	 * 
	 * @param accountType
	 *            单位用户，账号类型为2
	 * @return void
	 */
	private void getResult() {

		System.out.println(url);

		HashMap<String, String> params = new HashMap<String, String>();

		JsonObjectRequest req = new JsonObjectRequest(Method.GET, url,
				new JSONObject(params), new Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {

						System.out.println("@@" + response.toString());
						int result = GetRoomSearchJson.getJson(response.toString());
						if (result == 1) {
							entity = (ArrayList<RoomEntity>) GetRoomSearchJson.entity;
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
				//Toast.makeText(SystemSearchActivity.this, "获取成功", 1000).show();
				Intent intent=new Intent(RoomSearchActivity.this, RoomListActivity.class);
				intent.putExtra("rooms", entity);
				startActivity(intent);
				break;
			case -1:
				Toast.makeText(RoomSearchActivity.this, "验证不通过，非法用户", 1000).show();
				break;
			case 0:
				Toast.makeText(RoomSearchActivity.this, "获取失败", 1000).show();
				break;
			case 101:
				Toast.makeText(RoomSearchActivity.this, "系统信息不存在", 1000).show();
				break;
			case 103:
				Toast.makeText(RoomSearchActivity.this, "参数错误", 1000).show();
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

	/**
	 * 点击事件
	 */
	@Override
	public void onItemClick(int pos) {
		setHero(pos);
	}

	/**
	 * 点击事件
	 */
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.ll_lowwarn:
			showSpinWindow();
			break;
			
		case R.id.backBtn:
			finish();
			break;
		}
		
	}
	
	/**
	 * 选择事件
	 */
	private void setHero(int pos){
		System.out.println("hahahahahahaha---"+pos);
		if (pos >= 0 && pos <= nameList.size()){
			String value = nameList.get(pos);
			
			flag=pos;
		
			mTView.setText(value);
		}
	}

	
	private SpinerPopWindow mSpinerPopWindow;
	private void showSpinWindow(){
		Log.e("", "showSpinWindow");
		mSpinerPopWindow.setWidth(ll_lowwarn.getWidth());
		mSpinerPopWindow.showAsDropDown(ll_lowwarn);
	}

}
