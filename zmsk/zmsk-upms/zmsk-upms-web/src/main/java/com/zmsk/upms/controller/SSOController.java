package com.zmsk.upms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public String login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {

		
		return "";
	}

}
