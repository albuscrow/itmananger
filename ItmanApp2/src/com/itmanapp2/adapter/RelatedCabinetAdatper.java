package com.itmanapp2.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.itmanapp2.R;
import com.itmanapp2.entity.CabinetEntity;
import com.itmanapp2.entity.FileEntity;
import com.itmanapp2.entity.RelatedDeviceEntity;

/**
 * @date 2014-7-17
 * @author wangpeng
 * @class description 相关设备列表适配器
 * 
 */
public class RelatedCabinetAdatper extends BaseAdapter {

	private Context context;

	private List<CabinetEntity> cabinetList = new ArrayList<CabinetEntity>();

	public RelatedCabinetAdatper(Context context,
			List<CabinetEntity> cabinetList) {
		super();
		this.context = context;
		this.cabinetList = cabinetList;
	}

	@Override
	public int getCount() {
		return cabinetList.size();
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
			v = inflater.inflate(R.layout.list_item_related_cabinet, null);
			holder.nameTv = (TextView) v.findViewById(R.id.nameTv);
			holder.codingTv = (TextView) v.findViewById(R.id.codingTv);
			holder.locationTv = (TextView) v.findViewById(R.id.position);
			holder.addTime = (TextView)v.findViewById(R.id.addDateTv);
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
		holder.nameTv.setText(cabinetList.get(position).getTcName());
		holder.codingTv.setText(cabinetList.get(position).getTcCode());
		holder.locationTv.setText(cabinetList.get(position).getTcPosition()); 
		holder.addTime.setText(cabinetList.get(position).getTcLayDate());
		return v;
	}

	class ViewHolder {
		/** 编码 */
		private TextView codingTv;
		/** 名称 */
		private TextView nameTv;
		/** 设备位置 */
		private TextView locationTv;
		
		private TextView addTime;
	}

}
