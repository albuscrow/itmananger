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
import com.itmanapp.entity.CheckPlanEntity;
import com.itmanapp.entity.DeviceNeedToCheck;

/**
 * @date 2014-8-6
 * @author wangpeng
 * @class description 待巡检系统计划适配器
 * 
 */
public class InspectionPlanAdatper extends BaseAdapter {

	private Context context;

	private List<DeviceNeedToCheck> list = new ArrayList<DeviceNeedToCheck>();

	public InspectionPlanAdatper(Context context,
			List<DeviceNeedToCheck> list) {
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
			v = inflater.inflate(R.layout.list_item_inspection_plan, null);
			holder.planName = (TextView) v.findViewById(R.id.plan_name);
			holder.name = (TextView) v.findViewById(R.id.name);
			holder.code = (TextView) v.findViewById(R.id.code);
			holder.planType = (TextView) v.findViewById(R.id.plan_type);
			holder.codeLabel = (TextView) v.findViewById(R.id.code_label);
			holder.nameLabel = (TextView) v.findViewById(R.id.name_label);
			holder.date = (TextView) v.findViewById(R.id.time);
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
		DeviceNeedToCheck inspectionPlanEntity = list.get(position);
		holder.planName.setText(inspectionPlanEntity.getPlanName());
		holder.planType.setText(inspectionPlanEntity.getTypeName());
		holder.date.setText(inspectionPlanEntity.getAddDate());
		
		switch (inspectionPlanEntity.getTypeInt()) {
		case CheckPlanEntity.TYPE_ROOM:
			holder.codeLabel.setText("机房编号：");
			holder.nameLabel.setText("机房名称：");
			holder.name.setText(inspectionPlanEntity.getInfo().getTmrName());
			holder.code.setText(inspectionPlanEntity.getInfo().getTmrCode());
			break;

		case CheckPlanEntity.TYPE_CABINET:
			holder.codeLabel.setText("机柜编号：");
			holder.nameLabel.setText("机柜名称：");
			holder.name.setText(inspectionPlanEntity.getInfo().getTcName());
			holder.code.setText(inspectionPlanEntity.getInfo().getTcCode());
			break;
		case CheckPlanEntity.TYPE_DEVICE:
			holder.codeLabel.setText("设备编号：");
			holder.nameLabel.setText("设备名称：");
			holder.name.setText(inspectionPlanEntity.getInfo().getTdName());
			holder.code.setText(inspectionPlanEntity.getInfo().getTdCode());
			break;		default:
			break;
		}
	 
		return v;
	}

	class ViewHolder {
		public TextView date;
		
		/** 巡检计划 */
		private TextView planName;
		
		private TextView planType;
		
		/** 巡检系统 */
		private TextView name;
		/** 系统编码*/
		private TextView code;
		
		private TextView codeLabel;
		
		private TextView nameLabel;
		
	}

}
