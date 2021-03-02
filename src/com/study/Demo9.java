/*
 * 线程安全问题演示即解决
 */
package com.study;

public class Demo9 {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		//定义一个售票窗口
		TicketWindow tw1 = new TicketWindow();
		//启动三个线程
		Thread t1 = new Thread(tw1);
		Thread t2 = new Thread(tw1);
		Thread t3 = new Thread(tw1);

		t1.start();
		t2.start();
		t3.start();

	}

}
//售票窗口类
class TicketWindow implements Runnable {
	//一共有2000张票
	private int nums = 2000;
	
	public TicketWindow() {
		
	}
	
	public void run() {
		while(true) {
			
			//认为if else需要保证其原子性（使用对象锁——在这里是this，可以是任何对象）
			//后来的线程进入等待池等待上一个线程的对象锁解开
			synchronized (this) {
				if(nums > 0) {
					//显示售票信息
					//Thread.currentThread().getName()得到当前线程的名字
					System.out.println(Thread.currentThread().getName()+"在售出第"+nums+"张票");
					//出票速度1s一张
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
					nums--;
				}else {
					//售票结束
					break;
				}	
			}
		}
	}
}