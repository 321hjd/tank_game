/*
 * 文件字符流：拷贝文本文件
 */
package com.study;

import java.io.*;

public class Demo14 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		//文件读取字符流对象（输入流）
		FileReader fr = null;
		//文件写入字符流对象（输出流）
		FileWriter fw = null;
		
		try {
			//创建fr对象
			fr = new FileReader("E:\\18482\\test.txt");
			//创建输出对象
			fw = new FileWriter("E:/18482/ttt.txt");
			
			//读入到内存
			char[] c = new char[1024];
			int n = 0; //记录实际读取到的字符数
			while((n = fr.read(c)) != -1) {
//				String s = new String(c, 0, n);
//				System.out.println(s);
				fw.write(c, 0, n);	//指定位置写入
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fr.close();
				fw.close();
			} catch (Exception e) {
				
			}
		}
	}

}
