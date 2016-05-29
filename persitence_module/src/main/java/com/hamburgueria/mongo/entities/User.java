package com.hamburgueria.mongo.entities;

import java.util.Date;

import org.mongodb.morphia.annotations.Entity;

@Entity(value="usuarios",noClassnameStored=true)
public class User extends UsuarioSistema{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8374432547758380173L;
	/**
	 * 
	 */
	private String perfil;
	private Date ultimoLogin;
	private String status;
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	public Date getUltimoLogin() {
		return ultimoLogin;
	}
	public void setUltimoLogin(Date ultimoLogin) {
		this.ultimoLogin = ultimoLogin;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
