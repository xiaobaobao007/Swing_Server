package Controller;

import Enity.EnemyPeople;
import Enity.GameMap;
import Enity.Goods;
import GameStart.GameStart;
import GameStart.ServerStart;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@SuppressWarnings("ALL")
public class GameController implements Runnable {

	/**
	 * synchronized
	 */
	// private static int minx = 44;
	// private static int maxx = 113;
	// private static int miny = 16;
	// private static int maxy = 33;
	public static int leave = 1;
	public static int minleave;
	static int die_enemy;
	public static Vector<EnemyPeople> Enemys;// 怪物
	public static List<Goods> map_goods;// 掉落物品
	public static List<Goods> all_goods = new ArrayList<>();
	public static List<StringBuffer> goods_leave = new ArrayList<>();
	static int map = 0;
	public static int[][][] monster_nums = new int[10][10][3];
	public static int[] leave_maxs = new int[10];
	public static int[][] leave_maxsNums = new int[10][10];
	// public static GameMap map = new GameMap();//地图初始化

	public GameController(int leave) {
		map_goods = new ArrayList<Goods>();
		GameController.leave = leave;
		minleave = 0;
		try {
			Rest_Enemy(monster_nums[leave - 1][minleave]);
		} catch (IOException e) {
			System.out.println("GameController.GameController()");
		}
	}

	public synchronized static void add_good_leave(int leave, int type, int id) {
		char n = (char) (goods_leave.get(leave - 1).charAt(0) + 1);
		String info = (type < 10 ? "0" + type : "" + type) + (id < 10 ? "0" + id : "" + id);
		goods_leave.get(leave - 1).replace(0, 1, n + "");
		goods_leave.get(leave - 1).append(info);
	}

	public synchronized static void dos_enemy() {
		StringBuffer string = new StringBuffer(leave_maxsNums[leave - 1][minleave] + ":0401");
		for (EnemyPeople e : Enemys) {
			string.append(e.getAttribute().getString());
		}
		try {
			ServerStart.OutStreamAll(string.toString());
		} catch (IOException e1) {
			System.out.println("GameController.dos_enemy()");
		}
	}

	public synchronized static void dos_allgoods(int id) {
		int size = GameController.all_goods.size();
		int i = 0, m = size / 5, n = size % 5;
		StringBuffer string1 = null;
		for (Goods one : GameController.all_goods) {
			if (i == 0 && m > 0) {
				string1 = new StringBuffer("5:0501");
			}
			else if (i == 0 && m == 0) {
				string1 = new StringBuffer(n + ":0501");
			}
			i++;
			string1.append(one.getString());
			if (i == 5) {
				m--;
				i = 0;
				ServerStart.OutStreamOne(id, string1.toString());
			} else if (m == 0 && i == n) {
				ServerStart.OutStreamOne(id, string1.toString());
			}
		}
	}

	public synchronized static void stop_enemy(int id, int type, int time) {
		if (type == 1) {
			Enemys.get(id).getStateController().Start_Frozen(time);
		}
	}

	public synchronized static void hurt_enemy(int id, int blood, int id1) {
		Enemys.get(id).setBlood(blood, id1);
	}

	public synchronized static void delete_enemy(int Ene_id, int Peo_id) {
		Enemys.get(Ene_id).setAlive(false);
		die_enemy++;
		ServerStart.OutStreamExceptOne(Peo_id, Ene_id + ":0302");
		if (die_enemy == leave_maxsNums[leave - 1][minleave] && minleave < leave_maxs[leave - 1] - 1) {
			try {
				Rest_Enemy(monster_nums[leave - 1][++minleave]);
			} catch (IOException e) {
				System.out.println("GameController.delete_enemy()");
			}
		}
	}

	public static void Rest_Enemy(int[] nums) throws IOException {
		Enemys = new Vector<>();
		die_enemy = 0;
		System.out.println("大关卡" + leave + "//小关卡" + minleave + "已开始");
		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < nums[i]; j++) {
				Point point = GameMap.gamemap.getPoint(leave - 1);
				EnemyPeople Enemy = new EnemyPeople(i, (int) point.getX(), (int) point.getY(),
						GameStart.attributes.get(i).clone());
				Thread Enemy_thread = new Thread(Enemy);
				Enemy.setThread(Enemy_thread);
				Enemys.add(Enemy);
			}
		}
		dos_enemy();
		for (EnemyPeople enemyPeople : Enemys) {
			enemyPeople.getThread().start();
			try {
				int sleep = (int) (Math.random() * 200);
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		// while (true) {
		// try {
		// } catch (Exception e) {
		// System.out.println("游戏主流程关闭");
		// break;
		// }
		// }
	}
}