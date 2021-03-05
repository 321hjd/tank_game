/*
 * 图片拷贝
 */
package com.study;

import java.io.*;

public class Demo13 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		
		//先把图片读入到内存
		//因为是图片是二进制文件，因此只能用字节流完成
		File f1 = new File("D:\\18482\\Pictures\\Saved Pictures\\1.jpg");

		//输入流
		FileInputStream fis = null;
		//输出流
		FileOutputStream fos= null;
		
		try {
			fis = new FileInputStream(f1);
			fos = new FileOutputStream("e:/18482/1.jpg");
			
			byte[] buf = new byte[1024];
			int n = 0; //记录实际读取到的字节数
			//循环读取
			//从fis读取内容到字节数组buf中（一次读取1024个字节）
			while((n = fis.read(buf)) != -1) {
				//输出到指定文件
				fos.write(buf);
			}
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} finally {
			//关闭打开的文件流
			try {
				fis.close();
				fos.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
