/*
 * �̰߳�ȫ������ʾ�����
 */
package com.study;

public class Demo9 {

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		//����һ����Ʊ����
		TicketWindow tw1 = new TicketWindow();
		//���������߳�
		Thread t1 = new Thread(tw1);
		Thread t2 = new Thread(tw1);
		Thread t3 = new Thread(tw1);

		t1.start();
		t2.start();
		t3.start();

	}

}
//��Ʊ������
class TicketWindow implements Runnable {
	//һ����2000��Ʊ
	private int nums = 2000;
	
	public TicketWindow() {
		
	}
	
	public void run() {
		while(true) {
			
			//��Ϊif else��Ҫ��֤��ԭ���ԣ�ʹ�ö�����������������this���������κζ���
			//�������߳̽���ȴ��صȴ���һ���̵߳Ķ������⿪
			synchronized (this) {
				if(nums > 0) {
					//��ʾ��Ʊ��Ϣ
					//Thread.currentThread().getName()�õ���ǰ�̵߳�����
					System.out.println(Thread.currentThread().getName()+"���۳���"+nums+"��Ʊ");
					//��Ʊ�ٶ�1sһ��
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
					nums--;
				}else {
					//��Ʊ����
					break;
				}	
			}
		}
	}
}