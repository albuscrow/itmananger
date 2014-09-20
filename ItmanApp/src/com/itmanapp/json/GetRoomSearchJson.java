package com.itmanapp.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.itmanapp.entity.RoomEntity;

/**
 * @date 2014-7-17
 * @author wangpeng
 * @class description 系统查询结果数据解析类
 * 
 */
public class GetRoomSearchJson {

	public static List<RoomEntity> entity = new ArrayList<RoomEntity>();

	public static int result = 0;
	public static int getJson(String response) {
		try {
			JSONObject job = new JSONObject(response);
			result = job.getInt("result");
			if (result == 1) {
				JSONArray jsonArray = job.getJSONArray("details");
				entity.clear();
				for (int i = 0; i < jsonArray.length(); ++i) {
					JSONObject js = jsonArray.getJSONObject(i);
					RoomEntity temp;
					temp = new RoomEntity();
					temp.setDepName(js.getString("depName"));
					temp.setRoomManager(js.getString("roomManager"));
					temp.setRoomManagerPhone(js.getString("roomManagerPhone"));
					temp.setTmrAddDate(js.getString("tmrAddDate"));
					temp.setTmrAddPerson(js.getLong("tmrAddPerson"));
					temp.setTmrAddress(js.getString("tmrAddress"));
					temp.setTmrAreaId(js.getLong("tmrAreaId"));
					temp.setTmrCode(js.getString("tmrCode"));
					temp.setTmrDepId(js.getLong("tmrDepId"));
					temp.setTmrDescription(js.getString("tmrDescription"));
					temp.setTmrId(js.getLong("tmrId"));
					temp.setTmrManager(js.getLong("tmrManager"));
					temp.setTmrName(js.getString("tmrName"));
					temp.setTmrPosition(js.getString("tmrPosition"));
					temp.setTmrStatus(js.getInt("tmrStatus"));
					temp.setTmrUnitId(js.getLong("tmrUnitId"));
					temp.setTmrUnitManager(js.getLong("tmrUnitManager"));
					temp.setTmrWordsId(js.getLong("tmrWordsId"));
					temp.setUnitManager(js.getString("unitManager"));
					temp.setUnitManagerPhone(js.getString("unitManagerPhone"));
					temp.setUnitName(js.getString("unitName"));
					
					entity.add(temp);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}

}
