package com.zmsk.face.service.library;

import java.util.List;

import com.zmsk.face.service.library.dto.FaceVisitorLibraryDTO;

/****
 * 访客人脸库操作服务接口声明
 * 
 * @author warrior
 *
 */
public interface VisistorLibraryService {

	/***
	 * 新增人脸库
	 * 
	 * @param name
	 *            姓名
	 * @param sex
	 *            性别
	 * @param idNumber
	 *            身份证号
	 * @param nation
	 *            民族
	 * @param address
	 *            地址
	 * @param avatar
	 *            头像
	 * @param remark
	 *            备注
	 * @param organizationId
	 *            组织Id
	 * @param groupId
	 *            所属分组Id
	 * @return
	 */
	boolean addFaceLibrary(String name, int sex, String idNumber, String nation, String address, String avatar, String remark, int organizationId, int groupId);

	/****
	 * 获取组织下访客人脸信息
	 * 
	 * @param organizationId
	 *            组织Id
	 * @return
	 */
	List<FaceVisitorLibraryDTO> queryVisitorLibraryByOrganizationId(int organizationId);

	/****
	 * 根据Id获取访客人脸库信息
	 * 
	 * @param id
	 * @return
	 */
	FaceVisitorLibraryDTO queryVisitorLibrary(int id);

}
