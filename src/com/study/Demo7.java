/*
 * 线程：两个线程同时运行的案例
 */

package com.study;

public class Demo7 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
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
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			res += (++times);
			System.out.println("当前的结果是"+res);
			if(times == n) {
				System.out.println("最后的结果是"+res);
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
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			times++;
			System.out.println("我是一个线程，在输出第"+times+"个hello world");
			if(times == n) {
				break;
			}
		}
	}
	
}








