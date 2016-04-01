package com.hamburgueria.mongo.entities;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Field;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;
import org.mongodb.morphia.utils.IndexType;

@Indexes(@Index(fields={@Field(value="location", type=IndexType.GEO2D),
		@Field(value="dateCadastro", type=IndexType.DESC),
		@Field(value="emailId")}
		))
public class UsuarioSistema extends DomainSuperClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7917921256091335248L;
	private String emailId;
	private String password;
	private String telefone;
	private String celular;
	private String imagem;
	@Embedded
	private Endereco endereco;
	private String[] location ;
	
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getImagem() {
		return imagem;
	}
	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public String[] getLocation() {
		return location;
	}
	public void setLocation(String[] location) {
		this.location = location;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
