/*
 * 演示FileOutputStream流
 */
package com.study;

import java.io.*;

public class Demo12 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		File f = new File("e:/18482/test.txt");
		FileOutputStream fos = null;
		
		//字节输出流
		try {
			fos = new FileOutputStream(f);
			
			String s = "hello world 哈哈哈\r\n";
			String s1 = "没有问题";

			//如何把String转换为bytes数组并写入到输出流
			fos.write(s.getBytes());
			fos.write(s1.getBytes());
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}

}
