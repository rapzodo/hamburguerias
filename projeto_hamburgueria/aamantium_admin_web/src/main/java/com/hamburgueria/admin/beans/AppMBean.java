package com.hamburgueria.admin.beans;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import com.hamburgueria.mongo.entities.Categoria;
import com.hamburgueria.mongo.entities.Configuracoes;
import com.hamburgueria.morphia.dao.CategoriaDao;
import com.hamburgueria.morphia.dao.ConfiguracoesDao;

@ManagedBean
@ApplicationScoped
public class AppMBean {

	private Boolean admin = false;
	private List<String> ufList = Arrays.asList("SP","AM","PR","SC","RS","RJ");
	private List<String> profiles = Arrays.asList("ADMIN","SIMPLES");
	private List<String> categoria;
	private List<Categoria> categorias;
	private List<Configuracoes> configs;

	@PostConstruct
	public void init(){
		categoria = new CategoriaDao().listDistinct("descricao");
		configs = new ConfiguracoesDao().listAll();
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
	
	public String getAppContext(){
		return FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
	}
	public String getImgRealPath(){
		return FacesContext.getCurrentInstance().getExternalContext().getRequestPathInfo();
	}

	public List<Configuracoes> getConfigs() {
		return configs;
	}

	public void setConfigs(List<Configuracoes> configs) {
		this.configs = configs;
	}

	public List<String> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<String> profiles) {
		this.profiles = profiles;
	}
}
