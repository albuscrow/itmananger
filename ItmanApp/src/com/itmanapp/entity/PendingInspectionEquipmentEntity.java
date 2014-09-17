package com.itmanapp.entity;

/**
 * @date 2014-7-17
 * @author wangpeng
 * @class description 待巡检设备实体类
 * 
 */
public class PendingInspectionEquipmentEntity {
	
	/**巡检系统id*/
	private int axpId; 
	/**巡检系统计划名称*/
	private String planName; 
	/**状态*/
	private int status; 
	/**设备编码*/
	private String adCode; 
	/**巡检类型*/
	private int type; 
	/***/
	private String userids; 
	/***/
	private String desp; 
	/**设备编码*/
	private String asCode; 
	/**设备id*/
	private int id; 
	/**巡检时间*/
	private String axrAddtime; 
	/***/
	private String despName; 
	/***/
	private String userNames; 
	/**巡检系统*/
	private String asName; 
	/***/
	private int destId; 
	/**巡检目标*/
	private String adName;
	public int getAxpId() {
		return axpId;
	}
	public void setAxpId(int axpId) {
		this.axpId = axpId;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getAdCode() {
		return adCode;
	}
	public void setAdCode(String adCode) {
		this.adCode = adCode;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getUserids() {
		return userids;
	}
	public void setUserids(String userids) {
		this.userids = userids;
	}
	public String getDesp() {
		return desp;
	}
	public void setDesp(String desp) {
		this.desp = desp;
	}
	public String getAsCode() {
		return asCode;
	}
	public void setAsCode(String asCode) {
		this.asCode = asCode;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAxrAddtime() {
		return axrAddtime;
	}
	public void setAxrAddtime(String axrAddtime) {
		this.axrAddtime = axrAddtime;
	}
	public String getDespName() {
		return despName;
	}
	public void setDespName(String despName) {
		this.despName = despName;
	}
	public String getUserNames() {
		return userNames;
	}
	public void setUserNames(String userNames) {
		this.userNames = userNames;
	}
	public String getAsName() {
		return asName;
	}
	public void setAsName(String asName) {
		this.asName = asName;
	}
	public int getDestId() {
		return destId;
	}
	public void setDestId(int destId) {
		this.destId = destId;
	}
	public String getAdName() {
		return adName;
	}
	public void setAdName(String adName) {
		this.adName = adName;
	}
	@Override
	public String toString() {
		return "PendingInspectionEquipmentEntity [axpId=" + axpId
				+ ", planName=" + planName + ", status=" + status + ", adCode="
				+ adCode + ", type=" + type + ", userids=" + userids
				+ ", desp=" + desp + ", asCode=" + asCode + ", id=" + id
				+ ", axrAddtime=" + axrAddtime + ", despName=" + despName
				+ ", userNames=" + userNames + ", asName=" + asName
				+ ", destId=" + destId + ", adName=" + adName + "]";
	} 

}
