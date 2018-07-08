package com.zmsk.face.service.authentication.dto;

import java.io.Serializable;
import java.util.Date;

import cn.afterturn.easypoi.excel.annotation.Excel;

public class ExportAuthenticationInfoDTO implements Serializable {

	private static final long serialVersionUID = 5887456963557521789L;

	@Excel(name = "姓名", orderNum = "0")
	private String name;

	@Excel(name = "性别", replace = { "女_1", "男_2" }, orderNum = "1")
	private Integer sex;

	@Excel(name = "身份证号", orderNum = "2")
	private String idNumber;

	@Excel(name = "籍贯", orderNum = "3")
	private String nation;

	@Excel(name = "地址", orderNum = "4")
	private String address;

	@Excel(name = "认证类型", replace = { "人证_1", "人脸_2" }, orderNum = "5")
	private Integer type;

	@Excel(name = "来源信息", orderNum = "6")
	private String source;

	@Excel(name = "认证结果", replace = { "失败_0", "成功_1" }, orderNum = "7")
	private Integer result;

	@Excel(name = "所属分组", orderNum = "8")
	private String groupName;

	@Excel(name = "对比相似度", orderNum = "9")
	private String similarDegree;

	@Excel(name = "认证时间", exportFormat = "yyyy-MM-dd HH:mm:ss", orderNum = "10")
	private Date ctime;

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

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getSimilarDegree() {
		return similarDegree;
	}

	public void setSimilarDegree(String similarDegree) {
		this.similarDegree = similarDegree;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	@Override
	public String toString() {
		return "ImportAuthenticationInfoDTO [name=" + name + ", sex=" + sex + ", idNumber=" + idNumber + ", nation=" + nation + ", address=" + address + ", type=" + type + ", source=" + source + ", result=" + result + ", groupName=" + groupName + ", similarDegree=" + similarDegree + ", ctime=" + ctime + "]";
	}
}
