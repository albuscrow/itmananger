package com.itmanapp2.entity;

/**
 * @date 2014-7-18
 * @author wangpeng
 * @class description 报修类型实体类
 * 
 */
public class DeviceTypeEntity {
	long twcId;
    String twcName;
    long twcDtypeId;
    String twcDescription;
    long twcAddDate;
    long twcAddPerson;
    int twcStatus;
	/**
	 * @return the twcId
	 */
	public long getTwcId() {
		return twcId;
	}
	/**
	 * @param twcId the twcId to set
	 */
	public void setTwcId(long twcId) {
		this.twcId = twcId;
	}
	/**
	 * @return the twcName
	 */
	public String getTwcName() {
		return twcName;
	}
	/**
	 * @param twcName the twcName to set
	 */
	public void setTwcName(String twcName) {
		this.twcName = twcName;
	}
	/**
	 * @return the twcDtypeId
	 */
	public long getTwcDtypeId() {
		return twcDtypeId;
	}
	/**
	 * @param twcDtypeId the twcDtypeId to set
	 */
	public void setTwcDtypeId(long twcDtypeId) {
		this.twcDtypeId = twcDtypeId;
	}
	/**
	 * @return the twcDescription
	 */
	public String getTwcDescription() {
		return twcDescription;
	}
	/**
	 * @param twcDescription the twcDescription to set
	 */
	public void setTwcDescription(String twcDescription) {
		this.twcDescription = twcDescription;
	}
	/**
	 * @return the twcAddDate
	 */
	public long getTwcAddDate() {
		return twcAddDate;
	}
	/**
	 * @param twcAddDate the twcAddDate to set
	 */
	public void setTwcAddDate(long twcAddDate) {
		this.twcAddDate = twcAddDate;
	}
	/**
	 * @return the twcAddPerson
	 */
	public long getTwcAddPerson() {
		return twcAddPerson;
	}
	/**
	 * @param twcAddPerson the twcAddPerson to set
	 */
	public void setTwcAddPerson(long twcAddPerson) {
		this.twcAddPerson = twcAddPerson;
	}
	/**
	 * @return the twcStatus
	 */
	public int getTwcStatus() {
		return twcStatus;
	}
	/**
	 * @param twcStatus the twcStatus to set
	 */
	public void setTwcStatus(int twcStatus) {
		this.twcStatus = twcStatus;
	}

//	//时间
//	private String advAddDate;
//	
//	//报修类型名称
//	private String advItemName;
//	
//	//报修类型id
//	private int advDeviceTypeid;
//	
//	//id
//	private int advId;
//
//	public String getAdvAddDate() {
//		return advAddDate;
//	}
//
//	public void setAdvAddDate(String advAddDate) {
//		this.advAddDate = advAddDate;
//	}
//
//	public String getAdvItemName() {
//		return advItemName;
//	}
//
//	public void setAdvItemName(String advItemName) {
//		this.advItemName = advItemName;
//	}
//
//	public int getAdvDeviceTypeid() {
//		return advDeviceTypeid;
//	}
//
//	public void setAdvDeviceTypeid(int advDeviceTypeid) {
//		this.advDeviceTypeid = advDeviceTypeid;
//	}
//
//	public int getAdvId() {
//		return advId;
//	}
//
//	public void setAdvId(int advId) {
//		this.advId = advId;
//	}
//
//	@Override
//	public String toString() {
//		return "DeviceTypeEntity [advAddDate=" + advAddDate + ", advItemName="
//				+ advItemName + ", advDeviceTypeid=" + advDeviceTypeid
//				+ ", advId=" + advId + "]";
//	}
	
}
