package com.zmsk.face.service.group.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zmsk.face.mapper.FaceGroupMapper;
import com.zmsk.face.pojo.FaceGroup;
import com.zmsk.face.pojo.FaceGroupExample;
import com.zmsk.face.pojo.FaceGroupExample.Criteria;
import com.zmsk.face.service.group.GroupService;

/****
 * 所属分组操作服务接口实现
 * 
 * @author warrior
 *
 */
@Service
@Transactional
public class GroupServiceImpl implements GroupService {

	@Autowired
	private FaceGroupMapper groupMapper;

	@Override
	public boolean createGroup(int organizationId, String groupName) {

		FaceGroup group = new FaceGroup();

		group.setOrganizationId(organizationId);

		group.setGroupName(groupName);

		group.setCtime(System.currentTimeMillis() / 1000);

		return groupMapper.insert(group) > 0;
	}

	@Override
	public List<FaceGroup> queryGroupByorganizationId(int organizationId) {

		FaceGroupExample example = new FaceGroupExample();

		Criteria criteria = example.createCriteria();

		criteria.andOrganizationIdEqualTo(organizationId);

		return groupMapper.selectByExample(example);
	}

	@Override
	public String queryGroupNameById(int groupId) {

		FaceGroup group = groupMapper.selectByPrimaryKey(groupId);

		if (group == null) {
			return "暂无分组";
		}

		return group.getGroupName();
	}

}
