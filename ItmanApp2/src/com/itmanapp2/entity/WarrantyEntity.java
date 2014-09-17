package com.itmanapp2.entity;

/**
 * @date 2014-7-25
 * @author wangpeng
 * @class description 用户报修实体类
 * 
 */
public class WarrantyEntity {
	/**报修项目*/
	private String wxName;
	/***/
	private int id;
	/**报修时间*/
	private String addtime;
	/**工单号*/
	private String number;
	/**报修描述*/
	private String desp;
	/***/
	private int status;
	/**确认时间*/
	private String suredate;
	private String asName;
	private String adName;
	
	public String getWxName() {
		return wxName;
	}
	public void setWxName(String wxName) {
		this.wxName = wxName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
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
	public String getSuredate() {
		return suredate;
	}
	public void setSuredate(String suredate) {
		this.suredate = suredate;
	}
	public void setAsName(String asName) {
		this.asName = asName;
	}
	public String getAsName() {
		return asName;
	}
	public void setAdName(String adName) {
		this.adName = adName;
	}
	public String getAdName() {
		return adName;
	}
	@Override
	public String toString() {
		return "WarrantyEntity [wxName=" + wxName + ", id=" + id + ", addtime="
				+ addtime + ", number=" + number + ", desp=" + desp
				+ ", status=" + status + ", suredate=" + suredate + ", asName="
				+ asName + ", adName=" + adName + "]";
	}

}
