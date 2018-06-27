package com.zmsk.face.mapper;

import com.zmsk.face.pojo.FaceVisitorLibrary;
import com.zmsk.face.pojo.FaceVisitorLibraryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FaceVisitorLibraryMapper {
    int countByExample(FaceVisitorLibraryExample example);

    int deleteByExample(FaceVisitorLibraryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FaceVisitorLibrary record);

    int insertSelective(FaceVisitorLibrary record);

    List<FaceVisitorLibrary> selectByExample(FaceVisitorLibraryExample example);

    FaceVisitorLibrary selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FaceVisitorLibrary record, @Param("example") FaceVisitorLibraryExample example);

    int updateByExample(@Param("record") FaceVisitorLibrary record, @Param("example") FaceVisitorLibraryExample example);

    int updateByPrimaryKeySelective(FaceVisitorLibrary record);

    int updateByPrimaryKey(FaceVisitorLibrary record);
}