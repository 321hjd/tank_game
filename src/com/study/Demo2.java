/**
 * 事件处理机制
 */
package com.study;

import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * @author 18482
 *
 */
public class Demo2 extends JFrame implements ActionListener {
	//定义一个panel
	JPanel mp = null;
	JButton jb1 = null;
	JButton jb2 = null;
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Demo2 demo2 = new Demo2();
	}
	
	//构造函数
	public Demo2() {
		mp = new JPanel();
		jb1 = new JButton("黑色");
		jb2 = new JButton("红色");

		this.add(jb1, BorderLayout.NORTH);
		mp.setBackground(Color.black);
		this.add(mp);
		this.add(jb2, BorderLayout.SOUTH);
		
		Cat myCat1 = new Cat();
		
		//注册监听
		jb1.addActionListener(this);	//按钮jb1（事件源）被Demo2类（事件监听者）监听
		jb1.addActionListener(myCat1);	//按钮jb2被Cat类监听
		//指定action命令
		jb1.setActionCommand("黑色");
		jb2.setActionCommand("红色");
		jb2.addActionListener(this);
		
		this.setSize(400, 300);
		this.setTitle("事件监听演示");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	//对事件处理的方法（e为事件对象）
	public void actionPerformed(ActionEvent e) {
		//判断是哪个按钮被点击
		if(e.getActionCommand().equals("黑色")) {
			System.out.println("你点击黑色按钮");
			mp.setBackground(Color.black);
		}else if(e.getActionCommand().equals("红色")) {
			System.out.println("你点击红色按钮");
			mp.setBackground(Color.red);
		}else {
			System.out.println("不知道");
		}
	}
}

class Cat implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if(e.getActionCommand().equals("黑色")) {
			System.out.println("猫猫也知道你点击黑色按钮");
		}else if(e.getActionCommand().equals("红色")) {
			System.out.println("猫猫也知道你点击红色按钮");
		}
	}
}

//我的面板
//class MyPanel extends JPanel {
//	public void paint(Graphics g) {
//		super.paint(g);
//		
//		g.fillRect(0, 0, 200, 150);
//	}
//}





















