package com.itmanapp2.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.itmanapp2.entity.DepEntity;


/**
 * @date 2014-7-15
 * @author wangpeng
 * @class description 所属系统数据解析类
 * 
 */
public class GetDepJson {
	
    public static int result = 0; 
    
	public static List<DepEntity> getJson(String response){
		List<DepEntity> fillRepairList = new ArrayList<DepEntity>();
		try {
			JSONObject job=new JSONObject(response);
			result=job.getInt("result");
			if(result==1){
				if (job != null ) {
					JSONArray jsonArray = job.getJSONArray("details");
					for (int i = 0; i < jsonArray.length(); i++) {
						DepEntity entity=new DepEntity();
						JSONObject js = jsonArray.getJSONObject(i);
						entity.setTdId(js.getLong("tdId"));
						entity.setTdName(js.getString("tdName"));
						fillRepairList.add(entity);
					}
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return fillRepairList;
	}


}
