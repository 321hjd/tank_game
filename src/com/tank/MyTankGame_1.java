/**
 * ���ܣ�̹����Ϸ1.0�汾
 * 1.����̹��
 */
package com.tank;

import java.awt.*;
import javax.swing.*;
/**
 * @author 18482
 *
 */
public class MyTankGame_1 extends JFrame {
	MyPanel mp = null;
	
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		MyTankGame_1 game = new MyTankGame_1();
	}
	
	//���캯��
	public MyTankGame_1() {
		mp = new MyPanel();
		
		this.add(mp);
		this.setSize(400, 300);
		this.setTitle("̹�˴�ս");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}

//�ҵ����
class MyPanel extends JPanel {
	//����һ���ҵ�̹��
	Hero hero = null;
	
	//���캯��
	public MyPanel() {
		//̹�˵ĳ�ʼλ��
		hero = new Hero(10, 10);	
	}
	
	//��дpaint����
	public void paint(Graphics g) {
		//���ú�����ʼ��������ȱ�٣�
		super.paint(g);
		//����̹�˻����
		g.fillRect(0, 0, 400, 300);	
		//��̹��
		this.drawTank(hero.getX(), hero.getY(), g, 0, 0);
	}
	
	//����̹�˵ĺ���
	public void drawTank(int x, int y, Graphics g, int direct, int type) {
		//�ж�̹������
		switch(type) {
		case 0:
			g.setColor(Color.YELLOW);
			break;
		case 1:
			g.setColor(Color.CYAN);
			break;
		}
		//�ж�̹�˷���
		switch(direct) {
		//����
		case 0:					
			//�����ҵ�̹��
			//������ɫ	
			//1.������ߵľ���
			g.fill3DRect(x, y, 5, 30, false);
			//2.�����ұߵľ���
			g.fill3DRect(x+15, y, 5, 30, false);
			//3.�����м�ľ���
			g.fill3DRect(x+5, y+5, 10, 20, false);
			//4.����Բ��
			g.fillOval(x+5, y+10, 10, 10);
			//5.������
			g.drawLine(x+10, y+15, x+10, y);
		}	
	}
}

//̹����
class Tank{
	//̹�˺�����
	int x = 0;
	//̹��������
	int y = 0;
	
	//���캯��
	public Tank(int x, int y) {
		this.x = x;
		this.y = y;	
	}
	
	//get��set����
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}

//�ҵ�̹��
class Hero extends Tank {
	public Hero(int x, int y) {
		//���ú�����ʼ��������ȱ�٣�
		super(x,y);
	}
}










