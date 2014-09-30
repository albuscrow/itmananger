package com.itmanapp2.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.itmanapp2.entity.WarrantyEntity;

/**
 * @date 2014-7-17
 * @author wangpeng
 * @class description 待巡检数据解析类
 * 
 */
public class GetWarrantyJson {
	
	public static int result = 0; 
	
	public static int total = 0; 
	
	public static List<WarrantyEntity> getJson(String response){
		List<WarrantyEntity> warrantyList = new ArrayList<WarrantyEntity>();
		try {
			JSONObject job=new JSONObject(response);
			result=job.getInt("result");
			total=job.getInt("total");
			if(result==1){
				if (job != null ) {
					JSONArray jsonArray = job.getJSONArray("details");
					for (int i = 0; i < jsonArray.length(); i++) {
						WarrantyEntity entity=new WarrantyEntity();
						JSONObject js = jsonArray.getJSONObject(i);
						entity.setAddtime(js.getString("addtime"));
						entity.setDesp(js.getString("desp"));
						entity.setId(js.getInt("id"));
						entity.setNumber(js.getString("number"));
						entity.setStatus(js.getInt("status"));
						entity.setSuredate(js.getString("suredate"));
//						entity.setWxName(js.getString("wxName"));
//						entity.setAsName(js.getString("asName"));
						entity.setAdName(js.getString("adName"));
						entity.setItemNames(js.getString("itemNames"));
						entity.setTcName(js.getString("tcName"));
						warrantyList.add(entity);
					}
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return warrantyList;
	}

}
