package com.itmanapp.json;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.SharedPreferences;

import com.itmanapp.entity.LoginEntity;

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
				entity.setAccount(js.getString("account"));
				entity.setEmail(js.getString("email"));
				entity.setId(js.getInt("id"));
				entity.setName(js.getString("name"));
				entity.setTitle(js.getString("title"));
				
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return entity;
	}

}
