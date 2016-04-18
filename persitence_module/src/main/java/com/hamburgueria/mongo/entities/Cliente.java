package com.hamburgueria.mongo.entities;

import java.util.Date;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.IndexOptions;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Indexes;

@Entity(value="clientes",noClassnameStored=true)
@Indexes(@Index(fields={@Field(value="nome")}))
public class Cliente extends UsuarioSistema{

	/**
	 * 
	 */
	private static final long serialVersionUID = -397146575365864915L;
	private String nome;
	@Indexed(options=@IndexOptions(unique=true))
	private String cpf;
	private Date dataNascimento;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
}
