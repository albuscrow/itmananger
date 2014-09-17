package com.itmanapp2.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.itmanapp2.R;
import com.itmanapp2.entity.InspectionProjectEntity;

/**
 * @date 2014-7-29
 * @author wangpeng
 * @class description 待巡检提交-巡检项目-适配器
 * 
 */
public class WrittenInspectionDetailAdatper extends BaseAdapter {

	private Context context;
	
	private List<InspectionProjectEntity> list = new ArrayList<InspectionProjectEntity>();

	public WrittenInspectionDetailAdatper(Context context,
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		View v = convertView;
		LayoutInflater inflater;
		if (convertView == null) {
			holder = new ViewHolder();
			// 设置映射对象
			inflater = LayoutInflater.from(context);
			v = inflater.inflate(R.layout.list_item_written_inspection_detail, null);
			holder.resultSpn = (Spinner) v.findViewById(R.id.resultSpn);
			holder.itemNameTv = (TextView) v.findViewById(R.id.itemNameTv);
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
		holder.itemNameTv.setText(list.get(position).getItemName()+"");

		final String[] m={"正常","异常"};
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,m);
		//设置下拉列表的风格
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         
        //将adapter 添加到spinner中
        holder.resultSpn.setAdapter(adapter);
        
        holder.resultSpn.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				System.out.println(list.get(position).getItemName()+"--->>"+m[arg2]+"--"+arg2); 
				if(m[arg2].toString().equals("正常")){
					list.get(position).setStatus(1);
				}else if(m[arg2].toString().equals("异常")){
					list.get(position).setStatus(2);
				}
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
		});
		return v;
	}

	class ViewHolder {
		/** 标题 */
		private TextView itemNameTv;
		/** 状态 */
		private Spinner resultSpn;
	}

}
