package com.j2se.fundamentals.log;

import java.util.logging.Filter;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * 
 * Logger
 * 可以通过配置文件设置
 * Formatter
 * Handler
 * Filter
 * 
 * @author Discover
 *
 * 2016年5月24日
 */
public class LogExample {

	/**
	 * Java自带的Log类有七种日志级别
	 * SEVERE
	 * WARNING
	 * INFO
	 * CONFIG
	 * FINE
	 * FINER
	 * FINEST
	 * 可以通过Logger.getGlobal().setLevel(Level.ALL)设置全局日志Handler的级别,
	 * 但是INFO以下的日志级别修改必须改变logging.properties配置文件,该文件在jre/lib
	 * 下
	 */
	public void printByLogLevel() {		
		
//		为Log设置自定义的Handler类,同时禁用向父类Handler分发
		Handler handler = new MyHanlder();
		handler.setFilter(new MyFilter());
		handler.setFormatter(new MyFormat());
		Logger.getGlobal().addHandler(handler);
		Logger.getGlobal().setUseParentHandlers(false);
		
		Logger.getGlobal().severe("This is severe log");
		Logger.getGlobal().warning("This is warning log");
		Logger.getGlobal().info("This is info log");
		Logger.getGlobal().config("This is config log");
		Logger.getGlobal().fine("This is fine log");
		Logger.getGlobal().finer("This is finer log");
		Logger.getGlobal().finest("This is finest log");
		Logger.getGlobal().entering(LogExample.class.getCanonicalName(), "printByLogLevel");
		Logger.getGlobal().exiting(LogExample.class.getCanonicalName(), "printByLogLevel");
		Logger.getGlobal().throwing(this.getClass().getCanonicalName(), "printByLogLevel", new Throwable("Common Exception"));
	}
	
	class MyFormat extends Formatter{

		@Override
		public String getHead(Handler h) {
			return "[";
		}

		@Override
		public String getTail(Handler h) {
			return "]";
		}

		@Override
		public String format(LogRecord record) {
			
			StringBuffer builder = new StringBuffer();
			builder.append(record.getThreadID()).append("-").append(record.getSequenceNumber())
				   .append(record.getMessage());
			return builder.toString();
		}
	}
	
	/**
	 * 
	 * 实现Filter接口,实现自定义的过滤器,并调用
	 * setFilter为日志记录器设置过滤器
	 * 
	 * @author Discover
	 *
	 * 2016年5月24日
	 */
	class MyFilter implements Filter{

		@Override
		public boolean isLoggable(LogRecord record) {
			return record.getLevel().intValue() > Level.FINE.intValue();
		}
	}
	
	/**
	 * 
	 * 继承Handler实现自定义功能的处理器
	 * 
	 * @author Discover
	 *
	 * 2016年5月24日
	 */
	class MyHanlder extends Handler{

		@Override
		public void close() throws SecurityException {
			
		}

		@Override
		public void flush() {
			
		}

		@Override
		public void publish(LogRecord record) {
			System.err.println(record.getMessage());
		}
	}
	
	public static void main(String...args) {
		
		LogExample example = new LogExample();
		Logger.getGlobal().setLevel(Level.ALL);
		example.printByLogLevel();
		
//		打印堆栈信息
		Thread.dumpStack();
	}
}
