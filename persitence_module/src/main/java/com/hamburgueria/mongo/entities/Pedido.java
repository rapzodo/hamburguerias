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
	private Integer numMesa;
	@Reference
	private Cliente cliente; 
	@Reference(idOnly=true)
	private List<Produto> items;
	private Double valorTotal;
	private Double servico;
	private Boolean fechado;
	
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
	public Boolean getFechado() {
		return fechado;
	}
	public void setFechado(Boolean fechado) {
		this.fechado = fechado;
	}
	public Integer getNumMesa() {
		return numMesa;
	}
	public void setNumMesa(Integer numMesa) {
		this.numMesa = numMesa;
	}
	@Override
	public String toString() {
		return "Mesa: "+numMesa + ",criado em: "+getDateCadastro();
	}
}
