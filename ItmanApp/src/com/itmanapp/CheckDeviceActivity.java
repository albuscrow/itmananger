package com.itmanapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.itmanapp.adapter.InspectionSpinnerAdapter;
import com.itmanapp.adapter.WrittenInspectionDetailAdatper;
import com.itmanapp.entity.CheckItemEntity;
import com.itmanapp.json.GetCheckDetailJson;
import com.itmanapp.json.GetItemJson;
import com.itmanapp.util.AppManager;
import com.itmanapp.util.NetUtils;

/**
 * @date 2014-7-29
 * @author wangpeng
 * @class description 待巡检详细提交页面
 * 
 */
public class CheckDeviceActivity extends BaseActivity implements OnClickListener{
	
	/** 返回按钮 */
	private ImageView backBtn;
	
	/**巡检目标*/
	private TextView inspectionTargetTv;
	
	/**设备*/
	private TextView deviceNameTv;
	
	/**设备编号*/
	private TextView deviceNumberTv;
	
	private Button submitBtn;
        	
    private String key;
	
	private String base;

	private String code;
	
	/** 进度框 */
	private ProgressDialog mDialog = null;
	
	/**列表控件*/
	private LinearLayout inspectionProjectLv;
	
	/**待巡检提交-巡检项目-适配器*/
//	private WrittenInspectionDetailAdatper adapter;
	
	/**数据集合*/
	private List<CheckItemEntity> list = new ArrayList<CheckItemEntity>();
	
	/**id提交集合*/
	private StringBuffer sbId=new StringBuffer();
	
	/**审批参数*/
	private StringBuffer sbResult=new StringBuffer();
	
	private Intent intent;
	
	/**巡检记录id*/
	private long id;

	private String planName;

	private String name;

	private String deviceCode;

	private TextView despTv;

	private String roomName;

	private String cabName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_check_device);
		AppManager.getAppManager().addActivity(this);
		intent=getIntent();
		id=intent.getLongExtra("id", -1);
		planName = intent.getStringExtra("planName");
		name = intent.getStringExtra("name");
		deviceCode = intent.getStringExtra("code");
		
		roomName = intent.getStringExtra("roomName");
		cabName = intent.getStringExtra("cabName");
		
		getView();
		setPhone();
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// 结果码不等于取消时候
		if (resultCode != RESULT_CANCELED) {
			if (resultCode == 20) {
				Intent intent=new Intent();  
				//请求代码可以自己设置，这里设置成20  
				setResult(20, intent);  
				finish();
				return;
			}
			
			switch (requestCode) {
			case IMAGE_REQUEST_CODE:
				file = new File(data.getData().getPath());
				Bitmap bitmap = decodeUriAsBitmap(data.getData());
				final File tempFile = new File(getCacheDir(), "fileNeedToUpload" + System.currentTimeMillis() + ".png");
				try {
					bitmap.compress(CompressFormat.PNG, 80, new FileOutputStream(tempFile));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ImageView view = new ImageView(this);
				view.setImageBitmap(bitmap);
				new AlertDialog.Builder(this).setView(view).setNegativeButton("取消", null).setPositiveButton("确定", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {

						new Thread(new Runnable() {

							@Override
							public void run() {

								String urlForUploadFile = "http://121.40.188.122:8080/assetapi2/file/upload?"
										+ "key=z1zky&code=M0U3Q0IwQzE0RDMwNzUwQTI3MTZFNTc5NjIxMzJENzE="
										+ "&referId="+id+"&userId="+getSharedPreferences("user",Context.MODE_PRIVATE).getInt("Id", 0)
										+ "&type=6";
								try {
									if (NetUtils.post(tempFile,urlForUploadFile) != 200) {
										return;
									}
									handler.sendEmptyMessage(10088);
								} catch (Exception e) {
									e.printStackTrace();
								}

							}
						}).start();

					}
				}).setTitle("是否上传?").show();
				break;
			case CAMERA_REQUEST_CODE:
				File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);  
				File tempFile2 = new File(path,TEMP_PHOTO_FILE);
				file = tempFile2;
				Bitmap bitmap2 = decodeUriAsBitmap(Uri.fromFile(tempFile2));
				
				ImageView view2 = new ImageView(this);
				view2.setImageBitmap(bitmap2);
				new AlertDialog.Builder(this).setView(view2).setNegativeButton("取消", null).setPositiveButton("确定", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {

						new Thread(new Runnable() {

							@Override
							public void run() {

								String urlForUploadFile = "http://121.40.188.122:8080/assetapi2/file/upload?"
										+ "key=z1zky&code=M0U3Q0IwQzE0RDMwNzUwQTI3MTZFNTc5NjIxMzJENzE="
										+ "&referId="+ id +"&userId="+getSharedPreferences("user",Context.MODE_PRIVATE).getInt("Id", 0)
										+ "&type=7";
								try {
									if (NetUtils.post(file,urlForUploadFile) != 200) {
										return;
									}
									handler.sendEmptyMessage(10088);
								} catch (Exception e) {
									e.printStackTrace();
								}

							}
						}).start();

					}
				}).setTitle("是否上传?").show();
				break;
			
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
		Uri imageUri = getTempUri(); // The Uri to store the big bitmap

		File file = null;
	private Bitmap decodeUriAsBitmap(Uri uri) {
		Bitmap bitmap = null;
		try {
			Options options = new Options();
			options.inJustDecodeBounds = true;
			BitmapFactory.decodeStream(getContentResolver()
					.openInputStream(uri), null, options);
			
			int width = options.outWidth;
			int height = options.outHeight;
			int samplerSize = width * height / (1000 * 500);
			
			options.inJustDecodeBounds = false;
			options.inSampleSize = samplerSize;
			
			bitmap = BitmapFactory.decodeStream(getContentResolver()
					.openInputStream(uri), null, options);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return bitmap;
	}

	
	private static final int IMAGE_REQUEST_CODE = 20;
	private static final int CAMERA_REQUEST_CODE = 30;
	
	private Uri getTempUri() {
		return Uri.fromFile(getTempFile());
	}
	
	private static final String TEMP_PHOTO_FILE = "temporary_holder.jpg";
	
	private File getTempFile() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			File file = new File(Environment.getExternalStorageDirectory(),
					TEMP_PHOTO_FILE);
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return file;
		} else {
			return null;
		}
	}
	
	/**
	 * 控件显示
	 */
	private void getView() {
		mDialog = new ProgressDialog(CheckDeviceActivity.this);
		mDialog.setMessage(getString(R.string.login_msg));
		mDialog.show();
		mDialog.setCanceledOnTouchOutside(false);
		
		backBtn=(ImageView)findViewById(R.id.backBtn);
		backBtn.setOnClickListener(this);
		
		inspectionTargetTv=(TextView)findViewById(R.id.inspectionTargetTv);
		deviceNameTv=(TextView)findViewById(R.id.deviceNameTv);
		deviceNumberTv=(TextView)findViewById(R.id.deviceNumberTv);
		despTv=(TextView)findViewById(R.id.desp);
		
		submitBtn=(Button)findViewById(R.id.submitBtn);
		submitBtn.setOnClickListener(this);
		
		findViewById(R.id.bx_Btn).setOnClickListener(this);
		
		inspectionTargetTv.setText(planName);
		deviceNameTv.setText(name);
		deviceNumberTv.setText(deviceCode);
		findViewById(R.id.take_photo).setOnClickListener(this);
		
		key = getRandomString(5);
		String kb = key + "ASSET-HJTECH";
		base = md5(kb);
		code = Base64.encodeToString(base.getBytes(), Base64.DEFAULT);

		inspectionProjectLv=(LinearLayout)findViewById(R.id.inspectionProjectLv);
		
		getResult();
		
	}
	
	/**
	 * description 解析数据
	 * 
	 * @return void
	 */
	private void getResult() {

		// tencent 123456
		String url = "http://121.40.188.122:8080/assetapi2/xj/items?"
				+ "key=z1zky&code=M0U3Q0IwQzE0RDMwNzUwQTI3MTZFNTc5NjIxMzJENzE="
				+ "&txrId="+id;
		System.out.println(url);

		HashMap<String, String> params = new HashMap<String, String>();

		JsonObjectRequest req = new JsonObjectRequest(Method.GET, url,
				new JSONObject(params), new Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						System.out.println("@@" + response.toString());
						
						list=GetItemJson.getJson(response.toString());
						int result = GetItemJson.result;
						if (result == 1) {
							handler.sendEmptyMessage(1);
						} else if (result == -1) {
							handler.sendEmptyMessage(-1);
						} else if (result == 0) {
							handler.sendEmptyMessage(0);
						} else if (result == 103) {
							handler.sendEmptyMessage(103);
						} else if(result == 104){
							handler.sendEmptyMessage(104);
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
	
	public View getView(final CheckItemEntity item, ViewGroup parent) {
			// 设置映射对象
			LayoutInflater inflater = LayoutInflater.from(this);
			View v = inflater.inflate(R.layout.list_item_written_inspection_detail, null);
			Spinner resultSpn = (Spinner) v.findViewById(R.id.resultSpn);
			TextView itemNameTv = (TextView) v.findViewById(R.id.itemNameTv);
			
		itemNameTv.setText(item.getTxpName()+"");

		final String[] m={"正常","异常"};
		
		InspectionSpinnerAdapter adapter=new InspectionSpinnerAdapter(this, m);
		
//		ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,m);
//		//设置下拉列表的风格
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         
        //将adapter 添加到spinner中
        resultSpn.setAdapter(adapter);
        
        resultSpn.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
//				System.out.println(position+"--->>"+m[arg2]+"--"+arg2); 
				if(m[arg2].toString().equals("正常")){
//					System.out.println(position+"--->>"+m[arg2]+"--"+arg2);
					item.setTxpStatus(1);
				}else if(m[arg2].toString().equals("异常")){
//					System.out.println(position+"--->>"+m[arg2]+"--"+arg2);
					item.setTxpStatus(2);
				}
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
		});
		return v;
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
				//Toast.makeText(WrittenInspectionDetailActivity.this, "获取成功", 1000).show();
//				adapter=new WrittenInspectionDetailAdatper(CheckDeviceActivity.this, list);ListView
				for (final CheckItemEntity item : list) {
					View view = getView(item, inspectionProjectLv);
					view.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							String desc = item.getDesc();
							if (desc.indexOf("\n") == -1) {
								desc = "        " + desc.trim();
							}
							AlertDialog ad = new AlertDialog.Builder(CheckDeviceActivity.this).setTitle("巡检项目详情")
									.setMessage(desc).setNeutralButton("确定", null).create();
							ad.show();
						}
					});
					inspectionProjectLv.addView(view);
				}
				
				for(int i=0;i<list.size();i++){
					if(i==(list.size()-1)){
						sbId.append(list.get(i).getDetailId());
					}else if(list.size()==1){
						sbId.append(list.get(i).getDetailId());
					}else{
						sbId.append(list.get(i).getDetailId()+",");
					}
				}
				
				
				break;
			case -1:
				Toast.makeText(CheckDeviceActivity.this, "验证不通过，非法用户", 1000).show();
				break;
			case 0:
				Toast.makeText(CheckDeviceActivity.this, "获取失败", 1000).show();
				break;
			case 103:
				Toast.makeText(CheckDeviceActivity.this, "参数错误", 1000).show();
				break;
				
			case 104:
				Toast.makeText(CheckDeviceActivity.this, "用户不存在", 1000).show();
				break;
				
			case 2:
				Toast.makeText(CheckDeviceActivity.this, "提交成功", 1000).show();
				Intent data=new Intent();  
	            //请求代码可以自己设置，这里设置成20  
	            setResult(20, data);  
				finish();
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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.submitBtn:
			
			for(int i=0;i<list.size();i++){
				if(i==(list.size()-1)){
					sbResult.append(list.get(i).getTxpStatus());
				}else if(list.size()==1){
					sbResult.append(list.get(i).getTxpStatus());
				}else{
					sbResult.append(list.get(i).getTxpStatus()+",");
				}
			}
			System.out.println("id-->"+sbId.toString()+"--Result-->"+sbResult.toString());
			submitResult();
			break;
			
		case R.id.bx_Btn:
			CharSequence desp = despTv.getText();
			if (desp == null || desp.length() == 0) {
				Toast.makeText(this, "请输入检查描述！", Toast.LENGTH_SHORT).show();
				return;
			}
			
			for(int i=0;i<list.size();i++){
				if(i==(list.size()-1)){
					sbResult.append(list.get(i).getTxpStatus());
				}else if(list.size()==1){
					sbResult.append(list.get(i).getTxpStatus());
				}else{
					sbResult.append(list.get(i).getTxpStatus()+",");
				}
			}
			System.out.println("id-->"+sbId.toString()+"--Result-->"+sbResult.toString());
//			submitResult();
			
			Intent intent3=new Intent(CheckDeviceActivity.this,FillRepairActivity.class);
			String url = null;
			try {
				url = "http://121.40.188.122:8080/assetapi2/xj/commit_item_result?"
					+ "key=z1zky&code=M0U3Q0IwQzE0RDMwNzUwQTI3MTZFNTc5NjIxMzJENzE="
					+ "&recordIds="+id+"&detailIds="+ sbId.toString()
					+ "&resultIds=" + sbResult.toString()
					+ "&recordDesp=" + URLEncoder.encode(desp.toString(), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			intent3.putExtra("url", url);
			intent3.putExtra("id",id);
			intent3.putExtra("cabName",cabName);
			intent3.putExtra("roomName",roomName);
			intent3.putExtra("deviceName", name);
			startActivityForResult(intent3, 2);
			break;
			
		case R.id.backBtn:
			finish();
			break;
			
		case R.id.take_photo:
			new AlertDialog.Builder(this).setTitle("从哪里获取图片").setItems(
					new CharSequence[] { "相册", "相机" }, new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							if (which == 0) {
								Intent photoPickerIntent = new Intent( Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
								photoPickerIntent.setType("image/*");
								photoPickerIntent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
								photoPickerIntent.putExtra(MediaStore.EXTRA_OUTPUT, getTempUri());
								startActivityForResult(photoPickerIntent, IMAGE_REQUEST_CODE);
							}else{
								Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
								intentFromCapture.putExtra("outputFormat",Bitmap.CompressFormat.JPEG.toString());
								intentFromCapture.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
								File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);  
								File tempFile = new File(path,TEMP_PHOTO_FILE);
								// 判断存储卡是否可以用，可用进行存储
								intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
								startActivityForResult(intentFromCapture, CAMERA_REQUEST_CODE);
							}
							dialog.dismiss();
						}
					}).show();		
			break;
		}
	}

	/**
	 * description 提交数据
	 * 
	 * @param accountType
	 *            单位用户，账号类型为2
	 * @return void
	 */
	private void submitResult() {

		// tencent 123456
		String url = null;
		try {
			url = "http://121.40.188.122:8080/assetapi2/xj/commit_item_result?"
					+ "key=z1zky&code=M0U3Q0IwQzE0RDMwNzUwQTI3MTZFNTc5NjIxMzJENzE="
					+ "&recordIds="+id+"&detailIds="+ sbId.toString()
					+ "&resultIds=" + sbResult.toString()
					+ "&recordDesp=" + URLEncoder.encode(despTv.getText().toString(), "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(url);

		HashMap<String, String> params = new HashMap<String, String>();

		JsonObjectRequest req = new JsonObjectRequest(Method.POST, url,
				new JSONObject(params), new Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						System.out.println("@@" + response.toString());
						
						try {
							int result = response.getInt("result");
							if (result == 1) {
								handler.sendEmptyMessage(2);
							} else if (result == -1) {
								handler.sendEmptyMessage(-1);
							} else if (result == 0) {
								handler.sendEmptyMessage(0);
							} else if (result == 103) {
								handler.sendEmptyMessage(103);
							}
						} catch (JSONException e) {
							e.printStackTrace();
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


}

