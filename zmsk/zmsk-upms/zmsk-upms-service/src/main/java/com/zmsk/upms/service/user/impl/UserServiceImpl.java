package com.zmsk.upms.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zmsk.common.utils.IDMaker;
import com.zmsk.common.utils.StringDigestUtils;
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

	@Override
	public boolean createUser(String username, String password, String realName, String avatar, String phone, String email, int sex) {

		UpmsUserExample example = new UpmsUserExample();

		Criteria criteria = example.createCriteria();

		criteria.andUsernameEqualTo(username);

		long count = userMapper.countByExample(example);

		if (count > 0) {
			return false;
		}

		UpmsUser user = new UpmsUser();

		user.setUsername(username);

		String salt = IDMaker.makeId();

		user.setSalt(salt);

		user.setPassword(StringDigestUtils.md5(password + salt));

		user.setRealname(realName);

		user.setAvatar(avatar);

		user.setPhone(phone);

		user.setEmail(email);

		user.setSex(sex);

		user.setLocked(0);

		user.setCtime(System.currentTimeMillis() / 1000);

		return userMapper.insert(user) > 0;
	}

}
