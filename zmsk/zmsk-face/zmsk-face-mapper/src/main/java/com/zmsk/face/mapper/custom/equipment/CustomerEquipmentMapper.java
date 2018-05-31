package com.zmsk.face.mapper.custom.equipment;

import org.apache.ibatis.annotations.Param;

public interface CustomerEquipmentMapper {

	int bindEquipmentTag(@Param("equipmentId") int equipmentId, @Param("equipmentTagId") int equipmentTagId);

	int unbindEquipmentTag(@Param("equipmentId") int equipmentId);
}
