package com.itmanapp.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.itmanapp.entity.InspectionPlanEntity;

/**
 * @date 2014-8-6
 * @author wangpeng
 * @class description 待巡检系统计划数据解析类
 * 
 */
public class GetInspectionPlanJson {
	
	public static int result = 0; 
	
	public static int total = 0; 
	
	public static List<InspectionPlanEntity> getJson(String response){
		List<InspectionPlanEntity> list = new ArrayList<InspectionPlanEntity>();
		try {
			JSONObject job=new JSONObject(response);
			result=job.getInt("result");
//			total=job.getInt("total");
			total = 1;
			if(result==1){
				if (job != null ) {
					JSONArray jsonArray = job.getJSONArray("details");
					for (int i = 0; i < jsonArray.length(); i++) {
						InspectionPlanEntity entity=new InspectionPlanEntity();
						JSONObject js = jsonArray.getJSONObject(i);
						entity.setTxpId(js.getLong("txpId"));
						entity.setTxpName(js.getString("txpName"));
						entity.setTxpType(js.getInt("txpType"));
						entity.setName(js.getString("name"));
						entity.setNumber(js.getString("number"));
						entity.setPlanDate(js.getString("planDate"));
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
