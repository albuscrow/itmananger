package com.itmanapp.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.itmanapp.entity.FillRepairEntity;


/**
 * @date 2014-7-15
 * @author wangpeng
 * @class description 所属系统数据解析类
 * 
 */
public class GetFillRepairJson {
	
    public static int result = 0; 
    
	public static List<FillRepairEntity> getJson(String response){
		List<FillRepairEntity> fillRepairList = new ArrayList<FillRepairEntity>();
		try {
			JSONObject job=new JSONObject(response);
			result=job.getInt("result");
			if(result==1){
				if (job != null ) {
					JSONArray jsonArray = job.getJSONArray("details");
					for (int i = 0; i < jsonArray.length(); i++) {
						FillRepairEntity entity=new FillRepairEntity();
						JSONObject js = jsonArray.getJSONObject(i);
						entity.setAsAddress(js.getString("asAddress"));
						entity.setAsChargePerson(js.getString("asChargePhone"));
						entity.setAsChargePhone(js.getString("asChargePerson"));
						entity.setAsCode(js.getString("asCode"));
						entity.setAsId(js.getInt("asId"));
						entity.setAsName(js.getString("asName"));
						entity.setAsStatus(js.getInt("asStatus"));
						entity.setAuiName(js.getString("auiName"));
						entity.setSwName(js.getString("swName"));
						entity.setSystemPerspm(js.getString("systemPerspm"));
						entity.setSystemPhone(js.getString("systemPhone"));
						fillRepairList.add(entity);
					}
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return fillRepairList;
	}


}
