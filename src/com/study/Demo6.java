/*
 * ��ʾʵ��Runnable�ӿ��������߳�
 * 1.ÿ��1s��ӡhello world��10���Ժ��Զ��˳�
 */

package com.study;

public class Demo6 {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		Dog2 dog2 = new Dog2();
		//�����̡߳����ᵼ��run����������
		//����һ���̶߳���
		Thread t = new Thread(dog2);
		t.start();
	}

}

class Dog2 implements Runnable {
	int times = 0;
	
	//��дrun����
	public void run() {
		while(true) {
			//����һ��
			//1000��ʾ1000����
			//sleep���¸��߳̽���Blocked״̬���ͷ���Դ
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			times++;
			System.out.println("hello world");
			if(times == 10) {
				//�˳�
				break;
			}
		}
	}
}