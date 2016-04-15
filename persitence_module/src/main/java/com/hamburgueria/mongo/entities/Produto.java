package com.hamburgueria.mongo.entities;


import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.annotations.Reference;

@Entity(noClassnameStored=true,value="produtos")
@Indexes(@Index(fields={@Field(value="preco")}))
public class Produto extends DomainSuperClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6191882828638454346L;
	/**
	 * 
	 */
	@Reference
	private Categoria categoria;
	@Indexed(options=@IndexOptions(unique=true, name="nome"))
	private String nome;
	private Double preco;
	private String imagem;
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
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
	
}
