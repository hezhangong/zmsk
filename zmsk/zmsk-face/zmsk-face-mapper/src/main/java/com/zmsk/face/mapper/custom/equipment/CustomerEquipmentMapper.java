package com.zmsk.face.mapper.custom.equipment;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zmsk.face.dto.equipment.EquipmentRemarkDTO;

public interface CustomerEquipmentMapper {

	List<EquipmentRemarkDTO> queryEquipmentRemarkByorganizationId(@Param("organizationId") int organizationId);
}
