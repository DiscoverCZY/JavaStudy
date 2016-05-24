package com.j2se.fundamentals.exception;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 
 * Java中错误分类为Error, Exception.Exception
 * 还包括捕捉的Exception和不捕捉的RuntimeException
 * 
 * try..catch...finally语句块处理
 * 
 * @author Discover
 *
 * 2016年5月22日
 */
public class ExceptionExample {

	/**
	 * 1.try with resource语句块可以隐式进行资源关闭操作(实现了AutoCloseable的资源类)
	 * 2.StcakTraceElement可以获得详细的堆栈信息
	 */
	public void tryWithResource() {
		
		try(InputStream is = new FileInputStream(new File("./test.txt"));
			DataInputStream dis = new DataInputStream(is)){
			
			int length = is.available();
			byte[] buffer = new byte[length];
			is.read(buffer);
			System.out.printf("接受到的信息是：%s\n", new String(buffer, "gbk"));
		} catch (IOException e) {
			StackTraceElement[] traceEles = e.getStackTrace();
			for(StackTraceElement traceEle : traceEles) {
				System.out.printf("%s第%d:行,%s-%s\n", traceEle.getFileName(), traceEle.getLineNumber(), traceEle.getClassName(), traceEle.getMethodName());
			}
		}
	}
	
	/**
	 * finally语句块的执行顺序为方法return之后,
	 * 所以下面的代码会引起返回值错误,不推荐在finally
	 * 语句块中加入return
	 * 
	 * @return
	 */
	public int tryWithFinally() {
		
		int i = 0;
		try {
			i = 4;
			return i;
		}finally {
			i = 9;
			return i;
		}
	}
	
	public static void main(String...args) {
		
		ExceptionExample example = new ExceptionExample();
		example.tryWithResource();
		
		int i = example.tryWithFinally();
		System.out.printf("返回的是9不是4, %d", i);
	}
}
