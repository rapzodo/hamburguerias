package com.hamburgueria.admin.common;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class HTTPUtils {

	public static Object getParameter(String param){
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		return request.getParameter(param);
	}
	
	public void killSession(){
		HttpSession session =(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.invalidate();
	}
	
}
