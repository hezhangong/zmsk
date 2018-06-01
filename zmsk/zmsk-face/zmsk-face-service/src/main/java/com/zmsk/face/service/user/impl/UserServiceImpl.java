package com.zmsk.face.service.user.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.zmsk.common.utils.IDMaker;
import com.zmsk.common.utils.StringDigestUtils;
import com.zmsk.face.mapper.FaceUserMapper;
import com.zmsk.face.pojo.FaceUser;
import com.zmsk.face.pojo.FaceUserExample;
import com.zmsk.face.pojo.FaceUserExample.Criteria;
import com.zmsk.face.service.user.UserOrganizationService;
import com.zmsk.face.service.user.UserRoleService;
import com.zmsk.face.service.user.UserService;
import com.zmsk.face.service.user.constants.UserConstants;

/****
 * 用户操作服务接口实现
 * 
 * @author warrior
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private UserOrganizationService userOrganizationService;

	@Autowired
	private FaceUserMapper userMapper;

	@Override
	public FaceUser queryUserByName(String username) {

		FaceUserExample example = new FaceUserExample();

		Criteria criteria = example.createCriteria();

		criteria.andUsernameEqualTo(username);

		List<FaceUser> list = userMapper.selectByExample(example);

		if (list == null || list.size() == 0) {
			return null;
		}

		return list.get(0);
	}

	@Override
	public boolean createUser(String username, String password, String realName, String avatar, String phone, String email, int sex, int roleId, int organizationId) {

		FaceUser user = queryUserByName(username);

		if (user != null) {
			logger.warn(" username {} exists when create user ", username);
			return false;
		}

		user = new FaceUser();

		user.setUsername(username);

		String salt = IDMaker.makeId();

		user.setSalt(salt);

		user.setPassword(StringDigestUtils.md5(password, salt));

		user.setRealname(realName);

		user.setAvatar(avatar);

		user.setPhone(phone);

		user.setEmail(email);

		user.setSex(sex);

		user.setLocked(0);

		user.setCtime(System.currentTimeMillis() / 1000);

		boolean success = userMapper.insert(user) > 0;

		if (!success) {
			logger.error("create user error username {}", username);
			return false;
		}

		// 关联用户角色
		userRoleService.createUserRoleReleation(user.getUserId(), roleId);

		// 关联用户组织
		userOrganizationService.createUserOrganizationReleation(user.getUserId(), organizationId);

		return success;
	}

	@Override
	public List<FaceUser> queryUserList(String search) {

		FaceUserExample example = new FaceUserExample();

		if (!StringUtils.isEmpty(search)) {
			example.or().andUsernameLike("%" + search + "%");
			example.or().andRealnameLike("%" + search + "%");
		}

		return userMapper.selectByExample(example);
	}

	@Override
	public boolean deleteUser(List<Integer> ids) {

		FaceUserExample example = new FaceUserExample();

		Criteria criteria = example.createCriteria();

		criteria.andUserIdIn(ids);

		return userMapper.deleteByExample(example) > 0;
	}

	@Override
	public boolean updateUser(int id, String realName, String phone, int sex, String avatar, String email, int roleId, int organizationId) {

		FaceUser user = userMapper.selectByPrimaryKey(id);

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

		if (roleId > 0) {
			// 修改用户角色
			userRoleService.updateUserRoleReleation(id, roleId);
		}

		if (organizationId > 0) {
			// 修改用户组织
			userOrganizationService.updateUserOrganizationReleation(id, organizationId);
		}

		return userMapper.updateByPrimaryKey(user) > 0;
	}

	@Override
	public int updatePassword(int userId, String newPassword, String oldPassword) {

		FaceUser user = userMapper.selectByPrimaryKey(userId);

		if (user == null) {
			logger.warn("Invalid userId {} can not fund user when update ", userId);
			return UserConstants.FAIL;
		}

		String salt = user.getSalt();

		String password = user.getPassword();

		String oldPasswordDigest = StringDigestUtils.md5(oldPassword, salt);

		if (!oldPasswordDigest.equals(password)) {
			logger.error("invalid old password when update user password userId {}", userId);
			return UserConstants.OLD_PASSWORD_ERROR;
		}

		String newPasswordDigest = StringDigestUtils.md5(newPassword, salt);

		user.setPassword(newPasswordDigest);

		boolean success = userMapper.updateByPrimaryKey(user) > 0;

		if (success) {
			return UserConstants.SUCCESS;
		}

		return UserConstants.FAIL;
	}

}
