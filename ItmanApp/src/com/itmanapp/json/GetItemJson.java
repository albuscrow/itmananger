package com.itmanapp.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.itmanapp.entity.DeviceTypeEntity;
import com.itmanapp.entity.CheckItemEntity;

/**
 * @date 2014-7-28
 * @author wangpeng
 * @class description 我的巡检-巡检项目-数据解析类
 * 
 */
public class GetItemJson {
	
    public static int result = 0; 
    
	public static List<CheckItemEntity> getJson(String response){
		List<CheckItemEntity> list = new ArrayList<CheckItemEntity>();
		try {
			JSONObject job=new JSONObject(response);
			result=job.getInt("result");
			if(result==1){
				if (job != null ) {
//					JSONArray jsonArray = job.getJSONArray("details");
					JsonObject jo = new JsonParser().parse(response).getAsJsonObject();
					list = new Gson().fromJson(jo.get("details"), new TypeToken<List<CheckItemEntity>>(){}.getType());
//					for (int i = 0; i < jsonArray.length(); i++) {
//						CheckItemEntity entity=new CheckItemEntity();
//						JSONObject js = jsonArray.getJSONObject(i);
//						entity.setDetailId(js.getLong("detailId"));
//						entity.setTxpName(js.getString("txpName"));
////						entity.setTxpStatus(js.getInt("txpStatus"));
//						
////						entity.setId(js.getInt("id"));
////						entity.setItemName(js.getString("itemName"));
////						entity.setResult(js.getInt("result")); 
////						entity.setAddtime(js.getString("addtime"));
//						list.add(entity);
//					}
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;
	}

}
