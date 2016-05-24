package com.j2se.fundamentals.log;

/**
 * 
 * 可以通过使用java -ea开启java断言
 * -ea:Class 启用指定类的断言功能
 * -ea:com.mycompany.mylib 启用指定包的断言功能
 * -da:Class 禁用指定类断言功能
 * 
 * 或者通过ClassLoader来设置断言状态
 * 
 * @author Discover
 *
 * 2016年5月22日
 */
public class AssertExample {

	public void assertShow() {
		
		int i = 6;
		assert i == 4;
		i += 4;
		assert i > 6;
		System.out.printf("当前值:%8d\n", i);
	}
	
	public static void main(String...args) throws ClassNotFoundException {
		
		AssertExample example = new AssertExample();
		example.assertShow();
	}
}
