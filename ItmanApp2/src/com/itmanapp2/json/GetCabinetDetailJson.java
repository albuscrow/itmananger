package com.itmanapp2.json;

import org.json.JSONException;
import org.json.JSONObject;

import com.itmanapp2.entity.CabinetEntity;

/**
 * @date 2014-7-15
 * @author wangpeng
 * @class description 设备数据详细解析类
 * 
 */
public class GetCabinetDetailJson {
	
	public static CabinetEntity entity=null;
	
	public static int getJson(String response){
		int result = 0; 
		try {
			JSONObject job=new JSONObject(response);
			result=job.getInt("result");
			if(result==1){
				JSONObject js = job.getJSONObject("details");
				entity=new CabinetEntity();
				
				entity.setTcName(js.getString("tcName"));
				entity.setTcCode(js.getString("tcCode"));
				entity.setTcPosition(js.getString("tcPosition"));
				entity.setTcLayDate(js.getString("tcLayDate"));
				entity.setTcId(js.getLong("tcId"));
				entity.setRoomName(js.getString("roomName"));
				entity.setUnitName(js.getString("unitName"));
				entity.setDepName(js.getString("depName"));
				entity.setTcDescription(js.getString("tcDescription"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}

}
