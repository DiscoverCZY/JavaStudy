package com.j2se.fundamentals.io;

import java.io.PrintWriter;
import java.util.Date;

/**
 * 
 * 格式化输出示例	
 * 
 * @author Discover
 *
 * 2016年5月12日
 */
public class FormatPrint {

	/**
	 * 格式化输出 printf应用 
	 */
	public void demoAllFormat() {
		
		System.out.println("demoAllFormat:");
		PrintWriter print = new PrintWriter(System.out);
		print.printf("%8.2f%n", 3333.33);
		
		print.printf("My name is %s, and age is %d%n", "Zhengyu", 32);
		
		print.printf("%,.2f%n", 10000.0/3.0);		
		
		print.printf("%1$s %2$tB %2$te, %2$tY%n", "Due Date:", new Date());
		
		print.printf("%s %tB %<te, %<tY%n", "Due Date:", new Date());
		print.flush();
	}
	
	/**
	 * 格式化输出String.format应用
	 */
	public void demoAllFormat4Str() {
		
		System.out.println("demoAllFormat4Str:");
		System.out.print(String.format("%2$,8.2f %1$4s", "Show me the monery", 8888.88));
	}
	
	public static void main(String...args) {
		
		FormatPrint fp = new FormatPrint();
		fp.demoAllFormat();
		fp.demoAllFormat4Str();
	}
}
