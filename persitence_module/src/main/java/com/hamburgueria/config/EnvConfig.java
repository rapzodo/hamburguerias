package com.hamburgueria.config;

import com.hamburgueria.constants.DBConstants;

public enum EnvConfig {
	
	LOCAL(DBConstants.LOCAL_SERVER,DBConstants.PORT),JELASTIC(DBConstants.JELASTIC_SERVER,DBConstants.PORT);
	private String server;
	private int port;
	
	private EnvConfig(String server, int port){
		this.server = server;
		this.port = port;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
}
