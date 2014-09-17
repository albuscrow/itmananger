package com.itmanapp.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.itmanapp.entity.RelatedDeviceEntity;

/**
 * @date 2014-7-17
 * @author wangpeng
 * @class description 整改历史数据解析类
 * 
 */
public class GetRelatedDeviceJson {
	
	public static int result = 0; 
	
	public static List<RelatedDeviceEntity> getJson(String response){
		List<RelatedDeviceEntity> modifyInfoList = new ArrayList<RelatedDeviceEntity>();
		try {
			JSONObject job=new JSONObject(response);
			result=job.getInt("result");
			if(result==1){
				if (job != null ) {
					JSONArray jsonArray = job.getJSONArray("details");
					for (int i = 0; i < jsonArray.length(); i++) {
						RelatedDeviceEntity entity=new RelatedDeviceEntity();
						JSONObject js = jsonArray.getJSONObject(i);
						entity.setAdCode(js.getString("adCode"));
						entity.setAdDesp(js.getString("adDesp"));
						entity.setAdId(js.getInt("adId"));
						entity.setAdName(js.getString("adName"));
						entity.setAdPosition(js.getString("adPosition"));
						entity.setAsName(js.getString("asName"));
						entity.setSwName(js.getString("swName"));
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
