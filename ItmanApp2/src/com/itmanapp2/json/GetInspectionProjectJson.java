package com.itmanapp2.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.itmanapp2.entity.InspectionProjectEntity;

/**
 * @date 2014-7-28
 * @author wangpeng
 * @class description 我的巡检-巡检项目-数据解析类
 * 
 */
public class GetInspectionProjectJson {
	
    public static int result = 0; 
    
	public static List<InspectionProjectEntity> getJson(String response){
		List<InspectionProjectEntity> list = new ArrayList<InspectionProjectEntity>();
		try {
			JSONObject job=new JSONObject(response);
			result=job.getInt("result");
			if(result==1){
				if (job != null ) {
					JSONArray jsonArray = job.getJSONArray("details");
					for (int i = 0; i < jsonArray.length(); i++) {
						InspectionProjectEntity entity=new InspectionProjectEntity();
						JSONObject js = jsonArray.getJSONObject(i);
						entity.setId(js.getInt("id"));
						entity.setItemName(js.getString("itemName"));
						entity.setResult(js.getBoolean("result"));
						entity.setAddtime(js.getString("addtime"));
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
