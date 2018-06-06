package com.zmsk.face.service.library;

import java.util.List;

import com.zmsk.common.pagehelper.PageInfo;
import com.zmsk.face.pojo.FaceLibrary;

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
	 * @param equipmentIds
	 *            设备Id列表
	 * @return
	 */
	boolean addFaceLibrary(String name, int sex, String idNumber, String nation, String address, String avatar, String remark, int flag, int organizationId, List<Integer> equipmentIds);

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
	List<FaceLibrary> queryLibraryList(int organizationId, int flag, int pageSize, int pageNum);

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
	List<FaceLibrary> queryLibraryListByEquipmentId(int equipmentId, int flag, int pageSize, int pageNum);
}
