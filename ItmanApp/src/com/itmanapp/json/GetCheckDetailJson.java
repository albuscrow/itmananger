package com.itmanapp.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.itmanapp.entity.DeviceNeedToCheck;

/**
 * @date 2014-7-17
 * @author wangpeng
 * @class description 待巡检设备数据解析类
 * 
 */
public class GetCheckDetailJson {
	
	public static int result = 0; 
	
	public static DeviceNeedToCheck entity = new DeviceNeedToCheck();
	
	public static int getJson(String response){
		try {
			JSONObject job=new JSONObject(response);
			result=job.getInt("result");
			if(result==1){
				if (job != null ) {
					JSONObject js = job.getJSONObject("details");
					entity.setTxrId(js.getLong("txrId"));
					entity.setTdName(js.getString("tdName"));
					entity.setTdCode(js.getString("tdCode"));
					try {
						entity.setTxrStatus(js.getInt("txrStatus"));
					} catch (Exception e) {
						e.printStackTrace();
					}
					entity.setTxpName(js.getString("txpName"));
					entity.setPlanDate(js.getString("planDate"));
					entity.setTmrName(js.getString("tmrName"));
					entity.setTdPosition(js.getString("tdPosition"));
					entity.setTxcName(js.getString("txcName"));
					entity.setTxpNames(js.getString("txpNames"));
					entity.setTcName(js.getString("tcName"));
					entity.setTxcId(js.getLong("txcId"));
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return result;
	}

}
