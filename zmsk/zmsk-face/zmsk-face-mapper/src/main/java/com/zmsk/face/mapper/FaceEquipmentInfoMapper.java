package com.zmsk.face.mapper;

import com.zmsk.face.pojo.FaceEquipmentInfo;
import com.zmsk.face.pojo.FaceEquipmentInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FaceEquipmentInfoMapper {
    int countByExample(FaceEquipmentInfoExample example);

    int deleteByExample(FaceEquipmentInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FaceEquipmentInfo record);

    int insertSelective(FaceEquipmentInfo record);

    List<FaceEquipmentInfo> selectByExample(FaceEquipmentInfoExample example);

    FaceEquipmentInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FaceEquipmentInfo record, @Param("example") FaceEquipmentInfoExample example);

    int updateByExample(@Param("record") FaceEquipmentInfo record, @Param("example") FaceEquipmentInfoExample example);

    int updateByPrimaryKeySelective(FaceEquipmentInfo record);

    int updateByPrimaryKey(FaceEquipmentInfo record);
}