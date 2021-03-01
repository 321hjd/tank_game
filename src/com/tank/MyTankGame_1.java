/**
 * @author hjd
 * 功能：坦克游戏3.0版本
 * 1.画出坦克
 * 2.我的坦克可以上下左右移动
 * 3.画出三辆敌人的坦克（注意颜色）
 * 4.我的坦克可以发射子弹
 */
package com.tank;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;

public class MyTankGame_1 extends JFrame {
	MyPanel mp = null;
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		MyTankGame_1 game = new MyTankGame_1();
	}
	
	//构造函数
	public MyTankGame_1() {
		//创建组件
		mp = new MyPanel();
		
		//启动mp线程
		Thread t = new Thread(mp);
		t.start();
		
		//添加组件
		this.add(mp);
		
		//注册监听
		this.addKeyListener(mp);
		
		//设置窗口属性
		this.setSize(400, 300);
		this.setTitle("坦克大战");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//显示
		this.setVisible(true);
	}
}

//我的面板
class MyPanel extends JPanel implements KeyListener, Runnable {
	//定义一个我的坦克
	Hero hero = null;
	//定义敌人的坦克组（因为不同坦克的运动、子弹均不同，因此是多线程的，需要使用线程安全的Vector
	Vector<EnemyTank> ets = new Vector<EnemyTank>();
	
	//敌人数量
	int enSize = 3;		
	
	//构造函数
	public MyPanel() {
		//坦克的初始位置
		hero = new Hero(10, 10);	
		//初始化敌人的坦克
		for(int i = 0;i < enSize;i++) {
			//创建一个敌人对象
			EnemyTank et = new EnemyTank((i+1)*50, 0);
			//设置颜色
			et.setColor(0);
			et.setDirect(2);
			//将敌人对象加入敌人坦克组
			ets.add(et);
		}
	}
	
	//重写paint函数
	public void paint(Graphics g) {
		//调用函数初始化（不可缺少）
		super.paint(g);
		//设置坦克活动区域
		g.fillRect(0, 0, 400, 300);	
		//画我的坦克
		this.drawTank(hero.getX(), hero.getY(), g, this.hero.direct, 0);
		//画出敌人的坦克
		for(int i = 0;i < ets.size();i++) {
			this.drawTank(ets.get(i).getX(), ets.get(i).getY(), g, ets.get(i).getDirect(), 1);
		}
		//画出子弹
		if(hero.s != null && hero.s.isLive == true) {
			g.draw3DRect(hero.s.x, hero.s.y, 1, 1, false);
		}
	}
	
	//画出坦克的函数
	public void drawTank(int x, int y, Graphics g, int direct, int type) {
		//判断坦克类型
		switch(type) {
		case 0:
			g.setColor(Color.YELLOW);
			break;
		case 1:
			g.setColor(Color.CYAN);
			break;
		}
		//判断坦克方向
		switch(direct) {
		//向上
		case 0:					
			//画出我的坦克
			//1.画出左边的矩形
			g.fill3DRect(x, y, 5, 30, false);
			//2.画出右边的矩形
			g.fill3DRect(x+15, y, 5, 30, false);
			//3.画出中间的矩形
			g.fill3DRect(x+5, y+5, 10, 20, false);
			//4.画出圆形
			g.fillOval(x+5, y+10, 10, 10);
			//5.画出线
			g.drawLine(x+10, y+15, x+10, y);
			break;
		case 1:
			//炮筒向右（此处较为粗糙，仍以左上角为参照点）
			//1.画出左边的矩形
			g.fill3DRect(x, y, 30, 5, false);
			//2.画出右边的矩形
			g.fill3DRect(x, y+15, 30, 5, false);
			//3.画出中间的矩形
			g.fill3DRect(x+5, y+5, 20, 10, false);
			//4.画出圆形
			g.fillOval(x+10, y+5, 10, 10);
			//5.画出线
			g.drawLine(x+15, y+10, x+30, y+10);
			break;
		case 2:
			//炮筒向下（此处较为粗糙，仍以左上角为参照点）
			//1.画出左边的矩形
			g.fill3DRect(x, y, 5, 30, false);
			//2.画出右边的矩形
			g.fill3DRect(x+15, y, 5, 30, false);
			//3.画出中间的矩形
			g.fill3DRect(x+5, y+5, 10, 20, false);
			//4.画出圆形
			g.fillOval(x+5, y+10, 10, 10);
			//5.画出线
			g.drawLine(x+10, y+15, x+10, y+30);
			break;
		case 3:
			//炮筒向左（此处较为粗糙，仍以左上角为参照点）
			//1.画出左边的矩形
			g.fill3DRect(x, y, 30, 5, false);
			//2.画出右边的矩形
			g.fill3DRect(x, y+15, 30, 5, false);
			//3.画出中间的矩形
			g.fill3DRect(x+5, y+5, 20, 10, false);
			//4.画出圆形
			g.fillOval(x+10, y+5, 10, 10);
			//5.画出线
			g.drawLine(x+15, y+10, x, y+10);
			break;
		}	
	}

	//1.按键按下——a：左 s：下 w：上 d：右
	public void keyPressed(KeyEvent arg0) {
		//操控坦克移动
		switch(arg0.getKeyCode()) {
		case KeyEvent.VK_W:
			//设置我的坦克的方向：上
			this.hero.setDirect(0);
			this.hero.moveUp();
			break;
		case KeyEvent.VK_A:
			//左
			this.hero.setDirect(3);
			this.hero.moveLeft();
			break;
		case KeyEvent.VK_S:
			//下
			this.hero.setDirect(2);
			this.hero.moveDown();
			break;
		case KeyEvent.VK_D:
			//右
			this.hero.setDirect(1);
			this.hero.moveRight();
			break;
		default:
			break;
		}
		
		//判断玩家是否按下“j”键——射击
		if(arg0.getKeyCode() == KeyEvent.VK_J) {
			this.hero.shotEnemy();
		}
		
		//必须重绘Panel
		this.repaint();
	}

	//2.按键松开
	public void keyReleased(KeyEvent arg0) {
		// TODO 自动生成的方法存根
		
	}

	//3.按键输出字符
	public void keyTyped(KeyEvent arg0) {
		// TODO 自动生成的方法存根
		
	}

	//另一个线程
	public void run() {
		//每隔100毫秒去重绘
		while(true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			//重绘
			this.repaint();
		}
	}
}











