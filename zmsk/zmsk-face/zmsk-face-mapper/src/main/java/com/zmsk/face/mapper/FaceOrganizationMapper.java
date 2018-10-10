package com.zmsk.face.mapper;

import com.zmsk.face.pojo.FaceOrganization;
import com.zmsk.face.pojo.FaceOrganizationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FaceOrganizationMapper {
    int countByExample(FaceOrganizationExample example);

    int deleteByExample(FaceOrganizationExample example);

    int deleteByPrimaryKey(Integer organizationId);

    int insert(FaceOrganization record);

    int insertSelective(FaceOrganization record);

    List<FaceOrganization> selectByExample(FaceOrganizationExample example);

    FaceOrganization selectByPrimaryKey(Integer organizationId);

    int updateByExampleSelective(@Param("record") FaceOrganization record, @Param("example") FaceOrganizationExample example);

    int updateByExample(@Param("record") FaceOrganization record, @Param("example") FaceOrganizationExample example);

    int updateByPrimaryKeySelective(FaceOrganization record);

    int updateByPrimaryKey(FaceOrganization record);
}