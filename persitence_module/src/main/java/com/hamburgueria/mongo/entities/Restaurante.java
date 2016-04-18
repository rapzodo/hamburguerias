package com.hamburgueria.mongo.entities;

import java.util.List;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

@Entity(value="restaurantes", noClassnameStored=true)
public class Restaurante extends UsuarioSistema{

	
	public Restaurante(){
		super.setEndereco(new Endereco());
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7676572388295967653L;
	private String razaoSocial;
	@Reference(idOnly=true)
	private List<Cliente> clientes;
	private String cnpj;
	@Reference(idOnly=true)
	private List<Produto> menu;
	
	public List<Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public List<Produto> getMenu() {
		return menu;
	}
	public void setMenu(List<Produto> menu) {
		this.menu = menu;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	
	
}
