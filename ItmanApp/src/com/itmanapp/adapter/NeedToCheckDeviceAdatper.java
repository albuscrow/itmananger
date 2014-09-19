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
import com.itmanapp.entity.DeviceNeedToCheck;
import com.itmanapp.entity.PendingInspectionEquipmentEntity;

/**
 * @date 2014-7-17
 * @author wangpeng
 * @class description 待巡检记录适配器
 * 
 */
public class NeedToCheckDeviceAdatper extends BaseAdapter {

	private Context context;

	private List<DeviceNeedToCheck> list = new ArrayList<DeviceNeedToCheck>();

	public NeedToCheckDeviceAdatper(Context context,
			List<DeviceNeedToCheck> list2) {
		super();
		this.context = context;
		this.list = list2;
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
			v = inflater.inflate(R.layout.list_item_device_need_to_check, null);
			holder.name = (TextView) v.findViewById(R.id.name);
			holder.status = (TextView) v.findViewById(R.id.statu);
			holder.code = (TextView) v.findViewById(R.id.code);
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
		holder.name.setText(list.get(position).getTdName()+"");
		holder.code.setText(list.get(position).getTdCode()+"");
	 
		
		int status=list.get(position).getTxrStatus();
		if(status==0){
			holder.status.setText("任务生成");
		}else if(status==1){
			holder.status.setText("巡检完成");
		}else if(status==3){
			holder.status.setText("已打印");
		}else if(status==4){
			holder.status.setText("已验收");
		}else if(status==2){
			holder.status.setText("已领取");
		}
		
		return v;
	}

	class ViewHolder {
		
		/** 设备名称 */
		private TextView name;
		
		/** 状  态 */
		private TextView status;
		
		private TextView code;
	}

}