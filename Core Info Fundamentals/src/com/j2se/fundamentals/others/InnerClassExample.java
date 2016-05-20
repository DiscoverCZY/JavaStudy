package com.j2se.fundamentals.others;

/**
 * 
 * 内部类, 局部内部类, 匿名内部类, 静态内部类(静态内部类不包含外部类的隐式指针)
 * 
 * @author Discover
 *
 * 2016年5月19日
 */
public class InnerClassExample {
	
	private void outterClassMethod() {
		
		System.out.println("This is a outter class method");
	}
	
	public void partInnerClassMethod() {
		
		class PartInnerClass{
			
			public void printPartInnerClass() {
				
				System.out.println("This is a part inner class method");
			}
		}
		
		PartInnerClass p = new PartInnerClass();
		p.printPartInnerClass();
	}
	
	public void noNameInnerClassMethod(final String a) {
		
		System.out.println(new Comparable<String>() {

			@Override
			public int compareTo(String b) {

				return a.length();
			}
		});
	}
	
	public void staticInnerClassMethod() {
		
		InnerClassExample.StaticInnerClass.printStaticInnerClass();
	}
	
	/**
	 * 内部类有指向外部类隐式的指针
	 * 可以通过OutterClass.this.xxxx调用方法或者访问成员变量
	 * 
	 * @author Discover
	 *
	 * 2016年5月19日
	 */
	class InnerClassWithName{
		
		public void printOutterClass() {
			
			InnerClassExample.this.outterClassMethod();
		}
	}
	
	static class StaticInnerClass{
		
		public static void printStaticInnerClass() {
			
//			Compile Error
//			静态内部类没有外部类的隐式指针
//			InnerClassExample.this.outterClassMethod();
			System.out.println("This is a static inner class method");
		}
	}
	
	public static void main(String...args) {
		
		InnerClassExample example = new InnerClassExample();
		InnerClassExample.InnerClassWithName innerClass = example.new InnerClassWithName();
		innerClass.printOutterClass();
		example.partInnerClassMethod();
		example.noNameInnerClassMethod("ABCDE");
		example.staticInnerClassMethod();
	}
}
