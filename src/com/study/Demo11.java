/*
 * ��ʾFileInputStream���ʹ��
 */
package com.study;

import java.io.*;

public class Demo11 {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		File f = new File("src\\com\\study\\demo1.java");
		FileInputStream fis = null;
		
		//File�޶�д��������Ҫ��InputStream��
		try {
			fis = new FileInputStream(f);
			
			//����һ���ֽ����飨�൱�ڻ��棬�Է��ļ�������Ҫһ��һ���ȡ��
			byte[] bytes = new byte[1024];
			//�õ�ʵ�ʶ�ȡ�����ֽ���
			int n = 0;
			//ѭ����ȡ�������ı�ĩβ�򷵻�-1��˵����ȡ������
			while((n = fis.read(bytes)) != -1) {
				//���ֽ�ת��String
				String s = new String(bytes, 0, n);
				System.out.println(s);
			}
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} finally {
			//�ر��ļ���������������Ϊ���ܳ����쳣����Ҫ��֤�ļ��رգ�
			try {
				fis.close();
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
	}

}
