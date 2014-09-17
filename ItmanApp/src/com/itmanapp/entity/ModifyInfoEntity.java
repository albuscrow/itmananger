package com.itmanapp.entity;

/**
 * @date 2014-7-17
 * @author wangpeng
 * @class description 整改历史实体类
 * 
 */
public class ModifyInfoEntity {
	
	/**文件*/
	private String asmiFile;
	/** */
	private int asmiHeadId;
	/** */
	private int asmiAddPerson;
	/**日期 */
	private String asmiAddDate;
	/**标题*/
	private String asmiDesp;
	/** */
	private int asmiId;
	public String getAsmiFile() {
		return asmiFile;
	}
	public void setAsmiFile(String asmiFile) {
		this.asmiFile = asmiFile;
	}
	public int getAsmiHeadId() {
		return asmiHeadId;
	}
	public void setAsmiHeadId(int asmiHeadId) {
		this.asmiHeadId = asmiHeadId;
	}
	public int getAsmiAddPerson() {
		return asmiAddPerson;
	}
	public void setAsmiAddPerson(int asmiAddPerson) {
		this.asmiAddPerson = asmiAddPerson;
	}
	public String getAsmiAddDate() {
		return asmiAddDate;
	}
	public void setAsmiAddDate(String asmiAddDate) {
		this.asmiAddDate = asmiAddDate;
	}
	public String getAsmiDesp() {
		return asmiDesp;
	}
	public void setAsmiDesp(String asmiDesp) {
		this.asmiDesp = asmiDesp;
	}
	public int getAsmiId() {
		return asmiId;
	}
	public void setAsmiId(int asmiId) {
		this.asmiId = asmiId;
	}
	@Override
	public String toString() {
		return "ModifyInfoEntity [asmiFile=" + asmiFile + ", asmiHeadId="
				+ asmiHeadId + ", asmiAddPerson=" + asmiAddPerson
				+ ", asmiAddDate=" + asmiAddDate + ", asmiDesp=" + asmiDesp
				+ ", asmiId=" + asmiId + "]";
	}

}
