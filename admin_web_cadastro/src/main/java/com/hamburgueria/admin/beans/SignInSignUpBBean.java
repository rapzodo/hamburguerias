package com.hamburgueria.admin.beans;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class SignInSignUpBBean {

	private String email;
	private String senha;
	
	public SignInSignUpBBean() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	
}
