package Enity;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class OwnPeople {

	private int scope = 10;
	private int x;
	private int y;
	private int last_x;
	private int last_y;
	private int speed;
	private int behurt;
	private int behurt_time;
	private int state = -1;
	private int blood;
	private int magic;
	private int people;
	boolean weapon_state = false;
	int sleep_time = 100;
	private GameMap map;
	private int leave;

	private Attribute attribute;
	private DataInputStream dis;
	private DataOutputStream dos;

	OwnPeople() {
		super();
	}

	public OwnPeople(Attribute attribute, DataInputStream dis, DataOutputStream dos) {
		super();
		setAttribute(attribute);
		this.x = attribute.getX();
		this.y = attribute.getY();
		this.last_x = x;
		this.last_y = y;
		this.people = attribute.getId();
		this.dis = dis;
		this.dos = dos;
	}

	public String getString() {
		return ";" + magic + ":" + blood + this.attribute.getTeamString();
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

	public synchronized int getState() {
		return state;
	}

	public synchronized int getBlood() {
		return blood;
	}

	public synchronized void setBlood(int blood) {
		this.blood = blood;
	}

	public synchronized int getPeople() {
		return people;
	}

	public synchronized boolean isWeapon_state() {
		return weapon_state;
	}

	public synchronized void setWeapon_state(boolean weapon_state) {
		this.weapon_state = weapon_state;
	}

	public synchronized void setPeople(int people) {
		this.people = people;
	}

	public synchronized GameMap getMap() {
		return map;
	}

	public synchronized void setMap(GameMap map) {
		this.map = map;
	}

	public int getLeave() {
		return leave;
	}

	public void setLeave(int leave) {
		this.leave = leave;
	}

	public synchronized void people_attack() {
		weapon_state = true;
	}

	public Attribute getAttribute() {
		return attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
		this.blood = attribute.getHP();
	}

	public DataInputStream getDis() {
		return dis;
	}

	public DataOutputStream getDos() {
		return dos;
	}

	public int getScope() {
		return scope;
	}

	public void setScope(int scope) {
		this.scope = scope;
	}

}