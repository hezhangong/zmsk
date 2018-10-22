package com.zmsk.face.controller.manager.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zmsk.common.dto.BaseResultCode;
import com.zmsk.common.dto.ServiceResultDTO;
import com.zmsk.face.pojo.FaceFactory;
import com.zmsk.face.service.factory.FactoryService;

/****
 * 管理员工厂操作
 * 
 */
@Controller
@RequestMapping("manager/admin/factory/")
public class AdminFactoryController {

	@Autowired
	private FactoryService factoryService;

	/****
	 * 新增工厂
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
	@RequestMapping(value = "create", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultDTO createFactory(@RequestParam(value = "name") String name, @RequestParam(value = "shortName") String shortName,
			@RequestParam(value = "linkman", required = false) String linkman, @RequestParam(value = "phone", required = false) String phone) {

		boolean success = factoryService.createFactory(name, shortName, linkman, phone);

		if (!success) {
			return new ServiceResultDTO(BaseResultCode.ORGANIZATION_OPERATION_ERROR, "create factory error");
		}

		return ServiceResultDTO.success();
	}

	/****
	 * 修改工厂
	 * 
	 * @param factoryId
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
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultDTO updateFactory(@RequestParam(value = "factoryId") int factoryId, @RequestParam(value = "name") String name, @RequestParam(value = "shortName") String shortName,
			@RequestParam(value = "linkman", required = false) String linkman, @RequestParam(value = "phone", required = false) String phone) {

		boolean success = factoryService.updateFactory(factoryId, name, shortName, linkman, phone);

		if (!success) {
			return new ServiceResultDTO(BaseResultCode.ORGANIZATION_OPERATION_ERROR, "update factory error");
		}

		return ServiceResultDTO.success();
	}

	/****
	 * 删除工厂
	 * 
	 * @param factoryId
	 *            工厂Id
	 * @return
	 */
	@RequestMapping(value = "delete/{factoryId}", method = RequestMethod.GET)
	@ResponseBody
	public ServiceResultDTO deleteFactory(@PathVariable(value = "factoryId") int factoryId) {

		boolean success = factoryService.deleteFactory(factoryId);

		if (!success) {
			return new ServiceResultDTO(BaseResultCode.ORGANIZATION_OPERATION_ERROR, "delete factory fail");
		}

		return ServiceResultDTO.success();
	}
	
	/****
	 * 获取工厂名称查询工厂列表
	 * 
	 * @param factoryName
	 *            工厂名称
	 * @return
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	@ResponseBody
	public ServiceResultDTO queryFactoryList(@RequestParam(value = "factoryName", required = false) String factoryName) {
		
		List<FaceFactory> result = factoryService.queryFactoryList(factoryName);
		
		return ServiceResultDTO.success(result);
	}

	/****
	 * 根据工厂Id获取工厂信息
	 * 
	 * @param factoryId
	 *            组织Id
	 * @return
	 */
	@RequestMapping(value = "{factoryId}", method = RequestMethod.GET)
	@ResponseBody
	public ServiceResultDTO queryFactoryById(@PathVariable(value = "factoryId") int factoryId) {

		FaceFactory factory = factoryService.queryFactoryById(factoryId);

		return ServiceResultDTO.success(factory);
	}
}
