/**
 * java��ͼԭ��
 */
package com.study;

import java.awt.*;
import javax.swing.*;
/**
 * @author 18482
 *
 */
public class Demo1 extends JFrame {
	//����
	MyPanel mp = null;
	
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		Demo1 demo1 = new Demo1();
	}
	
	//���캯��
	public Demo1() {
		mp = new MyPanel();
		
		//��JPanel���뵽JFrame
		this.add(mp);
		
		//���ô�������
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//��ʾ
		this.setVisible(true);
	}
}

//����һ��MyPanel(�Լ�����壬���ڻ�ͼ��ʵ�ֻ�ͼ������)
class MyPanel extends JPanel {
	//����JPanel��paint�������������һ����ʾʱ�����Զ�����paint������
	//Graphics�ǻ�ͼ����Ҫ�࣬�������Ϊ����
	public void paint(Graphics g) {
		//���ø��ຯ����ʼ��������ȱ�٣�
		super.paint(g);
//		//��һ��Բ
//		g.drawOval(10, 10, 30, 30);	
//		//��ֱ��
//		g.drawLine(10, 10, 20, 20);
//		//�����α߿�
//		g.drawRect(10, 10, 40, 60);
//		//��������
//		g.setColor(new Color(30, 40, 200));		//���ɫ��Ĭ�Ϻ�ɫ��
//		g.fillRect(30, 30, 40,  60);
//		//������ϻ�ͼƬ
//		Image im = Toolkit.getDefaultToolkit().getImage("images/ye.jpg");
//		g.drawImage(im, 90, 90, 200, 150, this);
//		//������
//		g.setFont(new Font("���Ĳ���", Font.BOLD, 30));	//�޸����壨�Ӵ֣�
//		g.setColor(Color.red);							//�޸�������ɫ
//		g.drawString("�������", 100, 100);
		//��������
		g.drawArc(100, 200, 120, 300, 50, 100);
	}
}


















