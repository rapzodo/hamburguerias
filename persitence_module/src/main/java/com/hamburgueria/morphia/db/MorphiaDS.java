package com.hamburgueria.morphia.db;

import java.util.Arrays;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.hamburgueria.config.EnvConfig;
import com.hamburgueria.constants.DBConstants;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientOptions.Builder;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public class MorphiaDS {
	private Morphia morphia = new Morphia();
	private Datastore ds;
	private static MorphiaDS instance;
	
	private MorphiaDS(MongoClient client){
		config(client);
	}
	
	public void config(MongoClient client){
		morphia.mapPackage(DBConstants.ENTITIES_PKG);
		ds = morphia.createDatastore(client, DBConstants.DB_NAME);
		ds.ensureIndexes();
	}
	
	public static MorphiaDS getinstance(EnvConfig config, Boolean isSSLClient){
		MongoClient client;
		if(instance == null){
			if(isSSLClient){
				client = getSSLClient(config);
			}else{
				client = getSimpleClient(config);
			}
			synchronized(MorphiaDS.class){
				instance = new MorphiaDS(client);
			}
		}
		return instance;
	}
	public Datastore getDataStore(){
		return ds;
	}
	private static MongoClient getSimpleClient(EnvConfig environment){
		Builder builder = MongoClientOptions.builder();
		builder.connectTimeout(DBConstants.TIME_OUT)
		.socketTimeout(DBConstants.TIME_OUT)
		.serverSelectionTimeout(DBConstants.TIME_OUT);
		return new MongoClient(new ServerAddress(environment.getServer(), environment.getPort()), builder.build());
	}
	
	private static MongoClient getSSLClient(EnvConfig environment){
		MongoCredential shaCredential = MongoCredential.createScramSha1Credential(DBConstants.USER, DBConstants.DB_NAME, DBConstants.PWD.toCharArray());
		Builder builder = MongoClientOptions.builder();
//		TIMEOUT
		builder.connectTimeout(DBConstants.TIME_OUT)
		.socketTimeout(DBConstants.TIME_OUT)
		.serverSelectionTimeout(DBConstants.TIME_OUT)
//		ENABLING SSL
		.sslEnabled(true)
		.sslInvalidHostNameAllowed(true);
		return new MongoClient(new ServerAddress(environment.getServer(), environment.getPort()), Arrays.asList(shaCredential), builder.build());
	}
}
