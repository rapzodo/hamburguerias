package com.hamburgueria.mongo.entities;

import java.io.Serializable;

import org.mongodb.morphia.annotations.Embedded;

@Embedded
public class Endereco implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8682794728346343634L;
	private String rua;
	private String cidade;
	private String bairro;
	private String uf;
	private String cep;
	
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	
	
}
