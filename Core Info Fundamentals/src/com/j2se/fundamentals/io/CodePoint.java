package com.j2se.fundamentals.io;

/**
 * 
 * 代码点和代码单元示例
 * 
 * @author Discover
 *
 * 2016年5月11日
 */
public class CodePoint {

	/**
	 * 一般不需要考虑代码单元的长度值,可以简单地认为其与代码点的长度是一样的。
	 * 只有增补字符,即代码点为 U+10000～U+10FFFF 的字符,这是 Unicode5.0 中
	 * 新增的代码点字符。
	 * 
	 * 在 Java 中一个 Unicode字符是使用 UTF-16 编码的 char 进行表示的,也就是
	 * 一个 char只能表示 U+0000～U+FFFF 的 Unicode 基本字符（BMP）。因此在 
	 * Java 中需要表示 U+10000～U+10FFFF 的字符需要使用 一对代理字符进行表示,
	 * 高代理字符的范围为 U+D800～U+DBFF,低代理字符的范围为 U+DC00～U+DFFF。
	 * 比如表示 U+10400 的字符需要两个 char（U+D801, U+DC00）才能表示,这时的
	 * 代码点长度为 1,而代码单元长度为 2。
	 */
	public void codePointAndLengthMismacth() {
		
		System.out.println("codePointAndLengthDiff:");
		char[] chs = Character.toChars(0x10400);
		System.out.printf("U+10400高代理字符: %04x%n", (int)chs[0]);
        System.out.printf("U+10400低代理字符: %04x%n", (int)chs[1]);        
        String str = new String(chs);
        System.out.println("代码单元长度: " + str.length());
        System.out.println("代码点数量: " + str.codePointCount(0, str.length()));
	}
	
	public void codePointAndLengthMatch() {
		
		System.out.println("codePointAndLengthSame:");
		char[] chs = Character.toChars(0xffff);
		System.out.printf("U+ffff基本字符: %04x%n", (int)chs[0]);     
        String str = new String(chs);
        System.out.println("代码单元长度: " + str.length());
        System.out.println("代码点数量: " + str.codePointCount(0, str.length()));
	}
	
	/**
	 * 下面的循环匹配带有增补字符的字符串时,如果简单的使用char作为匹配项,会出现无法还原增补字符的问题,
	 * 所以需要用第二种方式使用CODE POINT来进行循环及判断当前代码点是否为增补字符的方式来获得正确匹配
	 */
	public void loopString() {
		
		System.out.println("loopString:");
		String str = "Hello𐐀";
		for(char c : str.toCharArray()) {
			if("𐐀".equals(c)) {
				System.out.println("匹配到特殊字符𐐀");
			}
		}
		
		for(int i=0; i<str.codePointCount(0, str.length()); i++) {
			
			String temp = "";
			if(Character.isSupplementaryCodePoint(str.codePointAt(i))) {
				temp = str.substring(i, i+2);
			}else {
				temp = str.substring(i, i+1);
			}
			if("𐐀".equals(temp)) {
				System.out.println("匹配到特殊字符𐐀");
			}
		}
	}
	
	public static void main(String...args) {
		
		CodePoint cp = new CodePoint();
		cp.codePointAndLengthMismacth();
		cp.codePointAndLengthMatch();
		cp.loopString();
	}
}
