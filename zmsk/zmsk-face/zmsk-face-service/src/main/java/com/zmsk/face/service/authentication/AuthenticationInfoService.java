package com.zmsk.face.service.authentication;

import java.util.List;

import com.zmsk.face.pojo.FaceAuthenticationInfo;
import com.zmsk.face.service.authentication.dto.AuthenticationInfoDTO;
import com.zmsk.face.service.authentication.dto.VisistorInfoDTO;

/****
 * 认证信息操作服务接口声明
 * 
 * @author warrior
 *
 */
public interface AuthenticationInfoService {

	/****
	 * 添加认证信息记录
	 * 
	 * @param name
	 *            姓名
	 * @param idNumber
	 *            身份证号
	 * @param nation
	 *            民族
	 * @param address
	 *            地址
	 * @param avatar
	 *            头像地址
	 * @param sex
	 *            性别
	 * @param type
	 *            类型
	 * @param result
	 *            结果
	 * @param deviceNumber
	 *            设备登入账号
	 * @param groupId
	 *            所属分组Id
	 * @param authTimeStamp
	 *            认证时间戳
	 * @param idcardImage
	 *            身份证图片
	 * @param idcardInfo
	 *            身份证基本信息
	 * @param similarDegree
	 *            相似度
	 * @return
	 */
	boolean addAuthenticationInfo(String name, String idNumber, String nation, String address, String avatar, int sex, int type, int result, String deviceNumber, int groupId, long authTimeStamp, String idcardImage, String idcardInfo, String similarDegree);

	/****
	 * 查询认证信息记录
	 * 
	 * @param search
	 *            查询条件
	 * @param organizationId
	 *            组织Id
	 * @param pageSize
	 *            每页显示条数
	 * @param pageNum
	 *            第几页
	 * @return
	 */
	List<AuthenticationInfoDTO> queryAuthenticationInfo(String search, int organizationId, int pageSize, int pageNum);

	/****
	 * 获取组织对应警告认证记录
	 * 
	 * @param search
	 *            查询条件
	 * @param organizationId
	 *            组织Id
	 * @param pageSize
	 *            每页显示条数
	 * @param pageNum
	 *            页数
	 * @return
	 */
	List<AuthenticationInfoDTO> queryWarnAuthenticationInfo(String search, int organizationId, int pageSize, int pageNum);

	/****
	 * 根据id获取认证记录信息
	 * 
	 * @param id
	 *            主键Id
	 * @return
	 */
	FaceAuthenticationInfo queryAuthenticationInfoById(int id);

	/****
	 * 登记成访客
	 * 
	 * @param id
	 *            主键Id
	 * @param remark
	 *            备注信息
	 * @return
	 */
	boolean registerVisitor(int id, String remark);

	/****
	 * 获取访客列表
	 * 
	 * @param organizationId
	 *            组织Id
	 * @return
	 */
	List<VisistorInfoDTO> queryVisitorByOrganizationId(int organizationId);
}
