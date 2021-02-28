/**
 * 功能：坦克游戏1.0版本
 * 1.画出坦克
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
		// TODO 自动生成的方法存根
		MyTankGame_1 game = new MyTankGame_1();
	}
	
	//构造函数
	public MyTankGame_1() {
		mp = new MyPanel();
		
		this.add(mp);
		this.setSize(400, 300);
		this.setTitle("坦克大战");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}

//我的面板
class MyPanel extends JPanel {
	//定义一个我的坦克
	Hero hero = null;
	
	//构造函数
	public MyPanel() {
		//坦克的初始位置
		hero = new Hero(10, 10);	
	}
	
	//重写paint函数
	public void paint(Graphics g) {
		//调用函数初始化（不可缺少）
		super.paint(g);
		//设置坦克活动区域
		g.fillRect(0, 0, 400, 300);	
		//画坦克
		this.drawTank(hero.getX(), hero.getY(), g, 0, 0);
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
			//设置颜色	
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
		}	
	}
}

//坦克类
class Tank{
	//坦克横坐标
	int x = 0;
	//坦克纵坐标
	int y = 0;
	
	//构造函数
	public Tank(int x, int y) {
		this.x = x;
		this.y = y;	
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
}

//我的坦克
class Hero extends Tank {
	public Hero(int x, int y) {
		//调用函数初始化（不可缺少）
		super(x,y);
	}
}










