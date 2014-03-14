package com.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MyMethodIntercepter implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation arg0) throws Throwable {
		
		System.out.println("Before invoking method...");
		Object obj = arg0.proceed();
		System.out.println("After invoking method...");
		
		return obj;
	}

}
