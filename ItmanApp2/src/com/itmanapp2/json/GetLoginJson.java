package com.itmanapp2.json;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.SharedPreferences;

import com.itmanapp2.entity.LoginEntity;

/**
 * @date 2014-7-15
 * @author wangpeng
 * @class description 登录数据解析类
 * 
 */
public class GetLoginJson {
	
	public static int result = 0; 
	
	public static LoginEntity getJson(String response){
		LoginEntity entity = null; 
		try {
			JSONObject job=new JSONObject(response);
			result=job.getInt("result");
			if(result==1){
				JSONObject js = job.getJSONObject("details");
				entity=new LoginEntity();
			
				entity.setTuiId(js.getLong("tuiId"));
				entity.setTuiName(js.getString("tuiName"));
				entity.setTuiAccount(js.getString("tuiAccount"));
				entity.setTuiEmail(js.getString("tuiEmail"));
				entity.setTuiPassword(js.getString("tuiPassword"));
				entity.setTuiPhone(js.getString("tuiPhone"));
				
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return entity;
	}

}
