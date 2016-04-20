package com.hamburgueria.admin.beans;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.mongodb.morphia.query.MorphiaIterator;
import org.mongodb.morphia.query.Query;

import com.hamburgueria.mongo.entities.Produto;
import com.hamburgueria.morphia.dao.CategoriaDao;
import com.hamburgueria.morphia.dao.ProdutoDao;
import com.mongodb.AggregationOptions;
import com.mongodb.AggregationOutput;
import com.mongodb.operation.AggregateToCollectionOperation;

@ManagedBean
@ApplicationScoped
public class AppMBean {

	private Boolean admin = false;
	private List<String> ufList = Arrays.asList("SP","AM","PR","SC","RS","RJ");
	private List<String> categoria = Arrays.asList("Bebidas","Lanches","Entradas","Aperitivos","Outros");

	@PostConstruct
	public void init(){
		categoria = new ProdutoDao().listDistinct("categoria");
	}
	
	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	public List<String> getUfList() {
		return ufList;
	}

	public void setUfList(List<String> ufList) {
		this.ufList = ufList;
	}

	public List<String> getCategoria() {
		return categoria;
	}

	public void setCategoria(List<String> categoria) {
		this.categoria = categoria;
	}
	
	
}
