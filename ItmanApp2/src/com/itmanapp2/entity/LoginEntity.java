package com.itmanapp2.entity;

/**
 * @date 2014-7-15
 * @author wangpeng
 * @class description 登录实体类
 * 
 */
public class LoginEntity {

	/**用户名称*/
	private String auuName;
	
	/**登录账号*/
	private String auuLoginUser;
	
	/**编号*/
	private int auuId;
	
	/**单位编号*/
	private int auiId;
	
	/**单位名称*/
	private String auiName;
	
	/***/
	private int auuAddPerson;
	
	/***/
	private int userid;
	
	/***/
	private String auuMail;
	
	/***/
	private int auuStatus;
	
	/***/
	private String auuAddDate;
	
	/***/
	private String auuPhone;
	
	public String getAuuName() {
		return auuName;
	}
	public void setAuuName(String auuName) {
		this.auuName = auuName;
	}
	public String getAuuLoginUser() {
		return auuLoginUser;
	}
	public void setAuuLoginUser(String auuLoginUser) {
		this.auuLoginUser = auuLoginUser;
	}
	public int getAuuId() {
		return auuId;
	}
	public void setAuuId(int auuId) {
		this.auuId = auuId;
	}
	public int getAuiId() {
		return auiId;
	}
	public void setAuiId(int auiId) {
		this.auiId = auiId;
	}
	public int getAuuAddPerson() {
		return auuAddPerson;
	}
	public void setAuuAddPerson(int auuAddPerson) {
		this.auuAddPerson = auuAddPerson;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getAuuMail() {
		return auuMail;
	}
	public void setAuuMail(String auuMail) {
		this.auuMail = auuMail;
	}
	public int getAuuStatus() {
		return auuStatus;
	}
	public void setAuuStatus(int auuStatus) {
		this.auuStatus = auuStatus;
	}
	public String getAuuAddDate() {
		return auuAddDate;
	}
	public void setAuuAddDate(String auuAddDate) {
		this.auuAddDate = auuAddDate;
	}
	public String getAuuPhone() {
		return auuPhone;
	}
	public void setAuuPhone(String auuPhone) {
		this.auuPhone = auuPhone;
	}
	public void setAuiName(String auiName) {
		this.auiName = auiName;
	}
	public String getAuiName() {
		return auiName;
	}
	@Override
	public String toString() {
		return "LoginEntity [auuName=" + auuName + ", auuLoginUser="
				+ auuLoginUser + ", auuId=" + auuId + ", auiId=" + auiId
				+ ", auiName=" + auiName + ", auuAddPerson=" + auuAddPerson
				+ ", userid=" + userid + ", auuMail=" + auuMail
				+ ", auuStatus=" + auuStatus + ", auuAddDate=" + auuAddDate
				+ ", auuPhone=" + auuPhone + "]";
	}
	
}
