package com.zmsk.face.service.organization;

import java.util.List;

import com.zmsk.face.pojo.FaceOrganization;

/****
 * 组织操作服务接口声明
 * 
 * @author warrior
 *
 */
public interface OrganizationService {

	/****
	 * 创建组织结构
	 * 
	 * @param organizationId
	 *            组织Id
	 * @param pid
	 *            所属上级
	 * @param name
	 *            名称
	 * @param description
	 *            组织描述
	 * @return
	 */
	boolean createOrganization(int pid, String name, String description);

	/****
	 * 修改组织结构
	 * 
	 * @param organizationId
	 *            组织Id
	 * @param pid
	 *            所属上级
	 * @param name
	 *            名称
	 * @param description
	 *            组织描述
	 * @return
	 */
	boolean updateOrganization(int organizationId, int pid, String name, String description);

	/****
	 * 删除组织
	 * 
	 * @param organizationId
	 *            组织Id
	 * @return
	 */
	boolean deleteOrganization(int organizationId);

	/****
	 * 查询组织列表
	 * 
	 * @param search
	 * @return
	 */
	List<FaceOrganization> queryOrganizationList(String search);
	
	/****
	 * 根据组织Id获取工厂信息
	 * 
	 * @param organizationId
	 *            组织Id
	 * @return
	 */
	FaceOrganization queryOrganizationById(int organizationId);

	/***
	 * 根据用户名获取组织Id
	 * 
	 * @param userName
	 *            用户名
	 * @return
	 */
	int queryOrganizationIdByUsername(String userName);
}
