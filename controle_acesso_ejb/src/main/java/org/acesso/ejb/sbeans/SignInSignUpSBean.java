/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.acesso.ejb.sbeans;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.acesso.ejb.interceptors.LoginInterceptor;

import com.hamburgueria.mongo.entities.User;
import com.hamburgueria.morphia.dao.UserDao;

/**
 *
 * @author Danilo
 */
@Stateless
@LocalBean
public class SignInSignUpSBean {
	
	@Resource
	private SessionContext sContext;
	private UserDao dao;
	
	@PostConstruct
	public void init(){
		dao = new UserDao();
	}
	
	@Interceptors(value=LoginInterceptor.class)
    public Object loginUser(String user, String password) {
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("emailId", user);
    	params.put("password",password);
    	Object result = dao.getModelByComplexQueryAnd(params);
    	return result;
    }
    
    public long signUp(User user){
    	long idResult = dao.saveOrUpdate(user);
    	return idResult;
    }

}
