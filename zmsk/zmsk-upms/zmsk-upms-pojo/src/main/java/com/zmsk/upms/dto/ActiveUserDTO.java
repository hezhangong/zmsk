package com.zmsk.upms.dto;

import java.io.Serializable;
import java.util.List;

import com.zmsk.upms.pojo.UpmsPermission;

/***
 * 用户身份信息，存入session
 * 
 * @author warrior
 *
 */
public class ActiveUserDTO implements Serializable {

	private static final long serialVersionUID = -6771630094112020564L;

	/** 用户Id **/
	private int userId;

	/** 用户账号 **/
	private String userAccount;

	/** 用户名 **/
	private String username;

	/** 菜单 **/
	private List<UpmsPermission> menus;

	/** 权限 **/
	private List<UpmsPermission> permissions;

	public ActiveUserDTO() {

	}

	public ActiveUserDTO(int userId, String userAccount, String username) {
		this.userId = userId;
		this.userAccount = userAccount;
		this.username = username;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<UpmsPermission> getMenus() {
		return menus;
	}

	public void setMenus(List<UpmsPermission> menus) {
		this.menus = menus;
	}

	public List<UpmsPermission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<UpmsPermission> permissions) {
		this.permissions = permissions;
	}

	@Override
	public String toString() {
		return "ActiveUserDTO [userId=" + userId + ", userAccount=" + userAccount + ", username=" + username + ", menus=" + menus + ", permissions=" + permissions + "]";
	}
}
