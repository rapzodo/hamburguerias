package com.hamburgueria.morphia.dao;

import com.hamburgueria.mongo.entities.Produto;

public class CategoriaDao extends BaseMongoDao<Object> {
	
	public CategoriaDao(){
		super(Produto.class);
	}
}
