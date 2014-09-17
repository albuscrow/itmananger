package com.itmanapp.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.itmanapp.entity.ModifyInfoEntity;

/**
 * @date 2014-7-17
 * @author wangpeng
 * @class description 整改历史数据解析类
 * 
 */
public class GetModifyInfoJson {
	
	public static int result = 0; 
	
	public static List<ModifyInfoEntity> getJson(String response){
		List<ModifyInfoEntity> modifyInfoList = new ArrayList<ModifyInfoEntity>();
		try {
			JSONObject job=new JSONObject(response);
			result=job.getInt("result");
			if(result==1){
				if (job != null ) {
					JSONArray jsonArray = job.getJSONArray("details");
					for (int i = 0; i < jsonArray.length(); i++) {
						ModifyInfoEntity entity=new ModifyInfoEntity();
						JSONObject js = jsonArray.getJSONObject(i);
						entity.setAsmiDesp(js.getString("asmiDesp"));
						entity.setAsmiAddDate(js.getString("asmiAddDate"));
						entity.setAsmiAddPerson(js.getInt("asmiAddPerson"));
						entity.setAsmiFile(js.getString("asmiFile"));
						entity.setAsmiHeadId(js.getInt("asmiHeadId"));
						entity.setAsmiId(js.getInt("asmiId"));
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
