package com.zmsk.face.service.user.dto;

import java.io.Serializable;

/****
 * 管理员用户信息封装
 * 
 * @author warrior
 *
 */
public class AdminUserDTO implements Serializable {

	private static final long serialVersionUID = 1861648035711291318L;

	private Integer userId;

	private String username;
	
	private String realname;
	
	private String phone;
	
	private Integer locked;

	private Long ctime;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getLocked() {
		return locked;
	}

	public void setLocked(Integer locked) {
		this.locked = locked;
	}

	public Long getCtime() {
		return ctime;
	}

	public void setCtime(Long ctime) {
		this.ctime = ctime;
	}

	@Override
	public String toString() {
		return "AdminUserDTO [userId=" + userId + ", username=" + username + ", realname=" + realname + ", phone=" + phone + ", locked=" + locked + ", ctime=" + ctime + "]";
	}
}
