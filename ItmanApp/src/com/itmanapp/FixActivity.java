package com.itmanapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
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
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.itmanapp.entity.WorkOrderEntity;
import com.itmanapp.json.GetWorkOrderDetailJson;
import com.itmanapp.util.AppManager;
import com.itmanapp.util.NetUtils;

/**
 * @date 2014-7-17
 * @author wangpeng
 * @class description 待维修工单详细页面
 * 
 */
public class FixActivity extends Activity implements OnClickListener{

	/** 返回按钮 */
	private ImageView backBtn;
	
	/**订单编号*/
	private TextView orderNumberTv;
	
	/**分配时间*/
	private TextView allocationTimeTv;
	
	/**维修项目*/
	private TextView projectTv;
	
	/**维修状态*/
	private TextView statusTv;
	
	/**维修描述编辑框*/
	private EditText despEdt;
	
	/**维修完成按钮*/
	private Button repairCompleteBtn;
	
	private String key;

	private String base;

	private String code;

	/** 进度框 */
	private ProgressDialog mDialog = null;
	
	/**维修明细Id*/
	private Long detailId;
	
	/**工单实体类*/
	private WorkOrderEntity entity=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fix_detail);
		AppManager.getAppManager().addActivity(this);
		getView();
	}
	
	/**
	 * 控件显示
	 */
	
	private void getView() {
		detailId=getIntent().getLongExtra("id", 0);
		System.out.println("detailId==="+detailId);
		mDialog = new ProgressDialog(FixActivity.this);
		mDialog.setMessage(getString(R.string.login_msg));
		
		orderNumberTv=(TextView)findViewById(R.id.orderNumberTv);
		allocationTimeTv=(TextView)findViewById(R.id.allocationTimeTv);
		projectTv=(TextView)findViewById(R.id.projectTv);
		statusTv=(TextView)findViewById(R.id.statusTv);
		despEdt=(EditText)findViewById(R.id.despEdt);
		
		backBtn=(ImageView)findViewById(R.id.backBtn);
		backBtn.setOnClickListener(this);
		
		repairCompleteBtn=(Button)findViewById(R.id.repairCompleteBtn);
		repairCompleteBtn.setOnClickListener(this);
		findViewById(R.id.giveupBtn).setOnClickListener(this);
		findViewById(R.id.take_photo).setOnClickListener(this);

		key = getRandomString(5);
		System.out.println("key-->" + key);
		String kb = key + "ASSET-HJTECH";
		base = md5(kb);
		System.out.println("base-->" + base);
		code = Base64.encodeToString(base.getBytes(), Base64.DEFAULT);
		System.out.println("code-->" + code);

		mDialog.show();
		mDialog.setCanceledOnTouchOutside(false);

		getResult();

	}

	/**
	 * description 解析数据 
	 * 
	 * @return void
	 */
	private void getResult() {

		// tencent 123456
		String url = "http://211.155.229.136:8080/assetapi2/order/detail?"
				+ "key=z1zky&code=M0U3Q0IwQzE0RDMwNzUwQTI3MTZFNTc5NjIxMzJENzE="
				+ "&detailId=" + detailId;
		System.out.println(url);

		HashMap<String, String> params = new HashMap<String, String>();
		
				JsonObjectRequest req = new JsonObjectRequest(Method.GET, url,
				new JSONObject(params), new Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {

						System.out.println("@@" + response.toString());
					
						int result = GetWorkOrderDetailJson.getJson(response.toString());
						if (result == 1) {
							entity=GetWorkOrderDetailJson.entity;
							handler.sendEmptyMessage(1);
						} else if (result == -1) {
							handler.sendEmptyMessage(-1);
						} else if (result == 0) {
							handler.sendEmptyMessage(0);
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
				//Toast.makeText(PendingWorkOrderDetailActivity.this, "获取成功", 1000).show();
				if(entity!=null){
					orderNumberTv.setText(entity.getOrderNo()+"");
					allocationTimeTv.setText(entity.getAllocateDate()+"");
					projectTv.setText(entity.getItemNames()+"");
					int status=entity.getOrderStatus();
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
					}
				}else{
					Toast.makeText(FixActivity.this, "获取失败", 1000).show();
				}
				break;
			case -1:
				Toast.makeText(FixActivity.this, "验证不通过，非法用户", 1000)
						.show();
				break;
			case 0:
				Toast.makeText(FixActivity.this, "获取失败", 1000).show();
				break;
			case 103:
				Toast.makeText(FixActivity.this, "参数错误", 1000).show();
				break;
			case 111:
				Toast.makeText(FixActivity.this, "提交成功", 1000).show();
				Intent data=new Intent();  
	            //请求代码可以自己设置，这里设置成20  
	            setResult(20, data);  
				finish();
				break;
			case 10088:
				Toast.makeText(FixActivity.this, "上传文件成功", 1000).show();
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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.backBtn:
			finish();
			break;
			
		case R.id.repairCompleteBtn:
			String desp=despEdt.getText().toString().trim();
			if(isEmpty(desp)){
				Toast.makeText(FixActivity.this, "维修描述未填写", 1000).show();
				return;
			}
			submitData(desp, 1);
			break;
			
		case R.id.giveupBtn:
			String desp2=despEdt.getText().toString().trim();
			if(isEmpty(desp2)){
				Toast.makeText(FixActivity.this, "维修描述未填写", 1000).show();
				return;
			}
			submitData(desp2, 2);
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
					}).setTitle("是否上传?").show();		
			break;

		default:
			break;
		}
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// 结果码不等于取消时候
		if (resultCode != RESULT_CANCELED) {
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

								String urlForUploadFile = "http://211.155.229.136:8080/assetapi2/file/upload?"
										+ "key=z1zky&code=M0U3Q0IwQzE0RDMwNzUwQTI3MTZFNTc5NjIxMzJENzE="
										+ "&referId="+detailId+"&userId="+getSharedPreferences("user",Context.MODE_PRIVATE).getInt("Id", 0)
										+ "&type=7";
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

								String urlForUploadFile = "http://211.155.229.136:8080/assetapi2/file/upload?"
										+ "key=z1zky&code=M0U3Q0IwQzE0RDMwNzUwQTI3MTZFNTc5NjIxMzJENzE="
										+ "&referId="+detailId+"&userId="+getSharedPreferences("user",Context.MODE_PRIVATE).getInt("Id", 0)
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
				}).show();
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
	 * description 解析数据
	 * 
	 * @return void
	 */
	private void submitData(String desp, int status) {

		try {
			desp=URLEncoder.encode(desp, "utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String url;
		if (status == 1) {
			url = "http://211.155.229.136:8080/assetapi2/order/complete?"
					+ "key=z1zky&code=M0U3Q0IwQzE0RDMwNzUwQTI3MTZFNTc5NjIxMzJENzE="
					+ "&detailId="+detailId+"&desp="+desp;
		}else{
			url = "http://211.155.229.136:8080/assetapi2/order/lose?"
					+ "key=z1zky&code=M0U3Q0IwQzE0RDMwNzUwQTI3MTZFNTc5NjIxMzJENzE="
					+ "&detailId="+detailId+"&desp="+desp;
		}
		System.out.println(url);
		

//		HashMap<String, String> params = new HashMap<String, String>();
//
//		JsonObjectRequest req = new JsonObjectRequest(Method.POST, url,
//				new JSONObject(params), new Listener<JSONObject>() {
//
//					@Override
//					public void onResponse(JSONObject response) {
//
//						System.out.println("@@" + response.toString());
//						
//						try {
//							int result = response.getInt("result");
//							if (result == 1) {
//								handler.sendEmptyMessage(111);
//							} else if (result == -1) {
//								handler.sendEmptyMessage(-1);
//							} else if (result == 0) {
//								handler.sendEmptyMessage(0);
//							} else if (result == 103) {
//								handler.sendEmptyMessage(103);
//							}
//						} catch (JSONException e) {
//							e.printStackTrace();
//						}
//						
//					}
//				}, new Response.ErrorListener() {
//
//					@Override
//					public void onErrorResponse(VolleyError error) {
//
//						System.out.println("##" + error.toString());
//						handler.sendEmptyMessage(0);
//
//					}
//				});
//		DemoApplication.getInstance().getRequestQueue().add(req);
	}

	
}
