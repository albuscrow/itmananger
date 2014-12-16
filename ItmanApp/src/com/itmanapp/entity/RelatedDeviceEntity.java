package com.itmanapp.entity;

import java.io.Serializable;

/**
 * @date 2014-7-17
 * @author wangpeng
 * @class description 关联设备实体类
 * 
 */
public class RelatedDeviceEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3797700006446794790L;
	long adId;
    String adCode;
    long typeId;
    String adName;
    String adDesp;
    String adPosition;
    String adBuyDate;
    long adWarranty;
    long cabinetId;
    String asName;
    long supplyId;
    String supplyName;
    String modelName;
    String tmrName;
    String tdcName;
    
    long unitId;
	String roomName;
    long roomId;
    long depId;
    String depName;
    
 
	/**
	 * @return the unitId
	 */
	public long getUnitId() {
		return unitId;
	}
	/**
	 * @param unitId the unitId to set
	 */
	public void setUnitId(long unitId) {
		this.unitId = unitId;
	}
	/**
	 * @return the roomName
	 */
	public String getRoomName() {
		return roomName;
	}
	/**
	 * @param roomName the roomName to set
	 */
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	/**
	 * @return the roomId
	 */
	public long getRoomId() {
		return roomId;
	}
	/**
	 * @param roomId the roomId to set
	 */
	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}
	/**
	 * @return the depId
	 */
	public long getDepId() {
		return depId;
	}
	/**
	 * @param depId the depId to set
	 */
	public void setDepId(long depId) {
		this.depId = depId;
	}
	/**
	 * @return the depName
	 */
	public String getDepName() {
		return depName;
	}
	/**
	 * @param depName the depName to set
	 */
	public void setDepName(String depName) {
		this.depName = depName;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * @return the adId
	 */
	public long getAdId() {
		return adId;
	}
	/**
	 * @param adId the adId to set
	 */
	public void setAdId(long adId) {
		this.adId = adId;
	}
	/**
	 * @return the adCode
	 */
	public String getAdCode() {
		return adCode;
	}
	/**
	 * @param adCode the adCode to set
	 */
	public void setAdCode(String adCode) {
		this.adCode = adCode;
	}
	/**
	 * @return the typeId
	 */
	public long getTypeId() {
		return typeId;
	}
	/**
	 * @param typeId the typeId to set
	 */
	public void setTypeId(long typeId) {
		this.typeId = typeId;
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
	 * @return the adDesp
	 */
	public String getAdDesp() {
		return adDesp;
	}
	/**
	 * @param adDesp the adDesp to set
	 */
	public void setAdDesp(String adDesp) {
		this.adDesp = adDesp;
	}
	/**
	 * @return the adPosition
	 */
	public String getAdPosition() {
		return adPosition;
	}
	/**
	 * @param adPosition the adPosition to set
	 */
	public void setAdPosition(String adPosition) {
		this.adPosition = adPosition;
	}
	/**
	 * @return the adBuyDate
	 */
	public String getAdBuyDate() {
		return adBuyDate;
	}
	/**
	 * @param adBuyDate the adBuyDate to set
	 */
	public void setAdBuyDate(String adBuyDate) {
		this.adBuyDate = adBuyDate;
	}
	/**
	 * @return the adWarranty
	 */
	public long getAdWarranty() {
		return adWarranty;
	}
	/**
	 * @param adWarranty the adWarranty to set
	 */
	public void setAdWarranty(long adWarranty) {
		this.adWarranty = adWarranty;
	}
	/**
	 * @return the cabinetId
	 */
	public long getCabinetId() {
		return cabinetId;
	}
	/**
	 * @param cabinetId the cabinetId to set
	 */
	public void setCabinetId(long cabinetId) {
		this.cabinetId = cabinetId;
	}
	/**
	 * @return the asName
	 */
	public String getAsName() {
		return asName;
	}
	/**
	 * @param asName the asName to set
	 */
	public void setAsName(String asName) {
		this.asName = asName;
	}
	/**
	 * @return the supplyId
	 */
	public long getSupplyId() {
		return supplyId;
	}
	/**
	 * @param supplyId the supplyId to set
	 */
	public void setSupplyId(long supplyId) {
		this.supplyId = supplyId;
	}
	/**
	 * @return the supplyName
	 */
	public String getSupplyName() {
		return supplyName;
	}
	/**
	 * @param supplyName the supplyName to set
	 */
	public void setSupplyName(String supplyName) {
		this.supplyName = supplyName;
	}
	/**
	 * @return the modelName
	 */
	public String getModelName() {
		return modelName;
	}
	/**
	 * @param modelName the modelName to set
	 */
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	/**
	 * @return the tmrName
	 */
	public String getTmrName() {
		return tmrName;
	}
	/**
	 * @param tmrName the tmrName to set
	 */
	public void setTmrName(String tmrName) {
		this.tmrName = tmrName;
	}
	/**
	 * @return the tdcName
	 */
	public String getTdcName() {
		return tdcName;
	}
	/**
	 * @param tdcName the tdcName to set
	 */
	public void setTdcName(String tdcName) {
		this.tdcName = tdcName;
	}
	
//	/**设备配置描述*/
//	private String adDesp;
//	/**设备编号*/
//	private String adCode;
//	/**设备名称*/
//	private String asName;
//	/**设备型号*/
//	private String swName;
//	/**设备id*/
//	private int adId;
//	/**设备配置*/
//	private String adPosition;
//	/***/
//	private String adName;
//	public String getAdDesp() {
//		return adDesp;
//	}
//	public void setAdDesp(String adDesp) {
//		this.adDesp = adDesp;
//	}
//	public String getAdCode() {
//		return adCode;
//	}
//	public void setAdCode(String adCode) {
//		this.adCode = adCode;
//	}
//	public String getAsName() {
//		return asName;
//	}
//	public void setAsName(String asName) {
//		this.asName = asName;
//	}
//	public String getSwName() {
//		return swName;
//	}
//	public void setSwName(String swName) {
//		this.swName = swName;
//	}
//	public int getAdId() {
//		return adId;
//	}
//	public void setAdId(int adId) {
//		this.adId = adId;
//	}
//	public String getAdPosition() {
//		return adPosition;
//	}
//	public void setAdPosition(String adPosition) {
//		this.adPosition = adPosition;
//	}
//	public String getAdName() {
//		return adName;
//	}
//	public void setAdName(String adName) {
//		this.adName = adName;
//	}
//	@Override
//	public String toString() {
//		return "RelatedDeviceEntity [adDesp=" + adDesp + ", adCode=" + adCode
//				+ ", asName=" + asName + ", swName=" + swName + ", adId="
//				+ adId + ", adPosition=" + adPosition + ", adName=" + adName
//				+ "]";
//	}

}
