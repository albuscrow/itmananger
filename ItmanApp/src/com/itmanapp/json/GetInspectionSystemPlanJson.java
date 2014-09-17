package com.itmanapp.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.itmanapp.entity.InspectionSystemPlanEntity;

/**
 * @date 2014-8-6
 * @author wangpeng
 * @class description 待巡检系统计划数据解析类
 * 
 */
public class GetInspectionSystemPlanJson {
	
	public static int result = 0; 
	
	public static int total = 0; 
	
	public static List<InspectionSystemPlanEntity> getJson(String response){
		List<InspectionSystemPlanEntity> list = new ArrayList<InspectionSystemPlanEntity>();
		try {
			JSONObject job=new JSONObject(response);
			result=job.getInt("result");
			total=job.getInt("total");
			if(result==1){
				if (job != null ) {
					JSONArray jsonArray = job.getJSONArray("details");
					for (int i = 0; i < jsonArray.length(); i++) {
						InspectionSystemPlanEntity entity=new InspectionSystemPlanEntity();
						JSONObject js = jsonArray.getJSONObject(i);
						entity.setAsCode(js.getString("asCode"));
						entity.setAsName(js.getString("asName"));
						entity.setAxpId(js.getInt("axpId"));
						entity.setAxpName(js.getString("axpName"));
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
