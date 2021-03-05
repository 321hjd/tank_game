/*
 * �ҵļ��±�
 */
package com.study;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class NotePad extends JFrame implements ActionListener {

	//������Ҫ�����
	JTextArea jta = null;
	//�˵���
	JMenuBar jmb = null;
	//����һ��JMenu
	JMenu jm1 = null;
	//����JMenuItem
	JMenuItem jmi1 = null;
	JMenuItem jmi2 = null;
	
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		NotePad np = new NotePad();
	}
	
	//���캯��
	public NotePad() {
		//����jta
		jta = new JTextArea();
		jmb = new JMenuBar();
		jm1 = new JMenu("�ļ�");
		//�������Ƿ�
		jm1.setMnemonic('F');
		
		jmi1 = new JMenuItem("��");
		//ע�����
		jmi1.addActionListener(this);
		jmi1.setActionCommand("open");
		
		jmi2 = new JMenuItem("����");
		//�Ա���ע�����
		jmi2.addActionListener(this);
		jmi2.setActionCommand("save");
		
		//����
		this.setJMenuBar(jmb);
		//��jm1���뵽jmb
		jmb.add(jm1);
		//��item���뵽menu
		jm1.add(jmi1);
		jm1.add(jmi2);
		
		//���뵽JFrame
		this.add(jta);
		
		//���ô�������
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 300);
		//��ʾ
		this.setVisible(true);
	}

	//��������
	public void actionPerformed(ActionEvent arg0) {
		// TODO �Զ����ɵķ������
		//�ж����ĸ��˵���ѡ��
		if(arg0.getActionCommand().equals("open")) {
			
			//�ļ�ѡ�����JFileChooser
			JFileChooser jfc1 = new JFileChooser();
			//��������
			jfc1.setDialogTitle("��ѡ���ļ�...");
			//ʹ��Ĭ�Ϸ�ʽ
			jfc1.showOpenDialog(null);
			//��ʾ
			jfc1.setVisible(true);
			
			//�õ��û�ѡ�е��ļ�����·��
			String filename = jfc1.getSelectedFile().getAbsolutePath();
			
			//FileReader��ȡ�ı��ļ�
			FileReader fr = null;
			BufferedReader br = null;
			try {
				//��ȡ
				fr = new FileReader(filename);
				br = new BufferedReader(fr);
				
				//���ļ��ж�ȡ��Ϣ����ʾ��jta
				String s = "";
				String allCon = "";
				while((s = br.readLine()) != null) {
					allCon += s + "\r\n";
				}
				
				//���õ�jta
				jta.setText(allCon);
				
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					br.close();
					fr.close();
				} catch (IOException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
			}
		} else if(arg0.getActionCommand().equals("save")) {
			//���ֱ���Ի���
			JFileChooser jfc = new JFileChooser();
			jfc.setDialogTitle("���Ϊ...");
			//��Ĭ�Ϸ�ʽ��ʾ
			jfc.showSaveDialog(null);
			jfc.setVisible(true);
			
			//�õ��û�ϣ�����ļ����浽�δ����ļ�ȫ·��
			String file = jfc.getSelectedFile().getAbsolutePath();
			
			//׼��д�뵽ָ���ļ�
			FileWriter fw = null;
			BufferedWriter bw = null;
			try {
				fw = new FileWriter(file);
				bw = new BufferedWriter(fw);
				
				//����һ��һ��д���ַ����飩�������ļ�����ᵼ�¿���
				bw.write(this.jta.getText());
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					bw.close();
					fw.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}










