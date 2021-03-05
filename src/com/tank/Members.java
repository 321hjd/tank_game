/*
 * 功能：坦克游戏成员类
 */
package com.tank;

import java.util.Vector;

import javax.sound.sampled.*;

import java.io.*;

//播放声音的类
class AePlayWave extends Thread {
	private String filename;
	
	public AePlayWave(String wavfile) {
		filename = wavfile;
	}
	
	public void run() {
		File soundFile = new File(filename);
		
		AudioInputStream audioInputStream = null;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(soundFile);
		} catch(Exception e) {
			e.printStackTrace();
			return;
		}
		
		AudioFormat format = audioInputStream.getFormat();
		SourceDataLine auline = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
		
		try {
			auline = (SourceDataLine) AudioSystem.getLine(info);
			auline.open(format);
		} catch(Exception e) {
			e.printStackTrace();
			return;
		}
		
		auline.start();
		int nBytesRead = 0;
		//定义字节数组，以便缓冲读取
		byte[]  abData = new byte[1024];
		
		try {
			//循环读取，因为可能声音文件很大
			while(nBytesRead != -1) {
				nBytesRead = audioInputStream.read(abData, 0, abData.length);
				if(nBytesRead >= 0) {
					auline.write(abData, 0, nBytesRead);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
			return;
		} finally {
			auline.drain();
			auline.close();
		}
	}
}


//恢复点
class Node{
	int x;
	int y;
	int direct;
	public Node(int x, int y, int direct) {
		this.x = x;
		this.y = y;
		this.direct = direct;
	}
}


//记录类(静态类)，同时保存玩家设置
class Recorder {
	//记录每关有多少敌人
	private static int enNum = 20;
	//设置我方坦克数量
	private static int myLife = 3;
	//记录总共消灭敌人数
	private static int allEnNum = 0;
	
	//读写文件所需
	private static FileWriter fw = null;
	private static BufferedWriter bw = null;
	private static FileReader fr = null;
	private static BufferedReader br = null;
	
	//获取外部tank数据的变量
	private Vector<EnemyTank> ets = new Vector<EnemyTank>();
	
	//从文件中恢复记录点
	static Vector<Node> nodes = new Vector<Node>();
	
	//完成读取任务
	public Vector<Node> getNodesAndEnNums() {
		try {
			//创建文件流
			fr = new FileReader("resources/data/myRecorder.txt");
			br = new BufferedReader(fr);
			
			//读取敌人数量（战绩）
			String n = "";
			n = br.readLine();
			allEnNum = Integer.parseInt(n);
			//读取结点
			while((n = br.readLine()) != null) {
				//按空格分割数据，分别得到每个坦克的x，y坐标和方向
				String[] xyz = n.split(" ");
				Node node = new Node(Integer.parseInt(xyz[0]), 
						Integer.parseInt(xyz[1]), 
						Integer.parseInt(xyz[2]));
				nodes.add(node);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			//关闭文件流
			try {
				//后开先关闭
				br.close();
				fr.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return nodes;
	}
	
	//保存击毁敌人的数量和敌人坦克坐标、方向
	public void keepRecAndEnemyTank() {
		try {
			//创建文件流
			fw = new FileWriter("resources/data/myRecorder.txt");
			bw = new BufferedWriter(fw);
			
			bw.write(allEnNum+"\r\n");
			
			//保存当前活的敌人坦克的坐标和方向
			for(int i = 0;i < ets.size();i++) {
				//取出第一辆坦克
				EnemyTank et = ets.get(i);
				//如果坦克活着，保存
				if(et.isLive) {
					String record = et.x+" "+et.y+" "+et.direct;
					//写入
					bw.write(record+"\r\n");
				}
				
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			//关闭文件流
			try {
				//后开先关闭
				bw.close();
				fw.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//减少敌人数
	public static void reduceEnNum() {
		enNum--;
	}
	//减少我方坦克数
	public static void reduceMyLife() {
		enNum--;
	}
	
	//消灭敌人
	public static void addEnNumRec() {
		allEnNum++;
	}
	
	//把玩家击毁敌人坦克数量保存到文件中
	public static void keepRecording() {
		try {
			//创建文件流
			fw = new FileWriter("resources/data/myRecorder.txt");
			bw = new BufferedWriter(fw);
			
			bw.write(allEnNum+"\r\n");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			//关闭文件流
			try {
				//后开先关闭
				bw.close();
				fw.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//从文件中读取记录
	public static void getRecording() {
		try {
			//创建文件流
			fr = new FileReader("resources/data/myRecorder.txt");
			br = new BufferedReader(fr);
			
			String n = br.readLine();
			allEnNum = Integer.parseInt(n);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			//关闭文件流
			try {
				//后开先关闭
				br.close();
				fr.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//getter/setter函数
	public static int getEnNum() {
		return enNum;
	}
	public static void setEnNum(int enNum) {
		Recorder.enNum = enNum;
	}
	public static int getMyLife() {
		return myLife;
	}
	public static void setMyLife(int myLife) {
		Recorder.myLife = myLife;
	}
	public static int getAllEnNum() {
		return allEnNum;
	}
	public static void setAllEnNum(int allEnNum) {
		Recorder.allEnNum = allEnNum;
	}
	public Vector<EnemyTank> getEts() {
		return ets;
	}

	public void setEts(Vector<EnemyTank> ets) {
		this.ets = ets;
	}
}

//炸弹类
class Bomb {
	//定义炸弹的坐标
	int x, y;
	//炸弹生命
	int life = 9;
	//炸弹是否存活
	boolean isLive = true;
	
	public Bomb(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	//减少生命值
	public void lifeDown() {
		if(life > 0) {
			life--;
		}else {
			this.isLive = false;
		}
	}
}

//子弹类
class Shot implements Runnable {
	//属性
	int x;
	int y;
	int direct;
	int speed = 2;
	boolean isLive = true;
	
	public Shot(int x, int y, int direct) {
		this.x = x;
		this.y = y;
		this.direct = direct;
	}
	
	//线程
	public void run() {
		while(true) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			switch(direct) {
			case 0:
				//上
				y -= speed;
				break;
			case 1:
				//右
				x += speed;
				break;
			case 2:
				//下
				y += speed;
				break;
			case 3:
				//左
				x -= speed;
				break;
			}
			
			//判断该子弹是否碰到边缘
			if(x<0 || x>400 || y < 0 || y > 300) {
				this.isLive = false;
				break;
			}	
		}	
	}
	
	//get和set方法
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getDirect() {
		return direct;
	}

	public void setDirect(int direct) {
		this.direct = direct;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
}


//坦克类
class Tank{
	//坦克横纵坐标
	int x = 0;
	int y = 0;
	
	//坦克方向——0：上 1：右 2：下 3：左
	int direct = 0;
	
	//坦克速度
	int speed = 1;

	//坦克颜色
	int color = 0;

	//
	boolean isLive = true;
	
	//构造函数
	public Tank(int x, int y) {
		this.x = x;
		this.y = y;	
	}
	
	//坦克移动
	public void moveUp() {
		if(y > 0) {
			y -= speed;
		}
	}
	public void moveRight() {
		if(x < 400) {
			x += speed;
		}
	}
	public void moveLeft() {
		if(x > 0) {
			x -= speed;
		}
	}
	public void moveDown() {
		if(y < 300) {
			y += speed;
		}
	}
	
	//get和set方法
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void setDirect(int direct) {
		this.direct = direct;
	}
	
	public int getDirect() {
		return direct;
	}
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}
}

//我的坦克
class Hero extends Tank {
	//子弹
	Vector<Shot> ss = new Vector<Shot>();
	Shot s = null;
	
	public Hero(int x, int y) {
		//调用函数初始化（不可缺少）
		super(x,y);
	}
	
	//开火
	public void shotEnemy() {
		switch(this.direct) {
		case 0:
			s = new Shot(x+10, y, 0);
			ss.add(s);
			break;
		case 1:
			s = new Shot(x+30, y+10, 1);
			ss.add(s);
			break;
		case 2:
			s = new Shot(x+10, y+30, 2);
			ss.add(s);
			break;
		case 3:
			s = new Shot(x, y+10, 3);
			ss.add(s);
			break;	
		}
		//启动线程
		Thread t = new Thread(s);
		t.start();
	}
}

//敌人的坦克
class EnemyTank extends Tank implements Runnable {
	
	//定义一个向量存放敌人的子弹
	Vector<Shot> ss = new Vector<Shot>();
	
	//定义一个向量，可以访问MyPanel上所有敌人的坦克
	Vector<EnemyTank> ets = new Vector<EnemyTank>();
	
	
	public EnemyTank(int x, int y) {
		//调用函数初始化（不可缺少）
		super(x,y);
	}
	
	//得到MyPanel上敌人的坦克向量
	public void setEts(Vector<EnemyTank> vv) {
		this.ets = vv;
	}
	
	//判断敌方坦克是否重叠
	public boolean isTouchOtherEnemy() {
		boolean b = false;
		
		switch(this.direct) {
		case 0:
			//向上
			//取出所有的坦克
			for(int i = 0;i < ets.size();i++) {
				//取出第一个坦克
				EnemyTank et = ets.get(i);
				//如果不是自己
				if(et != this) {
					//若对方坦克方向向下或向上
					if(et.direct == 0 || et.direct == 2) {
						if(this.x >= et.x && this.x <= et.x+20
								&& this.y >= et.y && this.y <= et.y+30) {
							return true;
						}
						if(this.x+20 >= et.x && this.x+20 <= et.x+20
								&& this.y >= et.y && this.y <= et.y+30) {
							return true;
						}
					}
					//若对方坦克方向向左或向右
					if(et.direct == 1 || et.direct == 3) {
						if(this.x >= et.x && this.x <= et.x+30
								&& this.y >= et.y && this.y <= et.y+20) {
							return true;
						}
						if(this.x+20 >= et.x && this.x+20 <= et.x+30
								&& this.y >= et.y && this.y <= et.y+20) {
							return true;
						}
					}
				}
			}
			break;
		case 1:
			//向右
			//取出所有的坦克
			for(int i = 0;i < ets.size();i++) {
				//取出第一个坦克
				EnemyTank et = ets.get(i);
				//如果不是自己
				if(et != this) {
					//若对方坦克方向向下或向上
					if(et.direct == 0 || et.direct == 2) {
						if(this.x+30 >= et.x && this.x+30 <= et.x+20
								&& this.y >= et.y && this.y <= et.y+30) {
							return true;
						}
						if(this.x+30 >= et.x && this.x+30 <= et.x+20
								&& this.y+20 >= et.y && this.y+20 <= et.y+30) {
							return true;
						}
					}
					//若对方坦克方向向左或向右
					if(et.direct == 1 || et.direct == 3) {
						if(this.x+30 >= et.x && this.x+30 <= et.x+30
								&& this.y >= et.y && this.y <= et.y+20) {
							return true;
						}
						if(this.x+30 >= et.x && this.x+30 <= et.x+30
								&& this.y+20 >= et.y && this.y+20 <= et.y+20) {
							return true;
						}
					}
				}
			}
			break;
		case 2:
			//向下
			//取出所有的坦克
			for(int i = 0;i < ets.size();i++) {
				//取出第一个坦克
				EnemyTank et = ets.get(i);
				//如果不是自己
				if(et != this) {
					//若对方坦克方向向下或向上
					if(et.direct == 0 || et.direct == 2) {
						if(this.x >= et.x && this.x <= et.x+20
								&& this.y+30 >= et.y && this.y+30 <= et.y+30) {
							return true;
						}
						if(this.x+20 >= et.x && this.x+20 <= et.x+20
								&& this.y+30 >= et.y && this.y+30 <= et.y+30) {
							return true;
						}
					}
					//若对方坦克方向向左或向右
					if(et.direct == 1 || et.direct == 3) {
						if(this.x >= et.x && this.x <= et.x+30
								&& this.y+30 >= et.y && this.y+30 <= et.y+20) {
							return true;
						}
						if(this.x+20 >= et.x && this.x+20 <= et.x+30
								&& this.y+30 >= et.y && this.y+30 <= et.y+20) {
							return true;
						}
					}
				}
			}
			break;
		case 3:
			//向左
			//取出所有的坦克
			for(int i = 0;i < ets.size();i++) {
				//取出第一个坦克
				EnemyTank et = ets.get(i);
				//如果不是自己
				if(et != this) {
					//若对方坦克方向向下或向上
					if(et.direct == 0 || et.direct == 2) {
						if(this.x >= et.x && this.x <= et.x+20
								&& this.y >= et.y && this.y <= et.y+30) {
							return true;
						}
						if(this.x >= et.x && this.x <= et.x+20
								&& this.y+20 >= et.y && this.y+20 <= et.y+30) {
							return true;
						}
					}
					//若对方坦克方向向左或向右
					if(et.direct == 1 || et.direct == 3) {
						if(this.x >= et.x && this.x <= et.x+30
								&& this.y >= et.y && this.y <= et.y+20) {
							return true;
						}
						if(this.x >= et.x && this.x <= et.x+30
								&& this.y+20 >= et.y && this.y+20 <= et.y+20) {
							return true;
						}
					}
				}
			}
			break;
		}
		
		return b;
	}

	//重写run函数
	public void run() {
		// TODO 自动生成的方法存根
		while(true) {
			//惯性移动
			switch(this.direct) {
			case 0:
				//说明正在向上移动
				for(int i = 0;i < 30;i++) {
					//保证坦克不出边界
					if(y > 0 && !this.isTouchOtherEnemy()) {
						y -= speed;	
					}
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}
				break;
			case 1:
				//向右
				for(int i = 0;i < 30;i++) {
					if(x < 400 && !this.isTouchOtherEnemy()) {
						x += speed;	
					}
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}
				break;
			case 2:
				//向下
				for(int i = 0;i < 30;i++) {
					if(y < 300 && !this.isTouchOtherEnemy()) {
						y += speed;	
					}
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}
				break;
			case 3:
				//向左
				for(int i = 0;i < 30;i++) {
					if(x > 0 && !this.isTouchOtherEnemy()) {	
						x -= speed;
					}
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}
				break;
			}
			
		
			//判断坦克是否仍存活
			if(isLive) {
				if(ss.size() < 5) {
					Shot s = null;
					//添加子弹
					switch(direct) {
					case 0:
						s = new Shot(x+10, y, 0);
						ss.add(s);
						break;
					case 1:
						s = new Shot(x+30, y+10, 1);
						ss.add(s);
						break;
					case 2:
						s = new Shot(x+10, y+30, 2);
						ss.add(s);
						break;
					case 3:
						s = new Shot(x, y+10, 3);
						ss.add(s);
						break;	
					}
					
					//启动子弹线程
					Thread t = new Thread(s);
					t.start();
				}
			}
						
			//让坦克随机产生新的方向
			this.direct = (int)(Math.random()*4);
			
			//判断敌人是否死亡
			if(this.isLive == false) {
				//坦克死亡后退出线程
				break;
			}
		}
	}
}
