package com.hamburgueria.admin.beans;

import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ApplicationScoped
public class AppMBean {

	private Boolean admin = false;
	private List<String> ufList = Arrays.asList("SP","AM","PR","SC","RS","RJ");
	private List<String> categoria = Arrays.asList("Bebidas","Lanches","Entradas","Aperitivos");

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public List<String> getUfList() {
		return ufList;
	}

	public void setUfList(List<String> ufList) {
		this.ufList = ufList;
	}

	public List<String> getCategoria() {
		return categoria;
	}

	public void setCategoria(List<String> categoria) {
		this.categoria = categoria;
	}
	
	
}
