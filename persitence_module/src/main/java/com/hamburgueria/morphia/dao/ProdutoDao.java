package com.hamburgueria.morphia.dao;

import com.hamburgueria.mongo.entities.Produto;

public class ProdutoDao extends BaseMongoDao<Produto> {
	
	public ProdutoDao(){
		super(Produto.class);
	}
}
