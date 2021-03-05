/**
 * @author hjd
 * 功能：坦克游戏5.0版本
 * 1.画出坦克
 * 2.我的坦克可以上下左右移动
 * 3.画出三辆敌人的坦克（注意颜色）
 * 4.我的坦克可以发射子弹
 * 5.子弹可以连发（最多5颗）
 * 6.我方坦克击中敌方坦克，敌方坦克消失(爆炸效果)
 * 7.敌人坦克可发子弹
 * 8.敌方坦克也可自由随机的上下左右移动
 * 9.控制我方和敌方坦克在固定范围内活动
 * 10.当敌人坦克击中我方坦克，我方坦克消失
 * 11.防止敌人坦克重叠运动
 * 12.分关卡(提示信息闪烁效果)
 * 13.记录玩家成绩
 * 14.能够存盘退出游戏，下次打开游戏时，可以恢复到上次退出的敌方继续玩
 * 15.引入声音
 */
package com.tank;

import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.util.Vector;
import java.io.*;

public class MyTankGame_1 extends JFrame implements ActionListener {
	MyPanel mp = null;
	
	//定义一个开始面板
	MyStartPanel msp = null;
	
	//做一个菜单
	JMenuBar jmb = null;
	//开始游戏
	JMenu jm1 = null;
	JMenuItem jmi1 = null;
	//退出系统
	JMenuItem jmi2 = null;
	//存盘退出
	JMenuItem jmi3 = null;
	//继续游戏
	JMenuItem jmi4 = null;
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		MyTankGame_1 game = new MyTankGame_1();
	}
	
	//构造函数
	public MyTankGame_1() {		
		//创建菜单及菜单选项
		jmb = new JMenuBar();
		jm1 = new JMenu("游戏(G)");
		jmi1 = new JMenuItem("开始新游戏(N)");
		jmi2 = new JMenuItem("退出游戏(E)");
		jmi3 = new JMenuItem("存盘退出游戏(S)");
		jmi4 = new JMenuItem("继续游戏(C)");
		//设置快捷方式
		jm1.setMnemonic('G');
		jmi1.setMnemonic('N');
		jmi2.setMnemonic('E');
		jmi3.setMnemonic('S');
		jmi4.setMnemonic('C');

		
		//注册监听
		jmi1.addActionListener(this);
		jmi1.setActionCommand("newgame");
		jmi2.addActionListener(this);
		jmi2.setActionCommand("exit");
		jmi3.addActionListener(this);
		jmi3.setActionCommand("saveExit");
		jmi4.addActionListener(this);
		jmi4.setActionCommand("conGame");
		
		//将菜单选项加到菜单条
		jm1.add(jmi1);
		jm1.add(jmi2);
		jm1.add(jmi3);
		jm1.add(jmi4);
		jmb.add(jm1);
		
		msp = new MyStartPanel();
		this.add(msp);
		Thread t = new Thread(msp);
		t.start();
		
		//将菜单条加到JFrame
		this.setJMenuBar(jmb);
		
		//设置窗口属性
		this.setSize(600, 500);
		this.setTitle("坦克大战");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//显示
		this.setVisible(true);
	}

	//菜单响应处理
	public void actionPerformed(ActionEvent arg0) {
		// TODO 自动生成的方法存根
		//对用户不同的点击作出处理
		if(arg0.getActionCommand().equals("newgame")) {
			//创建游戏战场面板
			mp = new MyPanel("newGame");
			
			//启动mp线程
			Thread t = new Thread(mp);
			t.start();
			
			//先删除旧的开始panel
			this.remove(msp);
			
			//添加组件
			this.add(mp);
			
			//注册监听
			this.addKeyListener(mp);
			
			//显示，书信JFrame
			this.setVisible(true);
		}else if(arg0.getActionCommand().equals("exit")) {
			//用户点击了“退出系统”的菜单
			//保存击毁敌人数量的数据
			Recorder.keepRecording();
			//退出
			System.exit(0);
		}else if(arg0.getActionCommand().equals("saveExit")) {
			//用户点击存盘退出
			//保存击毁敌人数量和敌人的坐标
			Recorder rd = new Recorder();
			rd.setEts(mp.ets);
			rd.keepRecAndEnemyTank();
			//退出
			System.exit(0);
		}else if(arg0.getActionCommand().equals("conGame")) {
			//用户点击继续游戏
			//创建游戏战场面板
			mp = new MyPanel("continue");
			
			//启动mp线程
			Thread t = new Thread(mp);
			t.start();
			
			//先删除旧的开始panel
			this.remove(msp);
			
			//添加组件
			this.add(mp);
			
			//注册监听
			this.addKeyListener(mp);
			
			//显示，书信JFrame
			this.setVisible(true);
		}
	}
}

//开始界面——起提示作用
class MyStartPanel extends JPanel implements Runnable {
	int times = 0;
	
	public void paint(Graphics g) {
		super.paint(g);
		g.fillRect(0, 0, 400, 300);
		
		//提示信息
		if(times%2 == 0) {
			g.setColor(Color.yellow);
			Font myFont = new Font("华文新魏", Font.BOLD, 30);
			g.setFont(myFont);
			g.drawString("stage: 1", 150, 150);
		}
	}

	public void run() {
		// TODO 自动生成的方法存根
		while(true) {
			//休眠
			try {
				Thread.sleep(400);
			} catch(Exception e) {
				e.printStackTrace();
			}
			
			times++;
			
			//重画
			this.repaint();
			
			//防止int超过范围，定时清零
			if(times == 1000) {
				times = 0;
			}
		}
	}
}


//我的面板
class MyPanel extends JPanel implements KeyListener, Runnable {
	//定义一个我的坦克
	Hero hero = null;
	
	//定义敌人的坦克组（因为不同坦克的运动、子弹均不同，因此是多线程的，需要使用线程安全的Vector
	Vector<EnemyTank> ets = new Vector<EnemyTank>();
	
	//定义恢复结点
	Vector<Node> nodes = new Vector<Node>();
	
	//定义炸弹集合
	Vector<Bomb> bombs = new Vector<Bomb>();
	
	//敌人数量
	int enSize = 3;		
	
	//定义三张图片，三张图片组成动态爆炸效果
	Image img1 = null;
	Image img2 = null;
	Image img3 = null;
	
	
	//构造函数
	public MyPanel(String flag) {
		//恢复记录
		Recorder.getRecording();
		
		//坦克的初始位置
		hero = new Hero(100, 200);	
		
		if(flag.equals("newGame")){
			//初始化敌人的坦克
			for(int i = 0;i < enSize;i++) {
				//创建一个敌人对象
				EnemyTank et = new EnemyTank((i+1)*50, 0);
				//设置颜色
				et.setColor(0);
				et.setDirect(2);
				//将MyPanle的敌人坦克向量交给敌人坦克
				et.setEts(ets);
				
				//启动敌人的坦克
				Thread t = new Thread(et);
				t.start();
				//给敌人的坦克添加子弹
				Shot s = new Shot(et.x+10, et.y+30, et.direct);
				//将子弹加给敌人坦克
				et.ss.add(s);
				Thread t2 = new Thread(s);
				t2.start();
				//将敌人对象加入敌人坦克组
				ets.add(et);
			}
		}else {
			nodes = new Recorder().getNodesAndEnNums();
			
			//初始化敌人的坦克
			for(int i = 0;i < nodes.size();i++) {
				//取一个node
				Node node = nodes.get(i);
				//创建一个敌人对象
				EnemyTank et = new EnemyTank(node.x, node.y);
				//设置颜色
				et.setColor(0);
				et.setDirect(node.direct);
				//将MyPanle的敌人坦克向量交给敌人坦克
				et.setEts(ets);
				
				//启动敌人的坦克
				Thread t = new Thread(et);
				t.start();
				//给敌人的坦克添加子弹
				Shot s = new Shot(et.x+10, et.y+30, et.direct);
				//将子弹加给敌人坦克
				et.ss.add(s);
				Thread t2 = new Thread(s);
				t2.start();
				//将敌人对象加入敌人坦克组
				ets.add(et);
			}
		}
		
		try {
			img1 = ImageIO.read(new File("resources/images/bomb_1.jpg"));
			img2 = ImageIO.read(new File("resources/images/bomb_2.jpg"));
			img3 = ImageIO.read(new File("resources/images/bomb_3.jpg"));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		//启动音频播放
		AePlayWave apw = new AePlayWave("resources/music/111.wav");
		apw.start();
		
		//初始化图片(这种方法第一颗子弹的爆炸效果不明显)
//		img1 = Toolkit.getDefaultToolkit().getImage("resources/images/bomb_1.jpg");
//		img2 = Toolkit.getDefaultToolkit().getImage("resources/images/bomb_2.jpg");
//		img3 = Toolkit.getDefaultToolkit().getImage("resources/images/bomb_3.jpg");
	}
	
	//画出提示信息
	public void showInfo(Graphics g) {
		//画出提示信息
		this.drawTank(80, 330, g, 0, 1);
		g.setColor(Color.black);
		g.drawString(Recorder.getEnNum()+"", 105, 350);
		this.drawTank(130, 330, g, 0, 0);
		g.setColor(Color.black);
		g.drawString(Recorder.getMyLife()+"", 155, 350);
	
		//画出玩家总成绩
		g.setColor(Color.black);
		Font f = new Font("宋体", Font.BOLD, 20);
		g.setFont(f);
		g.drawString("你的总成绩", 420, 30);
		
		//画出敌人坦克
		this.drawTank(420, 60, g, 0, 1);
		//画出消灭敌人数（+“”是为了int转换为String）
		g.setColor(Color.black);
		g.drawString(Recorder.getAllEnNum()+"", 460, 80);
	}
	
	//重写paint函数
	public void paint(Graphics g) {
		//调用函数初始化（不可缺少）
		super.paint(g);
		//设置坦克活动区域
		g.fillRect(0, 0, 400, 300);	
		
		//画出提示信息坦克（仅起提示作用）
		this.showInfo(g);
		
		//画我的坦克
		if(this.hero.isLive) {
			this.drawTank(hero.getX(), hero.getY(), g, this.hero.direct, 0);
		}
		
		//画出炸弹
		for(int i = 0;i < bombs.size();i++) {
			//取出炸弹
			Bomb b = bombs.get(i);
			
			if(b.life > 6) {
				g.drawImage(img1, b.x, b.y, 30, 30, this);
			}else if(b.life > 3) {
				g.drawImage(img2, b.x, b.y, 30, 30, this);
			}else {
				g.drawImage(img3, b.x, b.y, 30, 30, this);
			}
			
			//让炸弹的生命值减小
			b.lifeDown();
			//如果炸弹生命值为0，把该炸弹从bombs向量去掉
			if(b.life == 0) {
				bombs.remove(b);
			}
		}
		
		//画出敌人的坦克
		for(int i = 0;i < ets.size();i++) {
			EnemyTank et = ets.get(i);
			if(et.isLive) {
				this.drawTank(et.getX(), et.getY(), g, et.getDirect(), 1);
				//画出敌人的子弹
				for(int j = 0;j < et.ss.size();j++) {
					//取出一颗子弹
					Shot enemyShot = et.ss.get(j);
					//画出一颗子弹
					if(enemyShot.isLive) {
						g.draw3DRect(enemyShot.x, enemyShot.y, 1, 1, false);
					}else {
						//如果敌人坦克死亡就从Vector去掉
						et.ss.remove(enemyShot);
					}
				}
			}
		}
		//从ss中取出每个子弹并画出
		for(int i = 0;i < this.hero.ss.size();i++) {
			//取出一颗子弹
			Shot myShot = hero.ss.get(i);
			//画出一颗子弹
			if(myShot != null && myShot.isLive == true) {
				g.draw3DRect(myShot.x, myShot.y, 1, 1, false);
			}
			//删除失效子弹
			if(myShot.isLive == false) {
				//从ss中删除该子弹
				hero.ss.remove(myShot);
			}
		}
	}
	
	//判断是否击中敌人的坦克
	public void hitEnemy() {
		for(int i = 0;i < hero.ss.size();i++) {
			//取出子弹
			Shot myShot = hero.ss.get(i);
			//判断子弹是否有效
			if(myShot.isLive) {
				//取出每个敌人坦克与之判断
				for(int j = 0;j < ets.size();j++) {
					//取出坦克
					EnemyTank et = ets.get(j);
					if(et.isLive){
						if(this.hitTank(myShot, et)) {
							//敌人数量减少
							Recorder.reduceEnNum();
							//增加我的战绩
							Recorder.addEnNumRec();
						}
					}
				}
			}
		}
	}
	
	//判断敌人坦克是否击中我方坦克
	public void hitMe() {
		//取出每个敌人坦克
		for(int i = 0;i < this.ets.size();i++) {
			//取出坦克
			EnemyTank et = ets.get(i);
			
			//取出每颗子弹
			for(int j = 0;j < et.ss.size();j++) {
				//取出子弹
				Shot enemyShot = et.ss.get(j);
				if(hero.isLive) {
					if(this.hitTank(enemyShot, hero)) {	
						//to do
						
					}
				}
			}
		}
	}
	
	
	//判断子弹是否集中敌方坦克
	public boolean hitTank(Shot s, Tank et) {
		boolean ishitted = false;
		
		//判断该坦克的方向
		switch(et.direct) {
		//若敌人坦克方向是上或下
		case 0:
		case 2:
			if(s.x > et.x && s.x < et.x+20 && 
					s.y > et.y && s.y < et.y+30) {
				//击中
				//子弹死亡
				s.isLive = false;
				//敌人坦克死亡
				et.isLive = false;
				//击中bool值更改
				ishitted = true;
				//创建一颗炸弹，放入Vector
				Bomb b = new Bomb(et.x, et.y);
				bombs.add(b);
			}
			break;
		case 1:
		case 3:
			if(s.x > et.x && s.x < et.x+30 &&
					s.y > et.y && s.y < et.y+20) {
				//击中
				//子弹死亡
				s.isLive = false;
				//敌人坦克死亡
				et.isLive = false;
				//击中bool值更改
				ishitted = true;
				//创建一颗炸弹，放入Vector
				Bomb b = new Bomb(et.x, et.y);
				bombs.add(b);
			}
			break;
		}
		return ishitted;
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
			break;
		case 1:
			//炮筒向右（此处较为粗糙，仍以左上角为参照点）
			//1.画出左边的矩形
			g.fill3DRect(x, y, 30, 5, false);
			//2.画出右边的矩形
			g.fill3DRect(x, y+15, 30, 5, false);
			//3.画出中间的矩形
			g.fill3DRect(x+5, y+5, 20, 10, false);
			//4.画出圆形
			g.fillOval(x+10, y+5, 10, 10);
			//5.画出线
			g.drawLine(x+15, y+10, x+30, y+10);
			break;
		case 2:
			//炮筒向下（此处较为粗糙，仍以左上角为参照点）
			//1.画出左边的矩形
			g.fill3DRect(x, y, 5, 30, false);
			//2.画出右边的矩形
			g.fill3DRect(x+15, y, 5, 30, false);
			//3.画出中间的矩形
			g.fill3DRect(x+5, y+5, 10, 20, false);
			//4.画出圆形
			g.fillOval(x+5, y+10, 10, 10);
			//5.画出线
			g.drawLine(x+10, y+15, x+10, y+30);
			break;
		case 3:
			//炮筒向左（此处较为粗糙，仍以左上角为参照点）
			//1.画出左边的矩形
			g.fill3DRect(x, y, 30, 5, false);
			//2.画出右边的矩形
			g.fill3DRect(x, y+15, 30, 5, false);
			//3.画出中间的矩形
			g.fill3DRect(x+5, y+5, 20, 10, false);
			//4.画出圆形
			g.fillOval(x+10, y+5, 10, 10);
			//5.画出线
			g.drawLine(x+15, y+10, x, y+10);
			break;
		}	
	}

	//1.按键按下——a：左 s：下 w：上 d：右
	public void keyPressed(KeyEvent arg0) {
		//操控坦克移动
		switch(arg0.getKeyCode()) {
		case KeyEvent.VK_W:
			//设置我的坦克的方向：上
			this.hero.setDirect(0);
			this.hero.moveUp();
			break;
		case KeyEvent.VK_A:
			//左
			this.hero.setDirect(3);
			this.hero.moveLeft();
			break;
		case KeyEvent.VK_S:
			//下
			this.hero.setDirect(2);
			this.hero.moveDown();
			break;
		case KeyEvent.VK_D:
			//右
			this.hero.setDirect(1);
			this.hero.moveRight();
			break;
		default:
			break;
		}
		
		//判断玩家是否按下“j”键——射击
		if(arg0.getKeyCode() == KeyEvent.VK_J) {
			//限制子弹连发数
			if(this.hero.ss.size() < 5) {
				this.hero.shotEnemy();
			}
		}
		
		//必须重绘Panel
		this.repaint();
	}

	//2.按键松开
	public void keyReleased(KeyEvent arg0) {
		// TODO 自动生成的方法存根
		
	}

	//3.按键输出字符
	public void keyTyped(KeyEvent arg0) {
		// TODO 自动生成的方法存根
		
	}

	//另一个线程
	public void run() {
		//每隔100毫秒去重绘
		while(true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			//判断我方坦克是否击中敌人
			this.hitEnemy();
			//判断敌人坦克是否击中我方坦克
			this.hitMe();
			
			//重绘
			this.repaint();
		}
	}
}











