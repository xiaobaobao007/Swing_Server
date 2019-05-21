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
	public static Vector<OnlinePeople> onlinePeoples = new Vector<>();// ��Ҽ���
	public static int online_people = 0;// �����������
	private static int[] peoplesIndex = new int[MAX_PEOPLES];// �������id����Index

	/**
	 * �������
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
			OutStreamOne(ownPeople.getPeople(), "1:0201" + ownPeople.getAttribute().getString());// ����ǰ�û�������Ϣ
			dos_TeamsToOne(ownPeople.getPeople());// �Ѷ�����Ϣ����ǰ�û�
			ServerStart.OutStreamExceptOne(ownPeople.getPeople(), "1:0202" + ownPeople.getString());// �ѵ�ǰ�û���Ϣ���͸���������
		} catch (IOException e) {
			e.printStackTrace();
		}
		return onlinePeople;
	}

	/**
	 * �ı����״̬
	 *
	 * @param peopleIndexId
	 * @param state
	 */
	private synchronized static void changeState(int peopleIndexId, int state) {
		onlinePeoples.get(peopleIndexId).setState(state);
	}

	/**
	 * �ı����׼���ؿ�
	 *
	 * @param peopleIndexId
	 * @param level
	 */
	private synchronized static void changeLevel(int peopleIndexId, int level) {
		onlinePeoples.get(peopleIndexId).setLeave(level);
	}

	/**
	 * �Ƿ�ʼ��Ϸ
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
	 * �ı����׼��״̬
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
	 * ������Ƴ�
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
	 * ��ҷ���ѡ��ؿ�����
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
	 * �ж���������Ƿ�׼����һ���ؿ���
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
	 * ���������Ϣ������
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
	 * ������Ϊŭ��׷���������Ҫ������Ϣ
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
	 * �ܷ��½
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
	 * ���������Ϣ��һ���û�
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
	 * �ı����������Ϣ
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
	 * ֹͣ��Ϸ������
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
