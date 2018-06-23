package com.zmsk.face.mapper.custom.library;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zmsk.face.dto.library.EquipmetLibraryDTO;

public interface CustomerEquipmentLibraryMapper {

	List<EquipmetLibraryDTO> queryEquipmentLibraryByLibraryId(@Param("libraryId") int libraryId);

	List<Integer> queryEquipmentUnSyncFaceLibraryIds(@Param("deviceId") int deviceId);
	
}
