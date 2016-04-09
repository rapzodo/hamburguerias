package com.hamburgueria.mongo.entities;

import org.mongodb.morphia.annotations.Entity;

@Entity(value="categorias",noClassnameStored=true)
public class Categoria extends DomainSuperClass{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8215055446622874526L;
	private String descricao;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	

}
