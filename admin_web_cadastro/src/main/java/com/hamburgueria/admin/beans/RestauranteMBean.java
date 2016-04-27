package com.hamburgueria.admin.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.hamburgueria.mongo.entities.Cliente;
import com.hamburgueria.mongo.entities.Endereco;
import com.hamburgueria.mongo.entities.Restaurante;
import com.hamburgueria.morphia.dao.ClienteDao;
import com.hamburgueria.morphia.dao.RestauranteDao;

@ManagedBean
@ViewScoped
public class RestauranteMBean extends BaseMBean<Restaurante>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1253323164116452256L;
	private Endereco endereco;
	private List<Cliente> clientes;
	private ClienteDao cDao;
	
	
	public RestauranteMBean(){
		super(new RestauranteDao(), new Restaurante());
		endereco = new Endereco();
	}
	
	public void cadastraRestaurante(){
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
