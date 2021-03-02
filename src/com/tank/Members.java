/*
 * 功能：坦克游戏成员类
 */
package com.tank;

import java.util.Vector;

//炸弹类
class Bomb {
	//定义炸弹的坐标
	int x, y;
	//炸弹生命
	int life = 9;
	//炸弹是否存活
	boolean isLive = true;
	
	public Bomb(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	//减少生命值
	public void lifeDown() {
		if(life > 0) {
			life--;
		}else {
			this.isLive = false;
		}
	}
}

//子弹类
class Shot implements Runnable {
	//属性
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
	int speed = 1;

	//坦克颜色
	int color = 0;



	//构造函数
	public Tank(int x, int y) {
		this.x = x;
		this.y = y;	
	}
	
	//坦克移动
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
	Vector<Shot> ss = new Vector<Shot>();
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
		//启动线程
		Thread t = new Thread(s);
		t.start();
	}
}

//敌人的坦克
class EnemyTank extends Tank implements Runnable {
	//属性
	boolean isLive = true;
	
	//定义一个向量存放敌人的子弹
	Vector<Shot> ss = new Vector<Shot>();
	
	//敌人添加子弹，应当在刚刚创建坦克和敌人的子弹死亡后
	
	
	public EnemyTank(int x, int y) {
		//调用函数初始化（不可缺少）
		super(x,y);
	}

	//重写run函数
	public void run() {
		// TODO 自动生成的方法存根
		while(true) {
			//惯性移动
			switch(this.direct) {
			case 0:
				//说明正在向上移动
				for(int i = 0;i < 30;i++) {
					//保证坦克不出边界
					if(y > 0) {
						y -= speed;	
					}
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}
				break;
			case 1:
				//向右
				for(int i = 0;i < 30;i++) {
					if(x < 400) {
						x += speed;	
					}
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}
				break;
			case 2:
				//向下
				for(int i = 0;i < 30;i++) {
					if(y < 300) {
						y += speed;	
					}
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}
				break;
			case 3:
				//向左
				for(int i = 0;i < 30;i++) {
					if(x > 0) {	
						x -= speed;
					}
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}
				break;
			}
			
		
			//判断坦克是否仍存活
			if(isLive) {
				if(ss.size() < 5) {
					System.out.println("第"+ss.size()+"颗子弹");
					Shot s = null;
					//添加子弹
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
					
					//启动子弹线程
					Thread t = new Thread(s);
					t.start();
				}
			}
						
			//让坦克随机产生新的方向
			this.direct = (int)(Math.random()*4);
			
			//判断敌人是否死亡
			if(this.isLive == false) {
				//坦克死亡后退出线程
				break;
			}
		}
	}
}
