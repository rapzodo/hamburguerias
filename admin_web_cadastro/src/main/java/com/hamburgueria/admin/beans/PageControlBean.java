package com.hamburgueria.admin.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class PageControlBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -50312468817053402L;
	private String page;
	
	public PageControlBean() {
		// TODO Auto-generated constructor stub
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

}
