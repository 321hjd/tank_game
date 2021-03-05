/*
 * ���ܣ�̹����Ϸ��Ա��
 */
package com.tank;

import java.util.Vector;

import javax.sound.sampled.*;

import java.io.*;

//������������
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
		//�����ֽ����飬�Ա㻺���ȡ
		byte[]  abData = new byte[1024];
		
		try {
			//ѭ����ȡ����Ϊ���������ļ��ܴ�
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


//�ָ���
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


//��¼��(��̬��)��ͬʱ�����������
class Recorder {
	//��¼ÿ���ж��ٵ���
	private static int enNum = 20;
	//�����ҷ�̹������
	private static int myLife = 3;
	//��¼�ܹ����������
	private static int allEnNum = 0;
	
	//��д�ļ�����
	private static FileWriter fw = null;
	private static BufferedWriter bw = null;
	private static FileReader fr = null;
	private static BufferedReader br = null;
	
	//��ȡ�ⲿtank���ݵı���
	private Vector<EnemyTank> ets = new Vector<EnemyTank>();
	
	//���ļ��лָ���¼��
	static Vector<Node> nodes = new Vector<Node>();
	
	//��ɶ�ȡ����
	public Vector<Node> getNodesAndEnNums() {
		try {
			//�����ļ���
			fr = new FileReader("resources/data/myRecorder.txt");
			br = new BufferedReader(fr);
			
			//��ȡ����������ս����
			String n = "";
			n = br.readLine();
			allEnNum = Integer.parseInt(n);
			//��ȡ���
			while((n = br.readLine()) != null) {
				//���ո�ָ����ݣ��ֱ�õ�ÿ��̹�˵�x��y����ͷ���
				String[] xyz = n.split(" ");
				Node node = new Node(Integer.parseInt(xyz[0]), 
						Integer.parseInt(xyz[1]), 
						Integer.parseInt(xyz[2]));
				nodes.add(node);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			//�ر��ļ���
			try {
				//���ȹر�
				br.close();
				fr.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return nodes;
	}
	
	//������ٵ��˵������͵���̹�����ꡢ����
	public void keepRecAndEnemyTank() {
		try {
			//�����ļ���
			fw = new FileWriter("resources/data/myRecorder.txt");
			bw = new BufferedWriter(fw);
			
			bw.write(allEnNum+"\r\n");
			
			//���浱ǰ��ĵ���̹�˵�����ͷ���
			for(int i = 0;i < ets.size();i++) {
				//ȡ����һ��̹��
				EnemyTank et = ets.get(i);
				//���̹�˻��ţ�����
				if(et.isLive) {
					String record = et.x+" "+et.y+" "+et.direct;
					//д��
					bw.write(record+"\r\n");
				}
				
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			//�ر��ļ���
			try {
				//���ȹر�
				bw.close();
				fw.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//���ٵ�����
	public static void reduceEnNum() {
		enNum--;
	}
	//�����ҷ�̹����
	public static void reduceMyLife() {
		enNum--;
	}
	
	//�������
	public static void addEnNumRec() {
		allEnNum++;
	}
	
	//����һ��ٵ���̹���������浽�ļ���
	public static void keepRecording() {
		try {
			//�����ļ���
			fw = new FileWriter("resources/data/myRecorder.txt");
			bw = new BufferedWriter(fw);
			
			bw.write(allEnNum+"\r\n");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			//�ر��ļ���
			try {
				//���ȹر�
				bw.close();
				fw.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//���ļ��ж�ȡ��¼
	public static void getRecording() {
		try {
			//�����ļ���
			fr = new FileReader("resources/data/myRecorder.txt");
			br = new BufferedReader(fr);
			
			String n = br.readLine();
			allEnNum = Integer.parseInt(n);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			//�ر��ļ���
			try {
				//���ȹر�
				br.close();
				fr.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//getter/setter����
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

//ը����
class Bomb {
	//����ը��������
	int x, y;
	//ը������
	int life = 9;
	//ը���Ƿ���
	boolean isLive = true;
	
	public Bomb(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	//��������ֵ
	public void lifeDown() {
		if(life > 0) {
			life--;
		}else {
			this.isLive = false;
		}
	}
}

//�ӵ���
class Shot implements Runnable {
	//����
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
	
	//�߳�
	public void run() {
		while(true) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			switch(direct) {
			case 0:
				//��
				y -= speed;
				break;
			case 1:
				//��
				x += speed;
				break;
			case 2:
				//��
				y += speed;
				break;
			case 3:
				//��
				x -= speed;
				break;
			}
			
			//�жϸ��ӵ��Ƿ�������Ե
			if(x<0 || x>400 || y < 0 || y > 300) {
				this.isLive = false;
				break;
			}	
		}	
	}
	
	//get��set����
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


//̹����
class Tank{
	//̹�˺�������
	int x = 0;
	int y = 0;
	
	//̹�˷��򡪡�0���� 1���� 2���� 3����
	int direct = 0;
	
	//̹���ٶ�
	int speed = 1;

	//̹����ɫ
	int color = 0;

	//
	boolean isLive = true;
	
	//���캯��
	public Tank(int x, int y) {
		this.x = x;
		this.y = y;	
	}
	
	//̹���ƶ�
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
	
	//get��set����
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

//�ҵ�̹��
class Hero extends Tank {
	//�ӵ�
	Vector<Shot> ss = new Vector<Shot>();
	Shot s = null;
	
	public Hero(int x, int y) {
		//���ú�����ʼ��������ȱ�٣�
		super(x,y);
	}
	
	//����
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
		//�����߳�
		Thread t = new Thread(s);
		t.start();
	}
}

//���˵�̹��
class EnemyTank extends Tank implements Runnable {
	
	//����һ��������ŵ��˵��ӵ�
	Vector<Shot> ss = new Vector<Shot>();
	
	//����һ�����������Է���MyPanel�����е��˵�̹��
	Vector<EnemyTank> ets = new Vector<EnemyTank>();
	
	
	public EnemyTank(int x, int y) {
		//���ú�����ʼ��������ȱ�٣�
		super(x,y);
	}
	
	//�õ�MyPanel�ϵ��˵�̹������
	public void setEts(Vector<EnemyTank> vv) {
		this.ets = vv;
	}
	
	//�жϵз�̹���Ƿ��ص�
	public boolean isTouchOtherEnemy() {
		boolean b = false;
		
		switch(this.direct) {
		case 0:
			//����
			//ȡ�����е�̹��
			for(int i = 0;i < ets.size();i++) {
				//ȡ����һ��̹��
				EnemyTank et = ets.get(i);
				//��������Լ�
				if(et != this) {
					//���Է�̹�˷������»�����
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
					//���Է�̹�˷������������
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
			//����
			//ȡ�����е�̹��
			for(int i = 0;i < ets.size();i++) {
				//ȡ����һ��̹��
				EnemyTank et = ets.get(i);
				//��������Լ�
				if(et != this) {
					//���Է�̹�˷������»�����
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
					//���Է�̹�˷������������
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
			//����
			//ȡ�����е�̹��
			for(int i = 0;i < ets.size();i++) {
				//ȡ����һ��̹��
				EnemyTank et = ets.get(i);
				//��������Լ�
				if(et != this) {
					//���Է�̹�˷������»�����
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
					//���Է�̹�˷������������
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
			//����
			//ȡ�����е�̹��
			for(int i = 0;i < ets.size();i++) {
				//ȡ����һ��̹��
				EnemyTank et = ets.get(i);
				//��������Լ�
				if(et != this) {
					//���Է�̹�˷������»�����
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
					//���Է�̹�˷������������
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

	//��дrun����
	public void run() {
		// TODO �Զ����ɵķ������
		while(true) {
			//�����ƶ�
			switch(this.direct) {
			case 0:
				//˵�����������ƶ�
				for(int i = 0;i < 30;i++) {
					//��֤̹�˲����߽�
					if(y > 0 && !this.isTouchOtherEnemy()) {
						y -= speed;	
					}
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
				}
				break;
			case 1:
				//����
				for(int i = 0;i < 30;i++) {
					if(x < 400 && !this.isTouchOtherEnemy()) {
						x += speed;	
					}
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
				}
				break;
			case 2:
				//����
				for(int i = 0;i < 30;i++) {
					if(y < 300 && !this.isTouchOtherEnemy()) {
						y += speed;	
					}
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
				}
				break;
			case 3:
				//����
				for(int i = 0;i < 30;i++) {
					if(x > 0 && !this.isTouchOtherEnemy()) {	
						x -= speed;
					}
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
				}
				break;
			}
			
		
			//�ж�̹���Ƿ��Դ��
			if(isLive) {
				if(ss.size() < 5) {
					Shot s = null;
					//����ӵ�
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
					
					//�����ӵ��߳�
					Thread t = new Thread(s);
					t.start();
				}
			}
						
			//��̹����������µķ���
			this.direct = (int)(Math.random()*4);
			
			//�жϵ����Ƿ�����
			if(this.isLive == false) {
				//̹���������˳��߳�
				break;
			}
		}
	}
}
