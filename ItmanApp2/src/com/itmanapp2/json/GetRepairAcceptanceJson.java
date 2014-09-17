package com.itmanapp2.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.itmanapp2.entity.RepairAcceptanceEntity;
import com.itmanapp2.entity.WarrantyEntity;

/**
 * @date 2014-8-1
 * @author wangpeng
 * @class description 待验收数据解析类
 * 
 */
public class GetRepairAcceptanceJson {
	
	public static int result = 0; 
	
	public static int total = 0; 
	
	public static List<RepairAcceptanceEntity> getJson(String response){
		List<RepairAcceptanceEntity> list = new ArrayList<RepairAcceptanceEntity>();
		try {
			JSONObject job=new JSONObject(response);
			result=job.getInt("result");
			total=job.getInt("total");
			if(result==1){
				if (job != null ) {
					JSONArray jsonArray = job.getJSONArray("details");
					for (int i = 0; i < jsonArray.length(); i++) {
						RepairAcceptanceEntity entity=new RepairAcceptanceEntity();
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
						list.add(entity);
					}
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
