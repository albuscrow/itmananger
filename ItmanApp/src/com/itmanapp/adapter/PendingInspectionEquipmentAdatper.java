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
import com.itmanapp.entity.PendingInspectionEquipmentEntity;

/**
 * @date 2014-7-17
 * @author wangpeng
 * @class description 待巡检记录适配器
 * 
 */
public class PendingInspectionEquipmentAdatper extends BaseAdapter {

	private Context context;

	private List<PendingInspectionEquipmentEntity> list = new ArrayList<PendingInspectionEquipmentEntity>();

	public PendingInspectionEquipmentAdatper(Context context,
			List<PendingInspectionEquipmentEntity> list) {
		super();
		this.context = context;
		this.list = list;
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
			v = inflater.inflate(R.layout.list_item_written_inspection, null);
			holder.inspectionTimeTv = (TextView) v.findViewById(R.id.inspectionTimeTv);
			holder.inspectionPlanTv = (TextView) v.findViewById(R.id.inspectionPlanTv);
			holder.systemNameTv = (TextView) v.findViewById(R.id.systemNameTv);
			holder.deviceNameTv = (TextView) v.findViewById(R.id.deviceNameTv);
			holder.statusTv = (TextView) v.findViewById(R.id.statusTv);
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
		holder.inspectionTimeTv.setText(list.get(position).getAxrAddtime()+"");
		holder.inspectionPlanTv.setText(list.get(position).getPlanName()+"");
		holder.systemNameTv.setText(list.get(position).getAsName()+"");
		holder.deviceNameTv.setText(list.get(position).getAdName()+"");
	 
		
		int status=list.get(position).getStatus();
		if(status==0){
			holder.statusTv.setText("任务生成");
		}else if(status==1){
			holder.statusTv.setText("巡检完成");
		}else if(status==2){
			holder.statusTv.setText("已打印");
		}else if(status==3){
			holder.statusTv.setText("已验收");
		}
		
		return v;
	}

	class ViewHolder {
		/** 巡检时间 */
		private TextView inspectionTimeTv;
		/** 巡检计划 */
		private TextView inspectionPlanTv;
		/** 巡检系统 */
		private TextView systemNameTv;
		/** 设备名称 */
		private TextView deviceNameTv;
		/** 状  态 */
		private TextView statusTv;
	}

}
