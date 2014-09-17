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
import com.itmanapp2.entity.WrittenInspectionEntity;

/**
 * @date 2014-7-17
 * @author wangpeng
 * @class description 待巡检记录适配器
 * 
 */
public class WrittenInspectionAdatper extends BaseAdapter {

	private Context context;

	private List<WrittenInspectionEntity> modifyInfoList = new ArrayList<WrittenInspectionEntity>();

	public WrittenInspectionAdatper(Context context,
			List<WrittenInspectionEntity> modifyInfoList) {
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
			v = inflater.inflate(R.layout.list_item_written_inspection, null);
			holder.inspectionTimeTv = (TextView) v.findViewById(R.id.inspectionTimeTv);
			holder.inspectionPlanTv = (TextView) v.findViewById(R.id.inspectionPlanTv);
			holder.inspectionTypeTv = (TextView) v.findViewById(R.id.inspectionTypeTv);
			holder.inspectionObjectTv = (TextView) v.findViewById(R.id.inspectionObjectTv);
			holder.statusTv = (TextView) v.findViewById(R.id.statusTv);
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
		holder.inspectionTimeTv.setText(modifyInfoList.get(position).getAxrAddtime()+"");
		holder.inspectionPlanTv.setText(modifyInfoList.get(position).getPlanName()+"");
		holder.inspectionTypeTv.setText(modifyInfoList.get(position).getType()+"");
		holder.inspectionObjectTv.setText(modifyInfoList.get(position).getDespName()+"");
		
		int status=modifyInfoList.get(position).getStatus();
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
		/** 巡检类型 */
		private TextView inspectionTypeTv;
		/** 巡检目标 */
		private TextView inspectionObjectTv;
		/** 状  态 */
		private TextView statusTv;
	}

}
