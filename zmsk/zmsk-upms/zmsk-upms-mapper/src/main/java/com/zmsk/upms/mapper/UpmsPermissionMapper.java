package com.zmsk.upms.mapper;

import com.zmsk.upms.pojo.UpmsPermission;
import com.zmsk.upms.pojo.UpmsPermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UpmsPermissionMapper {
	int countByExample(UpmsPermissionExample example);

	int deleteByExample(UpmsPermissionExample example);

	int deleteByPrimaryKey(Integer permissionId);

	int insert(UpmsPermission record);

	int insertSelective(UpmsPermission record);

	List<UpmsPermission> selectByExample(UpmsPermissionExample example);

	UpmsPermission selectByPrimaryKey(Integer permissionId);

	int updateByExampleSelective(@Param("record") UpmsPermission record, @Param("example") UpmsPermissionExample example);

	int updateByExample(@Param("record") UpmsPermission record, @Param("example") UpmsPermissionExample example);

	int updateByPrimaryKeySelective(UpmsPermission record);

	int updateByPrimaryKey(UpmsPermission record);

	List<UpmsPermission> queryPermissionListByUserId(@Param("userId") int userId);

	List<UpmsPermission> queryMenuListByUserId(@Param("userId") int userId);
}