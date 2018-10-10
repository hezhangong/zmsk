package com.zmsk.face.service.authentication.dto;

import java.io.Serializable;

/****
 * 访客信息封装
 * 
 * @author warrior
 *
 */
public class VisistorInfoDTO implements Serializable {

	private static final long serialVersionUID = -5924199733664794654L;

	private Integer id;

	private String name;

	private String avatar;

	private Integer sex;

	private String idNumber;

	private String groupName;

	private String remark;

	private Long registerTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Long registerTime) {
		this.registerTime = registerTime;
	}

	@Override
	public String toString() {
		return "VisistorInfoDTO [id=" + id + ", name=" + name + ", avatar=" + avatar + ", sex=" + sex + ", idNumber=" + idNumber + ", groupName=" + groupName + ", remark=" + remark + ", registerTime=" + registerTime + "]";
	}
}
