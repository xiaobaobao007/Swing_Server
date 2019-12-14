package main.java.boot;

import main.java.framework.boot.BaseBoot;
import main.java.framework.manager.CacheManager;
import main.java.framework.manager.GameLogManager;
import main.java.framework.manager.MybatisManager;

/**
 * @author xiaobaobao
 * @date 2019/12/14，18:00
 */
public class GSBoot extends BaseBoot {

	private CacheManager cacheManager;

	@Override
	public void init() throws Exception {
		GameLogManager.init();
		cacheManager = new CacheManager();
		MybatisManager.init();
	}
}
