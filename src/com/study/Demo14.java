/*
 * �ļ��ַ����������ı��ļ�
 */
package com.study;

import java.io.*;

public class Demo14 {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		//�ļ���ȡ�ַ���������������
		FileReader fr = null;
		//�ļ�д���ַ��������������
		FileWriter fw = null;
		
		try {
			//����fr����
			fr = new FileReader("E:\\18482\\test.txt");
			//�����������
			fw = new FileWriter("E:/18482/ttt.txt");
			
			//���뵽�ڴ�
			char[] c = new char[1024];
			int n = 0; //��¼ʵ�ʶ�ȡ�����ַ���
			while((n = fr.read(c)) != -1) {
//				String s = new String(c, 0, n);
//				System.out.println(s);
				fw.write(c, 0, n);	//ָ��λ��д��
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
