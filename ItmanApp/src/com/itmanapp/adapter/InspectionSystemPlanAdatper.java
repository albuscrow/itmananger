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
import com.itmanapp.entity.InspectionSystemPlanEntity;

/**
 * @date 2014-8-6
 * @author wangpeng
 * @class description 待巡检系统计划适配器
 * 
 */
public class InspectionSystemPlanAdatper extends BaseAdapter {

	private Context context;

	private List<InspectionSystemPlanEntity> list = new ArrayList<InspectionSystemPlanEntity>();

	public InspectionSystemPlanAdatper(Context context,
			List<InspectionSystemPlanEntity> list) {
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
			v = inflater.inflate(R.layout.list_item_inspection_system_plan, null);
			holder.inspectionPlanTv = (TextView) v.findViewById(R.id.inspectionPlanTv);
			holder.systemPlanNameTv = (TextView) v.findViewById(R.id.systemPlanNameTv);
			holder.systemCodeTv = (TextView) v.findViewById(R.id.systemCodeTv);
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
		holder.inspectionPlanTv.setText(list.get(position).getAxpName()+"");
		holder.systemPlanNameTv.setText(list.get(position).getAsName()+"");
		holder.systemCodeTv.setText(list.get(position).getAsCode()+"");
	 
		return v;
	}

	class ViewHolder {
		/** 巡检计划 */
		private TextView inspectionPlanTv;
		/** 巡检系统 */
		private TextView systemPlanNameTv;
		/** 系统编码*/
		private TextView systemCodeTv;
	}

}
