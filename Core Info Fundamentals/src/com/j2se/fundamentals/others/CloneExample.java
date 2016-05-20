package com.j2se.fundamentals.others;

import java.util.Date;

/**
 * 
 * Java克隆,分深克隆和浅克隆
 * 深克隆: 被复制对象的所有变量都含有与原来的对象相同的值，除去那些引用其他对象的变量。那些引用其他对象的变量将指向被复制过的新对象，而不再是原有的那些被引用的对象
 * 浅克隆: 被复制对象的所有变量都含有与原来的对象相同的值，而所有的对其他对象的引用仍然指向原来的对象
 * 
 * @author Discover
 *
 * 2016年5月19日
 */
public class CloneExample {

	
	public void deepClone() throws CloneNotSupportedException{
		
		System.out.println("DeepClone Example:");
		DeepClone source = new DeepClone();
		source.name = "DeepClone Object A";
		source.date = new Date();
		System.out.println(source);
		DeepClone cloned = (DeepClone) source.clone();
		cloned.date.setTime(System.currentTimeMillis() + 10000);
		System.out.println(source);
		System.out.println(cloned);
	}
	
	public void shallowClone() throws CloneNotSupportedException {
	
		System.out.println("Shallow Example:");
		ShallowClone source = new ShallowClone();
		source.name = "Shallow Object A";
		source.date = new Date();
		System.out.println(source);
		ShallowClone cloned = (ShallowClone) source.clone();
		cloned.date.setTime(System.currentTimeMillis() + 10000);
		System.out.println(source);
		System.out.println(cloned);
	}
	
	class DeepClone implements Cloneable{

		private String name;
		
		private Date date;
		
		@Override
		protected Object clone() throws CloneNotSupportedException {
			
			DeepClone cloned = (DeepClone) super.clone();
			cloned.name = name;
			cloned.date = (Date) this.date.clone();
			return cloned;
		}

		@Override
		public String toString() {
			
			return "NAME:" + this.name + ", DATE:" + this.date;
		}
	}
	
	class ShallowClone implements Cloneable{

		private String name;
		
		private Date date;
		
		@Override
		protected Object clone() throws CloneNotSupportedException {
			
			ShallowClone cloned = (ShallowClone) super.clone();
			cloned.name = name;
			cloned.date = date;
			return cloned;
		}
		
		@Override
		public String toString() {
			
			return "NAME:" + this.name + ", DATE:" + this.date;
		}
	}
	
	public static void main(String...args) throws CloneNotSupportedException {
		
		CloneExample example = new CloneExample();
		example.deepClone();
		example.shallowClone();
	}
}
