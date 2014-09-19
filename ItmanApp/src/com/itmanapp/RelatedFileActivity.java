package com.itmanapp;

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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.itmanapp.adapter.FileListAdatper;
import com.itmanapp.entity.FileEntity;
import com.itmanapp.json.GetModifyInfoJson;
import com.itmanapp.util.AppManager;

/**
 * @date 2014-7-11
 * @author wangpeng
 * @class description 系统整改历史页面
 * 
 */
public class RelatedFileActivity extends Activity implements OnClickListener{
	
	public static final int TYPE_ROOM = 1;
	public static final int TYPE_CABINET = 2;
	public static final int TYPE_DEVICE = 3;

	/** 返回按钮 */
	private ImageView backBtn;
	
	private String key;
	
	private String base;

	private String code;
	
	/** 进度框 */
	private ProgressDialog mDialog = null;
	
	/**列表控件 */
	private ListView modifyInfoLv;
	
	/**系统整改历史适配器 */
	private FileListAdatper adapter;
	
	/** 数据集合*/
	private List<FileEntity> modifyInfoList = new ArrayList<FileEntity>();
	
	/**编号显示 */
	private TextView relatedType;
	
	/** 系统名称显示*/
	private TextView relatedNameTv;
	
	/**系统编码 */
	private Intent intent;
	
	private long id;
	
	private String name;
	private int type;
	private TextView relatedBelong;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_related_file);
		AppManager.getAppManager().addActivity(this);
		intent=getIntent();
		id=intent.getLongExtra("id", -1);
		name=intent.getStringExtra("name");
		type = intent.getIntExtra("type", -1);
		getView();
	}
	
	/**
	 * 控件显示
	 */
	private void getView() {
		mDialog = new ProgressDialog(RelatedFileActivity.this);
		mDialog.setMessage(getString(R.string.login_msg));
		
		backBtn=(ImageView)findViewById(R.id.backBtn);
		backBtn.setOnClickListener(this);
		
		relatedType=(TextView)findViewById(R.id.related_type);
		relatedNameTv=(TextView)findViewById(R.id.related_name);
		relatedBelong = (TextView)findViewById(R.id.related_file_belong);
		
		relatedNameTv.setText(name+"");
		
		modifyInfoLv=(ListView)findViewById(R.id.modifyInfoLv);
		
		key = getRandomString(5);
		String kb = key + "ASSET-HJTECH";
		base = md5(kb);
		code = Base64.encodeToString(base.getBytes(), Base64.DEFAULT);
		
		url = "http://211.155.229.136:8080/assetapi2/file/list?"
				+ "key=z1zky&code=M0U3Q0IwQzE0RDMwNzUwQTI3MTZFNTc5NjIxMzJENzE="
				+ "&referId="+id
				+ "&type=";	
		switch (type) {
		case TYPE_ROOM:
			relatedBelong.setText("所属机房：");
			relatedType.setText("机房关联");
			url += 1;
			break;
			
		case TYPE_CABINET:
			relatedBelong.setText("所属机柜：");
			relatedType.setText("机柜关联");
			url += 2;
			break;
			
		case TYPE_DEVICE:
			relatedBelong.setText("所属设备：");
			relatedType.setText("设备关联");
			url += 3;
			break;

		default:
			break;
		}

		mDialog.show();
		mDialog.setCanceledOnTouchOutside(false);
		
		getResult();
		
	}
	
	
	String url = null;	
	/**
	 * description 解析数据
	 * 
	 * @param accountType
	 *            单位用户，账号类型为2
	 * @return void
	 */
	private void getResult() {
		System.out.println("huh");
		System.out.println(url);

		HashMap<String, String> params = new HashMap<String, String>();

		JsonObjectRequest req = new JsonObjectRequest(Method.GET, url,
				new JSONObject(params), new Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {

						System.out.println("@@" + response.toString());
						modifyInfoList=GetModifyInfoJson.getJson(response.toString());
						int result = GetModifyInfoJson.result;
						if (result == 1) {
							System.out.println("List:"+modifyInfoList);
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
				//Toast.makeText(SystemModifyInfoActivity.this, "获取成功", 1000).show();
				adapter=new FileListAdatper(RelatedFileActivity.this, modifyInfoList);
				modifyInfoLv.setAdapter(adapter);
				break;
			case -1:
				Toast.makeText(RelatedFileActivity.this, "验证不通过，非法用户", 1000).show();
				break;
			case 0:
				Toast.makeText(RelatedFileActivity.this, "获取失败", 1000).show();
				break;
			case 103:
				Toast.makeText(RelatedFileActivity.this, "参数错误", 1000).show();
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

	/***
	 * 点击事件 
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.backBtn:
			finish();
			break;
		}
		
	}

}
