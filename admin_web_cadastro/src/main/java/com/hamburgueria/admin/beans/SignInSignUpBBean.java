package com.hamburgueria.admin.beans;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.acesso.ejb.sbeans.SignInSignUpSBean;

import com.hamburgueria.constants.CommonConstants;
import com.hamburgueria.mongo.entities.Cliente;
import com.hamburgueria.morphia.dao.ClienteDao;

@ManagedBean
public class SignInSignUpBBean {

	private String email;
	private String senha;
	@EJB
	private SignInSignUpSBean signInBean;
	private Cliente cliente;
	
	public void logar(){
		cliente = (Cliente)signInBean.loginCliente(email,senha, CommonConstants.TIPO_CLIENTE);
		if(cliente == null){
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR,"Falha no Login","Nao existe"));
		}
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_INFO,cliente.getEmailId(),cliente.getPassword()));
		
//		ClienteDao dao = new ClienteDao();
//    	Map<String, Object> params = new HashMap<String, Object>();
//    	params.put("emailId", email);
//    	params.put("password",params);
//    	dao.getDs().createQuery(Cliente.class).field("emailId").equal(email).field("password").equal(senha).get();
	}
	
	public void signUp(){
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("chamou o cadastrar com sucesso!"));
	}
	
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
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	
}
