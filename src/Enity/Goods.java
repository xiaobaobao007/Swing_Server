package Enity;

import Controller.GameController;

public class Goods {

	private String name;
	private int leave;
	private int type;
	private int id;
	private int x;
	private int y;
	private String note;
	private int money;
	private int power;// 力量
	private int magic;// 魔力
	private int skill;// 技巧
	private int speed;// 速度
	private int physical;// 体质
	private int armor;// 护甲
	private int resistance;// 抗性
	private int effect;
	private int effect_leave;
	int state = 1;

	public Goods() {
		super();
	}

	public Goods(String name, int leave, int type, int id, int x, int y, int money, String note, int power, int magic,
			int skill, int speed, int physical, int armor, int resistance, int effect, int effect_leave) {
		super();
		this.name = name;
		this.leave = leave;
		this.type = type;
		this.id = id;
		this.x = x;
		this.y = y;
		this.money = money;
		this.note = note;
		this.power = power;
		this.magic = magic;
		this.skill = skill;
		this.speed = speed;
		this.physical = physical;
		this.armor = armor;
		this.resistance = resistance;
		this.effect = effect;
		this.effect_leave = effect_leave;
		GameController.add_good_leave(leave, type, id);
	}

	public String getString() {
		return ";" + name + ":" + leave + ":" + type + ":" + id + ":" + money + ":" + note + ":" + power + ":" + magic
				+ ":" + skill + ":" + speed + ":" + physical + ":" + armor + ":" + resistance + ":" + effect + ":"
				+ effect_leave;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLeave() {
		return leave;
	}

	public void setLeave(int leave) {
		this.leave = leave;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}


	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}
