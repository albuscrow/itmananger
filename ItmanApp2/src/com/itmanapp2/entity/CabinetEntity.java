package com.itmanapp2.entity;

import java.io.Serializable;

public class CabinetEntity implements Serializable {
	long tcId;
    String tcName;
    String tcCode;
    long tcUnitId;
    long tcDepId;
    long tcRoomId;
    String tcLayDate;
    String tcPosition;
    String tcDescription;
    long tcAddPerson;
    long tcAddDate;
    int tcStatus;
    String roomName;
    String unitName;
    String depName;
	/**
	 * @return the tcId
	 */
	public long getTcId() {
		return tcId;
	}
	/**
	 * @param tcId the tcId to set
	 */
	public void setTcId(long tcId) {
		this.tcId = tcId;
	}
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
	 * @return the tcCode
	 */
	public String getTcCode() {
		return tcCode;
	}
	/**
	 * @param tcCode the tcCode to set
	 */
	public void setTcCode(String tcCode) {
		this.tcCode = tcCode;
	}
	/**
	 * @return the tcUnitId
	 */
	public long getTcUnitId() {
		return tcUnitId;
	}
	/**
	 * @param tcUnitId the tcUnitId to set
	 */
	public void setTcUnitId(long tcUnitId) {
		this.tcUnitId = tcUnitId;
	}
	/**
	 * @return the tcDepId
	 */
	public long getTcDepId() {
		return tcDepId;
	}
	/**
	 * @param tcDepId the tcDepId to set
	 */
	public void setTcDepId(long tcDepId) {
		this.tcDepId = tcDepId;
	}
	/**
	 * @return the tcRoomId
	 */
	public long getTcRoomId() {
		return tcRoomId;
	}
	/**
	 * @param tcRoomId the tcRoomId to set
	 */
	public void setTcRoomId(long tcRoomId) {
		this.tcRoomId = tcRoomId;
	}
	/**
	 * @return the tcLayDate
	 */
	public String getTcLayDate() {
		return "安放时间:" + tcLayDate;
	}
	/**
	 * @param tcLayDate the tcLayDate to set
	 */
	public void setTcLayDate(String tcLayDate) {
		this.tcLayDate = tcLayDate;
	}
	/**
	 * @return the tcPosition
	 */
	public String getTcPosition() {
		return tcPosition;
	}
	/**
	 * @param tcPosition the tcPosition to set
	 */
	public void setTcPosition(String tcPosition) {
		this.tcPosition = tcPosition;
	}
	/**
	 * @return the tcDescription
	 */
	public String getTcDescription() {
		return tcDescription;
	}
	/**
	 * @param tcDescription the tcDescription to set
	 */
	public void setTcDescription(String tcDescription) {
		this.tcDescription = tcDescription;
	}
	/**
	 * @return the tcAddPerson
	 */
	public long getTcAddPerson() {
		return tcAddPerson;
	}
	/**
	 * @param tcAddPerson the tcAddPerson to set
	 */
	public void setTcAddPerson(long tcAddPerson) {
		this.tcAddPerson = tcAddPerson;
	}
	/**
	 * @return the tcAddDate
	 */
	public long getTcAddDate() {
		return tcAddDate;
	}
	/**
	 * @param tcAddDate the tcAddDate to set
	 */
	public void setTcAddDate(long tcAddDate) {
		this.tcAddDate = tcAddDate;
	}
	/**
	 * @return the tcStatus
	 */
	public int getTcStatus() {
		return tcStatus;
	}
	/**
	 * @param tcStatus the tcStatus to set
	 */
	public void setTcStatus(int tcStatus) {
		this.tcStatus = tcStatus;
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
    
}
