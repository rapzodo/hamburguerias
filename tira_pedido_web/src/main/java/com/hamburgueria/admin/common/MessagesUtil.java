package com.hamburgueria.admin.common;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;


public class MessagesUtil {

	public static void createMsg(Severity severity, String componenteId, String summary,
			String detail) {
		FacesContext.getCurrentInstance().addMessage(componenteId, 
				new FacesMessage(severity, summary,detail));
	}
	public static void createSuccessMsg(String componenteId, String summary,
			String detail) {
		FacesContext.getCurrentInstance().addMessage(componenteId, 
				new FacesMessage(FacesMessage.SEVERITY_INFO, summary,detail));
	}
	public static void createFatalMsg(String componenteId, String summary,
			String detail) {
		FacesContext.getCurrentInstance().addMessage(componenteId, 
				new FacesMessage(FacesMessage.SEVERITY_FATAL, summary,detail));
	}
	public static void createErrMsg(String componenteId, String summary,
			String detail) {
		FacesContext.getCurrentInstance().addMessage(componenteId, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR, summary,detail));
	}

}
