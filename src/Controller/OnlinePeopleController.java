package Controller;

import java.awt.Point;
import java.io.IOException;
import java.util.Vector;

import Enity.EnemyPeople;
import Enity.GameMap;
import Enity.OnlinePeople;
import Enity.OwnPeople;
import GameStart.ServerStart;

public class OnlinePeopleController {

	private static final int MAX_PEOPLES = 6;
	public static Vector<OnlinePeople> onlinePeoples = new Vector<>();// 玩家集合
	public static int online_people = 0;// 在线玩家数量
	private static int[] peoplesIndex = new int[MAX_PEOPLES];// 根据玩家id查找Index

	/**
	 * 增加玩家
	 *
	 * @param ownPeople
	 * @return
	 */
	public synchronized static OnlinePeople add_people(OwnPeople ownPeople) {
		OnlinePeople onlinePeople = new OnlinePeople(ownPeople, 0);
		onlinePeoples.addElement(onlinePeople);
		index(ownPeople.getPeople(), online_people);
		++online_people;
		try {
			OutStreamOne(ownPeople.getPeople(), "1:0201" + ownPeople.getAttribute().getString());// 给当前用户发送信息
			dos_TeamsToOne(ownPeople.getPeople());// 把队友信息给当前用户
			ServerStart.OutStreamExceptOne(ownPeople.getPeople(), "1:0202" + ownPeople.getString());// 把当前用户信息发送给其他队友
		} catch (IOException e) {
			e.printStackTrace();
		}
		return onlinePeople;
	}

	/**
	 * 改变玩家状态
	 *
	 * @param peopleIndexId
	 * @param state
	 */
	private synchronized static void changeState(int peopleIndexId, int state) {
		onlinePeoples.get(peopleIndexId).setState(state);
	}

	/**
	 * 改变玩家准备关卡
	 *
	 * @param peopleIndexId
	 * @param level
	 */
	private synchronized static void changeLevel(int peopleIndexId, int level) {
		onlinePeoples.get(peopleIndexId).setLeave(level);
	}

	/**
	 * 是否开始游戏
	 *
	 * @return
	 */
	public synchronized static boolean canStartGame() {
		for (OnlinePeople one : onlinePeoples) {
			if (one.getState() != 1)
				return false;
		}
		System.out.println("====================");
		return true;
	}

	private static int index(int id) {
		return peoplesIndex[id];
	}

	private static void index(int id, int index) {
		peoplesIndex[id] = index;
	}

	public static void putChangePoint() {
		for (OnlinePeople one : onlinePeoples) {
			int level = GameController.leave - 1;
			Point point = GameMap.gamemap.getPoint(level);
			int x = (int) point.getX();
			int y = (int) point.getY();
			ServerStart.OutStreamOne(one.getId(),one.getId()+":0208:"+x+":"+y);
			ServerStart.OutStreamExceptOne(one.getId(), one.getId()+":0209:"+x+":"+y);
		}
	}

	/**
	 * 改变玩家准备状态
	 *
	 * @param id
	 * @param operation
	 */
	public synchronized static int inInfo(int id, String operation, String l) {
		int level = Integer.valueOf(l);
		if (operation.equals("+")) {
			changeLevel(index(id), level);
			changeState(index(id), 1);
		} else if (operation.equals("-")) {
			changeLevel(index(id), -1);
			changeState(index(id), 0);
		}
		return getGameLevel();
	}

	/**
	 * 把玩家移除
	 *
	 * @param onlinePeople
	 * @return
	 */
	public synchronized static boolean remove(OnlinePeople onlinePeople) {
		if (onlinePeople == null)
			return true;
		boolean state = false;
		int id = onlinePeople.getId();
		int index = peoplesIndex[id];
		peoplesIndex[id] = 0;
		for (int i = 0; i < MAX_PEOPLES; i++) {
			if (index < peoplesIndex[i])
				peoplesIndex[i]--;
		}
		onlinePeoples.remove(onlinePeople);
		--online_people;
		if (online_people == 0) {
			stop_game();
			state = true;
		}
		try {
			ServerStart.OutStreamAll(id + ":0102");
		} catch (IOException e) {
			System.out.println("OnlinePeopleController.remove()");
		}
		return state;
	}

	/**
	 * 玩家返回选择关卡界面
	 *
	 * @param onlinePeople
	 */
	public synchronized static void stop_people(OnlinePeople onlinePeople) {
		if (online_people - 1 == 0) {
			stop_game();
		}
		onlinePeople.setState(0);
		onlinePeople.setLeave(0);
		OutStreamOne(online_people, "0:0404");
		ServerStart.OutStreamExceptOne(onlinePeople.getId(), onlinePeople.getId() + ":0102");
	}

	/**
	 * 判断所有玩家是否准备的一个关卡数
	 *
	 * @return
	 */
	private synchronized static int getGameLevel() {
		int leave = -1;
		for (OnlinePeople onlinePeople : onlinePeoples) {
			int getLevel = onlinePeople.getLeave();
			if (getLevel > 0) {
				if (leave == -1)
					leave = getLevel;
				else {
					if (leave == getLevel)
						continue;
					else
						return -1;
				}
			}
		}
		return leave;
	}

	/**
	 * 输出个人信息给个人
	 *
	 * @param Id
	 * @param info
	 */
	public static void OutStreamOne(int Id, String info) {
		try {
			onlinePeoples.get(peoplesIndex[Id]).getOwnPeople().getDos().writeUTF(info);
		} catch (IOException e) {
			System.out.println("OnlinePeopleController.OutStreamOne()");
		}
	}

	/**
	 * 怪物因为怒气追踪玩家所需要坐标信息
	 *
	 * @param Id
	 * @return
	 */
	public synchronized static int[] people_xy(int Id) {
		int[] a = new int[2];
		OwnPeople own = onlinePeoples.get(peoplesIndex[Id]).getOwnPeople();
		a[0] = own.getX();
		a[1] = own.getY();
		return a;
	}

	/**
	 * 能否登陆
	 *
	 * @param Id
	 * @return
	 */
	public synchronized static boolean canLogin(int Id) {
		if (onlinePeoples.size() == 0)
			return true;
		for (OnlinePeople people : onlinePeoples) {
			if (people.getId() == Id)
				return false;
		}
		return true;
	}

	/**
	 * 输出队友信息给一个用户
	 *
	 * @param Id
	 * @throws IOException
	 */
	private synchronized static void dos_TeamsToOne(int Id) throws IOException {
		int size = online_people;
		boolean TF = false;
		StringBuffer string = new StringBuffer((size - 1) + ":0202");
		for (OnlinePeople one : onlinePeoples) {
			if (one.getId() != Id) {
				TF = true;
				string.append(one.getOwnPeople().getString());
			}
		}
		if (TF)
			OutStreamOne(Id, string.toString());
	}

	/**
	 * 改变玩家坐标信息
	 *
	 * @param Id
	 * @param type
	 * @param value
	 */
	public synchronized static void change_people(int Id, int type, int value) {

		switch (type) {
		case 0:
			onlinePeoples.get(peoplesIndex[Id]).getOwnPeople().setX(value);
			break;
		case 1:
			onlinePeoples.get(peoplesIndex[Id]).getOwnPeople().setY(value);
			break;
		default:
			break;
		}
	}

	/**
	 * 停止游戏主流程
	 */
	private static void stop_game() {
		if (GameController.Enemys != null) {
			for (EnemyPeople one : GameController.Enemys) {
				one.getThread().interrupt();
			}
		}
		ServerStart.GameState = true;
		GameController.minleave = 0;
	}

}
