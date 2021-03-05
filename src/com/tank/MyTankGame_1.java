/**
 * @author hjd
 * ���ܣ�̹����Ϸ5.0�汾
 * 1.����̹��
 * 2.�ҵ�̹�˿������������ƶ�
 * 3.�����������˵�̹�ˣ�ע����ɫ��
 * 4.�ҵ�̹�˿��Է����ӵ�
 * 5.�ӵ��������������5�ţ�
 * 6.�ҷ�̹�˻��ез�̹�ˣ��з�̹����ʧ(��ըЧ��)
 * 7.����̹�˿ɷ��ӵ�
 * 8.�з�̹��Ҳ��������������������ƶ�
 * 9.�����ҷ��͵з�̹���ڹ̶���Χ�ڻ
 * 10.������̹�˻����ҷ�̹�ˣ��ҷ�̹����ʧ
 * 11.��ֹ����̹���ص��˶�
 * 12.�ֹؿ�(��ʾ��Ϣ��˸Ч��)
 * 13.��¼��ҳɼ�
 * 14.�ܹ������˳���Ϸ���´δ���Ϸʱ�����Իָ����ϴ��˳��ĵз�������
 * 15.��������
 */
package com.tank;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;
import java.io.*;

public class MyTankGame_1 extends JFrame implements ActionListener {
	MyPanel mp = null;
	
	//����һ����ʼ���
	MyStartPanel msp = null;
	
	//��һ���˵�
	JMenuBar jmb = null;
	//��ʼ��Ϸ
	JMenu jm1 = null;
	JMenuItem jmi1 = null;
	//�˳�ϵͳ
	JMenuItem jmi2 = null;
	//�����˳�
	JMenuItem jmi3 = null;
	//������Ϸ
	JMenuItem jmi4 = null;
	
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		MyTankGame_1 game = new MyTankGame_1();
	}
	
	//���캯��
	public MyTankGame_1() {		
		//�����˵����˵�ѡ��
		jmb = new JMenuBar();
		jm1 = new JMenu("��Ϸ(G)");
		jmi1 = new JMenuItem("��ʼ����Ϸ(N)");
		jmi2 = new JMenuItem("�˳���Ϸ(E)");
		jmi3 = new JMenuItem("�����˳���Ϸ(S)");
		jmi4 = new JMenuItem("������Ϸ(C)");
		//���ÿ�ݷ�ʽ
		jm1.setMnemonic('G');
		jmi1.setMnemonic('N');
		jmi2.setMnemonic('E');
		jmi3.setMnemonic('S');
		jmi4.setMnemonic('C');

		
		//ע�����
		jmi1.addActionListener(this);
		jmi1.setActionCommand("newgame");
		jmi2.addActionListener(this);
		jmi2.setActionCommand("exit");
		jmi3.addActionListener(this);
		jmi3.setActionCommand("saveExit");
		jmi4.addActionListener(this);
		jmi4.setActionCommand("conGame");
		
		//���˵�ѡ��ӵ��˵���
		jm1.add(jmi1);
		jm1.add(jmi2);
		jm1.add(jmi3);
		jm1.add(jmi4);
		jmb.add(jm1);
		
		msp = new MyStartPanel();
		this.add(msp);
		Thread t = new Thread(msp);
		t.start();
		
		//���˵����ӵ�JFrame
		this.setJMenuBar(jmb);
		
		//���ô�������
		this.setSize(600, 500);
		this.setTitle("̹�˴�ս");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//��ʾ
		this.setVisible(true);
	}

	//�˵���Ӧ����
	public void actionPerformed(ActionEvent arg0) {
		// TODO �Զ����ɵķ������
		//���û���ͬ�ĵ����������
		if(arg0.getActionCommand().equals("newgame")) {
			//������Ϸս�����
			mp = new MyPanel("newGame");
			
			//����mp�߳�
			Thread t = new Thread(mp);
			t.start();
			
			//��ɾ���ɵĿ�ʼpanel
			this.remove(msp);
			
			//������
			this.add(mp);
			
			//ע�����
			this.addKeyListener(mp);
			
			//��ʾ������JFrame
			this.setVisible(true);
		}else if(arg0.getActionCommand().equals("exit")) {
			//�û�����ˡ��˳�ϵͳ���Ĳ˵�
			//������ٵ�������������
			Recorder.keepRecording();
			//�˳�
			System.exit(0);
		}else if(arg0.getActionCommand().equals("saveExit")) {
			//�û���������˳�
			//������ٵ��������͵��˵�����
			Recorder rd = new Recorder();
			rd.setEts(mp.ets);
			rd.keepRecAndEnemyTank();
			//�˳�
			System.exit(0);
		}else if(arg0.getActionCommand().equals("conGame")) {
			//�û����������Ϸ
			//������Ϸս�����
			mp = new MyPanel("continue");
			
			//����mp�߳�
			Thread t = new Thread(mp);
			t.start();
			
			//��ɾ���ɵĿ�ʼpanel
			this.remove(msp);
			
			//������
			this.add(mp);
			
			//ע�����
			this.addKeyListener(mp);
			
			//��ʾ������JFrame
			this.setVisible(true);
		}
	}
}

//��ʼ���桪������ʾ����
class MyStartPanel extends JPanel implements Runnable {
	int times = 0;
	
	public void paint(Graphics g) {
		super.paint(g);
		g.fillRect(0, 0, 400, 300);
		
		//��ʾ��Ϣ
		if(times%2 == 0) {
			g.setColor(Color.yellow);
			Font myFont = new Font("������κ", Font.BOLD, 30);
			g.setFont(myFont);
			g.drawString("stage: 1", 150, 150);
		}
	}

	public void run() {
		// TODO �Զ����ɵķ������
		while(true) {
			//����
			try {
				Thread.sleep(400);
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			times++;
			
			//�ػ�
			this.repaint();
			
			//��ֹint������Χ����ʱ����
			if(times == 1000) {
				times = 0;
			}
		}
	}
}


//�ҵ����
class MyPanel extends JPanel implements KeyListener, Runnable {
	//����һ���ҵ�̹��
	Hero hero = null;
	
	//������˵�̹���飨��Ϊ��̹ͬ�˵��˶����ӵ�����ͬ������Ƕ��̵߳ģ���Ҫʹ���̰߳�ȫ��Vector
	Vector<EnemyTank> ets = new Vector<EnemyTank>();
	
	//����ָ����
	Vector<Node> nodes = new Vector<Node>();
	
	//����ը������
	Vector<Bomb> bombs = new Vector<Bomb>();
	
	//��������
	int enSize = 3;		
	
	//��������ͼƬ������ͼƬ��ɶ�̬��ըЧ��
	Image img1 = null;
	Image img2 = null;
	Image img3 = null;
	
	
	//���캯��
	public MyPanel(String flag) {
		//�ָ���¼
		Recorder.getRecording();
		
		//̹�˵ĳ�ʼλ��
		hero = new Hero(100, 200);	
		
		if(flag.equals("newGame")){
			//��ʼ�����˵�̹��
			for(int i = 0;i < enSize;i++) {
				//����һ�����˶���
				EnemyTank et = new EnemyTank((i+1)*50, 0);
				//������ɫ
				et.setColor(0);
				et.setDirect(2);
				//��MyPanle�ĵ���̹��������������̹��
				et.setEts(ets);
				
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
		}else {
			nodes = new Recorder().getNodesAndEnNums();
			
			//��ʼ�����˵�̹��
			for(int i = 0;i < nodes.size();i++) {
				//ȡһ��node
				Node node = nodes.get(i);
				//����һ�����˶���
				EnemyTank et = new EnemyTank(node.x, node.y);
				//������ɫ
				et.setColor(0);
				et.setDirect(node.direct);
				//��MyPanle�ĵ���̹��������������̹��
				et.setEts(ets);
				
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
		}
		
		try {
			img1 = ImageIO.read(new File("resources/images/bomb_1.jpg"));
			img2 = ImageIO.read(new File("resources/images/bomb_2.jpg"));
			img3 = ImageIO.read(new File("resources/images/bomb_3.jpg"));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		//������Ƶ����
		AePlayWave apw = new AePlayWave("resources/music/111.wav");
		apw.start();
		
		//��ʼ��ͼƬ(���ַ�����һ���ӵ��ı�ըЧ��������)
//		img1 = Toolkit.getDefaultToolkit().getImage("resources/images/bomb_1.jpg");
//		img2 = Toolkit.getDefaultToolkit().getImage("resources/images/bomb_2.jpg");
//		img3 = Toolkit.getDefaultToolkit().getImage("resources/images/bomb_3.jpg");
	}
	
	//������ʾ��Ϣ
	public void showInfo(Graphics g) {
		//������ʾ��Ϣ
		this.drawTank(80, 330, g, 0, 1);
		g.setColor(Color.black);
		g.drawString(Recorder.getEnNum()+"", 105, 350);
		this.drawTank(130, 330, g, 0, 0);
		g.setColor(Color.black);
		g.drawString(Recorder.getMyLife()+"", 155, 350);
	
		//��������ܳɼ�
		g.setColor(Color.black);
		Font f = new Font("����", Font.BOLD, 20);
		g.setFont(f);
		g.drawString("����ܳɼ�", 420, 30);
		
		//��������̹��
		this.drawTank(420, 60, g, 0, 1);
		//���������������+������Ϊ��intת��ΪString��
		g.setColor(Color.black);
		g.drawString(Recorder.getAllEnNum()+"", 460, 80);
	}
	
	//��дpaint����
	public void paint(Graphics g) {
		//���ú�����ʼ��������ȱ�٣�
		super.paint(g);
		//����̹�˻����
		g.fillRect(0, 0, 400, 300);	
		
		//������ʾ��Ϣ̹�ˣ�������ʾ���ã�
		this.showInfo(g);
		
		//���ҵ�̹��
		if(this.hero.isLive) {
			this.drawTank(hero.getX(), hero.getY(), g, this.hero.direct, 0);
		}
		
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
	
	//�ж��Ƿ���е��˵�̹��
	public void hitEnemy() {
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
						if(this.hitTank(myShot, et)) {
							//������������
							Recorder.reduceEnNum();
							//�����ҵ�ս��
							Recorder.addEnNumRec();
						}
					}
				}
			}
		}
	}
	
	//�жϵ���̹���Ƿ�����ҷ�̹��
	public void hitMe() {
		//ȡ��ÿ������̹��
		for(int i = 0;i < this.ets.size();i++) {
			//ȡ��̹��
			EnemyTank et = ets.get(i);
			
			//ȡ��ÿ���ӵ�
			for(int j = 0;j < et.ss.size();j++) {
				//ȡ���ӵ�
				Shot enemyShot = et.ss.get(j);
				if(hero.isLive) {
					if(this.hitTank(enemyShot, hero)) {	
						//to do
						
					}
				}
			}
		}
	}
	
	
	//�ж��ӵ��Ƿ��ез�̹��
	public boolean hitTank(Shot s, Tank et) {
		boolean ishitted = false;
		
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
				//����boolֵ����
				ishitted = true;
				//����һ��ը��������Vector
				Bomb b = new Bomb(et.x, et.y);
				bombs.add(b);
			}
			break;
		case 1:
		case 3:
			if(s.x > et.x && s.x < et.x+30 &&
					s.y > et.y && s.y < et.y+20) {
				//����
				//�ӵ�����
				s.isLive = false;
				//����̹������
				et.isLive = false;
				//����boolֵ����
				ishitted = true;
				//����һ��ը��������Vector
				Bomb b = new Bomb(et.x, et.y);
				bombs.add(b);
			}
			break;
		}
		return ishitted;
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
			//�ж��ҷ�̹���Ƿ���е���
			this.hitEnemy();
			//�жϵ���̹���Ƿ�����ҷ�̹��
			this.hitMe();
			
			//�ػ�
			this.repaint();
		}
	}
}











