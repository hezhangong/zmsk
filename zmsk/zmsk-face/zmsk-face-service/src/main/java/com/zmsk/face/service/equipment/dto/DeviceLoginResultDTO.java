package com.zmsk.face.service.equipment.dto;

import java.io.Serializable;

/****
 * 设备登入返回值
 * 
 * @author warrior
 *
 */
public class DeviceLoginResultDTO implements Serializable {

	private static final long serialVersionUID = -7363064498415420836L;

	/** 设备账号 **/
	private String deviceNumber;

	/** 设备Id **/
	private Integer deviceId;

	/** 设备分配物理id **/
	private String macId;

	public DeviceLoginResultDTO() {

	}

	public DeviceLoginResultDTO(String deviceNumber, int deviceId, String macId) {
		this.deviceId = deviceId;
		this.deviceNumber = deviceNumber;
		this.macId = macId;
	}

	public String getDeviceNumber() {
		return deviceNumber;
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber;
	}

	public Integer getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	public String getMacId() {
		return macId;
	}

	public void setMacId(String macId) {
		this.macId = macId;
	}

	@Override
	public String toString() {
		return "DeviceLoginResultDTO [deviceNumber=" + deviceNumber + ", deviceId=" + deviceId + ", macId=" + macId + "]";
	}
}
