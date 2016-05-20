package com.j2se.fundamentals.others;

/**
 * 
 * addShutdownHook可以添加JVM退出前的回调方法
 * 
 * @author Discover
 *
 * 2016年5月17日
 */
public class ShutdownHook {

	public static void main(String...args){
		
		Runtime.getRuntime().addShutdownHook(new Thread() {

			@Override
			public void run() {
				
				System.out.printf("JVM退出前运行此方法,查看还有多少内存可使用, %8d", Runtime.getRuntime().freeMemory());
			}
		});
		System.out.println("退出JVM");
	}
}
