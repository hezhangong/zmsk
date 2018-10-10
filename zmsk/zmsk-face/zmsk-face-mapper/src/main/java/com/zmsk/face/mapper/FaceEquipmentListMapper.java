package com.zmsk.face.mapper;

import com.zmsk.face.pojo.FaceEquipmentList;
import com.zmsk.face.pojo.FaceEquipmentListExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FaceEquipmentListMapper {
    int countByExample(FaceEquipmentListExample example);

    int deleteByExample(FaceEquipmentListExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FaceEquipmentList record);

    int insertSelective(FaceEquipmentList record);

    List<FaceEquipmentList> selectByExample(FaceEquipmentListExample example);

    FaceEquipmentList selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FaceEquipmentList record, @Param("example") FaceEquipmentListExample example);

    int updateByExample(@Param("record") FaceEquipmentList record, @Param("example") FaceEquipmentListExample example);

    int updateByPrimaryKeySelective(FaceEquipmentList record);

    int updateByPrimaryKey(FaceEquipmentList record);
}