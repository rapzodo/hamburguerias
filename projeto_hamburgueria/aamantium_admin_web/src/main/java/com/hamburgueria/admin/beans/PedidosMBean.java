package com.hamburgueria.admin.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.hamburgueria.admin.mocks.MockServices;
import com.hamburgueria.mongo.entities.Pedido;
import com.hamburgueria.morphia.dao.PedidoDao;

@ManagedBean
@ViewScoped
public class PedidosMBean extends BaseMBean<Pedido> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4281621980316226625L;
	private List<Pedido> pedidosFiltrados;
	@ManagedProperty(value="#{mockServices}")
	private MockServices service;
	
	public PedidosMBean(){
		super(new PedidoDao(),new Pedido());
	}
	
	public List<Pedido> getPedidosFiltrados() {
		return pedidosFiltrados;
	}
	public void setPedidosFiltrados(List<Pedido> pedidosFiltrados) {
		this.pedidosFiltrados = pedidosFiltrados;
	}
	
	@PostConstruct
	public void inicializar() {
		setModelList(service.generatePedidos(20));
	}
	
	public MockServices getService() {
		return service;
	}
	public void setService(MockServices service) {
		this.service = service;
	}

}
