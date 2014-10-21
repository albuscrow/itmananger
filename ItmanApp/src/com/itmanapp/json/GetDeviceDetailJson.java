package com.itmanapp.json;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.itmanapp.entity.EquipmentEntity;
import com.itmanapp.entity.LoginEntity;
import com.itmanapp.entity.RelatedDeviceEntity;

/**
 * @date 2014-7-15
 * @author wangpeng
 * @class description 设备数据详细解析类
 * 
 */
public class GetDeviceDetailJson {
	
	public static RelatedDeviceEntity entity=null;
	
	public static int getJson(String response){
		int result = 0; 
		try {
			JSONObject job=new JSONObject(response);
			result=job.getInt("result");
			if(result==1){
				JsonObject jo = new JsonParser().parse(response).getAsJsonObject();
				entity = new Gson().fromJson(jo.get("details"), RelatedDeviceEntity.class);
//				entity=new RelatedDeviceEntity();
//				entity.setAdBuyDate(js.getString("adBuyDate"));
//				entity.setAdCode(js.getString("adCode"));
//				entity.setAdDesp(js.getString("adDesp"));
//				entity.setAdId(js.getInt("adId"));
//				entity.setAdPosition(js.getString("adPosition"));
//				entity.setAdName(js.getString("adName"));
//				entity.setModelName(js.getString("modelName"));
//				entity.setSupplyName(js.getString("supplyName"));
//				entity.setAsName(js.getString("asName"));
//				entity.setTdcName(js.getString("tdcName"));
//				entity.setAsName(js.getString("asName"));
//				entity.setTmrName(js.getString("tmrName"));
//				entity.setCabinetId(js.getLong("cabinetId"));
//				entity.setCabinetId(js.getLong("cabinetId"));
//				entity.setCabinetId(js.getLong("cabinetId"));
//				entity.setCabinetId(js.getLong("cabinetId"));
//				entity.setCabinetId(js.getLong("cabinetId"));
//				entity.setCabinetId(js.getLong("cabinetId"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}

}
