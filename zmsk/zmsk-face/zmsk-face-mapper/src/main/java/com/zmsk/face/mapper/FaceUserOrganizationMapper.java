package com.zmsk.face.mapper;

import com.zmsk.face.pojo.FaceUserOrganization;
import com.zmsk.face.pojo.FaceUserOrganizationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FaceUserOrganizationMapper {
    int countByExample(FaceUserOrganizationExample example);

    int deleteByExample(FaceUserOrganizationExample example);

    int deleteByPrimaryKey(Integer userOrganizationId);

    int insert(FaceUserOrganization record);

    int insertSelective(FaceUserOrganization record);

    List<FaceUserOrganization> selectByExample(FaceUserOrganizationExample example);

    FaceUserOrganization selectByPrimaryKey(Integer userOrganizationId);

    int updateByExampleSelective(@Param("record") FaceUserOrganization record, @Param("example") FaceUserOrganizationExample example);

    int updateByExample(@Param("record") FaceUserOrganization record, @Param("example") FaceUserOrganizationExample example);

    int updateByPrimaryKeySelective(FaceUserOrganization record);

    int updateByPrimaryKey(FaceUserOrganization record);
}