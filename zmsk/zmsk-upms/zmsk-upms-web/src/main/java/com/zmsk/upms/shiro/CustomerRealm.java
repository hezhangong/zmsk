package com.zmsk.upms.shiro;

import java.util.List;

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

import com.zmsk.upms.dto.ActiveUserDTO;
import com.zmsk.upms.pojo.UpmsPermission;
import com.zmsk.upms.pojo.UpmsUser;
import com.zmsk.upms.service.permission.PermissionService;
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

		// 构建shiro授权信息
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

		for (UpmsPermission permission : permissions) {
			simpleAuthorizationInfo.addStringPermission(permission.getPermissionValue());
		}

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
