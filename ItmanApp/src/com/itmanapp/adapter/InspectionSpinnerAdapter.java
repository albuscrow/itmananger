package com.itmanapp.adapter;

import com.itmanapp.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * @date 2014-8-4
 * @author wangpeng
 * @class description 待巡检提交-巡检项目-适配器
 * 
 */
public class InspectionSpinnerAdapter extends BaseAdapter {

	private Context context;

	private String [] array;

	public InspectionSpinnerAdapter(Context context, String[] array) {
		super();
		this.context = context;
		this.array = array;
	}

	@Override
	public int getCount() {
		return array.length;
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
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(context);
			convertView = inflater.inflate(R.layout.inspection_spinner_item, parent, false);
		}

		//此处text1是Spinner默认的用来显示文字的TextView
		TextView tv = (TextView) convertView.findViewById(R.id.textView1);
		tv.setText(array[position]);
		return convertView;
	}


}
