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
import com.itmanapp2.entity.RepairAcceptanceEntity;
import com.itmanapp2.entity.WorkOrderEntity;

/**
 * @date 2014-7-27
 * @author wangpeng
 * @class description 用户报修适配器
 * 
 */
public class RepairAcceptanceAdatper extends BaseAdapter {

	private Context context;

	private List<WorkOrderEntity> warrantyList = new ArrayList<WorkOrderEntity>();

	public RepairAcceptanceAdatper(Context context,
			List<WorkOrderEntity> warrantyList) {
		super();
		this.context = context;
		this.warrantyList = warrantyList;
	}

	@Override
	public int getCount() {
		return warrantyList.size();
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
			v = inflater.inflate(R.layout.list_item_repair_acceptance, null);
			holder.warrantyTimeTv = (TextView) v.findViewById(R.id.warrantyTimeTv);
			holder.orderNumberTv = (TextView) v.findViewById(R.id.orderNumberTv);
			holder.wxProjectTv = (TextView) v.findViewById(R.id.wxProjectTv);
			holder.statusTv = (TextView) v.findViewById(R.id.statusTv);
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
		holder.warrantyTimeTv.setText(warrantyList.get(position).getAllocateDate()+"");
		holder.orderNumberTv.setText(warrantyList.get(position).getOrderNo()+"");
		holder.wxProjectTv.setText(warrantyList.get(position).getTdName()+"");
		
		int status=warrantyList.get(position).getOrderStatus();
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
		/** 报修时间 */
		private TextView warrantyTimeTv;
		/** 订单编号 */
		private TextView orderNumberTv;
		/** 维修项目 */
		private TextView wxProjectTv;
		/** 状  态 */
		private TextView statusTv;
	}

}
