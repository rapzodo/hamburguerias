package com.hamburgueria.morphia.dao;

import com.hamburgueria.mongo.entities.Restaurante;

public class RestauranteDao extends BaseMongoDao<Restaurante> {
	
	public RestauranteDao(){
		super(Restaurante.class);
	}
}
