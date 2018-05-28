package com.zmsk.face.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.zmsk.face.pojo.FacePermission;
import com.zmsk.face.pojo.FaceRole;
import com.zmsk.face.pojo.FaceUser;
import com.zmsk.face.service.permission.PermissionService;
import com.zmsk.face.service.role.RoleService;
import com.zmsk.face.service.user.UserService;

/****
 * 自定义realm
 * 
 * @author warrior
 *
 */
public class CustomerRealm extends AuthorizingRealm {

	@Override
	public String getName() {
		return "CustomerRealm";
	}

	@Autowired
	private UserService userService;

	@Autowired
	private PermissionService permissionService;

	@Autowired
	private RoleService roleService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		// 获取身份信息
		FaceUser user = (FaceUser) principals.getPrimaryPrincipal();

		// 获取用户Id
		int userId = user.getUserId();

		// 获取用户权限信息
		List<FacePermission> permissions = permissionService.queryPermissionListByUserId(userId);

		// 获取用户角色信息
		List<FaceRole> faceRoles = roleService.queryRoleListByUserId(userId);

		Set<String> roles = new HashSet<>();

		for (FaceRole role : faceRoles) {
			String name = role.getName();

			if (StringUtils.isEmpty(name)) {
				continue;
			}
			roles.add(name);
		}

		// 构建shiro授权信息
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

		for (FacePermission permission : permissions) {
			simpleAuthorizationInfo.addStringPermission(permission.getPermissionValue());
		}

		// 新增角色信息
		simpleAuthorizationInfo.addRoles(roles);

		return simpleAuthorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		// 从token中 获取用户身份信息
		String userAccount = (String) token.getPrincipal();

		// 从DB中获取username对应的用户信息
		FaceUser user = userService.queryUserByName(userAccount);

		if (user == null) {
			return null;
		}

		String salt = user.getSalt();

		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(salt), this.getName());

		return simpleAuthenticationInfo;
	}

}
