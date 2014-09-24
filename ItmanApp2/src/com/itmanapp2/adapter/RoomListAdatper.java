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
import com.itmanapp2.entity.FileEntity;
import com.itmanapp2.entity.RelatedDeviceEntity;
import com.itmanapp2.entity.RoomEntity;

/**
 * @date 2014-7-17
 * @author wangpeng
 * @class description 相关设备列表适配器
 * 
 */
public class RoomListAdatper extends BaseAdapter {

	private Context context;

	private List<RoomEntity> modifyInfoList = new ArrayList<RoomEntity>();

	public RoomListAdatper(Context context,
			List<RoomEntity> modifyInfoList) {
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
			v = inflater.inflate(R.layout.list_item_room_list, null);
			holder.projectTv = (TextView) v.findViewById(R.id.project);
			holder.idTv = (TextView) v.findViewById(R.id.id);
			holder.unitTv = (TextView) v.findViewById(R.id.use_unit);
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
		
		//TODO
		holder.projectTv.setText(modifyInfoList.get(position).getTmrName());
		holder.idTv.setText(modifyInfoList.get(position).getTmrCode());
		holder.unitTv.setText(modifyInfoList.get(position).getUnitName()); 
		return v;
	}

	class ViewHolder {
		/** 编码 */
		private TextView idTv;
		/** 项目 */
		private TextView projectTv;
		/** 使用单位 */
		private TextView unitTv;
	}

}
