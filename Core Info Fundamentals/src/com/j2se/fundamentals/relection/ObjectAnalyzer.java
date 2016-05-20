package com.j2se.fundamentals.relection;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * 利用反射原理解析类构造
 * 已实现: 成员变量, 方法, 主类
 * 未实现: 注解, 泛型, 内部类
 * 
 * @author Discover
 *
 * 2016年5月19日
 */
public class ObjectAnalyzer {

	public void analysis(Object target) throws IllegalArgumentException, IllegalAccessException {
		
		StringBuffer builder = new StringBuffer(32);
		Class<?> cls = target.getClass();
		
		builder.append(Modifier.toString(cls.getModifiers())).append(" class ")
			   .append(cls.getSimpleName());
		Class<?> superCls = cls.getSuperclass();
		if(superCls != null) {
			builder.append(" extends ").append(superCls.getSimpleName());
		}
		Class<?>[] interfaces = cls.getInterfaces();
		if(interfaces.length > 0) {
			builder.append(" implements ");
			for(Class<?> iCls : interfaces) {
				builder.append(iCls.getSimpleName()).append(",");
			}
			builder = builder.deleteCharAt(builder.length()-1);
		}
		
		builder.append("{\n");
		
		builder.append(this.analysisFields(cls, target));
		builder.append(this.analysisConstructs(cls));
		builder.append(this.analysisMethods(cls));
		
		builder.append("}");
		
		System.out.println(builder.toString());
	}
	
	private StringBuffer analysisConstructs(Class<?> cls) {
		
		StringBuffer builder = new StringBuffer(32);
		Constructor<?>[] constructors = cls.getDeclaredConstructors();
		builder.append("\n");
		char s = 97;
		for(Constructor<?> constructor : constructors) {
			builder.append("    ")
				   .append(Modifier.toString(constructor.getModifiers())).append(" ")
				   .append(constructor.getName()).append("(");
			Class<?>[] parameters = constructor.getParameterTypes();
			for(Class<?> parameter : parameters) {
				builder.append(parameter.getSimpleName()).append(" ").append(s++).append(",");
			}
			if(parameters.length > 0) {
				builder.deleteCharAt(builder.length()-1);
			}
			builder.append("){\n").append("    }\n");
			s = 97;
		}
		return builder;
	}
	
	private StringBuffer analysisFields(Class<?> cls, Object target) throws IllegalArgumentException, IllegalAccessException {
		
		StringBuffer builder = new StringBuffer(32);
		Field[] fields = cls.getDeclaredFields();
		builder.append("\n");
		AccessibleObject.setAccessible(fields, true);
		for(Field field : fields) {
			builder.append("    ")
				   .append(Modifier.toString(field.getModifiers())).append(" ")
				   .append(field.getType().getSimpleName()).append(" ")
				   .append(field.getName());
			Object val = field.get(target);
			if(val != null) {
				builder.append(" = ").append(val).append("\n");
			}
		}
		return builder;
	}

	private StringBuffer analysisMethods(Class<?> cls) {
		
		StringBuffer builder = new StringBuffer(32);
		Method[] methods = cls.getDeclaredMethods();
		builder.append("\n");
		char s = 97;
		for(Method method : methods) {
			builder.append("    ")
				   .append(Modifier.toString(method.getModifiers())).append(" ")
				   .append(method.getReturnType().getSimpleName()).append(" ")
				   .append(method.getName()).append("(");
			Class<?>[] parameters = method.getParameterTypes();
			for(Class<?> parameter : parameters) {
				builder.append(parameter.getSimpleName()).append(" ").append(s++).append(",");
			}
			if(parameters.length > 0) {
				builder.deleteCharAt(builder.length()-1);
			}
			builder.append("){\n").append("    }\n");
			s = 97;
		}
		return builder;
	}
	
	public static void main(String...args) throws IllegalArgumentException, IllegalAccessException {
		
		ObjectAnalyzer analyzer = new ObjectAnalyzer();
		analyzer.analysis(new ArrayList<Integer>());
		analyzer.analysis(new HashMap<Integer, Object>());
	}
	
}
