package Enity;

import java.io.IOException;
import java.util.Random;

import Controller.GameController;
import Controller.OnlinePeopleController;
import Controller.StateController;
import GameStart.ServerStart;

public class EnemyPeople implements Runnable {

	public static final int width = 100;
	public static final int height = 100;
	// public static final int minmovewidth = 9;
	// public static final int minmoveheight = 9;
	// public static final int maxmovewidth = 100;
	// public static final int maxmoveheight = 61;
	public static final int minmovewidth = 1;
	public static final int minmoveheight = 1;
	public static final int maxmovewidth = 116;
	public static final int maxmoveheight = 74;
	private int x;
	private int y;
	private int last_x;
	private int last_y;
	private int speed;
	private int hurt;
	private int behurt;
	private int behurt_time;
	private boolean state = false;
	private int full_blood;
	private int blood;
	int scope = 15;
	private Integer beattack_peo;
	private boolean alive = true;
	private int direct;
	private int people;
	private int enemy_img_state = 1;
	boolean weapon_state = false;
	private Thread thread;
	private Attribute attribute;
	private StateController stateController;

	public EnemyPeople(int people, int x, int y, Attribute attribute) {
		super();
		this.stateController = new StateController();
		attribute.setX(x);
		attribute.setY(y);
		attribute.setId(people);
		this.attribute = attribute;
		this.x = x;
		this.y = y;
		this.last_x = x;
		this.last_y = y;
		this.people = people;
		this.speed = attribute.getSpeed_action();
	}

	public synchronized int getX() {
		return x;
	}

	public synchronized void setX(int x) {
		this.x = x;
	}

	public synchronized int getY() {
		return y;
	}

	public synchronized void setY(int y) {
		this.y = y;
	}

	public synchronized int getLast_x() {
		return last_x;
	}

	public synchronized void setLast_x(int last_x) {
		this.last_x = last_x;
	}

	public synchronized int getLast_y() {
		return last_y;
	}

	public synchronized void setLast_y(int last_y) {
		this.last_y = last_y;
	}

	public synchronized int getSpeed() {
		return speed;
	}

	public synchronized void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getEnemy_img_state() {
		return enemy_img_state;
	}

	public synchronized int getBehurt() {
		return behurt;
	}

	public synchronized void setBehurt(int behurt) {
		this.behurt = behurt;
	}

	public synchronized int getBehurt_time() {
		return behurt_time;
	}

	public synchronized void setBehurt_time(int behurt_time) {
		this.behurt_time = behurt_time;
	}

	public synchronized boolean getState() {
		return state;
	}

	public synchronized void setState(boolean state) {
		this.state = state;
	}

	public synchronized int getBlood() {
		return blood;
	}

	public synchronized void setBlood(int blood) {
		this.blood = blood;
	}

	public synchronized void setBlood(int blood, int id1) {
		this.blood = blood;
		if (this.beattack_peo == null)
			this.beattack_peo = id1;
	}

	public synchronized Integer getBeattack_peo() {
		return beattack_peo;
	}

	public void setBeattack_peo(Integer beattack_peo) {
		this.beattack_peo = beattack_peo;
	}

	public synchronized int getFull_blood() {
		return full_blood;
	}

	public synchronized void setFull_blood(int full_blood) {
		this.full_blood = full_blood;
	}

	public synchronized int getPeople() {
		return people;
	}

	public synchronized void setPeople(int people) {
		this.people = people;
	}

	public synchronized int getDirect() {
		return direct;
	}

	public synchronized void setDirect(int direct) {
		this.direct = direct;
	}

	public synchronized boolean getAlive() {
		return alive;
	}

	public synchronized void setAlive(boolean alive) {
		this.alive = alive;
	}

	public Attribute getAttribute() {
		return this.attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	public StateController getStateController() {
		return stateController;
	}

	public synchronized Thread getThread() {
		return thread;
	}

	public synchronized void setThread(Thread thread) {
		this.thread = thread;
	}

	public boolean map_collision(int nvert, int ver[][], float testx, float testy) {
		int i, j;
		boolean c = false;
		for (i = 0, j = nvert - 1; i < nvert; j = i++) {
			if (((ver[i][1] > testy) != (ver[j][1] > testy))
					&& (testx < (ver[j][0] - ver[i][0]) * (testy - ver[i][1]) / (ver[j][1] - ver[i][1]) + ver[i][0]))
				c = !c;
		}
		return c;

	}

	public void x_collision(int x, int people) {

		if (GameMap.gamemap.map_collision(GameController.leave - 1, x, this.y)) {

			GameMap.location[last_x][last_y] = 0;
			last_x = x;
			this.x = x;
			GameMap.location[x][y] = people;

			try {
				ServerStart.OutStreamAll(people + ":0001:0:" + x);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void y_collision(int y, int people) {

		if (GameMap.gamemap.map_collision(GameController.leave - 1, this.x, y)) {

			GameMap.location[last_x][last_y] = 0;
			last_y = y;
			this.y = y;
			GameMap.location[x][y] = people;

			try {
				ServerStart.OutStreamAll(people + ":0001:1:" + y);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// synchronized
	public void people_attack() throws IOException {

		this.weapon_state = true;
		ServerStart.OutStreamAll(people + ":0000");

	}

	@Override
	public String toString() {
		return ";" + x + ":" + y + ":" + speed + ":" + full_blood + ":" + blood + ":" + hurt + ":" + direct + ":"
				+ people;
	}

	@Override
	public void run() {

		int step = 1;
		int thread_num = 1;
		boolean TF = true;
		while (TF) {
			thread_num++;
			if (thread_num == 9)
				thread_num = 1;
			if (alive) {
				try {
					Thread.sleep(200);

					if (thread_num % 1 == 0) {

					}

					if (thread_num % 2 == 0) {

					}

					if (thread_num % 4 == 0) {
						step++;
						if (stateController.Move()) {
							if ((int) (Math.random() * 7) < 6) {
								if (this.beattack_peo == null) {
									if (step >= 4) {
										step = 1;
										direct = (int) (Math.random() * 4);
									}
									switch (direct) {
									case 0:
										y_collision(y - speed, people);
										break;
									case 1:
										x_collision(x + speed, people);
										break;
									case 2:
										y_collision(y + speed, people);
										break;
									case 3:
										x_collision(x - speed, people);
										break;
									}
								} else {
									int[] a = OnlinePeopleController.people_xy(this.beattack_peo);
									if (Math.pow(x - a[0], 2) + Math.pow(y - a[1], 2) > Math.pow(this.scope, 2)) {
										this.beattack_peo = null;
										continue;
									}
									if ((int) (Math.random() * 2) == 0) {
										if (this.x > a[0])
											x_collision(x - speed, people);
										else
											x_collision(x + speed, people);
									} else {
										if (this.y > a[1])
											y_collision(y - speed, people);
										else
											y_collision(y + speed, people);
									}
								}
							}
						}
						if ((int) (Math.random() * 5) == 0 && stateController.Attack())
							people_attack();
					}

					// if (thread_num % 8 == 0) {
					//
					// }
				} catch (Exception e) {
					System.out.println("����Ҵ��ڣ����" + people + "������");
					break;
				}

			} else {
				String info = null;
				int goods = new Random().nextInt(30);
				if (goods <= 0) {
					int size = GameController.goods_leave.get(0).charAt(0) - '0';
					int n = (int) Math.random() * size;
					info = GameController.goods_leave.get(0).substring(1 + 4 * n, 5 + 4 * n);
				} else if (goods <= 2) {
					int size = GameController.goods_leave.get(1).charAt(0) - '0';
					int n = (int) Math.random() * size;
					info = GameController.goods_leave.get(1).substring(1 + 4 * n, 5 + 4 * n);
				} else if (goods <= 5) {
					int size = GameController.goods_leave.get(2).charAt(0) - '0';
					int n = (int) Math.random() * size;
					info = GameController.goods_leave.get(2).substring(1 + 4 * n, 5 + 4 * n);
				} else if (goods <= 14) {
					int size = GameController.goods_leave.get(3).charAt(0) - '0';
					int n = (int) Math.random() * size;
					info = GameController.goods_leave.get(3).substring(1 + 4 * n, 5 + 4 * n);
				} else if (goods <= 30) {
					int size = GameController.goods_leave.get(4).charAt(0) - '0';
					int n = (int) Math.random() * size;
					info = GameController.goods_leave.get(4).substring(1 + 4 * n, 5 + 4 * n);
				}
				try {
					ServerStart.OutStreamAll("0:0301:" + x + ":" + y + ":" + info);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				TF = false;
				break;
			}
		}
	}
}
