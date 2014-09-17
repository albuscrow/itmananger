package com.itmanapp.entity;

/**
 * @date 2014-7-15
 * @author wangpeng
 * @class description 登录实体类
 * 
 */
public class LoginEntity {

	/**添加时间*/
	private String createTime;
	/***/
	private String startDate;
	/***/
	private String groupName;
	/**性别*/
	private int sex;
	/**备注*/
	private String remark;
	/***/
	private String qanswer;
	/**系统主题*/
	private String theme;
	/***/
	private String endDate;
	/**编码*/
	private String code;
	/**id*/
	private int id;
	/**用户组id*/
	private int groupId;
	/***/
	private String createUserName;
	/**职称*/
	private String title;
	/**修改人姓名*/
	private String modifyUserName;
	/**修改人id*/
	private int modifyUserId;
	/**状态*/
	private int deleteMark;
	/**邮箱*/
	private String email;
	/**姓名*/
	private String name;
	/***/
	private String sortType;
	/**登录账号*/
	private String account;
	/**密码提示问题*/
	private String question;
	/**修改时间*/
	private String modifyTime;
	/**添加人id*/
	private int createUserId;
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getQanswer() {
		return qanswer;
	}
	public void setQanswer(String qanswer) {
		this.qanswer = qanswer;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getModifyUserName() {
		return modifyUserName;
	}
	public void setModifyUserName(String modifyUserName) {
		this.modifyUserName = modifyUserName;
	}
	public int getModifyUserId() {
		return modifyUserId;
	}
	public void setModifyUserId(int modifyUserId) {
		this.modifyUserId = modifyUserId;
	}
	public int getDeleteMark() {
		return deleteMark;
	}
	public void setDeleteMark(int deleteMark) {
		this.deleteMark = deleteMark;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSortType() {
		return sortType;
	}
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	public int getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
	}
	@Override
	public String toString() {
		return "LoginEntity [createTime=" + createTime + ", startDate="
				+ startDate + ", groupName=" + groupName + ", sex=" + sex
				+ ", remark=" + remark + ", qanswer=" + qanswer + ", theme="
				+ theme + ", endDate=" + endDate + ", code=" + code + ", id="
				+ id + ", groupId=" + groupId + ", createUserName="
				+ createUserName + ", title=" + title + ", modifyUserName="
				+ modifyUserName + ", modifyUserId=" + modifyUserId
				+ ", deleteMark=" + deleteMark + ", email=" + email + ", name="
				+ name + ", sortType=" + sortType + ", account=" + account
				+ ", question=" + question + ", modifyTime=" + modifyTime
				+ ", createUserId=" + createUserId + "]";
	}

}
