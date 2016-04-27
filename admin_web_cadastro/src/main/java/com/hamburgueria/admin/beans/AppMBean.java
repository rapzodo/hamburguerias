package com.hamburgueria.admin.beans;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.hamburgueria.mongo.entities.Categoria;
import com.hamburgueria.morphia.dao.CategoriaDao;

@ManagedBean
@ApplicationScoped
public class AppMBean {

	private Boolean admin = false;
	private List<String> ufList = Arrays.asList("SP","AM","PR","SC","RS","RJ");
	private List<String> categoria;
	private List<Categoria> categorias;

	@PostConstruct
	public void init(){
		categoria = new CategoriaDao().listDistinct("descricao");
	}
	
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

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}
	
	
}
