/*
 * 功能：坦克游戏成员类
 */
package com.tank;

//子弹类
class Shot implements Runnable {
	//属性
	int x;
	int y;
	int direct;
	int speed = 4;
	boolean isLive = true;
	
	public Shot(int x, int y, int direct) {
		this.x = x;
		this.y = y;
		this.direct = direct;
	}
	
	//线程
	public void run() {
		while(true) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			switch(direct) {
			case 0:
				//上
				y -= speed;
				break;
			case 1:
				//右
				x += speed;
				break;
			case 2:
				//下
				y += speed;
				break;
			case 3:
				//左
				x -= speed;
				break;
			}
			System.out.println("子弹坐标 x="+x+" y="+y);
			
			//判断该子弹是否碰到边缘
			if(x<0 || x>400 || y < 0 || y > 300) {
				this.isLive = false;
				break;
			}	
		}	
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


//坦克类
class Tank{
	//坦克横纵坐标
	int x = 0;
	int y = 0;
	
	//坦克方向——0：上 1：右 2：下 3：左
	int direct = 0;
	
	//坦克速度
	int speed = 2;

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
	//子弹
	Shot s = null;
	
	public Hero(int x, int y) {
		//调用函数初始化（不可缺少）
		super(x,y);
	}
	
	//开火
	public void shotEnemy() {
		switch(this.direct) {
		case 0:
			s = new Shot(x+10, y, 0);
			break;
		case 1:
			s = new Shot(x+30, y+10, 1);
			break;
		case 2:
			s = new Shot(x+10, y+30, 2);
			break;
		case 3:
			s = new Shot(x, y+10, 3);
			break;	
		}
		//启动线程
		Thread t = new Thread(s);
		t.start();
	}
}

//敌人的坦克
class EnemyTank extends Tank {
	public EnemyTank(int x, int y) {
		//调用函数初始化（不可缺少）
		super(x,y);
	}
}
