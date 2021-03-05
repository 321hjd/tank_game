/*
 * 演示FileInputStream类的使用
 */
package com.study;

import java.io.*;

public class Demo11 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		File f = new File("src\\com\\study\\demo1.java");
		FileInputStream fis = null;
		
		//File无读写能力，需要用InputStream类
		try {
			fis = new FileInputStream(f);
			
			//定义一个字节数组（相当于缓存，以防文件过大，需要一点一点读取）
			byte[] bytes = new byte[1024];
			//得到实际读取到的字节数
			int n = 0;
			//循环读取（到达文本末尾则返回-1，说明读取结束）
			while((n = fis.read(bytes)) != -1) {
				//把字节转成String
				String s = new String(bytes, 0, n);
				System.out.println(s);
			}
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			//关闭文件流必须放在这里（因为可能出现异常，需要保证文件关闭）
			try {
				fis.close();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}

}
