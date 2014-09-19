package com.itmanapp.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.itmanapp.entity.DeviceTypeEntity;


/**
 * @date 2014-7-18
 * @author wangpeng
 * @class description 报修类型数据解析类
 * 
 */
public class GetDeviceTypeJson {
	
    public static int result = 0; 
    
	public static List<DeviceTypeEntity> getJson(String response){
		List<DeviceTypeEntity> deviceTypeList = new ArrayList<DeviceTypeEntity>();
		try {
			JSONObject job=new JSONObject(response);
			result=job.getInt("result");
			if(result==1){
				if (job != null ) {
					JSONArray jsonArray = job.getJSONArray("details");
					for (int i = 0; i < jsonArray.length(); i++) {
						DeviceTypeEntity entity=new DeviceTypeEntity();
						JSONObject js = jsonArray.getJSONObject(i);
						entity.setAdvAddDate(js.getString("advAddDate"));
						entity.setAdvDeviceTypeid(js.getInt("advDeviceTypeid"));
						entity.setAdvId(js.getInt("advId"));
						entity.setAdvItemName(js.getString("advItemName"));
						deviceTypeList.add(entity);
					}
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return deviceTypeList;
	}

}
