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
import com.itmanapp.entity.ModifyInfoEntity;
import com.itmanapp.entity.RelatedDeviceEntity;

/**
 * @date 2014-7-17
 * @author wangpeng
 * @class description 相关设备列表适配器
 * 
 */
public class RelatedDeviceAdatper extends BaseAdapter {

	private Context context;

	private List<RelatedDeviceEntity> modifyInfoList = new ArrayList<RelatedDeviceEntity>();

	public RelatedDeviceAdatper(Context context,
			List<RelatedDeviceEntity> modifyInfoList) {
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
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		View v = convertView;
		LayoutInflater inflater;
		if (convertView == null) {
			holder = new ViewHolder();
			// 设置映射对象
			inflater = LayoutInflater.from(context);
			v = inflater.inflate(R.layout.list_item_related_device, null);
			holder.nameTv = (TextView) v.findViewById(R.id.nameTv);
			holder.codingTv = (TextView) v.findViewById(R.id.codingTv);
			holder.deviceTypeTv = (TextView) v.findViewById(R.id.deviceTypeTv);
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
		holder.nameTv.setText(modifyInfoList.get(position).getAdName());
		holder.codingTv.setText(modifyInfoList.get(position).getAdCode());
		holder.deviceTypeTv.setText(modifyInfoList.get(position).getSwName()); 
		return v;
	}

	class ViewHolder {
		/** 编码 */
		private TextView codingTv;
		/** 名称 */
		private TextView nameTv;
		/** 设备类型 */
		private TextView deviceTypeTv;
	}

}
