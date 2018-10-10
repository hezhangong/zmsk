package com.zmsk.face.service.user.dto;

import java.io.Serializable;
import java.util.List;

import com.zmsk.face.pojo.FacePermission;

/****
 * 用户身份信息，存入session
 * 
 * @author warrior
 *
 */
public class ActiveUserDTO implements Serializable {

	private static final long serialVersionUID = -4832384802142129816L;

	/** 用户Id **/
	private int userId;

	/** 用户账号 **/
	private String userAccount;

	/** 用户名 **/
	private String username;

	/** 头像 **/
	private String avatar;

	/** 菜单 **/
	private List<FacePermission> menus;

	public ActiveUserDTO() {

	}

	public ActiveUserDTO(int userId, String userAccount, String username, String avatar) {
		this.userId = userId;
		this.userAccount = userAccount;
		this.username = username;
		this.avatar = avatar;
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

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public List<FacePermission> getMenus() {
		return menus;
	}

	public void setMenus(List<FacePermission> menus) {
		this.menus = menus;
	}

	@Override
	public String toString() {
		return "ActiveUserDTO [userId=" + userId + ", userAccount=" + userAccount + ", username=" + username + ", avatar=" + avatar + ", menus=" + menus + "]";
	}
}
