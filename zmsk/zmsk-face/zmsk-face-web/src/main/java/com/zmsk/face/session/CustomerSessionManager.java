package com.zmsk.face.session;

import java.io.Serializable;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

/****
 * 自定义session manager
 * 
 * @author warrior
 *
 */
public class CustomerSessionManager extends DefaultWebSessionManager {

	private static final String AUTHORIZATION = "Authorization";

	private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";

	public CustomerSessionManager() {
		super();
	}

	@Override
	protected Serializable getSessionId(ServletRequest request, ServletResponse response) {

		// 从请求头中获取
		String id = WebUtils.toHttp(request).getHeader(AUTHORIZATION);

		if (StringUtils.isEmpty(id)) {

			String name = this.getSessionIdName();

			id = request.getParameter(name);

			if (StringUtils.isEmpty(id)) {
				id = request.getParameter(name.toLowerCase());
			}
		}

		if (!StringUtils.isEmpty(id)) {

			request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, REFERENCED_SESSION_ID_SOURCE);

			request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);

			request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);

			return id;
		}

		return null;
	}

	private String getSessionIdName() {
		String name = this.getSessionIdCookie() != null ? this.getSessionIdCookie().getName() : null;
		if (name == null) {
			name = "JSESSIONID";
		}
		return name;
	}

}
