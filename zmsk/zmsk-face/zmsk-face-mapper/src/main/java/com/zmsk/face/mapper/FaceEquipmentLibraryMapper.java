package com.zmsk.face.mapper;

import com.zmsk.face.pojo.FaceEquipmentLibrary;
import com.zmsk.face.pojo.FaceEquipmentLibraryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public abstract interface FaceEquipmentLibraryMapper {
	public abstract int countByExample(FaceEquipmentLibraryExample paramFaceEquipmentLibraryExample);

	public abstract int deleteByExample(FaceEquipmentLibraryExample paramFaceEquipmentLibraryExample);

	public abstract int deleteByPrimaryKey(Integer paramInteger);

	public abstract int insert(FaceEquipmentLibrary paramFaceEquipmentLibrary);

	public abstract int insertSelective(FaceEquipmentLibrary paramFaceEquipmentLibrary);

	public abstract List<FaceEquipmentLibrary> selectByExample(
			FaceEquipmentLibraryExample paramFaceEquipmentLibraryExample);

	public abstract FaceEquipmentLibrary selectByPrimaryKey(Integer paramInteger);

	public abstract int updateByExampleSelective(@Param("record") FaceEquipmentLibrary paramFaceEquipmentLibrary,
			@Param("example") FaceEquipmentLibraryExample paramFaceEquipmentLibraryExample);

	public abstract int updateByExample(@Param("record") FaceEquipmentLibrary paramFaceEquipmentLibrary,
			@Param("example") FaceEquipmentLibraryExample paramFaceEquipmentLibraryExample);

	public abstract int updateByPrimaryKeySelective(FaceEquipmentLibrary paramFaceEquipmentLibrary);

	public abstract int updateByPrimaryKey(FaceEquipmentLibrary paramFaceEquipmentLibrary);
}