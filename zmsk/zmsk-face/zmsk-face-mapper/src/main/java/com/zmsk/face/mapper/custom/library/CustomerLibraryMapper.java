package com.zmsk.face.mapper.custom.library;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zmsk.face.dto.library.SyncFaceLibraryDTO;
import com.zmsk.face.pojo.FaceLibrary;

public interface CustomerLibraryMapper {

	List<FaceLibrary> queryLibraryListByEquipmentId(@Param("equipmentId") int equipmentId, @Param("flag") int flag, @Param("pageSize") int pageSize, @Param("pageStart") int pageStart);

	/****
	 * 获取设备对应未同步的人脸库
	 * 
	 * @param equipmentId
	 *            设备Id
	 * @return
	 */
	List<SyncFaceLibraryDTO> queryUnSyncFaceLibraryById(@Param("equipmentId") int equipmentId);
	
	/****
	 * 获取设备对应未同步的人脸库
	 * 
	 * @param equipmentNumber
	 *            设备号
	 * @return
	 */
	List<SyncFaceLibraryDTO> queryUnSyncFaceLibraryByNumber(@Param("equipmentNumber") String equipmentNumber);
}
