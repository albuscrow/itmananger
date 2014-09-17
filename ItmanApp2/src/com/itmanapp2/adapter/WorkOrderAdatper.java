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
import com.itmanapp2.entity.WorkOrderEntity;

/**
 * @date 2014-7-17
 * @author wangpeng
 * @class description 待处理工单适配器
 * 
 */
public class WorkOrderAdatper extends BaseAdapter {

	private Context context;

	private List<WorkOrderEntity> workOrderList = new ArrayList<WorkOrderEntity>();

	public WorkOrderAdatper(Context context,
			List<WorkOrderEntity> workOrderList) {
		super();
		this.context = context;
		this.workOrderList = workOrderList;
	}

	@Override
	public int getCount() {
		return workOrderList.size();
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
			v = inflater.inflate(R.layout.list_item_work_order, null);
			holder.assignTimeTv = (TextView) v.findViewById(R.id.assignTimeTv);
			holder.assignNumberTv = (TextView) v.findViewById(R.id.assignNumberTv);
			holder.repairProjectTv = (TextView) v.findViewById(R.id.repairProjectTv);
			holder.statusTv = (TextView) v.findViewById(R.id.statusTv);
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
		holder.assignTimeTv.setText(workOrderList.get(position).getAllocateDate()+"");
		holder.assignNumberTv.setText(workOrderList.get(position).getOrderNo()+"");
		holder.repairProjectTv.setText(workOrderList.get(position).getWxName()+"");
		
		int status=workOrderList.get(position).getOrderStatus();
		//1:提交报修 2:已经确认 3：已派工 4：待维修 5：已维修 6：已验收 0：审核失败 7：维修失败
		if(status==1){
			holder.statusTv.setText("提交报修");
		}else if(status==2){
			holder.statusTv.setText("已经确认");
		}else if(status==3){
			holder.statusTv.setText("已派工");
		}else if(status==4){
			holder.statusTv.setText("待维修");
		}else if(status==5){
			holder.statusTv.setText("已维修");
		}else if(status==6){
			holder.statusTv.setText("已验收");
		}else if(status==0){
			holder.statusTv.setText("审核失败");
		}else if(status==7){
			holder.statusTv.setText("维修失败");
		}
		
		return v;
	}

	class ViewHolder {
		/** 分配时间 */
		private TextView assignTimeTv;
		/** 订单编号 */
		private TextView assignNumberTv;
		/** 维修项目 */
		private TextView repairProjectTv;
		/** 状  态 */
		private TextView statusTv;
	}

}
