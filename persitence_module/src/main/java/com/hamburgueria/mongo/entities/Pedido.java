package com.hamburgueria.mongo.entities;

import java.util.List;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Reference;
import org.mongodb.morphia.utils.IndexType;

@Entity(value="pedidos", noClassnameStored=true)
@Indexes(@Index(fields={@Field(value="dateCadastro", type=IndexType.DESC)}))
public class Pedido extends DomainSuperClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2419518708903171252L;
	@Reference
	private Cliente cliente; 
	@Reference
	private List<Produto> items;
	private Double valorTotal;
	private Double servico;
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<Produto> getItems() {
		return items;
	}
	public void setItems(List<Produto> items) {
		this.items = items;
	}
	public Double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Double getServico() {
		return servico;
	}
	public void setServico(Double servico) {
		this.servico = servico;
	}
}
