package com.itmanapp2.entity;

import java.io.Serializable;

/**
 * @date 2014-7-17
 * @author wangpeng
 * @class description 房间查询结果实体类
 * 
 */
public class RoomEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8771296403900079838L;
	
	long tmrId;
    String tmrName;
    String tmrCode;
    String tmrAddress;
    long tmrWordsId;
    long tmrAreaId;
    long tmrUnitId;
    long tmrDepId;
    String tmrPosition;
    long tmrManager;
    long tmrUnitManager;
    String tmrDescription;
    long tmrAddPerson;
    String tmrAddDate;
    int tmrStatus;
    String unitManager;
    String unitManagerPhone;
    String roomManager;
    String roomManagerPhone;
    String unitName;
    String depName;
	/**
	 * @return the tmrId
	 */
	public long getTmrId() {
		return tmrId;
	}
	/**
	 * @param tmrId the tmrId to set
	 */
	public void setTmrId(long tmrId) {
		this.tmrId = tmrId;
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
	 * @return the tmrCode
	 */
	public String getTmrCode() {
		return tmrCode;
	}
	/**
	 * @param tmrCode the tmrCode to set
	 */
	public void setTmrCode(String tmrCode) {
		this.tmrCode = tmrCode;
	}
	/**
	 * @return the tmrAddress
	 */
	public String getTmrAddress() {
		if (tmrAddress == null || tmrAddress.equals("null")) {
			return "";
		}
		return tmrAddress;
	}
	/**
	 * @param tmrAddress the tmrAddress to set
	 */
	public void setTmrAddress(String tmrAddress) {
		this.tmrAddress = tmrAddress;
	}
	
	/**
	 * @return the tmrWordsId
	 */
	public long getTmrWordsId() {
		return tmrWordsId;
	}
	/**
	 * @param tmrWordsId the tmrWordsId to set
	 */
	public void setTmrWordsId(long tmrWordsId) {
		this.tmrWordsId = tmrWordsId;
	}
	/**
	 * @return the tmrAreaId
	 */
	public long getTmrAreaId() {
		return tmrAreaId;
	}
	/**
	 * @param tmrAreaId the tmrAreaId to set
	 */
	public void setTmrAreaId(long tmrAreaId) {
		this.tmrAreaId = tmrAreaId;
	}
	/**
	 * @return the tmrUnitId
	 */
	public long getTmrUnitId() {
		return tmrUnitId;
	}
	/**
	 * @param tmrUnitId the tmrUnitId to set
	 */
	public void setTmrUnitId(long tmrUnitId) {
		this.tmrUnitId = tmrUnitId;
	}
	/**
	 * @return the tmrDepId
	 */
	public long getTmrDepId() {
		return tmrDepId;
	}
	/**
	 * @param tmrDepId the tmrDepId to set
	 */
	public void setTmrDepId(long tmrDepId) {
		this.tmrDepId = tmrDepId;
	}
	/**
	 * @return the tmrPosition
	 */
	public String getTmrPosition() {
		return tmrPosition;
	}
	/**
	 * @param tmrPosition the tmrPosition to set
	 */
	public void setTmrPosition(String tmrPosition) {
		this.tmrPosition = tmrPosition;
	}
	/**
	 * @return the tmrManager
	 */
	public long getTmrManager() {
		return tmrManager;
	}
	/**
	 * @param tmrManager the tmrManager to set
	 */
	public void setTmrManager(long tmrManager) {
		this.tmrManager = tmrManager;
	}
	/**
	 * @return the tmrUnitManager
	 */
	public long getTmrUnitManager() {
		return tmrUnitManager;
	}
	/**
	 * @param tmrUnitManager the tmrUnitManager to set
	 */
	public void setTmrUnitManager(long tmrUnitManager) {
		this.tmrUnitManager = tmrUnitManager;
	}
	/**
	 * @return the tmrDescription
	 */
	public String getTmrDescription() {
		return tmrDescription;
	}
	/**
	 * @param tmrDescription the tmrDescription to set
	 */
	public void setTmrDescription(String tmrDescription) {
		this.tmrDescription = tmrDescription;
	}
	/**
	 * @return the tmrAddPerson
	 */
	public long getTmrAddPerson() {
		return tmrAddPerson;
	}
	/**
	 * @param tmrAddPerson the tmrAddPerson to set
	 */
	public void setTmrAddPerson(long tmrAddPerson) {
		this.tmrAddPerson = tmrAddPerson;
	}
	/**
	 * @return the tmrAddDate
	 */
	public String getTmrAddDate() {
		return tmrAddDate;
	}
	/**
	 * @param tmrAddDate the tmrAddDate to set
	 */
	public void setTmrAddDate(String tmrAddDate) {
		this.tmrAddDate = tmrAddDate;
	}
	/**
	 * @return the tmrStatus
	 */
	public int getTmrStatus() {
		return tmrStatus;
	}
	/**
	 * @param tmrStatus the tmrStatus to set
	 */
	public void setTmrStatus(int tmrStatus) {
		this.tmrStatus = tmrStatus;
	}
	/**
	 * @return the unitManager
	 */
	public String getUnitManager() {
		return unitManager;
	}
	/**
	 * @param unitManager the unitManager to set
	 */
	public void setUnitManager(String unitManager) {
		this.unitManager = unitManager;
	}
	/**
	 * @return the unitManagerPhone
	 */
	public String getUnitManagerPhone() {
		if (unitManagerPhone == null || unitManagerPhone.equals("null")) {
			return "";
		}
		return unitManagerPhone;
	}
	/**
	 * @param unitManagerPhone the unitManagerPhone to set
	 */
	public void setUnitManagerPhone(String unitManagerPhone) {
		this.unitManagerPhone = unitManagerPhone;
	}
	/**
	 * @return the roomManager
	 */
	public String getRoomManager() {
		return roomManager;
	}
	/**
	 * @param roomManager the roomManager to set
	 */
	public void setRoomManager(String roomManager) {
		this.roomManager = roomManager;
	}
	/**
	 * @return the roomManagerPhone
	 */
	public String getRoomManagerPhone() {
		if (roomManagerPhone == null || roomManagerPhone.equals("null")) {
			return "";
		}
		return roomManagerPhone;
	}
	/**
	 * @param roomManagerPhone the roomManagerPhone to set
	 */
	public void setRoomManagerPhone(String roomManagerPhone) {
		this.roomManagerPhone = roomManagerPhone;
	}
	/**
	 * @return the unitName
	 */
	public String getUnitName() {
		return unitName;
	}
	/**
	 * @param unitName the unitName to set
	 */
	public void setUnitName(String unitName) {
		this.unitName = unitName;
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
	
//	/**序列化*/
//	private static final long serialVersionUID = 1L;
//	
//	/**主键id*/
//	private int asId;
//	
//	/**管理员联系电话*/
//	private String asChargePhone;
//	
//	/**系统类型*/
//	private String swName;
//	
//	/**系统名称*/
//	private String asName;
//	
//	/**单位名称*/
//	private String auiName;
//	
//	/**客户管理员联系电话*/
//	private String systemPhone;
//	
//	/**所在地址*/
//	private String asAddress;
//	
//	/**客户管理员*/
//	private String systemPerspm;
//	
//	/**系统管理员*/
//	private String asChargePerson;
//	
//	/**状态1：正常,2：告警,3：异常*/
//	private int asStatus;
//	
//	/**系统编码*/
//	private String asCode;
//	
//	public String getAsChargePhone() {
//		return asChargePhone;
//	}
//	public void setAsChargePhone(String asChargePhone) {
//		this.asChargePhone = asChargePhone;
//	}
//	public String getSwName() {
//		return swName;
//	}
//	public void setSwName(String swName) {
//		this.swName = swName;
//	}
//	public int getAsId() {
//		return asId;
//	}
//	public void setAsId(int asId) {
//		this.asId = asId;
//	}
//	public String getAuiName() {
//		return auiName;
//	}
//	public void setAuiName(String auiName) {
//		this.auiName = auiName;
//	}
//	public String getSystemPhone() {
//		return systemPhone;
//	}
//	public void setSystemPhone(String systemPhone) {
//		this.systemPhone = systemPhone;
//	}
//	public String getAsAddress() {
//		return asAddress;
//	}
//	public void setAsAddress(String asAddress) {
//		this.asAddress = asAddress;
//	}
//	public String getSystemPerspm() {
//		return systemPerspm;
//	}
//	public void setSystemPerspm(String systemPerspm) {
//		this.systemPerspm = systemPerspm;
//	}
//	public String getAsChargePerson() {
//		return asChargePerson;
//	}
//	public void setAsChargePerson(String asChargePerson) {
//		this.asChargePerson = asChargePerson;
//	}
//	public int getAsStatus() {
//		return asStatus;
//	}
//	public void setAsStatus(int asStatus) {
//		this.asStatus = asStatus;
//	}
//	public String getAsCode() {
//		return asCode;
//	}
//	public void setAsCode(String asCode) {
//		this.asCode = asCode;
//	}
//	public static long getSerialversionuid() {
//		return serialVersionUID;
//	}
//	public void setAsName(String asName) {
//		this.asName = asName;
//	}
//	public String getAsName() {
//		return asName;
//	}
//	@Override
//	public String toString() {
//		return "SystemSearchEntity [asId=" + asId + ", asChargePhone="
//				+ asChargePhone + ", swName=" + swName + ", asName=" + asName
//				+ ", auiName=" + auiName + ", systemPhone=" + systemPhone
//				+ ", asAddress=" + asAddress + ", systemPerspm=" + systemPerspm
//				+ ", asChargePerson=" + asChargePerson + ", asStatus="
//				+ asStatus + ", asCode=" + asCode + "]";
//	}
	
}
