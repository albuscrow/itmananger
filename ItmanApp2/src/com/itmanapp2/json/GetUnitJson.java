package com.itmanapp2.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.itmanapp2.entity.UnitEntity;


/**
 * @date 2014-7-15
 * @author wangpeng
 * @class description 所属系统数据解析类
 * 
 */
public class GetUnitJson {
	
    public static int result = 0; 
    
	public static List<UnitEntity> getJson(String response){
		List<UnitEntity> fillRepairList = new ArrayList<UnitEntity>();
		try {
			JSONObject job=new JSONObject(response);
			result=job.getInt("result");
			if(result==1){
				if (job != null ) {
					JSONArray jsonArray = job.getJSONArray("details");
					for (int i = 0; i < jsonArray.length(); i++) {
						UnitEntity entity=new UnitEntity();
						JSONObject js = jsonArray.getJSONObject(i);
						entity.setTuId(js.getLong("tuId"));
						entity.setTuName(js.getString("tuName"));
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
