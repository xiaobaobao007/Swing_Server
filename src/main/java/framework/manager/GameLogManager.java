package main.java.framework.manager;

import org.apache.log4j.Logger;

/**
 * 日志记录
 *
 * @author xiaobaobao
 * @date 2019/12/14，20:32
 */
public class GameLogManager {

	public static Logger logger;

	public static void init() {
		logger = Logger.getLogger(GameLogManager.class);
	}

	public static void main(String[] args) {
		init();
		logger.debug(" This is debug!!!");
		logger.info(" This is info!!!");
		logger.warn(" This is warn!!!");
		logger.error(" This is error!!!");
		logger.fatal(" This is fatal!!!");
	}

}
