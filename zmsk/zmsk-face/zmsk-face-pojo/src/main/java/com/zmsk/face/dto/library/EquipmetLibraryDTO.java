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

	@Override
	public String toString() {
		return "EquipmetLibraryDTO [equipmentId=" + equipmentId + ", syncStatus=" + syncStatus + ", remark=" + remark + "]";
	}
}
