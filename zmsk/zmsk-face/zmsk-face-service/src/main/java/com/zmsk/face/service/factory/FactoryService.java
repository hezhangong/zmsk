package com.zmsk.face.service.factory;

import java.util.List;

import com.zmsk.face.pojo.FaceFactory;

/****
 * 工厂操作服务接口声明
 * 
 * @author warrior
 *
 */
public interface FactoryService {

	/****
	 * 创建工厂信息
	 * 
	 * @param name
	 *            工厂名称
	 * @param shortName
	 *            工厂简称
	 * @param linkman
	 *            联系人
	 * @param phone
	 *            电话
	 * @return
	 */
	boolean createFactory(String name, String shortName, String linkman, String phone);

	/****
	 * 修改工厂信息
	 * 
	 * @param id
	 *            工厂Id
	 * @param name
	 *            工厂名称
	 * @param shortName
	 *            工厂简称
	 * @param linkman
	 *            联系人
	 * @param phone
	 *            电话
	 * @return
	 */
	boolean updateFactory(int id, String name, String shortName, String linkman, String phone);

	/****
	 * 删除工厂
	 * 
	 * @param id
	 *            工厂Id
	 * @return
	 */
	boolean deleteFactory(int id);

	/****
	 * 查询工厂列表
	 * 
	 * @param search
	 * @return
	 */
	List<FaceFactory> queryFactoryList(String search);
	
	/****
	 * 根据工厂Id获取工厂信息
	 * 
	 * @param id
	 *             工厂Id
	 * @return
	 */
	FaceFactory queryFactoryById(int id);
}
