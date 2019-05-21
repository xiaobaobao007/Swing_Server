package Enity;

public class HurtBlood {

	private int x;
	private int y;
	private int blood;
	private int time;
	public HurtBlood() {
		super();
	}
	public HurtBlood(int x,int y,int blood,int time) {
		super();
		this.x=x;
		this.y=y;
		this.blood=blood;
		this.time=time;
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
	public int getBlood() {
		return blood;
	}
	public void setBlood(int blood) {
		this.blood = blood;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	
}
