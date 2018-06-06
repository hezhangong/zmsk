package com.zmsk.face.mapper;

import com.zmsk.face.pojo.FaceEquipmentLibrary;
import com.zmsk.face.pojo.FaceEquipmentLibraryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FaceEquipmentLibraryMapper {
    int countByExample(FaceEquipmentLibraryExample example);

    int deleteByExample(FaceEquipmentLibraryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FaceEquipmentLibrary record);

    int insertSelective(FaceEquipmentLibrary record);

    List<FaceEquipmentLibrary> selectByExample(FaceEquipmentLibraryExample example);

    FaceEquipmentLibrary selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FaceEquipmentLibrary record, @Param("example") FaceEquipmentLibraryExample example);

    int updateByExample(@Param("record") FaceEquipmentLibrary record, @Param("example") FaceEquipmentLibraryExample example);

    int updateByPrimaryKeySelective(FaceEquipmentLibrary record);

    int updateByPrimaryKey(FaceEquipmentLibrary record);
}