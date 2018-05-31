package com.zmsk.face.mapper;

import com.zmsk.face.pojo.FaceEquipmentTag;
import com.zmsk.face.pojo.FaceEquipmentTagExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FaceEquipmentTagMapper {
    int countByExample(FaceEquipmentTagExample example);

    int deleteByExample(FaceEquipmentTagExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FaceEquipmentTag record);

    int insertSelective(FaceEquipmentTag record);

    List<FaceEquipmentTag> selectByExample(FaceEquipmentTagExample example);

    FaceEquipmentTag selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FaceEquipmentTag record, @Param("example") FaceEquipmentTagExample example);

    int updateByExample(@Param("record") FaceEquipmentTag record, @Param("example") FaceEquipmentTagExample example);

    int updateByPrimaryKeySelective(FaceEquipmentTag record);

    int updateByPrimaryKey(FaceEquipmentTag record);
}