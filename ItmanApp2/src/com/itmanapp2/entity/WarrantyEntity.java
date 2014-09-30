package com.itmanapp2.entity;

/**
 * @date 2014-7-25
 * @author wangpeng
 * @class description 用户报修实体类
 * 
 */
public class WarrantyEntity {
	
	
	long id;
    String number;
    String adName;
    String desp;
    String addtime;
    String suredate;
    int status;
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
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
	/**
	 * @return the adName
	 */
	public String getAdName() {
		return adName;
	}
	/**
	 * @param adName the adName to set
	 */
	public void setAdName(String adName) {
		this.adName = adName;
	}
	/**
	 * @return the desp
	 */
	public String getDesp() {
		return desp;
	}
	/**
	 * @param desp the desp to set
	 */
	public void setDesp(String desp) {
		this.desp = desp;
	}
	/**
	 * @return the addtime
	 */
	public String getAddtime() {
		return addtime;
	}
	/**
	 * @param addtime the addtime to set
	 */
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	/**
	 * @return the suredate
	 */
	public String getSuredate() {
		return suredate;
	}
	/**
	 * @param suredate the suredate to set
	 */
	public void setSuredate(String suredate) {
		this.suredate = suredate;
	}
	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	
	String tcName;
    String itemIds;
    String itemNames;
	/**
	 * @return the tcName
	 */
	public String getTcName() {
		return tcName;
	}
	/**
	 * @param tcName the tcName to set
	 */
	public void setTcName(String tcName) {
		this.tcName = tcName;
	}
	/**
	 * @return the itemIds
	 */
	public String getItemIds() {
		return itemIds;
	}
	/**
	 * @param itemIds the itemIds to set
	 */
	public void setItemIds(String itemIds) {
		this.itemIds = itemIds;
	}
	/**
	 * @return the itemNames
	 */
	public String getItemNames() {
		return itemNames;
	}
	/**
	 * @param itemNames the itemNames to set
	 */
	public void setItemNames(String itemNames) {
		this.itemNames = itemNames;
	}
    
//	/**报修项目*/
//	private String wxName;
//	/***/
//	private int id;
//	/**报修时间*/
//	private String addtime;
//	/**工单号*/
//	private String number;
//	/**报修描述*/
//	private String desp;
//	/***/
//	private int status;
//	/**确认时间*/
//	private String suredate;
//	private String asName;
//	private String adName;
//	
//	public String getWxName() {
//		return wxName;
//	}
//	public void setWxName(String wxName) {
//		this.wxName = wxName;
//	}
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
//	public String getAddtime() {
//		return addtime;
//	}
//	public void setAddtime(String addtime) {
//		this.addtime = addtime;
//	}
//	public String getNumber() {
//		return number;
//	}
//	public void setNumber(String number) {
//		this.number = number;
//	}
//	public String getDesp() {
//		return desp;
//	}
//	public void setDesp(String desp) {
//		this.desp = desp;
//	}
//	public int getStatus() {
//		return status;
//	}
//	public void setStatus(int status) {
//		this.status = status;
//	}
//	public String getSuredate() {
//		return suredate;
//	}
//	public void setSuredate(String suredate) {
//		this.suredate = suredate;
//	}
//	public void setAsName(String asName) {
//		this.asName = asName;
//	}
//	public String getAsName() {
//		return asName;
//	}
//	public void setAdName(String adName) {
//		this.adName = adName;
//	}
//	public String getAdName() {
//		return adName;
//	}
//	@Override
//	public String toString() {
//		return "WarrantyEntity [wxName=" + wxName + ", id=" + id + ", addtime="
//				+ addtime + ", number=" + number + ", desp=" + desp
//				+ ", status=" + status + ", suredate=" + suredate + ", asName="
//				+ asName + ", adName=" + adName + "]";
//	}

}
