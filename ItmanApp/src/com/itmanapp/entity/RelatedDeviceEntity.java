package com.itmanapp.entity;

/**
 * @date 2014-7-17
 * @author wangpeng
 * @class description 关联设备实体类
 * 
 */
public class RelatedDeviceEntity {
	
	/**设备配置描述*/
	private String adDesp;
	/**设备编号*/
	private String adCode;
	/**设备名称*/
	private String asName;
	/**设备型号*/
	private String swName;
	/**设备id*/
	private int adId;
	/**设备配置*/
	private String adPosition;
	/***/
	private String adName;
	public String getAdDesp() {
		return adDesp;
	}
	public void setAdDesp(String adDesp) {
		this.adDesp = adDesp;
	}
	public String getAdCode() {
		return adCode;
	}
	public void setAdCode(String adCode) {
		this.adCode = adCode;
	}
	public String getAsName() {
		return asName;
	}
	public void setAsName(String asName) {
		this.asName = asName;
	}
	public String getSwName() {
		return swName;
	}
	public void setSwName(String swName) {
		this.swName = swName;
	}
	public int getAdId() {
		return adId;
	}
	public void setAdId(int adId) {
		this.adId = adId;
	}
	public String getAdPosition() {
		return adPosition;
	}
	public void setAdPosition(String adPosition) {
		this.adPosition = adPosition;
	}
	public String getAdName() {
		return adName;
	}
	public void setAdName(String adName) {
		this.adName = adName;
	}
	@Override
	public String toString() {
		return "RelatedDeviceEntity [adDesp=" + adDesp + ", adCode=" + adCode
				+ ", asName=" + asName + ", swName=" + swName + ", adId="
				+ adId + ", adPosition=" + adPosition + ", adName=" + adName
				+ "]";
	}

}
