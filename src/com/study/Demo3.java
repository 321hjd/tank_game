/**
 * 事件处理机制演示
 * 1.通过上下键控制小球的位置
 */
package com.study;

import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 * @author 18482
 *
 */
public class Demo3 extends JFrame {
	MyPanel_3 mp = null;

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Demo3 demo3 = new Demo3();
	}
	
	//构造函数
	public Demo3() {
		mp = new MyPanel_3();
		
		//mp加入到JFrame
		this.add(mp);
		
		//注册监听
		this.addKeyListener(mp);
		
		this.setSize(400, 300);
		this.setTitle("事件监听演示");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}

//我的面板
class MyPanel_3 extends JPanel implements KeyListener {
	int x = 10;
	int y = 10;
	
	//重写paint方法
	public void paint(Graphics g) {
		super.paint(g);
		
		g.fillOval(x, y, 10, 10);
	}

	//事件监听处理——按键下压
	public void keyPressed(KeyEvent e) {
		// TODO 自动生成的方法存根
		//System.out.println((char)e.getKeyCode()+"键被按下");
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			y++;
		}else if(e.getKeyCode() == KeyEvent.VK_UP) {
			y--;
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			x++;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			x--;
		}
		//调用repaint函数重绘界面
		this.repaint();

	}

	//事件监听处理——按键释放
	public void keyReleased(KeyEvent e) {
		// TODO 自动生成的方法存根
		System.out.println((char)e.getKeyCode()+"键被释放");
	}

	//事件监听处理——按键的一个值被输出
	public void keyTyped(KeyEvent e) {
		// TODO 自动生成的方法存根
		
	}
}