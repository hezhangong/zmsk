package com.zmsk.upms.service.user.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

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
			logger.warn(" username {} exists when create user ", username);
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

	@Override
	public List<UpmsUser> queryUserList(String search) {

		UpmsUserExample example = new UpmsUserExample();

		if (!StringUtils.isEmpty(search)) {
			example.or().andUsernameLike("%" + search + "%");
			example.or().andRealnameLike("%" + search + "%");
		}

		return userMapper.selectByExample(example);
	}

	@Override
	public boolean deleteUser(List<Integer> ids) {

		UpmsUserExample example = new UpmsUserExample();

		Criteria criteria = example.createCriteria();

		criteria.andUserIdIn(ids);

		return userMapper.deleteByExample(example) > 0;
	}

	@Override
	public boolean updateUser(int id, String realName, String phone, int sex, String avatar, String email) {

		UpmsUser user = userMapper.selectByPrimaryKey(id);

		if (user == null) {
			logger.warn("Invalid userId {} can not fund user when update user info ", id);
			return false;
		}

		if (!StringUtils.isEmpty(realName) && !realName.equals(user.getRealname())) {
			user.setRealname(realName);
		}

		if (!StringUtils.isEmpty(phone) && !phone.equals(user.getPhone())) {
			user.setPhone(phone);
		}

		if (sex > 0 && sex != user.getSex()) {
			user.setSex(sex);
		}

		if (!StringUtils.isEmpty(avatar) && !avatar.equals(user.getAvatar())) {
			user.setAvatar(avatar);
		}

		if (!StringUtils.isEmpty(email) && !email.equals(user.getEmail())) {
			user.setEmail(email);
		}

		return userMapper.updateByPrimaryKey(user) > 0;
	}

}
