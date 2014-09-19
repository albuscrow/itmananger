package com.itmanapp.entity;

/**
 * @date 2014-8-6
 * @author wangpeng
 * @class description 待巡检系统计划类
 * 
 */
public class InspectionPlanEntity {
	
	
	public static final int TYPE_ROOM = 1;
	public static final int TYPE_CABINET = 2;
	public static final int TYPE_DEVICE = 3;
	private static final CharSequence[] TYPE_STR = {"机房巡检", "机柜巡检", "设备巡检"};
	long txpId;
    String txpName;
    int txpType;
    long txpTypeId;
    String txpExecPerson;
    String txpPlanDate;
    long txpAddDate;
    long txpAddPerson;
    int txpStatus;
    String planDate;
    String name;
    String number;
	/**
	 * @return the txpId
	 */
	public long getTxpId() {
		return txpId;
	}
	/**
	 * @param txpId the txpId to set
	 */
	public void setTxpId(long txpId) {
		this.txpId = txpId;
	}
	/**
	 * @return the txpName
	 */
	public String getTxpName() {
		return txpName;
	}
	/**
	 * @param txpName the txpName to set
	 */
	public void setTxpName(String txpName) {
		this.txpName = txpName;
	}
	/**
	 * @return the txpType
	 */
	public int getTxpType() {
		return txpType;
	}
	/**
	 * @param txpType the txpType to set
	 */
	public void setTxpType(int txpType) {
		this.txpType = txpType;
	}
	/**
	 * @return the txpTypeId
	 */
	public long getTxpTypeId() {
		return txpTypeId;
	}
	/**
	 * @param txpTypeId the txpTypeId to set
	 */
	public void setTxpTypeId(long txpTypeId) {
		this.txpTypeId = txpTypeId;
	}
	/**
	 * @return the txpExecPerson
	 */
	public String getTxpExecPerson() {
		return txpExecPerson;
	}
	/**
	 * @param txpExecPerson the txpExecPerson to set
	 */
	public void setTxpExecPerson(String txpExecPerson) {
		this.txpExecPerson = txpExecPerson;
	}
	/**
	 * @return the txpPlanDate
	 */
	public String getTxpPlanDate() {
		return txpPlanDate;
	}
	/**
	 * @param txpPlanDate the txpPlanDate to set
	 */
	public void setTxpPlanDate(String txpPlanDate) {
		this.txpPlanDate = txpPlanDate;
	}
	/**
	 * @return the txpAddDate
	 */
	public long getTxpAddDate() {
		return txpAddDate;
	}
	/**
	 * @param txpAddDate the txpAddDate to set
	 */
	public void setTxpAddDate(long txpAddDate) {
		this.txpAddDate = txpAddDate;
	}
	/**
	 * @return the txpAddPerson
	 */
	public long getTxpAddPerson() {
		return txpAddPerson;
	}
	/**
	 * @param txpAddPerson the txpAddPerson to set
	 */
	public void setTxpAddPerson(long txpAddPerson) {
		this.txpAddPerson = txpAddPerson;
	}
	/**
	 * @return the txpStatus
	 */
	public int getTxpStatus() {
		return txpStatus;
	}
	/**
	 * @param txpStatus the txpStatus to set
	 */
	public void setTxpStatus(int txpStatus) {
		this.txpStatus = txpStatus;
	}
	/**
	 * @return the planDate
	 */
	public String getPlanDate() {
		return "巡检时间" + planDate;
	}
	/**
	 * @param planDate the planDate to set
	 */
	public void setPlanDate(String planDate) {
		this.planDate = planDate;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}
	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	public CharSequence getTxpTypeStr() {
		return TYPE_STR[txpType - 1];
	}
    
    
//	
//	/**巡检系统计划id*/
//	private int axpId ; 
//	/**巡检系统计划名称*/
//	private String axpName ; 
//	/**巡检系统名称*/
//	private String asName ; 
//	/**巡检系统编码*/
//	private String asCode ;
//	
//	public int getAxpId() {
//		return axpId;
//	}
//	public void setAxpId(int axpId) {
//		this.axpId = axpId;
//	}
//	public String getAxpName() {
//		return axpName;
//	}
//	public void setAxpName(String axpName) {
//		this.axpName = axpName;
//	}
//	public String getAsName() {
//		return asName;
//	}
//	public void setAsName(String asName) {
//		this.asName = asName;
//	}
//	public String getAsCode() {
//		return asCode;
//	}
//	public void setAsCode(String asCode) {
//		this.asCode = asCode;
//	}
//	@Override
//	public String toString() {
//		return "InspectionSystemPlanEntity [axpId=" + axpId + ", axpName="
//				+ axpName + ", asName=" + asName + ", asCode=" + asCode + "]";
//	} 

}
