package com.itmanapp2.json;

import org.json.JSONException;
import org.json.JSONObject;

import com.itmanapp2.entity.EquipmentEntity;

/**
 * @date 2014-7-15
 * @author wangpeng
 * @class description 设备数据解析类
 * 
 */
public class GetEptDetailJson {
	
	public static EquipmentEntity entity=null;
	
	public static int getJson(String response){
		int result = 0; 
		try {
			JSONObject job=new JSONObject(response);
			result=job.getInt("result");
			if(result==1){
				JSONObject js = job.getJSONObject("details");
				entity=new EquipmentEntity();
				entity.setAdBuyDate(js.getString("adBuyDate"));
				entity.setAdCode(js.getString("adCode"));
				entity.setAdDesp(js.getString("adDesp"));
				entity.setAdId(js.getInt("adId"));
				entity.setAdPosition(js.getString("adPosition"));
				entity.setAdWarranty(js.getString("adWarranty"));
				entity.setModelName(js.getString("modelName"));
				entity.setSupplyId(js.getString("supplyId"));
				entity.setSupplyName(js.getString("supplyName"));
				entity.setSystemId(js.getString("systemId"));
				entity.setAsName(js.getString("asName"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}

}
