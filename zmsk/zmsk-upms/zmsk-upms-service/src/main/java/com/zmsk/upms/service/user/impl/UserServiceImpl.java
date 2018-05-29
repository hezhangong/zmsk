package com.zmsk.upms.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zmsk.upms.mapper.UpmsUserMapper;
import com.zmsk.upms.pojo.UpmsUser;
import com.zmsk.upms.pojo.UpmsUserExample;
import com.zmsk.upms.pojo.UpmsUserExample.Criteria;
import com.zmsk.upms.service.user.UserService;

/****
 * 用户服务操作service接口实现
 * 
 * @author warrior
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UpmsUserMapper userMapper;

	@Override
	public UpmsUser queryUserByName(String username) {

		UpmsUserExample example = new UpmsUserExample();

		Criteria criteria = example.createCriteria();

		criteria.andUsernameEqualTo(username);

		List<UpmsUser> list = userMapper.selectByExample(example);

		if (list == null || list.size() == 0) {
			return null;
		}

		return list.get(0);
	}

}
