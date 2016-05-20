package com.j2se.fundamentals.others;

/**
 * 
 * 内部类实现外部类回调方法的应用,像Comparable接口就是使用类似的方法进行对象比较
 * 
 * @author Discover
 *
 * 2016年5月19日
 */

public class InnerClassCallback{

	private int weight;
	
	private void setWeight(int weight) {
		this.weight = weight;
	}
	
	public int getWeight() {
		
		return this.weight;
	}
	
	class AjustWeight{
		
		public void ajustWeight(int weight) {
			InnerClassCallback.this.setWeight(weight);
		}
	}
	
	public static void main(String...args) {
		
		InnerClassCallback callback = new InnerClassCallback();
		InnerClassCallback.AjustWeight inner = callback.new AjustWeight();
		inner.ajustWeight(20);
		System.out.printf("当前体重: %2d\n", callback.getWeight());
	}
}
