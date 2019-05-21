package GameStart;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Controller.GameController;
import Enity.Attribute;
import Mysql_operate.UserDaoImp;

public class GameStart {
    static Thread thread;
    private static GameController gameContrller;
    public static List<Attribute> attributes;
    static String game_leave;

    GameStart(int leave) {
        gameContrller = new GameController(leave);
        thread = new Thread(gameContrller);
    }

    static void PrepareGame() {
        try {
            UserDaoImp userDaoImp = new UserDaoImp();
            for (int i = 0; i < 5; i++) {
                GameController.goods_leave.add(new StringBuffer("0"));
            }
            attributes = new ArrayList<>();
            attributes.add(userDaoImp.monster_leave(1));
            attributes.add(userDaoImp.monster_leave(2));
            attributes.add(userDaoImp.monster_leave(3));
            userDaoImp.AllGoods();
            game_leave = userDaoImp.Game_leave();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}