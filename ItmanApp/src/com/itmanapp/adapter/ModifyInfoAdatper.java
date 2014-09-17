package com.itmanapp.adapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.itmanapp.R;
import com.itmanapp.entity.ModifyInfoEntity;

/**
 * @date 2014-7-17
 * @author wangpeng
 * @class description 系统整改历史列表适配器
 * 
 */
public class ModifyInfoAdatper extends BaseAdapter {

	private Context context;

	private List<ModifyInfoEntity> modifyInfoList = new ArrayList<ModifyInfoEntity>();

	public ModifyInfoAdatper(Context context,
			List<ModifyInfoEntity> modifyInfoList) {
		super();
		this.context = context;
		this.modifyInfoList = modifyInfoList;
	}

	@Override
	public int getCount() {
		return modifyInfoList.size();
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
			v = inflater.inflate(R.layout.list_item_modify_info, null);
			holder.asmiAddDate = (TextView) v.findViewById(R.id.asmiAddDateTv);
			holder.asmiDesp = (TextView) v.findViewById(R.id.asmiDespTv);
			holder.fileTv = (TextView) v.findViewById(R.id.fileTv);
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
		holder.asmiAddDate.setText(modifyInfoList.get(position)
				.getAsmiAddDate());
		holder.asmiDesp.setText(modifyInfoList.get(position).getAsmiDesp());
		holder.fileTv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
			    context.startActivity(getTextFileIntent("http://it.hjtechcn.cn/"+modifyInfoList.get(position).getAsmiFile(), true));;
			}
		});
		return v;
	}

	class ViewHolder {
		/** 标题 */
		private TextView asmiDesp;
		/** 日期 */
		private TextView asmiAddDate;
		/** 文件查看 */
		private TextView fileTv;
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
