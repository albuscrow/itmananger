package com.itmanapp2.entity;

import java.io.Serializable;

public class DepEntity implements Serializable {
    long tdId;
    String tdName;
    String tdDescription;
    int tdStatus;
    long tdAddDate;
    long tdAddPerson;
    long tdUnitId;
	/**
	 * @return the tdId
	 */
	public long getTdId() {
		return tdId;
	}
	/**
	 * @param tdId the tdId to set
	 */
	public void setTdId(long tdId) {
		this.tdId = tdId;
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
	 * @return the tdDescription
	 */
	public String getTdDescription() {
		return tdDescription;
	}
	/**
	 * @param tdDescription the tdDescription to set
	 */
	public void setTdDescription(String tdDescription) {
		this.tdDescription = tdDescription;
	}
	/**
	 * @return the tdStatus
	 */
	public int getTdStatus() {
		return tdStatus;
	}
	/**
	 * @param tdStatus the tdStatus to set
	 */
	public void setTdStatus(int tdStatus) {
		this.tdStatus = tdStatus;
	}
	/**
	 * @return the tdAddDate
	 */
	public long getTdAddDate() {
		return tdAddDate;
	}
	/**
	 * @param tdAddDate the tdAddDate to set
	 */
	public void setTdAddDate(long tdAddDate) {
		this.tdAddDate = tdAddDate;
	}
	/**
	 * @return the tdAddPerson
	 */
	public long getTdAddPerson() {
		return tdAddPerson;
	}
	/**
	 * @param tdAddPerson the tdAddPerson to set
	 */
	public void setTdAddPerson(long tdAddPerson) {
		this.tdAddPerson = tdAddPerson;
	}
	/**
	 * @return the tdUnitId
	 */
	public long getTdUnitId() {
		return tdUnitId;
	}
	/**
	 * @param tdUnitId the tdUnitId to set
	 */
	public void setTdUnitId(long tdUnitId) {
		this.tdUnitId = tdUnitId;
	}
    
}
