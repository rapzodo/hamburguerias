package com.hamburgueria.mongo.entities;


import java.util.List;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.utils.IndexType;

@Entity(noClassnameStored=true,value="produtos")
@Indexes(@Index(fields={@Field(value="preco"),@Field(value="_id",type=IndexType.ASC)}))
public class Produto extends DomainSuperClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6191882828638454346L;
	/**
	 * 
	 */
	private String nome;
	private Double preco;
	private List<String> imagens;
	private String descricao;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	public List<String> getImagens() {
		return imagens;
	}
	public void setImagens(List<String> imagens) {
		this.imagens = imagens;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
}