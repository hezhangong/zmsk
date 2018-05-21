package com.zmsk.upms.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zmsk.common.dto.ServiceResultDTO;
import com.zmsk.common.exception.UnauthorizedAccessException;

/****
 * 当点登入Controller
 * 
 * @author warrior
 *
 */
@Controller
@RequestMapping("sso/")
public class SSOController {

	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResultDTO login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {

		Subject subject = SecurityUtils.getSubject();

		// 创建token令牌，记录用的身份和凭证
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);

		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			throw new UnauthorizedAccessException(e.getMessage(), e);
		}

		return ServiceResultDTO.success();
	}

	@RequestMapping(value = "tologin", method = RequestMethod.GET)
	public String loginUI() {
		return "login";
	}

}
