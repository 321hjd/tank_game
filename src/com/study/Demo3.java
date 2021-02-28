/**
 * �¼����������ʾ
 * 1.ͨ�����¼�����С���λ��
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
		// TODO �Զ����ɵķ������
		Demo3 demo3 = new Demo3();
	}
	
	//���캯��
	public Demo3() {
		mp = new MyPanel_3();
		
		//mp���뵽JFrame
		this.add(mp);
		
		//ע�����
		this.addKeyListener(mp);
		
		this.setSize(400, 300);
		this.setTitle("�¼�������ʾ");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}

//�ҵ����
class MyPanel_3 extends JPanel implements KeyListener {
	int x = 10;
	int y = 10;
	
	//��дpaint����
	public void paint(Graphics g) {
		super.paint(g);
		
		g.fillOval(x, y, 10, 10);
	}

	//�¼�����������������ѹ
	public void keyPressed(KeyEvent e) {
		// TODO �Զ����ɵķ������
		//System.out.println((char)e.getKeyCode()+"��������");
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
		//����repaint�����ػ����
		this.repaint();

	}

	//�¼����������������ͷ�
	public void keyReleased(KeyEvent e) {
		// TODO �Զ����ɵķ������
		System.out.println((char)e.getKeyCode()+"�����ͷ�");
	}

	//�¼�����������������һ��ֵ�����
	public void keyTyped(KeyEvent e) {
		// TODO �Զ����ɵķ������
		
	}
}