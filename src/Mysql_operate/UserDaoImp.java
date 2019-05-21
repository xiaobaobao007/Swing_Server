package Mysql_operate;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Controller.GameController;
import Enity.Attribute;
import Enity.Goods;
import Enity.OwnPeople;
import Enity.User;

public class UserDaoImp extends BaseDao implements UserDao {
	/*
	 * @JDBC 2.1 API
	 *
	 * @throws SQLException
	 */
	public static Connection conn;

	public UserDaoImp() {
		super();
		try {
			conn = BaseDao.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	static Date now = new Date();
	static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss ");

	public synchronized List<User> findAllUsers() throws SQLException {
		String sql = "select * from user";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		List<User> userList = new ArrayList<User>();
		while (rs.next()) {
			User user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
			userList.add(user);
		}
		BaseDao.closeAll(null, stmt, rs);
		return userList;
	}

	public static void main(String[] args) throws SQLException, UnknownHostException {
		// System.out.println(new UserDaoImp().findAllUsers());
		// System.out.println(new UserDaoImp().userLogin("1","1"));
		// System.out.println(new UserDaoImp().userRegist("12223","123"));
		// System.out.println(new UserDaoImp().username("12323"));
		System.out.println(new UserDaoImp().Game_leave());
		// System.out.println(new UserDaoImp().monster_leave(1).getString());
		// new UserDaoImp().user_gamer1("1",null,null);
		// System.out.print(new UserDaoImp().OwnGoods(1));
		// String info="update gamer_coefficient set x='100' where id='1'";
		// new UserDaoImp().AllGoods();

	}

	@Override
	public int userLogin(String username, String password) throws SQLException {
		String sql = "select id from user where username=\"" + username + "\" and password=\"" + password + "\"";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		int user_id = 0;
		while (rs.next()) {
			user_id = rs.getInt("id");
		}
		BaseDao.closeAll(null, stmt, rs);
		return user_id;
	}

	@Override
	public boolean userRegist(String username, String password) throws SQLException {
		System.out.print(dateFormat.format(now));
		if (username(username)) {
			System.out.println("false");
			return false;
		}
		String sql = "INSERT INTO user (username,password) VALUES (\"" + username + "\",\"" + password + "\")";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.execute();
		System.out.println("true");
		return true;
	}

	@Override
	public boolean username(String username) throws SQLException {
		String sql = "SELECT * FROM user WHERE username = \"" + username + "\"";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		boolean result = rs.next();
		return result;
	}

	public OwnPeople user_gamer1(String id, DataInputStream dis, DataOutputStream dos) throws SQLException {
		String sql = "SELECT * FROM user AS a INNER JOIN gamer_coefficient AS b ON a.id=b.id WHERE a.id=" + id;
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		OwnPeople ownpeople = null;
		if (rs.next()) {
			Attribute attribute = new Attribute(0,0, rs.getInt("id"), rs.getInt("imgId"),
					rs.getString("name") + "," + rs.getInt("leave"), rs.getInt("money"), rs.getInt("con_phy_attack"),
					rs.getInt("con_mag_attack"), rs.getInt("con_MP"), rs.getInt("con_blow"), rs.getInt("con_shooting"),
					rs.getInt("con_dodge"), rs.getInt("con_speed_action"), rs.getInt("con_HP"),
					rs.getInt("con_phy_defence"), rs.getInt("con_mag_defence"), rs.getInt("power"), rs.getInt("magic"),
					rs.getInt("skill"), rs.getInt("speed"), rs.getInt("physical"), rs.getInt("armor"),
					rs.getInt("resitance"), dos);
			ownpeople = new OwnPeople(attribute, dis, dos);
		}
		BaseDao.closeAll(null, stmt, rs);
		return ownpeople;
	}

	/**
	 * 获取所有怪物数据
	 *
	 * @return
	 * @throws SQLException
	 */
	public String Game_leave() throws SQLException {
		String sql = "SELECT * FROM game_leave";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		StringBuffer string = new StringBuffer();
		int i = 0;
		while (rs.next()) {
			int leave = rs.getInt("leave");
			string.append(
					";" + leave + ":" + rs.getString("tip") + ":" + setMonster(leave - 1, rs.getString("monster")));
			i++;
		}
		BaseDao.closeAll(null, stmt, rs);
		return i + ":0405" + string.toString();
	}

	public String setMonster(int leave, String info) {
		String[] allLevel = info.split(";");
		int size = allLevel[0].charAt(0) - '0';
		GameController.leave_maxs[leave] = size;
		String[] oneLevel = allLevel[1].split(":");
		for (int i = 0; i < size; i++) {
			String[] allMa = oneLevel[i].split(",");
			for (String s1 : allMa) {
				String[] oneMa = s1.split("_");
				int lele = Integer.parseInt(oneMa[0]) - 1;
				int num = Integer.parseInt(oneMa[1]);
				GameController.monster_nums[leave][i][lele] = num;
				GameController.leave_maxsNums[leave][i] += num;
			}
		}
		return allLevel[0];
	}

	/**
	 * 单个怪物
	 *
	 * @param leave
	 * @return
	 * @throws SQLException
	 */
	public Attribute monster_leave(int leave) throws SQLException {
		String sql = "SELECT * FROM monster_leave AS a WHERE a.leave=" + leave;
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		Attribute attribute = null;
		if (rs.next()) {
			attribute = new Attribute(0, 0, 0, rs.getInt("imgId"), rs.getString("name"), rs.getInt("money"),
					rs.getInt("con_phy_attack"), rs.getInt("con_mag_attack"), rs.getInt("con_MP"),
					rs.getInt("con_blow"), rs.getInt("con_shooting"), rs.getInt("con_dodge"),
					rs.getInt("con_speed_action"), rs.getInt("con_HP"), rs.getInt("con_phy_defence"),
					rs.getInt("con_mag_defence"), rs.getInt("power"), rs.getInt("magic"), rs.getInt("skill"),
					rs.getInt("speed"), rs.getInt("physical"), rs.getInt("armor"), rs.getInt("resitance"), null);
		}
		BaseDao.closeAll(null, stmt, rs);
		return attribute;
	}

	/**
	 * 商城物品
	 *
	 * @throws SQLException
	 */
	public void AllGoods() throws SQLException {
		String sql = "SELECT * FROM goods";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		GameController.all_goods = new ArrayList<>();
		while (rs.next()) {
			GameController.all_goods.add(new Goods(rs.getString("name"), rs.getInt("leave"), rs.getInt("type"),
					rs.getInt("id"), -1, -1, rs.getInt("money"), rs.getString("note"), rs.getInt("power"),
					rs.getInt("magic"), rs.getInt("skill"), rs.getInt("speed"), rs.getInt("physical"),
					rs.getInt("armor"), rs.getInt("resistance"), rs.getInt("effect"), rs.getInt("effect_leave")));

		}
		BaseDao.closeAll(null, stmt, rs);
	}

	/**
	 * 查询个人物品
	 *
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public String OwnGoods(int id) throws SQLException {
		String sql = "SELECT * FROM own_goods where id=" + id;
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		String s = null;
		if (rs.next()) {
			s = rs.getString("goods");
		}
		BaseDao.closeAll(null, stmt, rs);
		return s;
	}

	/**
	 * 改变数据库内容
	 *
	 * @param info
	 * @throws SQLException
	 */
	public void Change(String info) throws SQLException {
		String sql = info;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.executeUpdate();
		BaseDao.closeAll(null, stmt, null);
	}

}
