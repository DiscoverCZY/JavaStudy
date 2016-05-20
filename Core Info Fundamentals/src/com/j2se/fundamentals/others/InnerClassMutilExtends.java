package com.j2se.fundamentals.others;

/**
 * 使用内部类实现多重继承是一种设计方式,从而可以实现父类的方法,但是只有一个类是真正可以使用多态的
 * 
 * @author Discover
 *
 * 2016年5月19日
 */
public class InnerClassMutilExtends extends Person{

	@Override
	protected void eat() {
		
		class PartInnerClass extends Kid{

			@Override
			protected void cry() {
				System.out.println("I can cry");
			}
		}
		
		PartInnerClass part = new PartInnerClass();
		part.cry();
		System.out.println("I can eat");
	}
	
	public static void main(String...args) {
		
		InnerClassMutilExtends mutilExtends = new InnerClassMutilExtends();
		mutilExtends.eat();
	}
}

abstract class Person{
	
	abstract protected void eat();
}

abstract class Kid{
	
	abstract protected void cry();
}
