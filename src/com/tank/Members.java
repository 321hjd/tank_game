/*
 * 功能：坦克游戏成员类
 */
package com.tank;

//坦克类
class Tank{
	//坦克横纵坐标
	int x = 0;
	int y = 0;
	
	//坦克方向——0：上 1：右 2：下 3：左
	int direct = 0;
	
	//坦克速度
	int speed = 5;

	//坦克颜色
	int color = 0;



	//构造函数
	public Tank(int x, int y) {
		this.x = x;
		this.y = y;	
	}
	
	//坦克移动
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
	
	//get和set方法
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

//我的坦克
class Hero extends Tank {
	public Hero(int x, int y) {
		//调用函数初始化（不可缺少）
		super(x,y);
	}
}

//敌人的坦克
class EnemyTank extends Tank {
	public EnemyTank(int x, int y) {
		//调用函数初始化（不可缺少）
		super(x,y);
	}
}
