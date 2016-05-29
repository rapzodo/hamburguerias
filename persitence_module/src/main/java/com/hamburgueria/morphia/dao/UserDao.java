package com.hamburgueria.morphia.dao;

import com.hamburgueria.mongo.entities.User;

public class UserDao extends BaseMongoDao<User> {
	
	public UserDao(){
		super(User.class);
	}
}
