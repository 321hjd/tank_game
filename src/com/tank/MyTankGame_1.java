/**
 * @author hjd
 * ���ܣ�̹����Ϸ2.0�汾
 * 1.����̹��
 * 2.�ҵ�̹�˿������������ƶ�
 * 3.�����������˵�̹�ˣ�ע����ɫ��
 */
package com.tank;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;

public class MyTankGame_1 extends JFrame {
	MyPanel mp = null;
	
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		MyTankGame_1 game = new MyTankGame_1();
	}
	
	//���캯��
	public MyTankGame_1() {
		//�������
		mp = new MyPanel();
		
		//������
		this.add(mp);
		
		//ע�����
		this.addKeyListener(mp);
		
		//���ô�������
		this.setSize(400, 300);
		this.setTitle("̹�˴�ս");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//��ʾ
		this.setVisible(true);
	}
}

//�ҵ����
class MyPanel extends JPanel implements KeyListener {
	//����һ���ҵ�̹��
	Hero hero = null;
	//������˵�̹���飨��Ϊ��̹ͬ�˵��˶����ӵ�����ͬ������Ƕ��̵߳ģ���Ҫʹ���̰߳�ȫ��Vector
	Vector<EnemyTank> ets = new Vector<EnemyTank>();
	
	//��������
	int enSize = 3;		
	
	//���캯��
	public MyPanel() {
		//̹�˵ĳ�ʼλ��
		hero = new Hero(10, 10);	
		//��ʼ�����˵�̹��
		for(int i = 0;i < enSize;i++) {
			//����һ�����˶���
			EnemyTank et = new EnemyTank((i+1)*50, 0);
			//������ɫ
			et.setColor(0);
			et.setDirect(2);
			//�����˶���������̹����
			ets.add(et);
		}
	}
	
	//��дpaint����
	public void paint(Graphics g) {
		//���ú�����ʼ��������ȱ�٣�
		super.paint(g);
		//����̹�˻����
		g.fillRect(0, 0, 400, 300);	
		//���ҵ�̹��
		this.drawTank(hero.getX(), hero.getY(), g, this.hero.direct, 0);
		//�������˵�̹��
		for(int i = 0;i < ets.size();i++) {
			this.drawTank(ets.get(i).getX(), ets.get(i).getY(), g, ets.get(i).getDirect(), 1);
		}
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
			break;
		case 1:
			//��Ͳ���ң��˴���Ϊ�ֲڣ��������Ͻ�Ϊ���յ㣩
			//1.������ߵľ���
			g.fill3DRect(x, y, 30, 5, false);
			//2.�����ұߵľ���
			g.fill3DRect(x, y+15, 30, 5, false);
			//3.�����м�ľ���
			g.fill3DRect(x+5, y+5, 20, 10, false);
			//4.����Բ��
			g.fillOval(x+10, y+5, 10, 10);
			//5.������
			g.drawLine(x+15, y+10, x+30, y+10);
			break;
		case 2:
			//��Ͳ���£��˴���Ϊ�ֲڣ��������Ͻ�Ϊ���յ㣩
			//1.������ߵľ���
			g.fill3DRect(x, y, 5, 30, false);
			//2.�����ұߵľ���
			g.fill3DRect(x+15, y, 5, 30, false);
			//3.�����м�ľ���
			g.fill3DRect(x+5, y+5, 10, 20, false);
			//4.����Բ��
			g.fillOval(x+5, y+10, 10, 10);
			//5.������
			g.drawLine(x+10, y+15, x+10, y+30);
			break;
		case 3:
			//��Ͳ���󣨴˴���Ϊ�ֲڣ��������Ͻ�Ϊ���յ㣩
			//1.������ߵľ���
			g.fill3DRect(x, y, 30, 5, false);
			//2.�����ұߵľ���
			g.fill3DRect(x, y+15, 30, 5, false);
			//3.�����м�ľ���
			g.fill3DRect(x+5, y+5, 20, 10, false);
			//4.����Բ��
			g.fillOval(x+10, y+5, 10, 10);
			//5.������
			g.drawLine(x+15, y+10, x, y+10);
			break;
		}	
	}

	//1.�������¡���a���� s���� w���� d����
	public void keyPressed(KeyEvent arg0) {
		//�ٿ�̹���ƶ�
		switch(arg0.getKeyCode()) {
		case KeyEvent.VK_W:
			//�����ҵ�̹�˵ķ�����
			this.hero.setDirect(0);
			this.hero.moveUp();
			break;
		case KeyEvent.VK_A:
			//��
			this.hero.setDirect(3);
			this.hero.moveLeft();
			break;
		case KeyEvent.VK_S:
			//��
			this.hero.setDirect(2);
			this.hero.moveDown();
			break;
		case KeyEvent.VK_D:
			//��
			this.hero.setDirect(1);
			this.hero.moveRight();
			break;
		default:
			break;
		}
		
		//�����ػ�Panel
		this.repaint();
	}

	//2.�����ɿ�
	public void keyReleased(KeyEvent arg0) {
		// TODO �Զ����ɵķ������
		
	}

	//3.��������ַ�
	public void keyTyped(KeyEvent arg0) {
		// TODO �Զ����ɵķ������
		
	}
}











