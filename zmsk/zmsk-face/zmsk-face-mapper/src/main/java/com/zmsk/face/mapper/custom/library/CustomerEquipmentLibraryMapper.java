package com.zmsk.face.mapper.custom.library;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zmsk.face.dto.library.EquipmetLibraryDTO;

public interface CustomerEquipmentLibraryMapper {

	List<EquipmetLibraryDTO> queryEquipmentLibraryByLibraryId(@Param("libraryId") int libraryId);

	List<Integer> queryEquipmentUnSyncFaceLibraryIds(@Param("deviceId") int deviceId);

	/***
	 * 获取新增类型的设备Id
	 * 
	 * @param libraryId
	 * @return
	 */
	List<Integer> queryIncreaseEquipmentIdByLibraryId(@Param("libraryId") int libraryId);

}
