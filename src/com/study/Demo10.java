/*
 * io流：File类的基本方法
 */
package com.study;

import java.io.*;

public class Demo10 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
//		//创建文件对象
//		File f = new File("README.md");
//		//得到文件的路径
//		System.out.println("文件路径："+f.getAbsolutePath());
//		//得到文件大小(字节大小)
//		System.out.println("文件字节大小："+f.length());
//		//文件是否可读
//		System.out.println("可读："+f.canRead());
		
//		//创建文件
//		File f = new File("e:/18482/test.txt");
//		if(!f.exists()) {
//			//可以创建
//			try {
//				f.createNewFile();
//				System.out.println("可以创建");
//			} catch (IOException e) {
//				// TODO 自动生成的 catch 块
//				e.printStackTrace();
//			}
//		}else {
//			System.out.println("文件存在，不能创建");
//		}
		
//		//创建文件夹
//		File f = new File("e:/18482/test");
//		if(f.isDirectory()) {
//			System.out.println("文件夹存在，不能创建");
//		}else {
//			f.mkdir();
//			System.out.println("文件夹不存在，可以创建");
//		}
		
		//列出一个文件夹下的所有文件
		File f = new File("e:/18482");
		if(f.isDirectory()) {
			File lists[] = f.listFiles();
			for(File file:lists) {
				System.out.println("文件名："+file.getName());
			}
		}
	}

}




