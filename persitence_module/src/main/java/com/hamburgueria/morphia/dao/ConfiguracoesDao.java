package com.hamburgueria.morphia.dao;

import com.hamburgueria.mongo.entities.Configuracoes;

public class ConfiguracoesDao extends BaseMongoDao<Configuracoes> {
	
	public ConfiguracoesDao(){
		super(Configuracoes.class);
	}
}
