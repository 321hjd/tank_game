/*
 * ͼƬ����
 */
package com.study;

import java.io.*;

public class Demo13 {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		
		//�Ȱ�ͼƬ���뵽�ڴ�
		//��Ϊ��ͼƬ�Ƕ������ļ������ֻ�����ֽ������
		File f1 = new File("D:\\18482\\Pictures\\Saved Pictures\\1.jpg");

		//������
		FileInputStream fis = null;
		//�����
		FileOutputStream fos= null;
		
		try {
			fis = new FileInputStream(f1);
			fos = new FileOutputStream("e:/18482/1.jpg");
			
			byte[] buf = new byte[1024];
			int n = 0; //��¼ʵ�ʶ�ȡ�����ֽ���
			//ѭ����ȡ
			//��fis��ȡ���ݵ��ֽ�����buf�У�һ�ζ�ȡ1024���ֽڣ�
			while((n = fis.read(buf)) != -1) {
				//�����ָ���ļ�
				fos.write(buf);
			}
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} finally {
			//�رմ򿪵��ļ���
			try {
				fis.close();
				fos.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

}
