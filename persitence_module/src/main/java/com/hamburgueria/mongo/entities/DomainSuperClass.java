package com.hamburgueria.mongo.entities;

import java.io.Serializable;
import java.util.Date;

import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.PrePersist;
import org.mongodb.morphia.annotations.Version;

public class DomainSuperClass implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2028232518111788326L;
	@Id
	private long _id;
	@Version("version")
	private long version;
	private Date dateCadastro;
	private Date lastUpdate;

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public Date getDateCadastro() {
		return dateCadastro;
	}

	public void setDateCadastro(Date dateCadastro) {
		this.dateCadastro = dateCadastro;
	}

	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}


	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
	@PrePersist
	public void prePersist(){
		lastUpdate = new Date();
	}

}
