package com.itmanapp.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.itmanapp.entity.WorkOrderEntity;

/**
 * @date 2014-7-17
 * @author wangpeng
 * @class description 待巡检数据解析类
 * 
 */
public class GetWorkOrderJson {
	
	public static int result = 0; 
	
	public static int total = 0; 
	
	public static List<WorkOrderEntity> getJson(String response){
		List<WorkOrderEntity> modifyInfoList = new ArrayList<WorkOrderEntity>();
		try {
			JSONObject job=new JSONObject(response);
			result=job.getInt("result");
			total=job.getInt("total");
			if(result==1){
				if (job != null ) {
					JSONArray jsonArray = job.getJSONArray("details");
					for (int i = 0; i < jsonArray.length(); i++) {
						WorkOrderEntity entity=new WorkOrderEntity();
						JSONObject js = jsonArray.getJSONObject(i);
						entity.setAddTime(js.getString("addTime"));
						entity.setAllocateDate(js.getString("allocateDate"));
						entity.setDesp(js.getString("desp"));
						entity.setDetailId(js.getInt("detailId"));
						entity.setDetailStatus(js.getInt("detailStatus"));
						entity.setOrderId(js.getInt("orderId"));
						entity.setOrderNo(js.getString("orderNo"));
						entity.setOrderStatus(js.getInt("orderStatus"));
						entity.setWxName(js.getString("wxName"));
						modifyInfoList.add(entity);
					}
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return modifyInfoList;
	}

}
