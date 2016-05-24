package com.j2se.fundamentals.others;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * 
 * Preferences类简化了在平台上存储用户喜好数据,Preferences存储数据的底层
 * 实现对平台有依赖性。比如WIN上使用注册表来存储,但是在LUNIX上使用home目录
 * 下的一个隐藏文件来存储
 * 
 * @author Discover
 *
 * 2016年5月22日
 */
public class PreferencesExample {

	public void registerSomething() throws BackingStoreException {
		
		Preferences pref = Preferences.userRoot();
		System.out.printf("用户注册表：%s\n", pref.name());
		for(String child : pref.childrenNames()) {
			System.out.printf("该注册表子表：%s\n", child);
		}
	}
	
	public static void main(String...args) throws BackingStoreException {
		
		PreferencesExample example = new PreferencesExample();
		example.registerSomething();
	}
}
