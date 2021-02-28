/**
 * java绘图原理
 */
package com.study;

import java.awt.*;
import javax.swing.*;
/**
 * @author 18482
 *
 */
public class Demo1 extends JFrame {
	//定义
	MyPanel mp = null;
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Demo1 demo1 = new Demo1();
	}
	
	//构造函数
	public Demo1() {
		mp = new MyPanel();
		
		//将JPanel加入到JFrame
		this.add(mp);
		
		//设置窗口属性
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//显示
		this.setVisible(true);
	}
}

//定义一个MyPanel(自己的面板，用于绘图和实现绘图的区域)
class MyPanel extends JPanel {
	//覆盖JPanel的paint方法（当组件第一次显示时，会自动调用paint方法）
	//Graphics是绘图的重要类，可以理解为画笔
	public void paint(Graphics g) {
		//调用父类函数初始化（不可缺少）
		super.paint(g);
//		//画一个圆
//		g.drawOval(10, 10, 30, 30);	
//		//画直线
//		g.drawLine(10, 10, 20, 20);
//		//画矩形边框
//		g.drawRect(10, 10, 40, 60);
//		//画填充矩形
//		g.setColor(new Color(30, 40, 200));		//填充色（默认黑色）
//		g.fillRect(30, 30, 40,  60);
//		//在面板上画图片
//		Image im = Toolkit.getDefaultToolkit().getImage("images/ye.jpg");
//		g.drawImage(im, 90, 90, 200, 150, this);
//		//画出字
//		g.setFont(new Font("华文彩云", Font.BOLD, 30));	//修改字体（加粗）
//		g.setColor(Color.red);							//修改字体颜色
//		g.drawString("你好世界", 100, 100);
		//画出弧形
		g.drawArc(100, 200, 120, 300, 50, 100);
	}
}


















