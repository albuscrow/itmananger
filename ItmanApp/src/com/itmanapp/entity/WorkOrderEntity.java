package com.itmanapp.entity;

/**
 * @date 2014-7-25
 * @author wangpeng
 * @class description 工单实体类
 * 
 */
public class WorkOrderEntity {
	long orderId;
    long detailId;
    String orderNo;
    String tdName;
    String addTime;
    String allocateDate;
    String desp;
    int orderStatus;
    int detailStatus;
    String auiName;
    String depName;
    String roomName;
    String cabinetName;
    String adName;
    String tdcName;
    String tdPosition;
    String itemIds;
    String itemNames;
    
	/**
	 * @return the auiName
	 */
	public String getAuiName() {
		return auiName;
	}
	/**
	 * @param auiName the auiName to set
	 */
	public void setAuiName(String auiName) {
		this.auiName = auiName;
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
	 * @return the cabinetName
	 */
	public String getCabinetName() {
		return cabinetName;
	}
	/**
	 * @param cabinetName the cabinetName to set
	 */
	public void setCabinetName(String cabinetName) {
		this.cabinetName = cabinetName;
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
	/**
	 * @return the tdPosition
	 */
	public String getTdPosition() {
		return tdPosition;
	}
	/**
	 * @param tdPosition the tdPosition to set
	 */
	public void setTdPosition(String tdPosition) {
		this.tdPosition = tdPosition;
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
	/**
	 * @return the orderId
	 */
	public long getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	/**
	 * @return the detailId
	 */
	public long getDetailId() {
		return detailId;
	}
	/**
	 * @param detailId the detailId to set
	 */
	public void setDetailId(long detailId) {
		this.detailId = detailId;
	}
	/**
	 * @return the orderNo
	 */
	public String getOrderNo() {
		return orderNo;
	}
	/**
	 * @param orderNo the orderNo to set
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * @return the tdName
	 */
	public String getTdName() {
		return tdName;
	}
	/**
	 * @param tdName the tdName to set
	 */
	public void setTdName(String tdName) {
		this.tdName = tdName;
	}
	/**
	 * @return the addTime
	 */
	public String getAddTime() {
		return addTime;
	}
	/**
	 * @param addTime the addTime to set
	 */
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	/**
	 * @return the allocateDate
	 */
	public String getAllocateDate() {
		return allocateDate;
	}
	/**
	 * @param allocateDate the allocateDate to set
	 */
	public void setAllocateDate(String allocateDate) {
		this.allocateDate = allocateDate;
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
	 * @return the orderStatus
	 */
	public int getOrderStatus() {
		return orderStatus;
	}
	/**
	 * @param orderStatus the orderStatus to set
	 */
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	/**
	 * @return the detailStatus
	 */
	public int getDetailStatus() {
		return detailStatus;
	}
	/**
	 * @param detailStatus the detailStatus to set
	 */
	public void setDetailStatus(int detailStatus) {
		this.detailStatus = detailStatus;
	}
	String tdCode;
	public void setTdCode(String string) {
		this.tdCode = string;
	}
	public String getTdCode(){
		return tdCode;
	}


//	/** 工单明细状态 */
//	private int detailStatus;
//	
//	/** 维修项目名称 */
//	private String wxName;
//	
//	/** 工单号 */
//	private String orderNo;
//	
//	/** 分配时间 */
//	private String allocateDate;
//	
//	/** 描述 */
//	private String desp;
//	
//	/**
//	 * 工单状态 (1:提交报修 2:已经确认 3：已派工 4：待维修 5：已维修 6：已验收 0：审核失败 7：维修失败)
//	 */
//	private int orderStatus;
//	
//	/** 工单id */
//	private int orderId;
//	
//	/** 时间 */
//	private String addTime;
//	
//	/** 工单明细id */
//	private int detailId;
//	
//	/** 系统名称 */
//	private String asName;
//	
//	/** 设备名称 */
//	private String adName;
//	
//	/** 设备编号 */
//	private String adCode;
//	
//	/**使用单位*/
//	private String auiName;
//
//	public String getAsName() {
//		return asName;
//	}
//
//	public void setAsName(String asName) {
//		this.asName = asName;
//	}
//
//	public String getAdName() {
//		return adName;
//	}
//
//	public void setAdName(String adName) {
//		this.adName = adName;
//	}
//
//	public String getAdCode() {
//		return adCode;
//	}
//
//	public void setAdCode(String adCode) {
//		this.adCode = adCode;
//	}
//
//	public int getDetailStatus() {
//		return detailStatus;
//	}
//
//	public void setDetailStatus(int detailStatus) {
//		this.detailStatus = detailStatus;
//	}
//
//	public String getWxName() {
//		return wxName;
//	}
//
//	public void setWxName(String wxName) {
//		this.wxName = wxName;
//	}
//
//	public String getOrderNo() {
//		return orderNo;
//	}
//
//	public void setOrderNo(String orderNo) {
//		this.orderNo = orderNo;
//	}
//
//	public String getAllocateDate() {
//		return allocateDate;
//	}
//
//	public void setAllocateDate(String allocateDate) {
//		this.allocateDate = allocateDate;
//	}
//
//	public String getDesp() {
//		return desp;
//	}
//
//	public void setDesp(String desp) {
//		this.desp = desp;
//	}
//
//	public int getOrderStatus() {
//		return orderStatus;
//	}
//
//	public void setOrderStatus(int orderStatus) {
//		this.orderStatus = orderStatus;
//	}
//
//	public int getOrderId() {
//		return orderId;
//	}
//
//	public void setOrderId(int orderId) {
//		this.orderId = orderId;
//	}
//
//	public String getAddTime() {
//		return addTime;
//	}
//
//	public void setAddTime(String addTime) {
//		this.addTime = addTime;
//	}
//
//	public int getDetailId() {
//		return detailId;
//	}
//
//	public void setDetailId(int detailId) {
//		this.detailId = detailId;
//	}
//
//	public void setAuiName(String auiName) {
//		this.auiName = auiName;
//	}
//
//	public String getAuiName() {
//		return auiName;
//	}
//
//	@Override
//	public String toString() {
//		return "WorkOrderEntity [detailStatus=" + detailStatus + ", wxName="
//				+ wxName + ", orderNo=" + orderNo + ", allocateDate="
//				+ allocateDate + ", desp=" + desp + ", orderStatus="
//				+ orderStatus + ", orderId=" + orderId + ", addTime=" + addTime
//				+ ", detailId=" + detailId + ", asName=" + asName + ", adName="
//				+ adName + ", adCode=" + adCode + ", auiName=" + auiName
//				+ ", getAsName()=" + getAsName() + ", getAdName()="
//				+ getAdName() + ", getAdCode()=" + getAdCode()
//				+ ", getDetailStatus()=" + getDetailStatus() + ", getWxName()="
//				+ getWxName() + ", getOrderNo()=" + getOrderNo()
//				+ ", getAllocateDate()=" + getAllocateDate() + ", getDesp()="
//				+ getDesp() + ", getOrderStatus()=" + getOrderStatus()
//				+ ", getOrderId()=" + getOrderId() + ", getAddTime()="
//				+ getAddTime() + ", getDetailId()=" + getDetailId()
//				+ ", getAuiName()=" + getAuiName() + ", getClass()="
//				+ getClass() + ", hashCode()=" + hashCode() + ", toString()="
//				+ super.toString() + "]";
//	}

}
