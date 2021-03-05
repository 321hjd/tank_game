/*
 * 缓冲字符流（提高了效率）——直接操作String
 */
package com.study;

import java.io.*;

public class Demo15 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		//文件缓冲字符流：输入流
		BufferedReader br = null;
		//输出流
		BufferedWriter bw = null;
		
		try {
			
			FileReader fr = new FileReader("E:\\18482\\test.txt");
			br = new BufferedReader(fr);

			FileWriter fw = new FileWriter("E:\\18482\\ttt.txt");
			bw = new BufferedWriter(fw);
			//循环读取文件
			String s = "";
			int n = 0; //记录实际读取到的字符数
			//readLine()不会读取换行符
			while((s = br.readLine())!=null) {
//				System.out.println(s);
				//输出到磁盘(可手动换行)
				bw.write(s+"\r\n");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				bw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
