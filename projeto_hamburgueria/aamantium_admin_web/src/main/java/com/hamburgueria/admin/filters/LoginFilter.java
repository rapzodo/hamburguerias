package com.hamburgueria.admin.filters;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hamburgueria.admin.beans.SignInSignUpMBean;
import com.hamburgueria.constants.CommonConstants;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.FORWARD,
		DispatcherType.INCLUDE, DispatcherType.ERROR }, urlPatterns = { "/private/*" })
public class LoginFilter implements Filter {

	private static final String LOGIN_URL = "/publico/login.xhtml";

	/**
	 * Default constructor.
	 */
	public LoginFilter() {
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		Boolean isLogged = session.getAttribute(CommonConstants.LOGGED_USER) != null;
		Map<String, Cookie> cookiesMap = getCookiesMap(req);
		Cookie idCookie = cookiesMap.get(SignInSignUpMBean.KEEP_COOKIE_KEY);
		if(req.getServletPath().equals(LOGIN_URL)){
			resp.sendRedirect(req.getContextPath() + "404.xhtml");
		}
		if (isLogged || idCookie != null) {
			chain.doFilter(request, response);
		} else {
			resp.sendRedirect(req.getContextPath() + LOGIN_URL);
		}
	}

	private Map<String, Cookie> getCookiesMap(HttpServletRequest req) {
		Cookie[] cookies = req.getCookies();
		Map<String, Cookie> cookiesMap = new HashMap<String, Cookie>();
		for (Cookie cookie : cookies) {
			cookiesMap.put(cookie.getName(), cookie);
		}
		return cookiesMap;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
