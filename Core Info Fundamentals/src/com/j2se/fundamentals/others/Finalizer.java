package com.j2se.fundamentals.others;

/**
 * 
 * runFinalization运行已回收对象的finalize方法
 * 
 * @author Discover
 *
 * 2016年5月17日
 */
public class Finalizer {

	@Override
	protected void finalize() throws Throwable {
		System.out.printf("This is a finalize methond %s", this.getClass().getName());
		super.finalize();
	}
	
	public static void main(String...args) throws InterruptedException{
		
		new Finalizer();
//		如果不调用垃圾回收,runFinalization没有已回收的对象可以寻找
		System.gc();
		Thread.sleep(1000);
		System.runFinalization();
	}
}
