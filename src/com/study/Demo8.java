/*
 * ���ܣ���ʾʹ���̵߳�ע������
 */
package com.study;

public class Demo8 {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		Fish fish1 = new Fish();
		fish1.start();
		Sheep sheep1 = new Sheep();
		Thread t = new Thread(sheep1);
		Thread t2 = new Thread(sheep1);
		t.start();
		t2.start();
	}

}

class Fish extends Thread {
	public void run() {
		System.out.println("11");
	}
}

class Sheep implements Runnable {
	public void run() {
		System.out.println("22");
	}
}