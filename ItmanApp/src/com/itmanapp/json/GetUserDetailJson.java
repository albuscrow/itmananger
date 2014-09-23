package com.itmanapp.json;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.SharedPreferences;

import com.itmanapp.entity.LoginEntity;
import com.itmanapp.entity.UserDetailEntity;

/**
 * @date 2014-7-15
 * @author wangpeng
 * @class description 登录数据解析类
 * 
 */
public class GetUserDetailJson {
	
	public static int result = 0; 
	
	public static UserDetailEntity getJson(String response){
		UserDetailEntity entity = null; 
		try {
			JSONObject job=new JSONObject(response);
			result=job.getInt("result");
			if(result==1){
				JSONObject js = job.getJSONObject("details");
				entity=new UserDetailEntity();
				
				entity.setTuiName(js.getString("tuiName"));
				entity.setTuiEmail(js.getString("tuiEmail"));
				entity.setTuiAccount(js.getString("tuiAccount"));
				
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return entity;
	}

}
