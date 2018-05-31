package com.zmsk.face.service.organization.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zmsk.face.mapper.FaceOrganizationMapper;
import com.zmsk.face.mapper.custom.organization.CustomerOrganizationMapper;
import com.zmsk.face.pojo.FaceOrganization;
import com.zmsk.face.pojo.FaceOrganizationExample;
import com.zmsk.face.service.organization.OrganizationService;

/****
 * 组织操作服务接口实现
 * 
 * @author warrior
 *
 */
@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService {

	private static final Logger logger = LoggerFactory.getLogger(OrganizationServiceImpl.class);

	@Autowired
	private FaceOrganizationMapper organizationMapper;

	@Autowired
	private CustomerOrganizationMapper customerOrganizationMapper;

	@Override
	public boolean createOrganization(int pid, String name, String description) {

		FaceOrganization organization = new FaceOrganization();

		organization.setPid(pid);

		organization.setName(name);

		organization.setDescription(description);

		organization.setCtime(System.currentTimeMillis() / 1000);

		return organizationMapper.insert(organization) > 0;
	}

	@Override
	public boolean updateOrganization(int organizationId, int pid, String name, String description) {

		FaceOrganization organization = organizationMapper.selectByPrimaryKey(organizationId);

		if (organization == null) {
			logger.warn("Invalid organization id {} when update organization can not fund organization ", organizationId);
			return false;
		}

		if (pid != organization.getPid()) {
			organization.setPid(pid);
		}

		if (!name.equals(organization.getName())) {
			organization.setName(name);
		}

		if (!description.equals(organization.getDescription())) {
			organization.setDescription(description);
		}

		return organizationMapper.updateByPrimaryKey(organization) > 0;
	}

	@Override
	public boolean deleteOrganization(int organizationId) {
		return organizationMapper.deleteByPrimaryKey(organizationId) > 0;
	}

	@Override
	public List<FaceOrganization> queryOrganizationList(String search) {

		FaceOrganizationExample example = new FaceOrganizationExample();

		example.or().andNameLike("%" + search + "%");

		return organizationMapper.selectByExample(example);
	}

	@Override
	public int queryOrganizationIdByUsername(String userName) {
		return customerOrganizationMapper.queryOrganizationIdByUsername(userName);
	}

}
