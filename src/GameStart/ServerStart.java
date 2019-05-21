package GameStart;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.rmi.ConnectException;
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

    ServerStart(Socket socket) {
        try {
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            Connect connect = new Connect(dis, dos,socket);
            Thread thread = new Thread(connect);
            thread.start();

        } catch (IOException e) {
            System.out.println("456");
        }
    }

    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception {
        try {
            ServerSocket ss = new ServerSocket(80);// �˿ں�
            System.out.println("���������������ȴ��û�����");
            GameStart.PrepareGame();
            while (true) {
                Socket socket = ss.accept();
                new ServerStart(socket);
            }
        } catch (IOException e) {
            System.out.println("�˿ڱ�ռ��");
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

        private DataInputStream dis;
        private DataOutputStream dos;
        private Socket socket;
        private int id;
        private OnlinePeople onlinePeople;

        public Connect(DataInputStream dis, DataOutputStream dos,Socket socket) {
            this.dis = dis;
            this.dos = dos;
            this.socket=socket;
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
            boolean TF = true;
            while (TF) {
                try {
                    if ((info = dis.readUTF()) != null) {
                        a = info.split(":");
                        // �������Ӳ���:00
                        if (a[1].compareTo("0100") < 0) {
                            Date date = new Date();
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��/HHʱmm��ss��");
                            String time = sdf.format(date);
                            System.out.print(time + ":�����:" + a[0] + "��");
                            switch (a[1]) {
                                case "0000":
                                    System.out.print("���Ե�½,���:");
                                    this.id = userDaoImp.userLogin(a[2], a[3]);
                                    if (!OnlinePeopleController.canLogin(id)) {
                                        dos.writeUTF("-1");
                                        System.out.println("�ظ���½");
                                    } else {
                                        dos.writeUTF(this.id + "");
                                        if (this.id == 0) {
                                            System.out.println("�˺��������");
                                        } else {
                                            System.out.println("��½�ɹ���ID:" + this.id);
                                        }
                                    }
                                    break;
                                case "0001":
                                    System.out.print("����ע��,���:");
                                    dos.writeUTF(userDaoImp.userRegist(a[2], a[3]) == true ? "1" : "0");
                                    break;
                                case "0002":
                                    System.out.println("����������");
                                    break;
                            }
                        }
                        // ���̲���:01
                        else if (a[1].compareTo("0200") < 0) {
                            if (a[1].equals("0101")) {// �Ѹ��˲�������������
                                OnlinePeopleController.change_people(id, Integer.valueOf(a[2]), Integer.valueOf(a[3]));
                                OutStreamExceptOne(id, info);
                            }
                        }
                        // �˵Ĳ���:02
                        else if (a[1].compareTo("0300") < 0) {
                            switch (a[1]) {
                                case "0201": // ���ظ��˵���Ϸ����
                                    this.onlinePeople = OnlinePeopleController
                                            .add_people(userDaoImp.user_gamer1(a[0], dis, dos));
                                    break;
                                case "0202": // ���ض�������
                                    break;
                                case "0203": // ����������
                                    OutStreamExceptOne(this.id, this.id + ":0403:" + a[2]);
                                    break;
                                case "0204": // ������Ʒ��Ϣ
                                    connectSelf(this.id + ":0204:" + userDaoImp.OwnGoods(this.id));
                                    break;
                                case "0207": // ���׼������ı�״̬
                                    OutStreamExceptOne(this.id, info);
                                    int l = OnlinePeopleController.inInfo(this.id, a[3], a[2]);
                                    if (l != -1) {
                                        leave = l;
                                    }
                                    break;
                                case "0208": // ���״̬�ĸı�
                                    OutStreamExceptOne(this.id, info);
                                    break;
                            }
                        }
                        // ����Ĳ���:03
                        else if (a[1].compareTo("0400") < 0) {
                            switch (a[1]) {
                                case "0301": // �����˺�����
                                    GameController.hurt_enemy(Integer.valueOf(a[0]), Integer.valueOf(a[2]),
                                            Integer.valueOf(a[3]));
                                    break;
                                case "0302": // ���˴�������
                                    GameController.delete_enemy(Integer.valueOf(a[0]), Integer.valueOf(a[2]));
                                    break;
                                case "0303": // ���˿��ƹ���
                                    GameController.stop_enemy(Integer.valueOf(a[0]), Integer.valueOf(a[2]),
                                            Integer.valueOf(a[3]));
                                    break;
                                case "0304": // �����˳���Ϸ�У����ӻ���
                                    OnlinePeopleController.stop_people(onlinePeople);
                                    break;
                                case "0305": // ������ȫ�˳���Ϸ�ͻ���
//								closeOwn();
                                    break;
                            }
                        }
                        // ��Ϸ������:04
                        else if (a[1].compareTo("0500") < 0) {
                            switch (a[1]) {
                                case "0401": // �ؿ�/�ڼ���

                                    break;
                                case "0402": // ������Ϸ��ʼ��ͬ��
                                    if (GameState) {
                                        gameStart = new GameStart(leave);
                                        GameState = false;
                                        OutStreamAll("1:0402");
                                    }
                                    break;
                                case "0403": // ������عؿ���Ϣ
                                    dos.writeUTF(GameStart.game_leave);
                                    break;
                                case "0404": // ��ʼ��Ϸ��ť���
                                    OutStreamExceptOne(this.id, "1:0406");
                                    OnlinePeopleController.putChangePoint();
                                    break;
                            }
                        }
                        // �̳���Ʒ:05
                        else if (a[1].compareTo("0600") < 0) {
                            if (a[1].equals("0501")) {// ������Ʒ����
                                GameController.dos_allgoods(this.id);
                            }
                        }
                        // �����޸�:06
                        else if (a[1].compareTo("0700") < 0) {
                            switch (a[1]) {
                                case "0601": // ����Ա����ָ
                                    userDaoImp.Change(a[2]);
                                    break;
                                case "0602": {// �޸Ľ�Ǯ
                                    String text = "update gamer_coefficient set `money` =" + a[2] + " where id=" + a[0];
                                    userDaoImp.Change(text);
                                    break;
                                }
                                case "0603": {// �޸ĸ�����Ʒ��Ϣ
                                    String text = "update own_goods set goods='" + a[2] + "' where id=" + a[0];
                                    userDaoImp.Change(text);
                                    break;
                                }
                                case "0604": {// �޸Ĵ�����
                                    String text = "update gamer_coefficient set `leave` =" + a[2] + " where id=" + a[0];
                                    userDaoImp.Change(text);
                                    break;
                                }
                                case "0605": {// �޸��������
                                    String text = "update gamer_coefficient set `x` =" + a[2] + "`y` =" + a[3] + " where id=" + a[0];
                                    userDaoImp.Change(text);
                                    break;
                                }
                            }
                        }
                    }
                } catch (SocketException e) {
                    closeOwn();
                    System.out.printf("�����:%d���Ͽ�:%s\n", this.id, info);
                    break;
                } catch (IOException e) {
                    System.err.printf("��������:%s��\n", info);
                } catch (Exception e) {
                    closeOwn();
                    System.out.printf("Running Time Error����������߼�����%s\n", info);
                    e.printStackTrace();
                    break;
                }
            }
        }

        public void closeOwn() {
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��/HHʱmm��ss��");
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