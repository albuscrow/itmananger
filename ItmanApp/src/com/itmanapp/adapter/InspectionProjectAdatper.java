package com.itmanapp.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.itmanapp.R;
import com.itmanapp.entity.InspectionProjectEntity;

/**
 * @date 2014-7-17
 * @author wangpeng
 * @class description 巡检项目列表适配器
 * 
 */
public class InspectionProjectAdatper extends BaseAdapter {

	private Context context;

	private List<InspectionProjectEntity> list = new ArrayList<InspectionProjectEntity>();

	public InspectionProjectAdatper(Context context,
			List<InspectionProjectEntity> list) {
		super();
		this.context = context;
		this.list =list;
	}

	@Override
	public int getCount() {
		return list.size();
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
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		View v = convertView;
		LayoutInflater inflater;
		if (convertView == null) {
			holder = new ViewHolder();
			// 设置映射对象
			inflater = LayoutInflater.from(context);
			v = inflater.inflate(R.layout.list_item_inspection_project, null);
			holder.resultTv = (TextView) v.findViewById(R.id.resultTv);
			holder.itemNameTv = (TextView) v.findViewById(R.id.itemNameTv);
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
		//int result=list.get(position).getResult();
		int result=list.get(position).getResult();
		if(result==1){
			holder.resultTv.setText("正常");
		}else{
			holder.resultTv.setText("异常");
		}
		
		holder.itemNameTv.setText(list.get(position).getItemName()+"");
		return v;
	}

	class ViewHolder {
		/** 标题 */
		private TextView itemNameTv;
		/** 日期 */
		private TextView resultTv;
	}

}
