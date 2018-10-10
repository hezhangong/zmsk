package com.zmsk.face.dto.library;

import java.io.Serializable;

/****
 * 同步人脸库操作返回信息封装
 * 
 * @author warrior
 *
 */
public class SyncFaceLibraryDTO implements Serializable {

	private static final long serialVersionUID = 3958459161515542378L;

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

	private Integer groupId;

	private Integer operation;
	
	private Integer libraryId;

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

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getOperation() {
		return operation;
	}

	public void setOperation(Integer operation) {
		this.operation = operation;
	}
	

	public Integer getLibraryId() {
		return libraryId;
	}

	public void setLibraryId(Integer libraryId) {
		this.libraryId = libraryId;
	}

	public Long getCtime() {
		return ctime;
	}

	public void setCtime(Long ctime) {
		this.ctime = ctime;
	}

	@Override
	public String toString() {
		return "SyncFaceLibraryDTO [id=" + id + ", name=" + name + ", sex=" + sex + ", idNumber=" + idNumber + ", nation=" + nation + ", address=" + address + ", avatar=" + avatar + ", remark=" + remark + ", flag=" + flag + ", organizationId=" + organizationId + ", groupId=" + groupId + ", operation=" + operation + ", libraryId=" + libraryId + ", ctime=" + ctime + "]";
	}
}
