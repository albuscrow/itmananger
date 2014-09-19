package com.itmanapp.json;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.itmanapp.entity.FileEntity;

/**
 * @date 2014-7-17
 * @author wangpeng
 * @class description 整改历史数据解析类
 * 
 */
public class GetModifyInfoJson {
	
	public static int result = 0; 
	
	public static List<FileEntity> getJson(String response){
		List<FileEntity> modifyInfoList = new ArrayList<FileEntity>();
		try {
			JSONObject job=new JSONObject(response);
			result=job.getInt("result");
			if(result==1){
				if (job != null ) {
					JSONArray jsonArray = job.getJSONArray("details");
					for (int i = 0; i < jsonArray.length(); i++) {
						FileEntity entity=new FileEntity();
						JSONObject js = jsonArray.getJSONObject(i);
						entity.setFileType(js.getString("fileType"));
						entity.setTfaAddDate(js.getLong("tfaAddDate"));
						entity.setTfaAddPerson(js.getLong("tfaAddPerson"));
						entity.setTfaFilePath(js.getString("tfaFilePath"));
						entity.setTfaFileType(js.getInt("tfaFileType"));
						entity.setTfaId(js.getLong("tfaId"));
						entity.setTfaName(js.getString("tfaName"));
						entity.setTfaReferId(js.getLong("tfaReferId"));
						entity.setTfaType(js.getInt("tfaType"));
						entity.setAddDate(js.getString("addDate"));
						modifyInfoList.add(entity);
					}
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return modifyInfoList;
	}

}
