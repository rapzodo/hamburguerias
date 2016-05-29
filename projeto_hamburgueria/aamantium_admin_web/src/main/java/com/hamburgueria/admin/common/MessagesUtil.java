package com.hamburgueria.admin.common;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

public class MessagesUtil {

	private static FacesContext facesContext = FacesContext
			.getCurrentInstance();

	public static void createMsg(Severity severity, String componenteId,
			String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(componenteId,
				new FacesMessage(severity, summary, detail));
	}

	public static void createSuccessMsg(String componenteId, String summary,
			String detail) {
		createMsg(FacesMessage.SEVERITY_INFO, componenteId, summary, detail);
	}

	public static void createFatalMsg(String componenteId, String summary,
			String detail) {
		createMsg(FacesMessage.SEVERITY_FATAL, componenteId, summary, detail);
	}

	public static void createErrMsg(String componenteId, String summary,
			String detail) {
		createMsg(FacesMessage.SEVERITY_ERROR, componenteId, summary, detail);
	}

	public static String getResourceMessage(String bundleVar, String key) {
		ResourceBundle resourceBundle = facesContext.getApplication()
				.getResourceBundle(facesContext, bundleVar);
		return resourceBundle.getString(key);
	}

}
