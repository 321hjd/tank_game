/*
 * ���ܣ�̹����Ϸ��Ա��
 */
package com.tank;

//̹����
class Tank{
	//̹�˺�������
	int x = 0;
	int y = 0;
	
	//̹�˷��򡪡�0���� 1���� 2���� 3����
	int direct = 0;
	
	//̹���ٶ�
	int speed = 5;

	//̹����ɫ
	int color = 0;



	//���캯��
	public Tank(int x, int y) {
		this.x = x;
		this.y = y;	
	}
	
	//̹���ƶ�
	public void moveUp() {
		y -= speed;
	}
	public void moveRight() {
		x += speed;
	}
	public void moveLeft() {
		x -= speed;
	}
	public void moveDown() {
		y += speed;
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
	public Hero(int x, int y) {
		//���ú�����ʼ��������ȱ�٣�
		super(x,y);
	}
}

//���˵�̹��
class EnemyTank extends Tank {
	public EnemyTank(int x, int y) {
		//���ú�����ʼ��������ȱ�٣�
		super(x,y);
	}
}
