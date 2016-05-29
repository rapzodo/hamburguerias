package org.acesso.ejb.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import com.hamburgueria.mongo.entities.User;
import com.hamburgueria.mongo.entities.UsuarioSistema;

public class LoginInterceptor {

	@AroundInvoke
	public Object interceptLoginUser(InvocationContext ctx) throws Exception{
		Object[] parameters = ctx.getParameters();
		printParams(parameters);
		if(parameters[0] instanceof UsuarioSistema){
			User usuario = (User)parameters[0]; 
			System.out.println(ctx.getMethod().getName() + " => User " + usuario.getUserName() + " SignedUp!" );
		}else{
			
			String username = (String)parameters[0];
			System.out.println(ctx.getMethod().getName() + " => User " + username + " Login In!" );
		}
		return ctx.proceed();
	}
	
	private void printParams(Object[] params){
		for (Object object : params) {
			System.out.println("Param: " +object);
		}
		
	}
}
