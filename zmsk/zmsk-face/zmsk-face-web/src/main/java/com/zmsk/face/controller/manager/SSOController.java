package com.zmsk.face.controller.manager;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zmsk.common.dto.SSOLoginResultDTO;
import com.zmsk.common.dto.ServiceResultDTO;
import com.zmsk.common.exception.UnauthorizedAccessException;
import com.zmsk.face.service.organization.OrganizationService;

/****
 * 当点登入Controller
 * 
 * @author warrior
 *
 */
@Controller
@RequestMapping("/manager/sso/")
public class SSOController {

	@Autowired
	private OrganizationService organizationService;

	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultDTO login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {

		Subject subject = SecurityUtils.getSubject();

		Session session = subject.getSession();

		String sessionId = session.getId().toString();

		// TODO 判断redis中是否存在sessionId对应的session

		// 创建token令牌，记录用的身份和凭证
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);

		try {
			subject.login(token);

			int organizationId = organizationService.queryOrganizationIdByUsername(username);

			return ServiceResultDTO.success(new SSOLoginResultDTO(sessionId, organizationId));

		} catch (AuthenticationException e) {
			throw new UnauthorizedAccessException(e.getMessage(), e);
		}

	}

	@RequestMapping(value = "tologin", method = RequestMethod.GET)
	@ResponseBody
	public ServiceResultDTO loginUI() {
		throw new UnauthorizedAccessException("Unauthorized access exception");
	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {

		// shiro退出登入
		SecurityUtils.getSubject().logout();

		// 回跳原地址
		String redirectUrl = request.getHeader("Referer");

		if (redirectUrl == null) {
			redirectUrl = "/";
		}

		return "redirect:" + redirectUrl;
	}

}
