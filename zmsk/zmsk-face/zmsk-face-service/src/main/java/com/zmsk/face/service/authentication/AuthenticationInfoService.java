package com.zmsk.face.service.authentication;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.zmsk.common.utils.DataTablePageUtil;
import com.zmsk.face.dto.authentic.AuthenticationInfoDTO;
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
	 * @param start
	 *            开始的数据行数
	 * @param length
	 *            每页的数据数
	 * @param draw
	 * @return
	 */
	DataTablePageUtil<AuthenticationInfoDTO> queryAuthenticationInfo(String search, int organizationId, int start, int length, int draw);

	/****
	 * 获取组织对应警告认证记录
	 * 
	 * @param search
	 *            查询条件
	 * @param organizationId
	 *            组织Id
	 * @param start
	 *            开始的数据行数
	 * @param length
	 *            每页的数据数
	 * @param draw
	 * @return
	 */
	DataTablePageUtil<AuthenticationInfoDTO>  queryWarnAuthenticationInfo(String search, int organizationId, int start, int length, int draw);

	/****
	 * 根据id获取认证记录信息
	 * 
	 * @param id
	 *            主键Id
	 * @return
	 */
	AuthenticationInfoDTO queryAuthenticationInfoById(int id);

	/****
	 * 登记成访客
	 * 
	 * @param id
	 *            主键Id
	 * @param remark
	 *            备注信息
	 * @param groupId
	 *            分组Id
	 * @return
	 */
	boolean registerVisitor(int id, String remark, int groupId);

	/****
	 * 获取访客列表
	 * 
	 * @param organizationId
	 *            组织Id
	 * @return
	 */
	List<VisistorInfoDTO> queryVisitorByOrganizationId(int organizationId);

	/****
	 * 导出认证记录
	 * 
	 * @param organizationId
	 *            组织Id
	 * @param dateStr
	 *            日期字符串
	 * @return
	 */
	Workbook exportAuthenticationInfo(int organizationId, String dateStr);
}
