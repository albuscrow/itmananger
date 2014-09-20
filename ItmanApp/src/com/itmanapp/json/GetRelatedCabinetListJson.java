package com.itmanapp.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.itmanapp.entity.CabinetEntity;
import com.itmanapp.entity.RelatedDeviceEntity;

/**
 * @date 2014-7-17
 * @author wangpeng
 * @class description 整改历史数据解析类
 * 
 */
public class GetRelatedCabinetListJson {
	
	public static int result = 0; 
	
	public static List<CabinetEntity> getJson(String response){
		List<CabinetEntity> modifyInfoList = new ArrayList<CabinetEntity>();
		try {
			JSONObject job=new JSONObject(response);
			result=job.getInt("result");
			if(result==1){
				if (job != null ) {
					JSONArray jsonArray = job.getJSONArray("details");
					for (int i = 0; i < jsonArray.length(); i++) {
						CabinetEntity entity=new CabinetEntity();
						JSONObject js = jsonArray.getJSONObject(i);
						entity.setTcName(js.getString("tcName"));
						entity.setTcCode(js.getString("tcCode"));
						entity.setTcPosition(js.getString("tcPosition"));
						entity.setTcLayDate(js.getString("tcLayDate"));
						entity.setTcId(js.getLong("tcId"));
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
