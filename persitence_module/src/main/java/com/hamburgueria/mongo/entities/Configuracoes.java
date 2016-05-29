package com.hamburgueria.mongo.entities;

import org.mongodb.morphia.annotations.Entity;

@Entity(value="configuracoes",noClassnameStored=true)
public class Configuracoes extends DomainSuperClass{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8215055446622874526L;
	private String configName;
	private String configValue;
	
	public String getConfigName() {
		return configName;
	}
	public void setConfigName(String configName) {
		this.configName = configName;
	}
	public String getConfigValue() {
		return configValue;
	}
	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}
	

}
