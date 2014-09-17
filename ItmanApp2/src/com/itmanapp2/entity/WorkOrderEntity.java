package com.itmanapp2.entity;

/**
 * @date 2014-7-25
 * @author wangpeng
 * @class description 工单实体类
 * 
 */
public class WorkOrderEntity {

	/** 工单明细状态 */
	private int detailStatus;
	
	/** 维修项目名称 */
	private String wxName;
	
	/** 工单号 */
	private String orderNo;
	
	/** 分配时间 */
	private String allocateDate;
	
	/** 描述 */
	private String desp;
	
	/**
	 * 工单状态 (1:提交报修 2:已经确认 3：已派工 4：待维修 5：已维修 6：已验收 0：审核失败 7：维修失败)
	 */
	private int orderStatus;
	
	/** 工单id */
	private int orderId;
	
	/** 时间 */
	private String addTime;
	
	/** 工单明细id */
	private int detailId;
	
	/** 系统名称 */
	private String asName;
	
	/** 设备名称 */
	private String adName;
	
	/** 设备编号 */
	private String adCode;
	
	/**使用单位*/
	private String auiName;

	public String getAsName() {
		return asName;
	}

	public void setAsName(String asName) {
		this.asName = asName;
	}

	public String getAdName() {
		return adName;
	}

	public void setAdName(String adName) {
		this.adName = adName;
	}

	public String getAdCode() {
		return adCode;
	}

	public void setAdCode(String adCode) {
		this.adCode = adCode;
	}

	public int getDetailStatus() {
		return detailStatus;
	}

	public void setDetailStatus(int detailStatus) {
		this.detailStatus = detailStatus;
	}

	public String getWxName() {
		return wxName;
	}

	public void setWxName(String wxName) {
		this.wxName = wxName;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getAllocateDate() {
		return allocateDate;
	}

	public void setAllocateDate(String allocateDate) {
		this.allocateDate = allocateDate;
	}

	public String getDesp() {
		return desp;
	}

	public void setDesp(String desp) {
		this.desp = desp;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public int getDetailId() {
		return detailId;
	}

	public void setDetailId(int detailId) {
		this.detailId = detailId;
	}

	public void setAuiName(String auiName) {
		this.auiName = auiName;
	}

	public String getAuiName() {
		return auiName;
	}

	@Override
	public String toString() {
		return "WorkOrderEntity [detailStatus=" + detailStatus + ", wxName="
				+ wxName + ", orderNo=" + orderNo + ", allocateDate="
				+ allocateDate + ", desp=" + desp + ", orderStatus="
				+ orderStatus + ", orderId=" + orderId + ", addTime=" + addTime
				+ ", detailId=" + detailId + ", asName=" + asName + ", adName="
				+ adName + ", adCode=" + adCode + ", auiName=" + auiName
				+ ", getAsName()=" + getAsName() + ", getAdName()="
				+ getAdName() + ", getAdCode()=" + getAdCode()
				+ ", getDetailStatus()=" + getDetailStatus() + ", getWxName()="
				+ getWxName() + ", getOrderNo()=" + getOrderNo()
				+ ", getAllocateDate()=" + getAllocateDate() + ", getDesp()="
				+ getDesp() + ", getOrderStatus()=" + getOrderStatus()
				+ ", getOrderId()=" + getOrderId() + ", getAddTime()="
				+ getAddTime() + ", getDetailId()=" + getDetailId()
				+ ", getAuiName()=" + getAuiName() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

}
