package com.jdbc;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class ReflactionTest {
	
	public static void main(String args[]) throws Exception{
		Class clazz = BeanTest.class;
		Object obj = create(clazz);
		System.out.println(obj);
		field(clazz);
	}
	
	//reflection
	static Object create(Class clazz) throws Exception{
		Constructor con = clazz.getConstructor(String.class);
		Object obj = con.newInstance("ad");
		return obj;
	}
	
	static void invoke1(Object obj, String methodName) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException{
		Method[] ms = obj.getClass().getMethods();
		for (Method m : ms) {
			//System.out.println(m.getName()));
			if(methodName.equals(m.getName())){
				m.invoke(obj, null);
			}
		}
	}
	
	static void field(Class clazz) {
		Field[] fs = clazz.getDeclaredFields();
		for(Field f : fs){
			System.out.println(f.getName());
		}
	}
	
	static void annon(Class clazz){
		Annotation[] as = clazz.getAnnotations();
	}

}
