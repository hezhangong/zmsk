package com.zmsk.face.pojo;

public class FaceEquipmentList {
    private Integer id;

    private String deviceNumber;

    private String deviceIP;

    private Long ctime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

	public String getDeviceNumber() {
		return deviceNumber;
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber = deviceNumber == null ? null : deviceNumber.trim();
	}

	public String getDeviceIP() {
		return deviceIP;
	}

	public void setDeviceIP(String deviceIP) {
		this.deviceIP = deviceIP == null ? null : deviceIP.trim();
	}

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }
}