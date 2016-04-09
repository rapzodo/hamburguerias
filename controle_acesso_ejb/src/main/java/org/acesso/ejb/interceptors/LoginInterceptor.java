package org.acesso.ejb.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class LoginInterceptor {

	@AroundInvoke
	public Object interceptLoginUser(InvocationContext ctx) throws Exception{
		Object[] parameters = ctx.getParameters();
		String username = (String)parameters[0];
		System.out.println(ctx.getMethod().getName() + " => User " + username + " Login In!" );
		return ctx.proceed();
	}
}
