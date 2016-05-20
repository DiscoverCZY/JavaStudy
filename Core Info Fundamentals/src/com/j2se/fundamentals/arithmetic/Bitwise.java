package com.j2se.fundamentals.arithmetic;


/**
 * 
 * 位运算示例
 * 
 * @author Discover
 *
 * 2016年5月9日
 */
public class Bitwise {

	/**
	 * 与运算符 & 规则:
	 * 1 & 1 -> 1
	 * 1 & 0 -> 0
	 * 0 & 1 -> 0
	 * 0 & 0 -> 0
	 */
	public void bitwise4And() {
		
		System.out.println("bitwise4And:");
		int a = 0b1010;
		int b = 0b1100;
		int c = a & b;
		System.out.println(Integer.toBinaryString(c));
	}
	
	/**
	 * 或运算符 | 规则:
	 * 1 | 1 -> 1
	 * 1 | 0 -> 1
	 * 0 | 1 -> 1
	 * 0 | 0 -> 0
	 */
	public void bitwiseOr() {
		
		System.out.println("bitwiseOr:");
		int a = 0b1010;
		int b = 0b1100;
		int c = a | b;
		System.out.println(Integer.toBinaryString(c));
	}
	
	/**
	 * 异或运算符 | 规则:
	 * 1 ^ 1 -> 0
	 * 1 ^ 0 -> 1
	 * 0 ^ 1 -> 1
	 * 0 ^ 0 -> 0
	 */
	public void bitwiseXor() {
		
		System.out.println("bitwiseXor:");
		int a = 0b1010;
		int b = 0b1100;
		int c = a ^ b;
		System.out.println(Integer.toBinaryString(c));
	}
	
	public void bitwiseNot() {
		
		System.out.println("bitwiseNot:");
		int a = 0b1010;
		int b = ~a;
		System.out.println(Integer.toBinaryString(b));
	}
	
	/**
	 * 移位运算符
	 * >> 带符号右移
	 * << 带符号左移
	 * >> 不带符号右移,高位用0补位
	 */
	public void bitwiseBitmove() {
		
		System.out.println("bitwiseBitmove:");
		int a = 0b1;
		int b = a << 3;
		System.out.println(Integer.toBinaryString(b));
		
		int c = -0b1;
		int d = c >> 3;
		System.out.println(Integer.toBinaryString(d));
		
		int e = -0b1;
		int f = e >>> 3;
		System.out.println(Integer.toBinaryString(f));
	}
	
	/**
	 * 利用位运算判断输入信号串
	 * 
	 * @param signal 输入的信号串
	 */
	public void bitwiseSwitch(int signal) {
		
		System.out.println("bitwiseSwitch:");
		int len = Integer.toBinaryString(signal).length();
		for(int i=0; i<len; i++) {
			int seed = 1;
			seed <<= i;
			seed &= signal;
			if(seed != 0) {
				System.out.println("输入信号第" + i + "位为开");
			}else {
				System.out.println("输入信号第" + i + "位为闭");
			}
		}
	}
	
	public static void main(String...args) {
		
		Bitwise bw = new Bitwise();
		bw.bitwise4And();
		bw.bitwiseOr();
		bw.bitwiseXor();
		bw.bitwiseNot();
		bw.bitwiseBitmove();
		bw.bitwiseSwitch(0b10100101);
	}
}
