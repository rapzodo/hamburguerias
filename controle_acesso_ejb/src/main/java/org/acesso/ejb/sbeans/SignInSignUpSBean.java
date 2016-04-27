/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.acesso.ejb.sbeans;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.acesso.ejb.interceptors.LoginInterceptor;

import com.hamburgueria.mongo.entities.Cliente;
import com.hamburgueria.morphia.dao.DaoFactory;

/**
 *
 * @author Danilo
 */
@Stateless
@LocalBean
@Interceptors(value=LoginInterceptor.class)
public class SignInSignUpSBean {
	
	@Resource
	private SessionContext sContext;
	
    public Object loginUser(String user, String password, String userType) {
    	if(sContext != null){
    		System.out.println(sContext.getCallerPrincipal().getName());
    	}
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("emailId", user);
    	params.put("password",password);
    	Object result = DaoFactory.getDao(userType).getModelByComplexQueryAnd(params);
    	return result;
    }

}
