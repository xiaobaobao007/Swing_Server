package main.java;

import main.java.boot.GSBoot;
import main.java.framework.manager.GameLogManager;

/**
 * 主方法
 *
 * @author xiaobaobao
 * @date 2019/12/14，18:01
 */
public class GameServer {

	public static GSBoot boot;

	public static void main(String[] args) {
		GameServer.boot = new GSBoot();
		try {
			boot.init();
		} catch (Exception e) {
			GameLogManager.logger.error("服务器启动异常", e);
		}
	}

}
