package com.itmanapp2.json;

import org.json.JSONException;
import org.json.JSONObject;

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
				entity.setAuuId(js.getInt("auuId"));
				entity.setAuiName(js.getString("auiName"));
				entity.setAuuName(js.getString("auuName"));
				entity.setAuuLoginUser(js.getString("auuLoginUser"));
				entity.setAuuMail(js.getString("auuEmail"));
				entity.setAuuPhone(js.getString("auuPhone"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return entity;
	}

}
