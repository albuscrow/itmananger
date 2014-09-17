package com.itmanapp2.entity;

/**
 * @date 2014-8-1
 * @author wangpeng
 * @class description 待验收工单实体类
 * 
 */
public class RepairAcceptanceEntity {

	/***/
	private int detailStatus;
	/***/
	private String wxName;
	/***/
	private String orderNo;
	/***/
	private String allocateDate;
	/***/
	private String desp;
	/***/
	private int orderStatus;
	/***/
	private int orderId;
	/***/
	private String addTime;
	/***/
	private int detailId;

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

	@Override
	public String toString() {
		return "RepairAcceptanceEntity [detailStatus=" + detailStatus
				+ ", wxName=" + wxName + ", orderNo=" + orderNo
				+ ", allocateDate=" + allocateDate + ", desp=" + desp
				+ ", orderStatus=" + orderStatus + ", orderId=" + orderId
				+ ", addTime=" + addTime + ", detailId=" + detailId + "]";
	}

}
