/*
 * �̣߳������߳�ͬʱ���еİ���
 */

package com.study;

public class Demo7 {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		Pig pig = new Pig(10);
		Bird bird = new Bird(10);
		Thread t1 = new Thread(pig);
		Thread t2 = new Thread(bird);
		t1.start();
		t2.start();
	}

}

class Bird implements Runnable {
	int n = 0;
	int res = 0;
	int times = 0;
	
	public Bird(int n) {
		this.n = n;
	}
	
	public void run() {
		while(true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			res += (++times);
			System.out.println("��ǰ�Ľ����"+res);
			if(times == n) {
				System.out.println("���Ľ����"+res);
				break;
			}
		}
	}
}

class Pig implements Runnable {
	int n = 0; 
	int times = 0;
	
	public Pig(int n) {
		this.n = n;
	}

	public void run() {
		while(true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			times++;
			System.out.println("����һ���̣߳��������"+times+"��hello world");
			if(times == n) {
				break;
			}
		}
	}
	
}








