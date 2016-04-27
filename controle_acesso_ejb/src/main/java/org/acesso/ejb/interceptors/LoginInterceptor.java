package org.acesso.ejb.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class LoginInterceptor {

	@AroundInvoke
	public Object interceptLoginUser(InvocationContext ctx) throws Exception{
		Object[] parameters = ctx.getParameters();
		printParams(parameters);
		String username = (String)parameters[0];
		System.out.println(ctx.getMethod().getName() + " => User " + username + " Login In!" );
		return ctx.proceed();
	}
	
	private void printParams(Object[] params){
		for (Object object : params) {
			System.out.println("Param: " +object);
		}
		
	}
}
