package com.j2se.fundamentals.others;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 
 * Java的代理机制 
 * Proxy.newProxyInstance方法需要三个参数
 * ClassLoader 主要考虑对接口的可见性,选择正确ClassLoader
 * Class<?> 期望代理的接口的Class类数组 
 * InvocationHandler 代理处理对象
 * 
 * 代理机制主要实现AOP编程,面向切面可以快速的大面积的改变方法逻辑,比如日志开关等
 * 
 * @author Discover
 *
 * 2016年5月19日
 */
public class ProxyExample implements Eatable, Cryable{

	@Override
	public void eat() {

		System.out.println("我正在吃东西");
	}

	@Override
	public void cry() {
		
		System.out.println("我正在哭");
	}
	
	class TraceHandler implements InvocationHandler{

		private Object target;
		
		public TraceHandler(Object aTarget) {
			
			this.target = aTarget;
		}
		
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			
			Class<?>[] interfaces = target.getClass().getInterfaces();
			for(Class<?> cls : interfaces) {
				if("Eatable".equals(cls.getSimpleName()) && "eat".equals(method.getName())) {
					System.out.println("跟踪到马上执行Eatable接口的eat方法");
				}
			}
			return method.invoke(this.target, args);
		}
		
	}
	
	public static void main(String...args) {
		
		ProxyExample example = new ProxyExample();
		ProxyExample.TraceHandler handler = example.new TraceHandler(example);
		Object proxy = Proxy.newProxyInstance(ProxyExample.class.getClassLoader(), new Class<?>[]{Eatable.class, Cryable.class}, handler);
		Eatable eat = (Eatable) proxy;
		eat.eat();
		Cryable cry = (Cryable) proxy;
		cry.cry();
	}
}

interface Cryable{
	
	void cry();
}

interface Eatable{
	
	void eat();
}
