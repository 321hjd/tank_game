/*
 * �����ַ����������Ч�ʣ�����ֱ�Ӳ���String
 */
package com.study;

import java.io.*;

public class Demo15 {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		//�ļ������ַ�����������
		BufferedReader br = null;
		//�����
		BufferedWriter bw = null;
		
		try {
			
			FileReader fr = new FileReader("E:\\18482\\test.txt");
			br = new BufferedReader(fr);

			FileWriter fw = new FileWriter("E:\\18482\\ttt.txt");
			bw = new BufferedWriter(fw);
			//ѭ����ȡ�ļ�
			String s = "";
			int n = 0; //��¼ʵ�ʶ�ȡ�����ַ���
			//readLine()�����ȡ���з�
			while((s = br.readLine())!=null) {
//				System.out.println(s);
				//���������(���ֶ�����)
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
