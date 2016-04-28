package com.hamburgueria.admin.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.hamburgueria.mongo.entities.Cliente;
import com.hamburgueria.mongo.entities.Endereco;
import com.hamburgueria.morphia.dao.ClienteDao;

@ManagedBean
@ViewScoped
public class ClienteMBean extends BaseMBean<Cliente>{

	private Endereco endereco;
	private List<Cliente> clientes;
	private ClienteDao cDao;
	
	
	public ClienteMBean(){
		super(new ClienteDao(), new Cliente());
		endereco = new Endereco();
	}
	
	public void cadastraCliente(){
		getModel().setEndereco(endereco);
		inserir();
	}
	
	public List<Cliente> getClientes() {
		return clientes;
	}
	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	public ClienteDao getcDao() {
		return cDao;
	}
	public void setcDao(ClienteDao cDao) {
		this.cDao = cDao;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
