package com.itmanapp2.entity;

import java.io.Serializable;

/**
 * @date 2014-7-17
 * @author wangpeng
 * @class description 系统查询结果实体类
 * 
 */
public class SystemSearchEntity implements Serializable {
	
	/**序列化*/
	private static final long serialVersionUID = 1L;
	
	/**主键id*/
	private int asId;
	
	/**管理员联系电话*/
	private String asChargePhone;
	
	/**系统类型*/
	private String swName;
	
	/**系统名称*/
	private String asName;
	
	/**单位名称*/
	private String auiName;
	
	/**客户管理员联系电话*/
	private String systemPhone;
	
	/**所在地址*/
	private String asAddress;
	
	/**客户管理员*/
	private String systemPerspm;
	
	/**系统管理员*/
	private String asChargePerson;
	
	/**状态1：正常,2：告警,3：异常*/
	private int asStatus;
	
	/**系统编码*/
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setAsName(String asName) {
		this.asName = asName;
	}
	public String getAsName() {
		return asName;
	}
	@Override
	public String toString() {
		return "SystemSearchEntity [asId=" + asId + ", asChargePhone="
				+ asChargePhone + ", swName=" + swName + ", asName=" + asName
				+ ", auiName=" + auiName + ", systemPhone=" + systemPhone
				+ ", asAddress=" + asAddress + ", systemPerspm=" + systemPerspm
				+ ", asChargePerson=" + asChargePerson + ", asStatus="
				+ asStatus + ", asCode=" + asCode + "]";
	}
	
}
