package com.itmanapp.json;

import org.json.JSONException;
import org.json.JSONObject;

import com.itmanapp.entity.SystemSearchEntity;

/**
 * @date 2014-7-17
 * @author wangpeng
 * @class description 系统查询结果数据解析类
 * 
 */
public class GetSystemSearchJson {

	public static SystemSearchEntity entity = null;

	public static int getJson(String response) {
		int result = 0;
		try {
			JSONObject job = new JSONObject(response);
			result = job.getInt("result");
			if (result == 1) {
				JSONObject js = job.getJSONObject("details");
				entity = new SystemSearchEntity();
				entity.setAsAddress(js.getString("asAddress"));
				entity.setAsChargePerson(js.getString("asChargePerson"));
				entity.setAsChargePhone(js.getString("asChargePhone"));
				entity.setAsCode(js.getString("asCode"));
				entity.setAsStatus(js.getInt("asStatus"));
				entity.setAuiName(js.getString("auiName"));
				entity.setAsName(js.getString("asName"));
				entity.setSwName(js.getString("swName"));
				entity.setSystemPerspm(js.getString("systemPerspm"));
				entity.setSystemPhone(js.getString("systemPhone"));
				entity.setAsId(js.getInt("asId"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}

}
