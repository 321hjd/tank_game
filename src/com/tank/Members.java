/*
 * ���ܣ�̹����Ϸ��Ա��
 */
package com.tank;

import java.util.Vector;

//ը����
class Bomb {
	//����ը��������
	int x, y;
	//ը������
	int life = 9;
	//ը���Ƿ���
	boolean isLive = true;
	
	public Bomb(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	//��������ֵ
	public void lifeDown() {
		if(life > 0) {
			life--;
		}else {
			this.isLive = false;
		}
	}
}

//�ӵ���
class Shot implements Runnable {
	//����
	int x;
	int y;
	int direct;
	int speed = 2;
	boolean isLive = true;
	
	public Shot(int x, int y, int direct) {
		this.x = x;
		this.y = y;
		this.direct = direct;
	}
	
	//�߳�
	public void run() {
		while(true) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			switch(direct) {
			case 0:
				//��
				y -= speed;
				break;
			case 1:
				//��
				x += speed;
				break;
			case 2:
				//��
				y += speed;
				break;
			case 3:
				//��
				x -= speed;
				break;
			}
			
			//�жϸ��ӵ��Ƿ�������Ե
			if(x<0 || x>400 || y < 0 || y > 300) {
				this.isLive = false;
				break;
			}	
		}	
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

	public int getDirect() {
		return direct;
	}

	public void setDirect(int direct) {
		this.direct = direct;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
}


//̹����
class Tank{
	//̹�˺�������
	int x = 0;
	int y = 0;
	
	//̹�˷��򡪡�0���� 1���� 2���� 3����
	int direct = 0;
	
	//̹���ٶ�
	int speed = 1;

	//̹����ɫ
	int color = 0;



	//���캯��
	public Tank(int x, int y) {
		this.x = x;
		this.y = y;	
	}
	
	//̹���ƶ�
	public void moveUp() {
		if(y > 0) {
			y -= speed;
		}
	}
	public void moveRight() {
		if(x < 400) {
			x += speed;
		}
	}
	public void moveLeft() {
		if(x > 0) {
			x -= speed;
		}
	}
	public void moveDown() {
		if(y < 300) {
			y += speed;
		}
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
	
	public void setDirect(int direct) {
		this.direct = direct;
	}
	
	public int getDirect() {
		return direct;
	}
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}
}

//�ҵ�̹��
class Hero extends Tank {
	//�ӵ�
	Vector<Shot> ss = new Vector<Shot>();
	Shot s = null;
	
	public Hero(int x, int y) {
		//���ú�����ʼ��������ȱ�٣�
		super(x,y);
	}
	
	//����
	public void shotEnemy() {
		switch(this.direct) {
		case 0:
			s = new Shot(x+10, y, 0);
			ss.add(s);
			break;
		case 1:
			s = new Shot(x+30, y+10, 1);
			ss.add(s);
			break;
		case 2:
			s = new Shot(x+10, y+30, 2);
			ss.add(s);
			break;
		case 3:
			s = new Shot(x, y+10, 3);
			ss.add(s);
			break;	
		}
		//�����߳�
		Thread t = new Thread(s);
		t.start();
	}
}

//���˵�̹��
class EnemyTank extends Tank implements Runnable {
	//����
	boolean isLive = true;
	
	//����һ��������ŵ��˵��ӵ�
	Vector<Shot> ss = new Vector<Shot>();
	
	//��������ӵ���Ӧ���ڸոմ���̹�˺͵��˵��ӵ�������
	
	
	public EnemyTank(int x, int y) {
		//���ú�����ʼ��������ȱ�٣�
		super(x,y);
	}

	//��дrun����
	public void run() {
		// TODO �Զ����ɵķ������
		while(true) {
			//�����ƶ�
			switch(this.direct) {
			case 0:
				//˵�����������ƶ�
				for(int i = 0;i < 30;i++) {
					//��֤̹�˲����߽�
					if(y > 0) {
						y -= speed;	
					}
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
				}
				break;
			case 1:
				//����
				for(int i = 0;i < 30;i++) {
					if(x < 400) {
						x += speed;	
					}
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
				}
				break;
			case 2:
				//����
				for(int i = 0;i < 30;i++) {
					if(y < 300) {
						y += speed;	
					}
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
				}
				break;
			case 3:
				//����
				for(int i = 0;i < 30;i++) {
					if(x > 0) {	
						x -= speed;
					}
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
				}
				break;
			}
			
		
			//�ж�̹���Ƿ��Դ��
			if(isLive) {
				if(ss.size() < 5) {
					System.out.println("��"+ss.size()+"���ӵ�");
					Shot s = null;
					//����ӵ�
					switch(direct) {
					case 0:
						s = new Shot(x+10, y, 0);
						ss.add(s);
						break;
					case 1:
						s = new Shot(x+30, y+10, 1);
						ss.add(s);
						break;
					case 2:
						s = new Shot(x+10, y+30, 2);
						ss.add(s);
						break;
					case 3:
						s = new Shot(x, y+10, 3);
						ss.add(s);
						break;	
					}
					
					//�����ӵ��߳�
					Thread t = new Thread(s);
					t.start();
				}
			}
						
			//��̹����������µķ���
			this.direct = (int)(Math.random()*4);
			
			//�жϵ����Ƿ�����
			if(this.isLive == false) {
				//̹���������˳��߳�
				break;
			}
		}
	}
}
