package com.hamburgueria.morphia.dao;

import com.hamburgueria.mongo.entities.Cliente;

public class ClienteDao extends BaseMongoDao<Cliente> {
	
	public ClienteDao(){
		super(Cliente.class);
	}
}
