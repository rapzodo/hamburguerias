package com.hamburgueria.admin.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Default;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.TabChangeEvent;

import com.hamburgueria.mongo.entities.Produto;
import com.hamburgueria.morphia.dao.ProdutoDao;

@Named
@ViewScoped
public class CardapioBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1030083277726195392L;
	private List<Produto> menu;
	private Produto produto;
	private ProdutoDao dao;

	public CardapioBean() {
		produto = new Produto();
		produto.setNome("TESTE");
	}

	@PostConstruct
	public void init(){
		produto = new Produto();
		dao = new ProdutoDao();
	}
	
	public void listaMenu(TabChangeEvent event){
		menu = dao.listAll();
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
}
