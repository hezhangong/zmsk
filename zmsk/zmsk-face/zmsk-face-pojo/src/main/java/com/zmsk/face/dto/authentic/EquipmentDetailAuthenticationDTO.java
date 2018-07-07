package com.zmsk.face.dto.authentic;

import java.io.Serializable;

/****
 * 设备认证详细信息封装对象
 * 
 * @author warrior
 *
 */
public class EquipmentDetailAuthenticationDTO implements Serializable {

	private static final long serialVersionUID = -1655419494520947464L;

	/** 设备Id **/
	private int equipmentId;

	/** 设备备注 **/
	private String remark;

	/** 设备状态 **/
	private int status;

	/** 今日认证成功数 **/
	private long todaySuccessCount;

	/** 今日认证失败数 **/
	private long todayFailCount;

	public int getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(int equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getTodaySuccessCount() {
		return todaySuccessCount;
	}

	public void setTodaySuccessCount(long todaySuccessCount) {
		this.todaySuccessCount = todaySuccessCount;
	}

	public long getTodayFailCount() {
		return todayFailCount;
	}

	public void setTodayFailCount(long todayFailCount) {
		this.todayFailCount = todayFailCount;
	}

	@Override
	public String toString() {
		return "EquipmentDetailAuthenticationDTO [equipmentId=" + equipmentId + ", remark=" + remark + ", status=" + status + ", todaySuccessCount=" + todaySuccessCount + ", todayFailCount=" + todayFailCount + "]";
	}
}
