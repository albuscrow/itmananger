package com.itmanapp2.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.itmanapp2.entity.DeviceTypeEntity;


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
						entity.setTwcName(js.getString("twcName"));
						entity.setTwcId(js.getLong("twcId"));
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
