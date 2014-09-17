package com.itmanapp.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.itmanapp.entity.EquipmentEntity;
import com.itmanapp.entity.FillRepairEntity;
import com.itmanapp.entity.LoginEntity;

/**
 * @date 2014-7-15
 * @author wangpeng
 * @class description 设备数据解析类
 * 
 */
public class GetEptJson {
	
    public static int result = 0; 
    
	public static List<EquipmentEntity> getJson(String response){
		List<EquipmentEntity> eptList = new ArrayList<EquipmentEntity>();
		try {
			JSONObject job=new JSONObject(response);
			result=job.getInt("result");
			if(result==1){
				if (job != null ) {
					JSONArray jsonArray = job.getJSONArray("details");
					for (int i = 0; i < jsonArray.length(); i++) {
						EquipmentEntity entity=new EquipmentEntity();
						JSONObject js = jsonArray.getJSONObject(i);
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
						entity.setAdName(js.getString("adName"));
						entity.setTypeId(js.getInt("typeId"));
						eptList.add(entity);
					}
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return eptList;
	}

}
