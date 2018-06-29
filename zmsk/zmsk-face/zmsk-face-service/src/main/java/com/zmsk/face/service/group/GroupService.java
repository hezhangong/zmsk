package com.zmsk.face.service.group;

import java.util.List;

import com.zmsk.face.pojo.FaceGroup;

/****
 * 所属分组服务操作接口声明
 * 
 * @author warrior
 *
 */
public interface GroupService {

	/****
	 * 创建所属分组
	 * 
	 * @param organizationId
	 *            组织Id
	 * @param groupName
	 *            分组名称
	 * @return
	 */
	boolean createGroup(int organizationId, String groupName);

	/****
	 * 根据组织Id获取所属分组信息
	 * 
	 * @param organizationId
	 *            组织Id
	 * @return
	 */
	List<FaceGroup> queryGroupByorganizationId(int organizationId);

	/****
	 * 根据Id获取组名
	 * 
	 * @param groupId
	 * @return
	 */
	String queryGroupNameById(int groupId);

	/****
	 * 根据Id获取分组信息
	 * 
	 * @param id
	 *            主键
	 * @return
	 */
	FaceGroup queryGroupById(int id);

	/****
	 * 更改分组名称
	 * 
	 * @param id
	 *            主键Id
	 * @param name
	 *            分组名称
	 * @return
	 */
	boolean updateGroupName(int id, String name);
}
