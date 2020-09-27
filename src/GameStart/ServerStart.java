package GameStart;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

import Controller.GameController;
import Controller.OnlinePeopleController;
import Enity.OnlinePeople;
import Mysql_operate.UserDaoImp;

public class ServerStart {
	/**
	 * @param args
	 */
	UserDaoImp userDaoImp = new UserDaoImp();
	static GameStart gameStart;
	static int leave;
	public static boolean GameState = true;
	private final transient boolean TF = true;

	ServerStart(Socket socket) {
		try {
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

			Connect connect = new Connect(dis, dos, socket);
			Thread thread = new Thread(connect);
			thread.start();

		} catch (IOException e) {
			System.out.println("456");
		}
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		try {
			ServerSocket ss = new ServerSocket(8080);// 端口号
			System.out.println("服务器已启动，等待用户连接");
			GameStart.PrepareGame();
			while (true) {
				Socket socket = ss.accept();
				new ServerStart(socket);
			}
		} catch (IOException e) {
			System.out.println("端口被占用");
		}
	}

	public static void OutStreamOne(int Id, String info) {
		OnlinePeopleController.OutStreamOne(Id, info);
	}

	public static void OutStreamExceptOne(int id, String info) {
		for (OnlinePeople own : OnlinePeopleController.onlinePeoples) {
			try {
				if (own.getId() != id)
					own.getOwnPeople().getDos().writeUTF(info);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

//	public static void TEST(int id, String info) {
//		System.out.println("id = [" + id + "], info = [" + info + "]");
//		for (OnlinePeople own : OnlinePeopleController.onlinePeoples) {
//			try {
//				if (own.getId() != id){
//					System.out.println("1:"+own.getId()+":"+own.getOwnPeople().getPeople());
//					own.getOwnPeople().getDos().writeUTF(info);
//				}else{
//					System.out.println("0:"+own.getId()+":"+own.getOwnPeople().getPeople());
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}

	public static synchronized void OutStreamAll(String info) throws IOException {

		for (OnlinePeople own : OnlinePeopleController.onlinePeoples) {
			try {
				own.getOwnPeople().getDos().writeUTF(info);
			} catch (IOException e) {
				if (OnlinePeopleController.online_people == 0) {
					GameStart.thread.interrupt();
				}
			}
		}
	}

	class Connect implements Runnable {

		private final DataInputStream dis;
		private final DataOutputStream dos;
		private final Socket socket;
		private int id;
		private OnlinePeople onlinePeople;

		public Connect(DataInputStream dis, DataOutputStream dos, Socket socket) {
			this.dis = dis;
			this.dos = dos;
			this.socket = socket;
		}

		public void connectSelf(String info) {
			try {
				this.dos.writeUTF(info);
			} catch (IOException e) {
				System.out.println("ServerStart.Connect.connectSelf()");
			}
		}

		@Override
		public void run() {
			String info = null;
			String[] a = null;
			while (TF) {
				try {
					if ((info = dis.readUTF()) != null) {
						a = info.split(":");
						// 服务连接操作:00
						if (a[1].compareTo("0100") < 0) {
							Date date = new Date();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日/HH时mm分ss秒");
							String time = sdf.format(date);
							System.out.print(time + ":《编号:" + a[0] + "》");
							switch (a[1]) {
								case "0000":
									System.out.print("尝试登陆,结果:");
									this.id = userDaoImp.userLogin(a[2], a[3]);
									if (!OnlinePeopleController.canLogin(id)) {
										dos.writeUTF("-1");
										System.out.println("重复登陆");
									} else {
										dos.writeUTF(this.id + "");
										if (this.id == 0) {
											System.out.println("账号密码错误");
										} else {
											System.out.println("登陆成功，ID:" + this.id);
										}
									}
									break;
								case "0001":
									System.out.print("尝试注册,结果:");
									dos.writeUTF(userDaoImp.userRegist(a[2], a[3]) == true ? "1" : "0");
									break;
								case "0002":
									System.out.println("正在连接中");
									break;
							}
						}
						// 键盘操作:01
						else if (a[1].compareTo("0200") < 0) {
							if (a[1].equals("0101")) {// 把个人操作告诉其他人
								OnlinePeopleController.change_people(id, Integer.valueOf(a[2]), Integer.valueOf(a[3]));
								OutStreamExceptOne(id, info);
							}
						}
						// 人的操作:02
						else if (a[1].compareTo("0300") < 0) {
							switch (a[1]) {
								case "0201": // 加载个人的游戏数据
									this.onlinePeople = OnlinePeopleController
																.add_people(userDaoImp.user_gamer1(a[0], dis, dos));
									break;
								case "0202": // 加载队友数据
									break;
								case "0203": // 聊天室内容
									OutStreamExceptOne(this.id, this.id + ":0403:" + a[2]);
									break;
								case "0204": // 个人物品信息
									connectSelf(this.id + ":0204:" + userDaoImp.OwnGoods(this.id));
									break;
								case "0207": // 玩家准备界面改变状态
									OutStreamExceptOne(this.id, info);
									int l = OnlinePeopleController.inInfo(this.id, a[3], a[2]);
									if (l != -1) {
										leave = l;
									}
									break;
								case "0208": // 玩家状态的改变
									OutStreamExceptOne(this.id, info);
									break;
							}
						}
						// 怪物的操作:03
						else if (a[1].compareTo("0400") < 0) {
							switch (a[1]) {
								case "0301": // 个人伤害怪物
									GameController.hurt_enemy(Integer.valueOf(a[0]), Integer.valueOf(a[2]),
											Integer.valueOf(a[3]));
									break;
								case "0302": // 个人打死怪物
									GameController.delete_enemy(Integer.valueOf(a[0]), Integer.valueOf(a[2]));
									break;
								case "0303": // 个人控制怪物
									GameController.stop_enemy(Integer.valueOf(a[0]), Integer.valueOf(a[2]),
											Integer.valueOf(a[3]));
									break;
								case "0304": // 个人退出游戏中，连接还在
									OnlinePeopleController.stop_people(onlinePeople);
									break;
								case "0305": // 个人完全退出游戏客户端
//								closeOwn();
									break;
							}
						}
						// 游戏总设置:04
						else if (a[1].compareTo("0500") < 0) {
							switch (a[1]) {
								case "0401": // 关卡/第几关

									break;
								case "0402": // 请求游戏开始并同意
									if (GameState) {
										gameStart = new GameStart(leave);
										GameState = false;
										OutStreamAll("1:0402");
									}
									break;
								case "0403": // 请求加载关卡信息
									dos.writeUTF(GameStart.game_leave);
									break;
								case "0404": // 开始游戏按钮点击
									OutStreamExceptOne(this.id, "1:0406");
									OnlinePeopleController.putChangePoint();
									break;
							}
						}
						// 商城物品:05
						else if (a[1].compareTo("0600") < 0) {
							if (a[1].equals("0501")) {// 加载商品数据
								GameController.dos_allgoods(this.id);
							}
						}
						// 数据修改:06
						else if (a[1].compareTo("0700") < 0) {
							switch (a[1]) {
								case "0601": // 管理员金手指
									userDaoImp.Change(a[2]);
									break;
								case "0602": {// 修改金钱
									String text = "update gamer_coefficient set `money` =" + a[2] + " where id=" + a[0];
									userDaoImp.Change(text);
									break;
								}
								case "0603": {// 修改个人物品信息
									String text = "update own_goods set goods='" + a[2] + "' where id=" + a[0];
									userDaoImp.Change(text);
									break;
								}
								case "0604": {// 修改闯关数
									String text = "update gamer_coefficient set `leave` =" + a[2] + " where id=" + a[0];
									userDaoImp.Change(text);
									break;
								}
								case "0605": {// 修改玩家坐标
									String text = "update gamer_coefficient set `x` =" + a[2] + "`y` =" + a[3] + " where id=" + a[0];
									userDaoImp.Change(text);
									break;
								}
							}
						}
					}
				} catch (SocketException e) {
					closeOwn();
					System.out.printf("《编号:%d》断开:%s\n", this.id, info);
					break;
				} catch (IOException e) {
					System.err.printf("错误内容:%s。\n", info);
				} catch (Exception e) {
					closeOwn();
					System.out.printf("Running Time Error请检查服务器逻辑错误：%s\n", info);
					e.printStackTrace();
					break;
				}
			}
		}

		public void closeOwn() {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日/HH时mm分ss秒");
			String time = sdf.format(date);
			System.out.print(time + ":");
			try {
				dis.close();
				dos.close();
				socket.close();
				OnlinePeopleController.remove(onlinePeople);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}