/**
 * 事件监听机制——监听多个事件（实现多个接口）
 */
package com.study;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author 18482
 *
 */
public class Demo4 extends JFrame {

	MyPanel_4 mp = null;
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Demo4 demo = new Demo4();
	}
	
	//构造函数
	public Demo4() {
		mp = new MyPanel_4();
		
		this.add(mp);
		
		//注册监听
		this.addMouseListener(mp);
		this.addKeyListener(mp);
		this.addMouseMotionListener(mp);
		this.addWindowListener(mp);
		
		this.setSize(400, 300);
		this.setTitle("事件监听演示");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
}
//我的面板
//1.让MyPanel_4知道鼠标按下的消息和点击的位置(x,y)
//2.让MyPanel_4知道哪个键按下
//3.让MyPanel_4知道鼠标移动、拖拽
//4.让MyPanel_4知道窗口变化（关闭、最小最大化等）
class MyPanel_4 extends JPanel implements MouseListener, KeyListener, MouseMotionListener, WindowListener {
	//重写paint方法
	public void paint(Graphics g) {
		super.paint(g);
	}

	//鼠标监听
	//1.鼠标点击
	public void mouseClicked(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		System.out.println("鼠标点击了 x="+arg0.getX()+" y="+arg0.getY());
	}

	//2.鼠标移动到MyPanel_4
	public void mouseEntered(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		System.out.println("鼠标来");
	}

	//3.鼠标移开
	public void mouseExited(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		System.out.println("鼠标走");
	}

	//4.鼠标按下
	public void mousePressed(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		
	}

	//5.鼠标松开
	public void mouseReleased(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		
	}

	//按键监听
	//1.按键按下
	public void keyPressed(KeyEvent arg0) {
		// TODO 自动生成的方法存根
		System.out.println(arg0.getKeyChar()+"键按下");
	}

	//2.按键松开
	public void keyReleased(KeyEvent arg0) {
		// TODO 自动生成的方法存根
		System.out.println(arg0.getKeyChar()+"键松开");
	}

	//3.键输入具体字符
	public void keyTyped(KeyEvent arg0) {
		// TODO 自动生成的方法存根
		
	}

	//鼠标移动、拖拽监控
	//1.鼠标拖拽
	public void mouseDragged(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		
	}

	//2.鼠标移动
	public void mouseMoved(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		System.out.println("鼠标移动");
	}

	//窗口监听
	//1.窗口激活
	public void windowActivated(WindowEvent arg0) {
		// TODO 自动生成的方法存根
		System.out.println("windowActivated");

	}

	//2.窗口关闭
	public void windowClosed(WindowEvent arg0) {
		// TODO 自动生成的方法存根
		System.out.println("windowClosed");

	}

	//3.窗口正在关闭
	public void windowClosing(WindowEvent arg0) {
		// TODO 自动生成的方法存根
		System.out.println("windowClosing");

	}

	//4.窗口最小化
	public void windowDeactivated(WindowEvent arg0) {
		// TODO 自动生成的方法存根
		System.out.println("windowDeactivated");

	}

	//5.窗口取消最小化
	public void windowDeiconified(WindowEvent arg0) {
		// TODO 自动生成的方法存根
		System.out.println("windowDeiconified");

	}

	//6.窗口最小化
	public void windowIconified(WindowEvent arg0) {
		// TODO 自动生成的方法存根
		System.out.println("windowIconified");

	}

	//7.窗口打开
	public void windowOpened(WindowEvent arg0) {
		// TODO 自动生成的方法存根
		System.out.println("windowOpened");

	}	
}









