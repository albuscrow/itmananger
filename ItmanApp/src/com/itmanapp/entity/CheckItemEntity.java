package com.itmanapp.entity;

/**
 * @date 2014-7-28
 * @author wangpeng
 * @class description 我的巡检-巡检项目-实体类
 * 
 */
public class CheckItemEntity {
	long txpId;
    String txpName;
    long txpImportant;
    long txpCategoryId;
    long txpReferenceValue;
    int txpMaxValue;
    int txpMinValue;
    String txpDescription;
    long txpAddDate;
    long txpAddPerson;
    int txpStatus;
    String txpValue;
    
    long detailId;
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
//	/**
//	 * @return the txpId
//	 */
//	public long getTxpId() {
//		return txpId;
//	}
//	/**
//	 * @param txpId the txpId to set
//	 */
//	public void setTxpId(long txpId) {
//		this.txpId = txpId;
//	}
	/**
	 * @return the txpName
	 */
	public String getTxpName() {
		return txpName;
	}
	/**
	 * @param txpName the txpName to set
	 */
	public void setTxpName(String txpName) {
		this.txpName = txpName;
	}
	/**
	 * @return the txpImportant
	 */
	public long getTxpImportant() {
		return txpImportant;
	}
	/**
	 * @param txpImportant the txpImportant to set
	 */
	public void setTxpImportant(long txpImportant) {
		this.txpImportant = txpImportant;
	}
	/**
	 * @return the txpCategoryId
	 */
	public long getTxpCategoryId() {
		return txpCategoryId;
	}
	/**
	 * @param txpCategoryId the txpCategoryId to set
	 */
	public void setTxpCategoryId(long txpCategoryId) {
		this.txpCategoryId = txpCategoryId;
	}
	/**
	 * @return the txpReferenceValue
	 */
	public long getTxpReferenceValue() {
		return txpReferenceValue;
	}
	/**
	 * @param txpReferenceValue the txpReferenceValue to set
	 */
	public void setTxpReferenceValue(long txpReferenceValue) {
		this.txpReferenceValue = txpReferenceValue;
	}
	/**
	 * @return the txpMaxValue
	 */
	public int getTxpMaxValue() {
		return txpMaxValue;
	}
	/**
	 * @param txpMaxValue the txpMaxValue to set
	 */
	public void setTxpMaxValue(int txpMaxValue) {
		this.txpMaxValue = txpMaxValue;
	}
	/**
	 * @return the txpMinValue
	 */
	public int getTxpMinValue() {
		return txpMinValue;
	}
	/**
	 * @param txpMinValue the txpMinValue to set
	 */
	public void setTxpMinValue(int txpMinValue) {
		this.txpMinValue = txpMinValue;
	}
	/**
	 * @return the txpDescription
	 */
	public String getTxpDescription() {
		return txpDescription;
	}
	/**
	 * @param txpDescription the txpDescription to set
	 */
	public void setTxpDescription(String txpDescription) {
		this.txpDescription = txpDescription;
	}
	/**
	 * @return the txpAddDate
	 */
	public long getTxpAddDate() {
		return txpAddDate;
	}
	/**
	 * @param txpAddDate the txpAddDate to set
	 */
	public void setTxpAddDate(long txpAddDate) {
		this.txpAddDate = txpAddDate;
	}
	/**
	 * @return the txpAddPerson
	 */
	public long getTxpAddPerson() {
		return txpAddPerson;
	}
	/**
	 * @param txpAddPerson the txpAddPerson to set
	 */
	public void setTxpAddPerson(long txpAddPerson) {
		this.txpAddPerson = txpAddPerson;
	}
	/**
	 * @return the txpStatus
	 */
	public int getTxpStatus() {
		return txpStatus;
	}
	/**
	 * @param txpStatus the txpStatus to set
	 */
	public void setTxpStatus(int txpStatus) {
		this.txpStatus = txpStatus;
	}
	/**
	 * @return the txpValue
	 */
	public String getTxpValue() {
		return txpValue;
	}
	/**
	 * @param txpValue the txpValue to set
	 */
	public void setTxpValue(String txpValue) {
		this.txpValue = txpValue;
	}
	
//	/**工单明细id*/
//	private int id;
//	/**巡检结果*/
//	private int result;
//	/**巡检项目名称*/
//	private String itemName;
//	/**巡检时间*/
//	private String addtime;
//	private int status;
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
//	public int getResult() {
//		return result;
//	}
//	public void setResult(int result) {
//		this.result = result;
//	}
//	public String getItemName() {
//		return itemName;
//	}
//	public void setItemName(String itemName) {
//		this.itemName = itemName;
//	}
//	public String getAddtime() {
//		return addtime;
//	}
//	public void setAddtime(String addtime) {
//		this.addtime = addtime;
//	}
//	public int getStatus() {
//		return status;
//	}
//	public void setStatus(int status) {
//		this.status = status;
//	}
//	@Override
//	public String toString() {
//		return "InspectionProjectEntity [id=" + id + ", result=" + result
//				+ ", itemName=" + itemName + ", addtime=" + addtime
//				+ ", status=" + status + "]";
//	}
	 

}
