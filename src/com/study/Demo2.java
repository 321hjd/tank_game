/**
 * �¼�������ƣ�ί���¼�ģ�ͣ�
 * 1.һ����Ҫʵ�ּ����Ĳ���
 * a.ʵ����Ӧ�Ľӿڡ�KeyListener��Mouse Listener��ActionListener��WindowListener��
 * b.�ѽӿڵĴ�����������Ҫ��д��Override��
 * c.��<�¼�Դ>��ע�����
 * d.�¼����������¼�����ActionEvent��
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
	//����һ��panel
	JPanel mp = null;
	JButton jb1 = null;
	JButton jb2 = null;
	
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		Demo2 demo2 = new Demo2();
	}
	
	//���캯��
	public Demo2() {
		mp = new JPanel();
		jb1 = new JButton("��ɫ");
		jb2 = new JButton("��ɫ");

		this.add(jb1, BorderLayout.NORTH);
		mp.setBackground(Color.black);
		this.add(mp);
		this.add(jb2, BorderLayout.SOUTH);
		
		Cat myCat1 = new Cat();
		
		//ע�����
		jb1.addActionListener(this);	//��ťjb1���¼�Դ����Demo2�ࣨ�¼������ߣ�����
		jb1.addActionListener(myCat1);	//��ťjb2��Cat�����
		//ָ��action����
		jb1.setActionCommand("��ɫ");
		jb2.setActionCommand("��ɫ");
		jb2.addActionListener(this);
		
		this.setSize(400, 300);
		this.setTitle("�¼�������ʾ");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	//���¼�����ķ�����eΪ�¼�����
	public void actionPerformed(ActionEvent e) {
		//�ж����ĸ���ť�����
		if(e.getActionCommand().equals("��ɫ")) {
			System.out.println("������ɫ��ť");
			mp.setBackground(Color.black);
		}else if(e.getActionCommand().equals("��ɫ")) {
			System.out.println("������ɫ��ť");
			mp.setBackground(Color.red);
		}else {
			System.out.println("��֪��");
		}
	}
}

//�κ�һ���ֻ࣬Ҫʵ������Ӧ�Ľӿڣ��Ϳ��Լ���ĳ���¼�Դ
class Cat implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������
		if(e.getActionCommand().equals("��ɫ")) {
			System.out.println("èèҲ֪��������ɫ��ť");
		}else if(e.getActionCommand().equals("��ɫ")) {
			System.out.println("èèҲ֪��������ɫ��ť");
		}
	}
}

//�ҵ����
//class MyPanel extends JPanel {
//	public void paint(Graphics g) {
//		super.paint(g);
//		
//		g.fillRect(0, 0, 200, 150);
//	}
//}





















