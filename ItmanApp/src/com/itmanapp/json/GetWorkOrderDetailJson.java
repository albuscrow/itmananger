package com.itmanapp.json;

import org.json.JSONException;
import org.json.JSONObject;

import com.itmanapp.entity.EquipmentEntity;
import com.itmanapp.entity.LoginEntity;
import com.itmanapp.entity.WorkOrderEntity;

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
					entity.setDetailId(js.getLong("detailId"));
					entity.setDetailStatus(js.getInt("detailStatus"));
					entity.setOrderId(js.getLong("orderId"));
					entity.setOrderNo(js.getString("orderNo"));
					entity.setOrderStatus(js.getInt("orderStatus"));
					
					entity.setAdName(js.getString("adName"));
					entity.setAuiName(js.getString("auiName"));
					entity.setDepName(js.getString("depName"));
					entity.setRoomName(js.getString("roomName"));
					entity.setCabinetName(js.getString("cabinetName"));
					entity.setTdcName(js.getString("tdcName"));
					entity.setTdPosition(js.getString("tdPosition"));
					entity.setItemNames(js.getString("itemNames"));
					entity.setTdCode(js.getString("tdCode"));
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}

}
