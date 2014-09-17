package com.itmanapp2.entity;

/**
 * @date 2014-7-18
 * @author wangpeng
 * @class description 报修类型实体类
 * 
 */
public class DeviceTypeEntity {

	//时间
	private String advAddDate;
	
	//报修类型名称
	private String advItemName;
	
	//报修类型id
	private int advDeviceTypeid;
	
	//id
	private int advId;

	public String getAdvAddDate() {
		return advAddDate;
	}

	public void setAdvAddDate(String advAddDate) {
		this.advAddDate = advAddDate;
	}

	public String getAdvItemName() {
		return advItemName;
	}

	public void setAdvItemName(String advItemName) {
		this.advItemName = advItemName;
	}

	public int getAdvDeviceTypeid() {
		return advDeviceTypeid;
	}

	public void setAdvDeviceTypeid(int advDeviceTypeid) {
		this.advDeviceTypeid = advDeviceTypeid;
	}

	public int getAdvId() {
		return advId;
	}

	public void setAdvId(int advId) {
		this.advId = advId;
	}

	@Override
	public String toString() {
		return "DeviceTypeEntity [advAddDate=" + advAddDate + ", advItemName="
				+ advItemName + ", advDeviceTypeid=" + advDeviceTypeid
				+ ", advId=" + advId + "]";
	}
	
}
