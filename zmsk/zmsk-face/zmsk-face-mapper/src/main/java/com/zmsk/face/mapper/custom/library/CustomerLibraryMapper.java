package com.zmsk.face.mapper.custom.library;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zmsk.face.pojo.FaceLibrary;

public interface CustomerLibraryMapper {

	List<FaceLibrary> queryLibraryListByEquipmentId(@Param("equipmentId") int equipmentId, @Param("flag") int flag, @Param("pageSize") int pageSize, @Param("pageStart") int pageStart);
}
