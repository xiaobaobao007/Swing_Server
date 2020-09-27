package Controller;

public class StateController implements Runnable {

	private boolean TF = true;

	private int attack = 1;
	private int magic = 1;
	private int move = 1;
	private int vertigo = 0;
	private int frozen = 0;

	private int attack_time = 0;
	private int magic_time = 0;
	private int move_time = 0;
	private int vertigo_time = 0;
	private int frozen_time = 0;

	public StateController() {
		new Thread(this).start();
	}

	public synchronized void setTF() {
		TF = false;
	}

	public synchronized void Stop_Attack(int time) {
		attack = 0;
		attack = time;
	}

	public synchronized void Stop_Magic(int time) {
		magic = 0;
		magic_time = time;
	}

	public synchronized void Stop_Move(int time) {
		move = 0;
		move_time = time;
	}

	public synchronized void Start_Vertigo(int time) {
		vertigo = 1;
		vertigo_time = time;
	}

	public synchronized void Start_Frozen(int time) {
		frozen = 1;
		frozen_time = time;
	}

	public synchronized boolean Attack() {
		return attack == 1 && vertigo == 0 && frozen == 0;
	}

	public synchronized boolean Magic() {
		return magic == 1;
	}

	public synchronized boolean Move() {
		return move == 1 && vertigo == 0 && frozen == 0;
	}

	public synchronized boolean Vertigo() {
		return vertigo == 1;
	}

	public synchronized boolean Frozen() {
		return frozen == 1;
	}

	public synchronized void TF_Attack() {
		if (attack_time > 0) {
			attack_time -= 100;
			if (attack_time <= 0)
				attack = 1;
		}
	}

	public synchronized void TF_Magic() {
		if (magic_time > 0) {
			magic_time -= 100;
			if (magic_time <= 0)
				magic = 1;
		}
	}

	public synchronized void TF_Move() {
		if (move_time > 0) {
			move_time -= 100;
			if (move_time <= 0)
				move = 1;
		}
	}

	public synchronized void TF_Vertigo() {
		if (vertigo_time > 0) {
			vertigo_time -= 100;
			if (vertigo_time <= 0)
				vertigo = 0;
		}
	}

	public synchronized void TF_Frozen() {
		if (frozen_time > 0) {
			frozen_time -= 100;
			if (frozen_time <= 0)
				frozen = 0;
		}
	}

	@Override
	public void run() {
		while (TF) {
			try {
				Thread.sleep(100);
				TF_Attack();
				TF_Magic();
				TF_Move();
				TF_Vertigo();
				TF_Frozen();
			} catch (Exception e) {
				e.getStackTrace();
			}
		}
	}

//	public static void main(String[] args) throws InterruptedException {
//		StateController s=new StateController();
//		System.out.println(s.Move());
//		s.Start_Frozen(300);
//		System.out.println(s.Move());
//		Thread.sleep(400);
//		System.out.println(s.Move());
//		Thread.sleep(300);
//		System.out.println(s.Move());
//	}

}
