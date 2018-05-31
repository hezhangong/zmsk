package com.zmsk.common.dto;

import java.io.Serializable;

/****
 * 单点登入返回值封装
 * 
 * @author warrior
 *
 */
public class SSOLoginResultDTO implements Serializable {

	private static final long serialVersionUID = 3013972097165946049L;

	private String token;

	private int organizationId;

	public SSOLoginResultDTO() {

	}

	public SSOLoginResultDTO(String token, int organizationId) {
		this.token = token;
		this.organizationId = organizationId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	@Override
	public String toString() {
		return "SSOLoginResultDTO [token=" + token + ", organizationId=" + organizationId + "]";
	}

}
