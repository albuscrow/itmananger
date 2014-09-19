package com.itmanapp.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.itmanapp.entity.DeviceNeedToCheck;
import com.itmanapp.entity.PendingInspectionEquipmentEntity;

/**
 * @date 2014-7-17
 * @author wangpeng
 * @class description 待巡检设备数据解析类
 * 
 */
public class GetCheckDeviceListJson {
	
	public static int result = 0; 
	
	public static List<DeviceNeedToCheck> getJson(String response){
		List<DeviceNeedToCheck> list = new ArrayList<DeviceNeedToCheck>();
		try {
			JSONObject job=new JSONObject(response);
			result=job.getInt("result");
			if(result==1){
				if (job != null ) {
					JSONArray jsonArray = job.getJSONArray("details");
					for (int i = 0; i < jsonArray.length(); i++) {
						DeviceNeedToCheck entity=new DeviceNeedToCheck();
						JSONObject js = jsonArray.getJSONObject(i);
						
						entity.setTxrId(js.getLong("txrId"));
						entity.setTdName(js.getString("tdName"));
						entity.setTdCode(js.getString("tdCode"));
						try {
							entity.setTxrStatus(js.getInt("txrStatus"));
						} catch (Exception e) {
							e.printStackTrace();
						}					
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
