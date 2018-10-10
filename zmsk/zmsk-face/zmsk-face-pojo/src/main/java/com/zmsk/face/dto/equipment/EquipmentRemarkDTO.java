package com.zmsk.face.dto.equipment;

import java.io.Serializable;

/****
 * 组织备注名称信息封装
 * 
 * @author warrior
 *
 */
public class EquipmentRemarkDTO implements Serializable {

	private static final long serialVersionUID = -1729472777828070368L;

	/** 设备Id **/
	private int id;

	/** 备注名称 **/
	private String remark;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "EquipmentRemarkDTO [id=" + id + ", remark=" + remark + "]";
	}

}
