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
import com.itmanapp.entity.WarrantyEntity;

/**
 * @date 2014-7-27
 * @author wangpeng
 * @class description 用户报修适配器
 * 
 */
public class WarrantyAdatper extends BaseAdapter {

	private Context context;

	private List<WarrantyEntity> warrantyList = new ArrayList<WarrantyEntity>();

	public WarrantyAdatper(Context context,
			List<WarrantyEntity> warrantyList) {
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
			v = inflater.inflate(R.layout.list_item_warranty, null);
			holder.warrantyTimeTv = (TextView) v.findViewById(R.id.warrantyTimeTv);
			holder.belongsSystemTv = (TextView) v.findViewById(R.id.belongsSystemTv);
			holder.warrantyEptTv = (TextView) v.findViewById(R.id.warrantyEptTv);
			holder.warrantyTypeTv = (TextView) v.findViewById(R.id.warrantyTypeTv);
			holder.statusTv = (TextView) v.findViewById(R.id.statusTv);
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
		holder.warrantyTimeTv.setText(warrantyList.get(position).getAddtime()+"");
		holder.belongsSystemTv.setText(warrantyList.get(position).getId()+"");
		holder.warrantyEptTv.setText(warrantyList.get(position).getWxName()+"");
		holder.warrantyTypeTv.setText(warrantyList.get(position).getDesp()+"");
		
		int status=warrantyList.get(position).getStatus();
		//1:提交报修 2:已经确认 3：已派工 4：待维修 5：已维修 6：已验收 0：审核失败 7：维修失败
		if(status==0){
			holder.statusTv.setText("提交报修");
		}else if(status==1){
			holder.statusTv.setText("已经确认");
		}else if(status==2){
			holder.statusTv.setText("已派工");
		}else if(status==3){
			holder.statusTv.setText("待维修");
		}else if(status==4){
			holder.statusTv.setText("已维修");
		}else if(status==5){
			holder.statusTv.setText("已验收");
		}else if(status==6){
			holder.statusTv.setText("审核失败");
		}else if(status==7){
			holder.statusTv.setText("维修失败");
		}
		
		return v;
	}

	class ViewHolder {
		/** 报修时间 */
		private TextView warrantyTimeTv;
		/** 所属系统 */
		private TextView belongsSystemTv;
		/** 所属设备 */
		private TextView warrantyEptTv;
		/** 保修类型*/
		private TextView warrantyTypeTv;
		/** 状  态 */
		private TextView statusTv;
	}

}
