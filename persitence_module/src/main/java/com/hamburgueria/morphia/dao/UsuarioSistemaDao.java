package com.hamburgueria.morphia.dao;

import com.hamburgueria.mongo.entities.UsuarioSistema;

public class UsuarioSistemaDao extends BaseMongoDao<Object> {
	
	public UsuarioSistemaDao(){
		super(UsuarioSistema.class);
	}
}
