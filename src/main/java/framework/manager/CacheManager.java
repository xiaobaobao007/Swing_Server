package main.java.framework.manager;

import main.java.framework.db.BaseObject;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 缓存数据管理
 *
 * @author xiaobaobao
 * @date 2019/12/14，17:53
 */
public class CacheManager {

	public static Map<Integer, Map<String, BaseObject>> cacheData;

	public CacheManager() {
		cacheData = new ConcurrentHashMap<>();
	}
}
