package com.zmsk.face.pojo;

public class FaceEquipment {
	private Integer id;

	private Integer organizationId;

	private String macId;

	private String equipmentNumber;

	private String equipmentPwd;

	private String equipmentPlainPwd;

	private Integer equipmentType;

	private Integer renewalFee;

	private Integer status;

	private Long createTime;

	private Long activationTime;

	private Long endTime;

	private Long renewalTime;

	private String remark;

	private Integer factoryId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public String getMacId() {
		return macId;
	}

	public void setMacId(String macId) {
		this.macId = macId == null ? null : macId.trim();
	}

	public String getEquipmentNumber() {
		return equipmentNumber;
	}

	public void setEquipmentNumber(String equipmentNumber) {
		this.equipmentNumber = equipmentNumber == null ? null : equipmentNumber.trim();
	}

	public String getEquipmentPwd() {
		return equipmentPwd;
	}

	public void setEquipmentPwd(String equipmentPwd) {
		this.equipmentPwd = equipmentPwd == null ? null : equipmentPwd.trim();
	}

	public String getEquipmentPlainPwd() {
		return equipmentPlainPwd;
	}

	public void setEquipmentPlainPwd(String equipmentPlainPwd) {
		this.equipmentPlainPwd = equipmentPlainPwd == null ? null : equipmentPlainPwd.trim();
	}

	public Integer getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(Integer equipmentType) {
		this.equipmentType = equipmentType;
	}

	public Integer getRenewalFee() {
		return renewalFee;
	}

	public void setRenewalFee(Integer renewalFee) {
		this.renewalFee = renewalFee;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getActivationTime() {
		return activationTime;
	}

	public void setActivationTime(Long activationTime) {
		this.activationTime = activationTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public Long getRenewalTime() {
		return renewalTime;
	}

	public void setRenewalTime(Long renewalTime) {
		this.renewalTime = renewalTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public Integer getFactoryId() {
		return factoryId;
	}

	public void setFactoryId(Integer factoryId) {
		this.factoryId = factoryId;
	}
}