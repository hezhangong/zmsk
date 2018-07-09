package com.zmsk.face.service.library.dto;

import java.io.Serializable;

/****
 * 人脸库传输对象数据封装
 * 
 * @author warrior
 *
 */
public class FaceLibraryDTO implements Serializable {

	private static final long serialVersionUID = -2070552036630182989L;

	private Integer id;

	private String name;

	private Integer sex;

	private String idNumber;

	private String nation;

	private String address;

	private String avatar;

	private String remark;

	private Integer flag;

	private Integer organizationId;

	private Long ctime;

	private String groupName;
	
	private String equipmentIds;

	private String supportDevice;

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

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public Long getCtime() {
		return ctime;
	}

	public void setCtime(Long ctime) {
		this.ctime = ctime;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getSupportDevice() {
		return supportDevice;
	}

	public void setSupportDevice(String supportDevice) {
		this.supportDevice = supportDevice;
	}

	public String getEquipmentIds() {
		return equipmentIds;
	}

	public void setEquipmentIds(String equipmentIds) {
		this.equipmentIds = equipmentIds;
	}

	@Override
	public String toString() {
		return "FaceLibraryDTO [id=" + id + ", name=" + name + ", sex=" + sex + ", idNumber=" + idNumber + ", nation=" + nation + ", address=" + address + ", avatar=" + avatar + ", remark=" + remark + ", flag=" + flag + ", organizationId=" + organizationId + ", ctime=" + ctime + ", groupName=" + groupName + ", equipmentIds=" + equipmentIds + ", supportDevice=" + supportDevice + "]";
	}
}
