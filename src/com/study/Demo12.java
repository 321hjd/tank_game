/*
 * ��ʾFileOutputStream��
 */
package com.study;

import java.io.*;

public class Demo12 {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		File f = new File("e:/18482/test.txt");
		FileOutputStream fos = null;
		
		//�ֽ������
		try {
			fos = new FileOutputStream(f);
			
			String s = "hello world ������\r\n";
			String s1 = "û������";

			//��ΰ�Stringת��Ϊbytes���鲢д�뵽�����
			fos.write(s.getBytes());
			fos.write(s1.getBytes());
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}
	}

}
