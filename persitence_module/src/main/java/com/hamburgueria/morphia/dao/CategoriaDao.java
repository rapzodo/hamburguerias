package com.hamburgueria.morphia.dao;

import com.hamburgueria.mongo.entities.Categoria;

public class CategoriaDao extends BaseMongoDao<Categoria> {
	
	public CategoriaDao(){
		super(Categoria.class);
	}
}
