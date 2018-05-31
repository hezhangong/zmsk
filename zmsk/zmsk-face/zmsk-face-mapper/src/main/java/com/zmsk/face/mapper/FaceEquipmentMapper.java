package com.zmsk.face.mapper;

import com.zmsk.face.pojo.FaceEquipment;
import com.zmsk.face.pojo.FaceEquipmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FaceEquipmentMapper {
    int countByExample(FaceEquipmentExample example);

    int deleteByExample(FaceEquipmentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FaceEquipment record);

    int insertSelective(FaceEquipment record);

    List<FaceEquipment> selectByExample(FaceEquipmentExample example);

    FaceEquipment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FaceEquipment record, @Param("example") FaceEquipmentExample example);

    int updateByExample(@Param("record") FaceEquipment record, @Param("example") FaceEquipmentExample example);

    int updateByPrimaryKeySelective(FaceEquipment record);

    int updateByPrimaryKey(FaceEquipment record);
}