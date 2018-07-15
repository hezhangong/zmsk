package com.zmsk.face.dto.library;

import java.io.Serializable;

/****
 * 人脸库对应设备信息封装
 * 
 * @author warrior
 *
 */
public class EquipmetLibraryDTO implements Serializable {

	private static final long serialVersionUID = 6502430018601817555L;

	private Integer equipmentId;

	private Integer syncStatus;

	private Integer operation;

	private String remark;

	public Integer getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}

	public Integer getSyncStatus() {
		return syncStatus;
	}

	public void setSyncStatus(Integer syncStatus) {
		this.syncStatus = syncStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getOperation() {
		return operation;
	}

	public void setOperation(Integer operation) {
		this.operation = operation;
	}

	@Override
	public String toString() {
		return "EquipmetLibraryDTO [equipmentId=" + equipmentId + ", syncStatus=" + syncStatus + ", operation=" + operation + ", remark=" + remark + "]";
	}
}
