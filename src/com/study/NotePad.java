/*
 * 我的记事本
 */
package com.study;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class NotePad extends JFrame implements ActionListener {

	//定义需要的组件
	JTextArea jta = null;
	//菜单条
	JMenuBar jmb = null;
	//定义一个JMenu
	JMenu jm1 = null;
	//定义JMenuItem
	JMenuItem jmi1 = null;
	JMenuItem jmi2 = null;
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		NotePad np = new NotePad();
	}
	
	//构造函数
	public NotePad() {
		//创建jta
		jta = new JTextArea();
		jmb = new JMenuBar();
		jm1 = new JMenu("文件");
		//设置助记符
		jm1.setMnemonic('F');
		
		jmi1 = new JMenuItem("打开");
		//注册监听
		jmi1.addActionListener(this);
		jmi1.setActionCommand("open");
		
		jmi2 = new JMenuItem("保存");
		//对保存注册监听
		jmi2.addActionListener(this);
		jmi2.setActionCommand("save");
		
		//加入
		this.setJMenuBar(jmb);
		//把jm1放入到jmb
		jmb.add(jm1);
		//把item放入到menu
		jm1.add(jmi1);
		jm1.add(jmi2);
		
		//放入到JFrame
		this.add(jta);
		
		//设置窗口属性
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(400, 300);
		//显示
		this.setVisible(true);
	}

	//监听处理
	public void actionPerformed(ActionEvent arg0) {
		// TODO 自动生成的方法存根
		//判断是哪个菜单被选中
		if(arg0.getActionCommand().equals("open")) {
			
			//文件选择组件JFileChooser
			JFileChooser jfc1 = new JFileChooser();
			//设置名字
			jfc1.setDialogTitle("请选择文件...");
			//使用默认方式
			jfc1.showOpenDialog(null);
			//显示
			jfc1.setVisible(true);
			
			//得到用户选中的文件绝对路径
			String filename = jfc1.getSelectedFile().getAbsolutePath();
			
			//FileReader读取文本文件
			FileReader fr = null;
			BufferedReader br = null;
			try {
				//读取
				fr = new FileReader(filename);
				br = new BufferedReader(fr);
				
				//从文件中读取信息并显示到jta
				String s = "";
				String allCon = "";
				while((s = br.readLine()) != null) {
					allCon += s + "\r\n";
				}
				
				//放置到jta
				jta.setText(allCon);
				
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					br.close();
					fr.close();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		} else if(arg0.getActionCommand().equals("save")) {
			//出现保存对话框
			JFileChooser jfc = new JFileChooser();
			jfc.setDialogTitle("另存为...");
			//按默认方式显示
			jfc.showSaveDialog(null);
			jfc.setVisible(true);
			
			//得到用户希望把文件保存到何处，文件全路径
			String file = jfc.getSelectedFile().getAbsolutePath();
			
			//准备写入到指定文件
			FileWriter fw = null;
			BufferedWriter bw = null;
			try {
				fw = new FileWriter(file);
				bw = new BufferedWriter(fw);
				
				//可以一点一点写（字符数组），否则文件过大会导致卡顿
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










