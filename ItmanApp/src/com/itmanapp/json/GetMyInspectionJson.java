package com.itmanapp.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.itmanapp.entity.PendingInspectionEquipmentEntity;

/**
 * @date 2014-7-17
 * @author wangpeng
 * @class description 我的巡检数据解析类
 * 
 */
public class GetMyInspectionJson {
	
	public static int result = 0; 
	
	public static int total = 0; 
	
	public static List<PendingInspectionEquipmentEntity> getJson(String response){
		List<PendingInspectionEquipmentEntity> list = new ArrayList<PendingInspectionEquipmentEntity>();
		try {
			JSONObject job=new JSONObject(response);
			result=job.getInt("result");
			total=job.getInt("total");
			if(result==1){
				if (job != null ) {
					JSONArray jsonArray = job.getJSONArray("details");
					for (int i = 0; i < jsonArray.length(); i++) {
						PendingInspectionEquipmentEntity entity=new PendingInspectionEquipmentEntity();
						JSONObject js = jsonArray.getJSONObject(i);
						entity.setDesp(js.getString("desp"));
						entity.setDestId(js.getInt("destId"));
						entity.setId(js.getInt("id"));
						entity.setPlanName(js.getString("planName"));
						entity.setStatus(js.getInt("status"));
						entity.setType(js.getInt("type"));
						entity.setUserids(js.getString("userids"));
						entity.setUserNames(js.getString("userNames"));
						entity.setAxrAddtime(js.getString("axrAddtime"));
					    entity.setAdName(js.getString("adName"));
					    entity.setAsCode(js.getString("asCode"));
					    entity.setAsName(js.getString("asName"));
					    entity.setAdCode(js.getString("adCode"));
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
