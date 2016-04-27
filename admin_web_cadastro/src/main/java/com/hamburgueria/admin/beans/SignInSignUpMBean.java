package com.hamburgueria.admin.beans;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.acesso.ejb.sbeans.SignInSignUpSBean;

import com.hamburgueria.admin.common.MessagesUtil;
import com.hamburgueria.constants.CommonConstants;
import com.hamburgueria.constants.ServiceConstants;
import com.hamburgueria.mongo.entities.Cliente;
import com.hamburgueria.mongo.entities.Endereco;
import com.hamburgueria.mongo.entities.Restaurante;
import com.hamburgueria.morphia.dao.RestauranteDao;

@ManagedBean
@SessionScoped
public class SignInSignUpMBean{

	private String email;
	private String senha;
	private String brand = "HamburgWeb";
	@EJB
	private SignInSignUpSBean signInBean;
	@ManagedProperty(value="#{restauranteMbean}")
	private RestauranteMBean restaurantMBean;
	private Cliente cliente;
	private Restaurante restaurante;
	private Endereco endereco;
	
	@PostConstruct
	public void init(){
		cliente = new Cliente();
		endereco = new Endereco();
		restaurante = new Restaurante();
	}
	
	public String logar(){
		Restaurante restauranteUser = (Restaurante)signInBean.loginUser(email,senha, CommonConstants.TIPO_RESTAURANTE);
		if(restauranteUser == null){
			Cliente clienteUser = (Cliente)signInBean.loginUser(email,senha, CommonConstants.TIPO_CLIENTE);
			if(clienteUser == null){
				MessagesUtil.createErrMsg("msgs","Usuário Não encontrado","e-mail ou senha não conferem");
			}else{
				cliente = clienteUser;
				return null;
			}
		}else{
			restaurante = restauranteUser;
			brand = restaurante.getRazaoSocial();
		}
		return "/app/home";
	}
	
	public String signUpRestaurante(){
		restaurantMBean.setModel(restaurante);
		restaurantMBean.cadastraRestaurante();
		brand = restaurante.getRazaoSocial();
		return "/app/home";
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
	public Restaurante getRestaurante() {
		return restaurante;
	}
	public void setRestaurante(Restaurante restaurante) {
		this.restaurante = restaurante;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public RestauranteMBean getRestaurantMBean() {
		return restaurantMBean;
	}

	public void setRestaurantMBean(RestauranteMBean restaurantMBean) {
		this.restaurantMBean = restaurantMBean;
	}
}
