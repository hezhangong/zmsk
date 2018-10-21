package com.zmsk.face.pojo;

public class FaceFactory {
    private Integer id;

    private String name;
    
    private String shortName;

    private String linkman;
    
    private String phone;

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
    
    public String getShortName() {
    	return shortName;
    }
    
    public void setShortName(String shortName) {
    	this.shortName = shortName == null ? null : shortName.trim();
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman == null ? null : linkman.trim();
    }
    
    public String getPhone() {
    	return phone;
    }
    
    public void setPhone(String phone) {
    	this.phone = phone == null ? null : phone.trim();
    }

    public Long getCtime() {
        return ctime;
    }

    public void setCtime(Long ctime) {
        this.ctime = ctime;
    }
}