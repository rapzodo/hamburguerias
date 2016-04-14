package com.hamburgueria.admin.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import com.hamburgueria.mongo.entities.Cliente;
import com.hamburgueria.mongo.entities.Endereco;
import com.hamburgueria.morphia.dao.ClienteDao;

@ManagedBean
@SessionScoped
public class ClienteMBean extends BaseMBean<Cliente>{

	private Cliente newCliente;
	private List<Cliente> clientes;
	private ClienteDao cDao;
	
	
	public ClienteMBean(){
		super(new ClienteDao());
		if(newCliente == null){
			newCliente = new Cliente();
			newCliente.setEndereco(new Endereco());
		}
	}
	
	public void cadastraCliente(ActionEvent event){
		setNewCliente(inserir(newCliente));
	}
	
	public Cliente getNewCliente() {
		return newCliente;
	}
	public void setNewCliente(Cliente newCliente) {
		this.newCliente = newCliente;
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
	
}
