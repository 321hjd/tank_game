/*
 * io����File��Ļ�������
 */
package com.study;

import java.io.*;

public class Demo10 {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
//		//�����ļ�����
//		File f = new File("README.md");
//		//�õ��ļ���·��
//		System.out.println("�ļ�·����"+f.getAbsolutePath());
//		//�õ��ļ���С(�ֽڴ�С)
//		System.out.println("�ļ��ֽڴ�С��"+f.length());
//		//�ļ��Ƿ�ɶ�
//		System.out.println("�ɶ���"+f.canRead());
		
//		//�����ļ�
//		File f = new File("e:/18482/test.txt");
//		if(!f.exists()) {
//			//���Դ���
//			try {
//				f.createNewFile();
//				System.out.println("���Դ���");
//			} catch (IOException e) {
//				// TODO �Զ����ɵ� catch ��
//				e.printStackTrace();
//			}
//		}else {
//			System.out.println("�ļ����ڣ����ܴ���");
//		}
		
//		//�����ļ���
//		File f = new File("e:/18482/test");
//		if(f.isDirectory()) {
//			System.out.println("�ļ��д��ڣ����ܴ���");
//		}else {
//			f.mkdir();
//			System.out.println("�ļ��в����ڣ����Դ���");
//		}
		
		//�г�һ���ļ����µ������ļ�
		File f = new File("e:/18482");
		if(f.isDirectory()) {
			File lists[] = f.listFiles();
			for(File file:lists) {
				System.out.println("�ļ�����"+file.getName());
			}
		}
	}

}




