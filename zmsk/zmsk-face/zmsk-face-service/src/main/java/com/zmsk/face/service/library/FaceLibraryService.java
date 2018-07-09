package com.zmsk.face.service.library;

import java.util.List;

import com.zmsk.face.pojo.FaceLibrary;
import com.zmsk.face.service.library.dto.FaceLibraryDTO;

/****
 * 人脸库操作服务接口声明
 * 
 * @author warrior
 *
 */
public interface FaceLibraryService {

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
	 * @param flag
	 *            黑白名单标识
	 * @param organizationId
	 *            组织Id
	 * @param groupId
	 *            所属分组Id
	 * @param equipmentIds
	 *            设备Id列表
	 * @return
	 */
	boolean addFaceLibrary(String name, int sex, String idNumber, String nation, String address, String avatar, String remark, int flag, int organizationId, int groupId, List<Integer> equipmentIds);

	/****
	 * 根据Id获取人脸库信息
	 * 
	 * @param id
	 *            主键
	 * @return
	 */
	FaceLibraryDTO queryLibraryById(int id);

	/***
	 * 修改人脸库
	 * 
	 * @param id
	 *            主键id
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
	 * @param flag
	 *            黑白名单标识
	 * @param organizationId
	 *            组织Id
	 * @param groupId
	 *            所属分组Id
	 * @param equipmentIds
	 *            设备Id列表
	 * @return
	 */
	boolean updateFaceLibrary(int id, String name, int sex, String idNumber, String nation, String address, String avatar, String remark, int flag, int groupId, List<Integer> equipmentIds);

	/****
	 * 查看组织下的人脸库列表
	 * 
	 * @param organizationId
	 *            组织Id
	 * @param flag
	 *            黑白名单标识
	 * @param pageSize
	 *            每页显示条数
	 * @param pageNum
	 *            当前页
	 * @return
	 */
	List<FaceLibraryDTO> queryLibraryList(int organizationId, int flag, int pageSize, int pageNum);

	/****
	 * 查看设备下的人脸库列表
	 * 
	 * @param equipmentId
	 *            设备Id
	 * @param flag
	 *            黑白名单标识
	 * @param pageSize
	 *            每页显示条数
	 * @param pageNum
	 *            当前页
	 * @return
	 */
	List<FaceLibraryDTO> queryLibraryListByEquipmentId(int equipmentId, int flag, int pageSize, int pageNum);

	/****
	 * 获取设备对应未同步的人脸库
	 * 
	 * @param equipmentId
	 *            设备Id
	 * @return
	 */
	List<FaceLibrary> queryUnSyncFaceLibrary(int equipmentId);

	/****
	 * 根据身份证Id获取人脸库信息
	 * 
	 * @param idNumber
	 *            身份证Id
	 * @return
	 */
	FaceLibrary queryLibraryBuIdNumber(String idNumber);
}
