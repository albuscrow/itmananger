package com.itmanapp.entity;

/**
 * @date 2014-7-18
 * @author wangpeng
 * @class description 填写报修-所属系统-实体类
 * 
 */
public class FillRepairEntity {

	private String adName;
	// 15990189023
	private String asChargePhone;
	// 综合布线系统
	private String swName;
	// 1
	private int asId;
	// 腾讯华舰
	private String auiName;
	// 15990189045
	private String systemPhone;
	// 杭州西湖区
	private String asAddress;
	// 资产管理系统
	private String asName;
	// 张三
	private String systemPerspm;
	// 李四
	private String asChargePerson;
	// 1
	private int asStatus;
	// 102
	private String asCode;
	
	public String getAsChargePhone() {
		return asChargePhone;
	}

	public void setAsChargePhone(String asChargePhone) {
		this.asChargePhone = asChargePhone;
	}

	public String getSwName() {
		return swName;
	}

	public void setSwName(String swName) {
		this.swName = swName;
	}

	public int getAsId() {
		return asId;
	}

	public void setAsId(int asId) {
		this.asId = asId;
	}

	public String getAuiName() {
		return auiName;
	}

	public void setAuiName(String auiName) {
		this.auiName = auiName;
	}

	public String getSystemPhone() {
		return systemPhone;
	}

	public void setSystemPhone(String systemPhone) {
		this.systemPhone = systemPhone;
	}

	public String getAsAddress() {
		return asAddress;
	}

	public void setAsAddress(String asAddress) {
		this.asAddress = asAddress;
	}

	public String getAsName() {
		return asName;
	}

	public void setAsName(String asName) {
		this.asName = asName;
	}

	public String getSystemPerspm() {
		return systemPerspm;
	}

	public void setSystemPerspm(String systemPerspm) {
		this.systemPerspm = systemPerspm;
	}

	public String getAsChargePerson() {
		return asChargePerson;
	}

	public void setAsChargePerson(String asChargePerson) {
		this.asChargePerson = asChargePerson;
	}

	public int getAsStatus() {
		return asStatus;
	}

	public void setAsStatus(int asStatus) {
		this.asStatus = asStatus;
	}

	public String getAsCode() {
		return asCode;
	}

	public void setAsCode(String asCode) {
		this.asCode = asCode;
	}

	public void setAdName(String adName) {
		this.adName = adName;
	}

	public String getAdName() {
		return adName;
	}

	@Override
	public String toString() {
		return "FillRepairEntity [adName=" + adName + ", asChargePhone="
				+ asChargePhone + ", swName=" + swName + ", asId=" + asId
				+ ", auiName=" + auiName + ", systemPhone=" + systemPhone
				+ ", asAddress=" + asAddress + ", asName=" + asName
				+ ", systemPerspm=" + systemPerspm + ", asChargePerson="
				+ asChargePerson + ", asStatus=" + asStatus + ", asCode="
				+ asCode + "]";
	}

}
