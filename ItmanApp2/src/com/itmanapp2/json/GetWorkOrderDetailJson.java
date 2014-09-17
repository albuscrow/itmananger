package com.itmanapp2.json;

import org.json.JSONException;
import org.json.JSONObject;

import com.itmanapp2.entity.WorkOrderEntity;

/**
 * @date 2014-7-15
 * @author wangpeng
 * @class description 设备数据解析类
 * 
 */
public class GetWorkOrderDetailJson {
	
	public static WorkOrderEntity entity=null;
	
	public static int getJson(String response){
		int result = 0; 
		try {
			JSONObject job=new JSONObject(response);
			result=job.getInt("result");
			if(result==1){
				JSONObject js = job.getJSONObject("details");
				if(js!=null){
					entity=new WorkOrderEntity();
					entity.setAddTime(js.getString("addTime"));
					entity.setAllocateDate(js.getString("allocateDate"));
					entity.setDesp(js.getString("desp"));
					entity.setDetailId(js.getInt("detailId"));
					entity.setDetailStatus(js.getInt("detailStatus"));
					entity.setOrderId(js.getInt("orderId"));
					entity.setOrderNo(js.getString("orderNo"));
					entity.setOrderStatus(js.getInt("orderStatus"));
					entity.setWxName(js.getString("wxName"));
					entity.setAdCode(js.getString("adCode"));
					entity.setAdName(js.getString("adName"));
					entity.setAsName(js.getString("asName"));
					entity.setAuiName(js.getString("auiName"));
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}

}
