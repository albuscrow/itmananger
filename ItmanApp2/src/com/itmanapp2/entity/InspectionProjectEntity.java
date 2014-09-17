package com.itmanapp2.entity;

/**
 * @date 2014-7-28
 * @author wangpeng
 * @class description 我的巡检-巡检项目-实体类
 * 
 */
public class InspectionProjectEntity {
	
	/**工单明细id*/
	private int id;
	/**巡检结果*/
	private boolean result;
	/**巡检项目名称*/
	private String itemName;
	/**巡检时间*/
	private String addtime;
	private int status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	@Override
	public String toString() {
		return "InspectionProjectEntity [id=" + id + ", result=" + result
				+ ", itemName=" + itemName + ", addtime=" + addtime + "]";
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getStatus() {
		return status;
	}

}
