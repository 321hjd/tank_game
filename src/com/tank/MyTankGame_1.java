/**
 * @author hjd
 * ���ܣ�̹����Ϸ4.0�汾
 * 1.����̹��
 * 2.�ҵ�̹�˿������������ƶ�
 * 3.�����������˵�̹�ˣ�ע����ɫ��
 * 4.�ҵ�̹�˿��Է����ӵ�
 * 5.�ӵ��������������5�ţ�
 * 6.�ҷ�̹�˻��ез�̹�ˣ��з�̹����ʧ(��ըЧ��)
 * 7.����̹�˿ɷ��ӵ�
 * 8.�з�̹��Ҳ��������������������ƶ�
 * 9.�����ҷ��͵з�̹���ڹ̶���Χ�ڻ
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
		
		//����mp�߳�
		Thread t = new Thread(mp);
		t.start();
		
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
class MyPanel extends JPanel implements KeyListener, Runnable {
	//����һ���ҵ�̹��
	Hero hero = null;
	//������˵�̹���飨��Ϊ��̹ͬ�˵��˶����ӵ�����ͬ������Ƕ��̵߳ģ���Ҫʹ���̰߳�ȫ��Vector
	Vector<EnemyTank> ets = new Vector<EnemyTank>();
	
	//����ը������
	Vector<Bomb> bombs = new Vector<Bomb>();
	
	//��������
	int enSize = 3;		
	
	//��������ͼƬ������ͼƬ��ɶ�̬��ըЧ��
	Image img1 = null;
	Image img2 = null;
	Image img3 = null;
	
	
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
			//�������˵�̹��
			Thread t = new Thread(et);
			t.start();
			//�����˵�̹������ӵ�
			Shot s = new Shot(et.x+10, et.y+30, et.direct);
			//���ӵ��Ӹ�����̹��
			et.ss.add(s);
			Thread t2 = new Thread(s);
			t2.start();
			//�����˶���������̹����
			ets.add(et);
		}
		//��ʼ��ͼƬ
		img1 = Toolkit.getDefaultToolkit().getImage("images/bomb_1.jpg");
		img2 = Toolkit.getDefaultToolkit().getImage("images/bomb_2.jpg");
		img3 = Toolkit.getDefaultToolkit().getImage("images/bomb_3.jpg");
	}
	
	//��дpaint����
	public void paint(Graphics g) {
		//���ú�����ʼ��������ȱ�٣�
		super.paint(g);
		//����̹�˻����
		g.fillRect(0, 0, 400, 300);	
		
		//���ҵ�̹��
		this.drawTank(hero.getX(), hero.getY(), g, this.hero.direct, 0);
		
		//����ը��
		for(int i = 0;i < bombs.size();i++) {
			//ȡ��ը��
			Bomb b = bombs.get(i);
			
			if(b.life > 6) {
				g.drawImage(img1, b.x, b.y, 30, 30, this);
			}else if(b.life > 3) {
				g.drawImage(img2, b.x, b.y, 30, 30, this);
			}else {
				g.drawImage(img3, b.x, b.y, 30, 30, this);
			}
			
			//��ը��������ֵ��С
			b.lifeDown();
			//���ը������ֵΪ0���Ѹ�ը����bombs����ȥ��
			if(b.life == 0) {
				bombs.remove(b);
			}
		}
		
		//�������˵�̹��
		for(int i = 0;i < ets.size();i++) {
			EnemyTank et = ets.get(i);
			if(et.isLive) {
				this.drawTank(et.getX(), et.getY(), g, et.getDirect(), 1);
				//�������˵��ӵ�
				for(int j = 0;j < et.ss.size();j++) {
					//ȡ��һ���ӵ�
					Shot enemyShot = et.ss.get(j);
					//����һ���ӵ�
					if(enemyShot.isLive) {
						g.draw3DRect(enemyShot.x, enemyShot.y, 1, 1, false);
					}else {
						//�������̹�������ʹ�Vectorȥ��
						et.ss.remove(enemyShot);
					}
				}
			}
		}
		//��ss��ȡ��ÿ���ӵ�������
		for(int i = 0;i < this.hero.ss.size();i++) {
			//ȡ��һ���ӵ�
			Shot myShot = hero.ss.get(i);
			//����һ���ӵ�
			if(myShot != null && myShot.isLive == true) {
				g.draw3DRect(myShot.x, myShot.y, 1, 1, false);
			}
			//ɾ��ʧЧ�ӵ�
			if(myShot.isLive == false) {
				//��ss��ɾ�����ӵ�
				hero.ss.remove(myShot);
			}
		}
	}
	
	//�ж��ӵ��Ƿ��ез�̹��
	public void hitTank(Shot s, EnemyTank et) {
		//�жϸ�̹�˵ķ���
		switch(et.direct) {
		//������̹�˷������ϻ���
		case 0:
		case 2:
			if(s.x > et.x && s.x < et.x+20 && 
					s.y > et.y && s.y < et.y+30) {
				//����
				//�ӵ�����
				s.isLive = false;
				//����̹������
				et.isLive = false;
				//����һ��ը��������Vector
				Bomb b = new Bomb(et.x, et.y);
				bombs.add(b);
			}
		case 1:
		case 3:
			if(s.x > et.x && s.x < et.x+30 &&
					s.y > et.y && s.y < et.y+20) {
				//����
				//�ӵ�����
				s.isLive = false;
				//����̹������
				et.isLive = false;
				//����һ��ը��������Vector
				Bomb b = new Bomb(et.x, et.y);
				bombs.add(b);
			}
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
		
		//�ж�����Ƿ��¡�j�����������
		if(arg0.getKeyCode() == KeyEvent.VK_J) {
			//�����ӵ�������
			if(this.hero.ss.size() < 5) {
				this.hero.shotEnemy();
			}
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

	//��һ���߳�
	public void run() {
		//ÿ��100����ȥ�ػ�
		while(true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			
			//�ж��Ƿ����
			for(int i = 0;i < hero.ss.size();i++) {
				//ȡ���ӵ�
				Shot myShot = hero.ss.get(i);
				//�ж��ӵ��Ƿ���Ч
				if(myShot.isLive) {
					//ȡ��ÿ������̹����֮�ж�
					for(int j = 0;j < ets.size();j++) {
						//ȡ��̹��
						EnemyTank et = ets.get(j);
						if(et.isLive){
							this.hitTank(myShot, et);
						}
					}
				}
			}
			
			//�ػ�
			this.repaint();
		}
	}
}











