/**
 * �¼��������ơ�����������¼���ʵ�ֶ���ӿڣ�
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
		// TODO �Զ����ɵķ������
		Demo4 demo = new Demo4();
	}
	
	//���캯��
	public Demo4() {
		mp = new MyPanel_4();
		
		this.add(mp);
		
		//ע�����
		this.addMouseListener(mp);
		this.addKeyListener(mp);
		this.addMouseMotionListener(mp);
		this.addWindowListener(mp);
		
		this.setSize(400, 300);
		this.setTitle("�¼�������ʾ");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
}
//�ҵ����
//1.��MyPanel_4֪����갴�µ���Ϣ�͵����λ��(x,y)
//2.��MyPanel_4֪���ĸ�������
//3.��MyPanel_4֪������ƶ�����ק
//4.��MyPanel_4֪�����ڱ仯���رա���С��󻯵ȣ�
class MyPanel_4 extends JPanel implements MouseListener, KeyListener, MouseMotionListener, WindowListener {
	//��дpaint����
	public void paint(Graphics g) {
		super.paint(g);
	}

	//������
	//1.�����
	public void mouseClicked(MouseEvent arg0) {
		// TODO �Զ����ɵķ������
		System.out.println("������� x="+arg0.getX()+" y="+arg0.getY());
	}

	//2.����ƶ���MyPanel_4
	public void mouseEntered(MouseEvent arg0) {
		// TODO �Զ����ɵķ������
		System.out.println("�����");
	}

	//3.����ƿ�
	public void mouseExited(MouseEvent arg0) {
		// TODO �Զ����ɵķ������
		System.out.println("�����");
	}

	//4.��갴��
	public void mousePressed(MouseEvent arg0) {
		// TODO �Զ����ɵķ������
		
	}

	//5.����ɿ�
	public void mouseReleased(MouseEvent arg0) {
		// TODO �Զ����ɵķ������
		
	}

	//��������
	//1.��������
	public void keyPressed(KeyEvent arg0) {
		// TODO �Զ����ɵķ������
		System.out.println(arg0.getKeyChar()+"������");
	}

	//2.�����ɿ�
	public void keyReleased(KeyEvent arg0) {
		// TODO �Զ����ɵķ������
		System.out.println(arg0.getKeyChar()+"���ɿ�");
	}

	//3.����������ַ�
	public void keyTyped(KeyEvent arg0) {
		// TODO �Զ����ɵķ������
		
	}

	//����ƶ�����ק���
	//1.�����ק
	public void mouseDragged(MouseEvent arg0) {
		// TODO �Զ����ɵķ������
		
	}

	//2.����ƶ�
	public void mouseMoved(MouseEvent arg0) {
		// TODO �Զ����ɵķ������
		System.out.println("����ƶ�");
	}

	//���ڼ���
	//1.���ڼ���
	public void windowActivated(WindowEvent arg0) {
		// TODO �Զ����ɵķ������
		System.out.println("windowActivated");

	}

	//2.���ڹر�
	public void windowClosed(WindowEvent arg0) {
		// TODO �Զ����ɵķ������
		System.out.println("windowClosed");

	}

	//3.�������ڹر�
	public void windowClosing(WindowEvent arg0) {
		// TODO �Զ����ɵķ������
		System.out.println("windowClosing");

	}

	//4.������С��
	public void windowDeactivated(WindowEvent arg0) {
		// TODO �Զ����ɵķ������
		System.out.println("windowDeactivated");

	}

	//5.����ȡ����С��
	public void windowDeiconified(WindowEvent arg0) {
		// TODO �Զ����ɵķ������
		System.out.println("windowDeiconified");

	}

	//6.������С��
	public void windowIconified(WindowEvent arg0) {
		// TODO �Զ����ɵķ������
		System.out.println("windowIconified");

	}

	//7.���ڴ�
	public void windowOpened(WindowEvent arg0) {
		// TODO �Զ����ɵķ������
		System.out.println("windowOpened");

	}	
}









