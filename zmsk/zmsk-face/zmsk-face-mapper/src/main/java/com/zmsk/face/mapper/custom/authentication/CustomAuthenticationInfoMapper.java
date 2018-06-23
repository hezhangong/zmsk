package com.zmsk.face.mapper.custom.authentication;

import java.util.List;

import com.zmsk.face.dto.authentic.AuthenticationInfoDTO;

public interface CustomAuthenticationInfoMapper {
 
	List<AuthenticationInfoDTO> queryAuthenticationInfo(String search, int organizationId, int pageSize, int pageNum);
}