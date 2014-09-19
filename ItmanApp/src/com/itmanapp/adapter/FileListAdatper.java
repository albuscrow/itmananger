package com.itmanapp.adapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream.GetField;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.itmanapp.R;
import com.itmanapp.StartActivity;
import com.itmanapp.entity.FileEntity;

/**
 * @date 2014-7-17
 * @author wangpeng
 * @class description 系统整改历史列表适配器
 * 
 */
public class FileListAdatper extends BaseAdapter {

	private Activity context;

	private List<FileEntity> fileList = new ArrayList<FileEntity>();

	public FileListAdatper(Activity context,
			List<FileEntity> modifyInfoList) {
		super();
		this.context = context;
		this.fileList = modifyInfoList;
	}

	@Override
	public int getCount() {
		return fileList.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		View v = convertView;
		LayoutInflater inflater;
		if (convertView == null) {
			holder = new ViewHolder();
			// 设置映射对象
			inflater = LayoutInflater.from(context);
			v = inflater.inflate(R.layout.list_item_file, null);
			holder.addDate = (TextView) v.findViewById(R.id.addDateTv);
			holder.name = (TextView) v.findViewById(R.id.name);
			holder.type = (TextView) v.findViewById(R.id.type);
			v.setTag(holder);
		}else {
			holder = (ViewHolder) v.getTag();
		}
		holder.addDate.setText(fileList.get(position)
				.getAddDate());
		holder.type.setText(fileList.get(position).getFileType());
		holder.name.setText(fileList.get(position).getTfaName());
		v.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				final String tfaFilePath = fileList.get(position).getTfaFilePath();
				final String url_str = "http://it.hjtechcn.cn/upload/"+tfaFilePath;
				final int type = fileList.get(position).getTfaFileType();
//				if (type == 1 || type == 4) {
				Toast.makeText(context, "正在加载文件...", Toast.LENGTH_SHORT).show();  
//				}else{
//					//其它下载
//					Toast.makeText(context, "正在下载文件...", Toast.LENGTH_SHORT).show();  
//				}
				
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						final File file = new File(context.getExternalFilesDir(null), tfaFilePath); 
						try {
							URL url = new URL(url_str);
							System.out.println(url_str);
							try {
								HttpURLConnection conn = (HttpURLConnection) url  
										.openConnection();  
								InputStream is = conn.getInputStream();  
								context.getExternalFilesDir(null);
								FileOutputStream fos = new FileOutputStream(file);  
								byte[] buf = new byte[256];  
								conn.connect();  
								double count = 0;
								if (conn.getResponseCode() >= 400) {  
									Toast.makeText(context, "连接超时", Toast.LENGTH_SHORT).show();  
									Log.i("time","time exceed");
								} else {
									while (count <= 100) {  
										if (is != null) {  
											int numRead = is.read(buf);  
											if (numRead <= 0) {  
												break;  
											} else {  
												fos.write(buf, 0, numRead);  
											}  
										} else {  
											break;  
										}  
									}  
								}  
								conn.disconnect();  
								fos.close();  
								is.close();
								System.out
										.println("FileListAdatper.getView(...).new OnClickListener() {...}.onClick(...).new Runnable() {...}.run()");
								context.runOnUiThread(new Runnable() {
									
									@Override
									public void run() {
										Intent intent = new Intent();
										intent.setAction(Intent.ACTION_VIEW);
										Uri uri = Uri.fromFile(file);
										String viewType = null;
										switch (type) {
										case 1:
											viewType = "image/*";
											break;
										case 2:
											viewType = "application/msword";
											break;
										case 3:
											viewType = "application/vnd.ms-excel";
											break;
										case 4:
											viewType = "text/plain";
											break;
										case 5:
											viewType = "application/vnd.ms-powerpoint";
											break;
										case 6:
											viewType = "application/pdf";
											break;

										default:
											break;
										}
										intent.setDataAndType(uri, viewType);
										try {
											context.startActivity(intent);
										} catch (Exception e) {
											context.runOnUiThread(new Runnable() {

												@Override
												public void run() {
													Toast.makeText(context, "打开文件失败!\n已保存至"+file.getAbsolutePath(), Toast.LENGTH_SHORT).show();  
												}
											});		
										}
									}
								});
							} catch (IOException e) {  
								context.runOnUiThread(new Runnable() {
									
									@Override
									public void run() {
										Toast.makeText(context, "加载失败!", Toast.LENGTH_SHORT).show();  
									}
								});
								e.printStackTrace();
							}
						} catch (MalformedURLException e) {
							e.printStackTrace();  
						}  
					}
				}).start();
			}
		});
		return v;
	}

	class ViewHolder {
		/** 标题 */
		private TextView type;
		/** 日期 */
		private TextView addDate;
		/** 文件查看 */
		private TextView name;
	}
	
	//android获取一个用于打开文本文件的intent   
	public static Intent getTextFileIntent( String param, boolean paramBoolean)   
	{   
	    Intent intent = new Intent("android.intent.action.VIEW");   
	    intent.addCategory("android.intent.category.DEFAULT");   
	    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);   
	    if (paramBoolean)   
	    {   
	        Uri uri1 = Uri.parse(param );   
	        intent.setDataAndType(uri1, "text/plain");   
	    }   
	    else   
	    {   
	        Uri uri2 = Uri.fromFile(new File(param));   
	        intent.setDataAndType(uri2, "text/plain");   
	    }   
	    return intent;   
	}   

}
