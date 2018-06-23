package com.zmsk.face.dto.authentic;

import java.io.Serializable;

/****
 * 认证记录返回值封装
 * 
 * @author warrior
 *
 */
public class AuthenticationInfoDTO implements Serializable {

	private static final long serialVersionUID = 8295750438057613859L;
	
	private Integer id;

	private String name;

	private String avatar;

	private Integer sex;

	private String idNumber;

	private String nation;

	private String address;

	private Integer type;

	private String source;

	private Integer result;

	private Integer organizationId;

	private Integer equipmentId;

	private String  groupName;

	private Long ctime;

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

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public Integer getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Long getCtime() {
		return ctime;
	}

	public void setCtime(Long ctime) {
		this.ctime = ctime;
	}

	@Override
	public String toString() {
		return "AuthenticationInfoDTO [id=" + id + ", name=" + name + ", avatar=" + avatar + ", sex=" + sex + ", idNumber=" + idNumber + ", nation=" + nation + ", address=" + address + ", type=" + type + ", source=" + source + ", result=" + result + ", organizationId=" + organizationId + ", equipmentId=" + equipmentId + ", groupName=" + groupName + ", ctime=" + ctime + "]";
	}
}