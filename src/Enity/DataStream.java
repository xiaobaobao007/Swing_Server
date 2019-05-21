package Enity;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class DataStream {

	private int id;
	private String ip;
	private DataInputStream dis;
	private DataOutputStream dos;
	public DataStream(int id, String ip, DataInputStream dis, DataOutputStream dos) {
		super();
		this.id = id;
		this.ip = ip;
		this.dis = dis;
		this.dos = dos;
	}
	public DataStream() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public DataInputStream getDis() {
		return dis;
	}
	public void setDis(DataInputStream dis) {
		this.dis = dis;
	}
	public DataOutputStream getDos() {
		return dos;
	}
	public void setDos(DataOutputStream dos) {
		this.dos = dos;
	}
}
