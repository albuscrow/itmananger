package com.itmanapp.entity;

/**
 * @date 2014-7-17
 * @author wangpeng
 * @class description 整改历史实体类
 * 
 */
public class FileEntity {
	
    long tfaId;
    String tfaName;
    int tfaType;
    long tfaReferId;
    int tfaFileType;
    String tfaFilePath;
    long tfaAddDate;
    String addDate;
    /**
	 * @return the addDate
	 */
	public String getAddDate() {
		return addDate;
	}
	/**
	 * @param addDate the addDate to set
	 */
	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}
	long tfaAddPerson;
    String fileType;
	/**
	 * @return the tfaId
	 */
	public long getTfaId() {
		return tfaId;
	}
	/**
	 * @param tfaId the tfaId to set
	 */
	public void setTfaId(long tfaId) {
		this.tfaId = tfaId;
	}
	/**
	 * @return the tfaName
	 */
	public String getTfaName() {
		return tfaName;
	}
	/**
	 * @param tfaName the tfaName to set
	 */
	public void setTfaName(String tfaName) {
		this.tfaName = tfaName;
	}
	/**
	 * @return the tfaType
	 */
	public int getTfaType() {
		return tfaType;
	}
	/**
	 * @param tfaType the tfaType to set
	 */
	public void setTfaType(int tfaType) {
		this.tfaType = tfaType;
	}
	/**
	 * @return the tfaReferId
	 */
	public long getTfaReferId() {
		return tfaReferId;
	}
	/**
	 * @param tfaReferId the tfaReferId to set
	 */
	public void setTfaReferId(long tfaReferId) {
		this.tfaReferId = tfaReferId;
	}
	/**
	 * @return the tfaFileType
	 */
	public int getTfaFileType() {
		return tfaFileType;
	}
	/**
	 * @param tfaFileType the tfaFileType to set
	 */
	public void setTfaFileType(int tfaFileType) {
		this.tfaFileType = tfaFileType;
	}
	/**
	 * @return the tfaFilePath
	 */
	public String getTfaFilePath() {
		return tfaFilePath;
	}
	/**
	 * @param tfaFilePath the tfaFilePath to set
	 */
	public void setTfaFilePath(String tfaFilePath) {
		this.tfaFilePath = tfaFilePath;
	}
	/**
	 * @return the tfaAddDate
	 */
	public long getTfaAddDate() {
		return tfaAddDate;
	}
	/**
	 * @param tfaAddDate the tfaAddDate to set
	 */
	public void setTfaAddDate(long tfaAddDate) {
		this.tfaAddDate = tfaAddDate;
	}
	/**
	 * @return the tfaAddPerson
	 */
	public long getTfaAddPerson() {
		return tfaAddPerson;
	}
	/**
	 * @param tfaAddPerson the tfaAddPerson to set
	 */
	public void setTfaAddPerson(long tfaAddPerson) {
		this.tfaAddPerson = tfaAddPerson;
	}
	/**
	 * @return the fileType
	 */
	public String getFileType() {
		return fileType;
	}
	/**
	 * @param fileType the fileType to set
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

    
//	
//	/**文件*/
//	private String asmiFile;
//	/** */
//	private int asmiHeadId;
//	/** */
//	private int asmiAddPerson;
//	/**日期 */
//	private String asmiAddDate;
//	/**标题*/
//	private String asmiDesp;
//	/** */
//	private int asmiId;
//	public String getAsmiFile() {
//		return asmiFile;
//	}
//	public void setAsmiFile(String asmiFile) {
//		this.asmiFile = asmiFile;
//	}
//	public int getAsmiHeadId() {
//		return asmiHeadId;
//	}
//	public void setAsmiHeadId(int asmiHeadId) {
//		this.asmiHeadId = asmiHeadId;
//	}
//	public int getAsmiAddPerson() {
//		return asmiAddPerson;
//	}
//	public void setAsmiAddPerson(int asmiAddPerson) {
//		this.asmiAddPerson = asmiAddPerson;
//	}
//	public String getAsmiAddDate() {
//		return asmiAddDate;
//	}
//	public void setAsmiAddDate(String asmiAddDate) {
//		this.asmiAddDate = asmiAddDate;
//	}
//	public String getAsmiDesp() {
//		return asmiDesp;
//	}
//	public void setAsmiDesp(String asmiDesp) {
//		this.asmiDesp = asmiDesp;
//	}
//	public int getAsmiId() {
//		return asmiId;
//	}
//	public void setAsmiId(int asmiId) {
//		this.asmiId = asmiId;
//	}
//	@Override
//	public String toString() {
//		return "ModifyInfoEntity [asmiFile=" + asmiFile + ", asmiHeadId="
//				+ asmiHeadId + ", asmiAddPerson=" + asmiAddPerson
//				+ ", asmiAddDate=" + asmiAddDate + ", asmiDesp=" + asmiDesp
//				+ ", asmiId=" + asmiId + "]";
//	}

}
