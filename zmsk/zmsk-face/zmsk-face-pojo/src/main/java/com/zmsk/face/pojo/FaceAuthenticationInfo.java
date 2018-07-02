package com.zmsk.face.pojo;

public class FaceAuthenticationInfo {
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

	private Integer groupId;

	private String idcardImage;

	private String idcardInfo;

	private String similarDegree;

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
		this.name = name == null ? null : name.trim();
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar == null ? null : avatar.trim();
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
		this.idNumber = idNumber == null ? null : idNumber.trim();
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation == null ? null : nation.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
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
		this.source = source == null ? null : source.trim();
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

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getIdcardImage() {
		return idcardImage;
	}

	public void setIdcardImage(String idcardImage) {
		this.idcardImage = idcardImage == null ? null : idcardImage.trim();
	}

	public String getIdcardInfo() {
		return idcardInfo;
	}

	public void setIdcardInfo(String idcardInfo) {
		this.idcardInfo = idcardInfo == null ? null : idcardInfo.trim();
	}

	public String getSimilarDegree() {
		return similarDegree;
	}

	public void setSimilarDegree(String similarDegree) {
		this.similarDegree = similarDegree == null ? null : similarDegree.trim();
	}

	public Long getCtime() {
		return ctime;
	}

	public void setCtime(Long ctime) {
		this.ctime = ctime;
	}
}