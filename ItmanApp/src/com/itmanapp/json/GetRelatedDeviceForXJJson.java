package com.itmanapp.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.itmanapp.entity.DeviceNeedToCheck;
import com.itmanapp.entity.RelatedDeviceEntity;

/**
 * @date 2014-7-17
 * @author wangpeng
 * @class description 整改历史数据解析类
 * 
 */
public class GetRelatedDeviceForXJJson {
	
	public static int result = 0;
	
	public static List<DeviceNeedToCheck> getJson(String response){
		List<DeviceNeedToCheck> modifyInfoList = new ArrayList<DeviceNeedToCheck>();
		try {
			JSONObject job=new JSONObject(response);
			result=job.getInt("result");
			if(result==1){
				if (job != null ) {
					JsonObject jo = new JsonParser().parse(response).getAsJsonObject();
					JsonElement je = jo.get("details");
					
					modifyInfoList = new Gson().fromJson(je, new TypeToken<List<DeviceNeedToCheck>>(){}.getType());
//					JSONArray jsonArray = job.getJSONArray("details");
//					for (int i = 0; i < jsonArray.length(); i++) {
//						RelatedDeviceEntity entity=new RelatedDeviceEntity();
//						JSONObject js = jsonArray.getJSONObject(i);
//						entity.setAdId(js.getInt("adId"));
//						entity.setAdCode(js.getString("adCode"));
//						entity.setAdName(js.getString("adName"));
//						entity.setTdcName(js.getString("tdcName"));
//						entity.setAdDesp(js.getString("adDesp"));
//						entity.setAdPosition(js.getString("adPosition"));
//						entity.setAsName(js.getString("asName"));
//						entity.setTmrName(js.getString("tmrName"));
//						modifyInfoList.add(entity);
//					}
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return modifyInfoList;
	}

}
