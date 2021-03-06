/*
 * 演示继承Thread类来开发线程
 * 1.每隔1s打印hello world，10次以后自动退出
 */

package com.study;

public class Demo5 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Dog	dog = new Dog();
		//启动线程——会导致run函数的运行
		dog.start();
	}

}

class Dog extends Thread {
	int times = 0;
	
	//重写run函数
	public void run() {
		while(true) {
			//休眠一秒
			//1000表示1000毫秒
			//sleep导致该线程进入Blocked状态并释放资源
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			times++;
			System.out.println("hello world");
			if(times == 10) {
				//退出
				break;
			}
		}
	}
}