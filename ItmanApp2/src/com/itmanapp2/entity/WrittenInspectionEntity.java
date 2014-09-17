package com.itmanapp2.entity;

/**
 * @date 2014-7-17
 * @author wangpeng
 * @class description 待巡检实体类
 * 
 */
public class WrittenInspectionEntity {
	
	/**巡检编号*/
	private int id; 
	/**描述*/
	private String desp; 
	/**状态*/
	private int status; 
	/***/
	private String userids;
	/**巡检计划名称*/
	private String planName;
	/**巡检类型 0设备1系统*/
	private int type;
	/***/
	private int destId;
	/**目标名称*/
	private String despName;
	/**巡检人*/
	private String userNames;
	/**巡检时间*/
	private String axrAddtime;
	/**巡检设备*/
	private String adName;
	/***/
	private String asName;
	/***/
	private String asCode;
	public String getAdName() {
		return adName;
	}
	public void setAdName(String adName) {
		this.adName = adName;
	}
	public String getAsName() {
		return asName;
	}
	public void setAsName(String asName) {
		this.asName = asName;
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
	public String getDesp() {
		return desp;
	}
	public void setDesp(String desp) {
		this.desp = desp;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getUserids() {
		return userids;
	}
	public void setUserids(String userids) {
		this.userids = userids;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getDestId() {
		return destId;
	}
	public void setDestId(int destId) {
		this.destId = destId;
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
	public void setAxrAddtime(String axrAddtime) {
		this.axrAddtime = axrAddtime;
	}
	public String getAxrAddtime() {
		return axrAddtime;
	}
	@Override
	public String toString() {
		return "WrittenInspectionEntity [id=" + id + ", desp=" + desp
				+ ", status=" + status + ", userids=" + userids + ", planName="
				+ planName + ", type=" + type + ", destId=" + destId
				+ ", despName=" + despName + ", userNames=" + userNames
				+ ", axrAddtime=" + axrAddtime + ", adName=" + adName
				+ ", asName=" + asName + ", asCode=" + asCode + "]";
	}

}
