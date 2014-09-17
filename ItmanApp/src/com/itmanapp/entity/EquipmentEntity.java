package com.itmanapp.entity;

/**
 * @date 2014-7-17
 * @author wangpeng
 * @class description 设备实体类
 * 
 */
public class EquipmentEntity {
	/***/
	private String adBuyDate;
	/**设备类型*/
	private String modelName;
	/***/
	private String adWarranty;
	/**设备id*/
	private int adId;
	/**设备配置*/
	private String adPosition;
	/**设备配置*/
	private String adDesp;
	/***/
	private String systemId;
	/**设备编号*/
	private String adCode;
	/**厂商名称*/
	private String supplyName;
	/***/
	private String supplyId;
	/**系统名称*/
	private String asName;
	private String adName;
	private int typeId;
	public String getAdBuyDate() {
		return adBuyDate;
	}
	public void setAdBuyDate(String adBuyDate) {
		this.adBuyDate = adBuyDate;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getAdWarranty() {
		return adWarranty;
	}
	public void setAdWarranty(String adWarranty) {
		this.adWarranty = adWarranty;
	}
	public String getAdPosition() {
		return adPosition;
	}
	public void setAdPosition(String adPosition) {
		this.adPosition = adPosition;
	}
	public String getAdDesp() {
		return adDesp;
	}
	public void setAdDesp(String adDesp) {
		this.adDesp = adDesp;
	}
	public String getSystemId() {
		return systemId;
	}
	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}
	public String getAdCode() {
		return adCode;
	}
	public void setAdCode(String adCode) {
		this.adCode = adCode;
	}
	public String getSupplyName() {
		return supplyName;
	}
	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}
	public String getSupplyId() {
		return supplyId;
	}
	public void setSupplyId(String supplyId) {
		this.supplyId = supplyId;
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
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public int getTypeId() {
		return typeId;
	}
	public int getAdId() {
		return adId;
	}
	public void setAdId(int adId) {
		this.adId = adId;
	}
	@Override
	public String toString() {
		return "EquipmentEntity [adBuyDate=" + adBuyDate + ", modelName="
				+ modelName + ", adWarranty=" + adWarranty + ", adId=" + adId
				+ ", adPosition=" + adPosition + ", adDesp=" + adDesp
				+ ", systemId=" + systemId + ", adCode=" + adCode
				+ ", supplyName=" + supplyName + ", supplyId=" + supplyId
				+ ", asName=" + asName + ", adName=" + adName + ", typeId="
				+ typeId + "]";
	}
	 

}
