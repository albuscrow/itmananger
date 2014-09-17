package com.itmanapp.entity;

/**
 * @date 2014-8-6
 * @author wangpeng
 * @class description 待巡检系统计划类
 * 
 */
public class InspectionSystemPlanEntity {
	
	/**巡检系统计划id*/
	private int axpId ; 
	/**巡检系统计划名称*/
	private String axpName ; 
	/**巡检系统名称*/
	private String asName ; 
	/**巡检系统编码*/
	private String asCode ;
	
	public int getAxpId() {
		return axpId;
	}
	public void setAxpId(int axpId) {
		this.axpId = axpId;
	}
	public String getAxpName() {
		return axpName;
	}
	public void setAxpName(String axpName) {
		this.axpName = axpName;
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
	@Override
	public String toString() {
		return "InspectionSystemPlanEntity [axpId=" + axpId + ", axpName="
				+ axpName + ", asName=" + asName + ", asCode=" + asCode + "]";
	} 

}
