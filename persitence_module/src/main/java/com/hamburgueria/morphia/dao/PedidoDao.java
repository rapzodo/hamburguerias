package com.hamburgueria.morphia.dao;

import com.hamburgueria.mongo.entities.Pedido;

public class PedidoDao extends BaseMongoDao<Pedido> {
	
	public PedidoDao(){
		super(Pedido.class);
	}
}
