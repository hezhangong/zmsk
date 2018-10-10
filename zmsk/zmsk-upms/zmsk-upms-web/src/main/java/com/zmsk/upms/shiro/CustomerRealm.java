package com.zmsk.upms.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.zmsk.upms.dto.ActiveUserDTO;
import com.zmsk.upms.pojo.UpmsPermission;
import com.zmsk.upms.pojo.UpmsRole;
import com.zmsk.upms.pojo.UpmsUser;
import com.zmsk.upms.service.permission.PermissionService;
import com.zmsk.upms.service.role.RoleService;
import com.zmsk.upms.service.user.UserService;

/****
 * 自定义realm
 * 
 * @author warrior
 *
 */
public class CustomerRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;

	@Autowired
	private PermissionService permissionService;

	@Autowired
	private RoleService roleService;

	@Override
	public String getName() {
		return "CustomerRealm";
	}

	// 授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		// 获取身份信息
		ActiveUserDTO activeUser = (ActiveUserDTO) principals.getPrimaryPrincipal();

		// 获取用户Id
		int userId = activeUser.getUserId();

		// 获取用户权限信息
		List<UpmsPermission> permissions = permissionService.queryPermissionListByUserId(userId);

		// 获取用户角色信息
		List<UpmsRole> upmsRoles = roleService.queryRoleListByUserId(userId);

		Set<String> roles = new HashSet<>();

		for (UpmsRole role : upmsRoles) {
			String name = role.getName();

			if (StringUtils.isEmpty(name)) {
				continue;
			}
			roles.add(name);
		}

		// 构建shiro授权信息
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

		for (UpmsPermission permission : permissions) {
			simpleAuthorizationInfo.addStringPermission(permission.getPermissionValue());
		}

		// 新增角色信息
		simpleAuthorizationInfo.addRoles(roles);

		return simpleAuthorizationInfo;
	}

	// 认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		// 从token中 获取用户身份信息
		String userAccount = (String) token.getPrincipal();

		// 从DB中获取username对应的用户信息
		UpmsUser user = userService.queryUserByName(userAccount);

		if (user == null) {
			return null;
		}

		String salt = user.getSalt();

		String username = user.getRealname();

		int userId = user.getUserId();

		ActiveUserDTO activeUser = new ActiveUserDTO(userId, userAccount, username);

		List<UpmsPermission> menus = permissionService.queryMenuListByUserId(userId);

		activeUser.setMenus(menus);

		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(activeUser, user.getPassword(), ByteSource.Util.bytes(salt), this.getName());

		return simpleAuthenticationInfo;
	}

	public static void main(String[] args) {
		String hashAlgorithmName = "MD5";
		String credentials = "123456";
		int hashIterations = 1;
		ByteSource credentialsSalt = ByteSource.Util.bytes("4p7lrs7m9vsts8o3b2og");
		Object obj = new SimpleHash(hashAlgorithmName, credentials, credentialsSalt, hashIterations);
		System.out.println(obj);
	}
}
