package com.zmsk.common.exception.handler;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.zmsk.common.exception.ZmskException;

/***
 * 控制层异常统一处理类 如果controller中抛出异常，如果类型不是modelview，则转入此类中处理
 * 
 * @author majun@12301.cc
 *
 */
public class RestHandlerExceptionResolver implements HandlerExceptionResolver {

	private static final Logger logger = LoggerFactory.getLogger(RestHandlerExceptionResolver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

		response.setCharacterEncoding("UTF-8");

		logger.warn(ex.getClass().getSimpleName() + " when process rest request from host " + request.getRemoteHost());

		try {
			/**
			 * 大于或等于500的异常信息和堆栈记录到ERROR日志中，其他类型只打出异常信息到WARN日志中
			 */
			if (ex instanceof ZmskException && ((ZmskException) ex).getHttpCode() >= 500) {
				logger.error(ex.getMessage(), ex);
			}

			if (ex instanceof ZmskException) {
				return handleZmskException((ZmskException) ex, request, response, handler);
			}

			if (ex instanceof NullPointerException) {
				return handleNullPointerException((NullPointerException) ex, request, response, handler);
			}

			if (ex instanceof IllegalArgumentException) {
				return handleIllegalArgumentException((IllegalArgumentException) ex, request, response, handler);
			}

			if (ex instanceof SQLException) {
				return handleSQLException((SQLException) ex, request, response, handler);
			}

			logger.error("exception when do exception resolver with path " + request.getPathInfo(), ex);

			return handleOtherException(ex, request, response, handler);

		} catch (Exception e) {
			logger.error("exception when do exception resolver with path {}", request.getPathInfo(), e);
			return null;
		}
	}

	protected ModelAndView handleZmskException(ZmskException ex, HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
		HttpStatus status = HttpStatus.valueOf(ex.getHttpCode());
		if (status == null) {
			logger.warn("invalid code for JumpPacketException with code {}", ex.getCode());
			status = HttpStatus.BAD_REQUEST;
		}

		return new RestModelAndView("", ex.getMessage(), ex.getClass().getSimpleName(), status, ex.getCode());
	}

	protected ModelAndView handleOtherException(Exception ex, HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

		return new RestModelAndView("", ex.getMessage(), ex.getClass().getSimpleName(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	protected ModelAndView handleNullPointerException(NullPointerException ex, HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

		return new RestModelAndView("", ex.getMessage(), ex.getClass().getSimpleName(), HttpStatus.BAD_REQUEST);
	}

	protected ModelAndView handleIllegalArgumentException(IllegalArgumentException ex, HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

		return new RestModelAndView("", ex.getMessage(), ex.getClass().getSimpleName(), HttpStatus.BAD_REQUEST);
	}

	protected ModelAndView handleSQLException(SQLException ex, HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
		return new RestModelAndView("", ex.getMessage(), ex.getClass().getSimpleName(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
