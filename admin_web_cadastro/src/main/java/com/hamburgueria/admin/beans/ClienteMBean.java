package com.hamburgueria.admin.beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.hamburgueria.mongo.entities.Cliente;
import com.hamburgueria.mongo.entities.Endereco;
import com.hamburgueria.morphia.dao.ClienteDao;

@ManagedBean
@ViewScoped
public class ClienteMBean extends BaseMBean<Cliente>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -669373584118048179L;
	private Endereco endereco;
	private ClienteDao cDao;
	
	public ClienteMBean(){
		super(new ClienteDao(), new Cliente());
		endereco = new Endereco();
	}
	
	@PostConstruct
	public void inicializar() {
		super.inicializar();
		getResults();
	}
	
	public void cadastraCliente(){
		getModel().setEndereco(endereco);
		inserir();
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
