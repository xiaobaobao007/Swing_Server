package main.java.framework.dao;

import main.java.framework.db.BaseObject;
import main.java.framework.manager.CacheManager;
import main.java.framework.manager.MybatisManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiaobaobao
 * @date 2019/12/14，20:51
 */
public class BaseDao {

	/**
	 * 插入玩家数据
	 */
	public void insert(String sqlId, BaseObject obj) {
		insertCache(obj);
		insertDB(sqlId, obj);
	}

	/**
	 * 更新玩家数据
	 */
	public void update(String sqlId, BaseObject obj) {
		updateCache(obj);
		updateDB(sqlId, obj);
	}

	/**
	 * 删除玩家数据
	 */
	public void delete(int userId, String sqlId, BaseObject obj) {
		deleteCache(obj);
		deleteDB(sqlId, obj);
	}

	/**
	 * 获取缓存数据
	 */
	public BaseObject getCacheObject(int userId, String key) {
		Map<String, BaseObject> map = CacheManager.cacheData.get(userId);
		if (map != null) {
			return map.get(key);
		}
		return null;
	}


	/**
	 * 添加缓存数据
	 */
	public void insertCache(BaseObject obj) {
		Map<String, BaseObject> map = CacheManager.cacheData.computeIfAbsent(obj.getUserId(), k -> new HashMap<>());
		map.put(obj.getBaseObjectKey(), obj);
	}

	/**
	 * 更新缓存数据
	 */
	public void updateCache(BaseObject obj) {
		Map<String, BaseObject> map = CacheManager.cacheData.computeIfAbsent(obj.getUserId(), k -> new HashMap<>());
		map.put(obj.getBaseObjectKey(), obj);
	}

	/**
	 * 删除缓存数据
	 */
	public void deleteCache(BaseObject obj) {
		Map<String, BaseObject> map = CacheManager.cacheData.get(obj.getUserId());
		if (map != null) {
			map.remove(obj.getBaseObjectKey());
		}
	}

	/**
	 * 添加数据到数据库
	 */
	public void insertDB(String sqlId, BaseObject obj) {
		MybatisManager.getSession().insert(sqlId, obj);
	}

	/**
	 * 更新数据到数据库
	 */
	public void updateDB(String sqlId, BaseObject obj) {
		MybatisManager.getSession().update(sqlId, obj);
	}

	/**
	 * 删除数据到数据库
	 */
	public void deleteDB(String sqlId, BaseObject obj) {
		MybatisManager.getSession().delete(sqlId, obj);
	}

	/**
	 * 从db得到数据，单行数据
	 */
	public Object queryForObjectFromDb(String id, Object paramObject) {
		try {
			MybatisManager.getSession().clearCache();
			Object obj = MybatisManager.getSession().selectOne(id, paramObject);
			return obj;
		} catch (Exception e) {
			throw new RuntimeException("queryForObjectFromDb", e);
		}
	}

	/**
	 * 从db得到数据，列表数据
	 */
	public List queryForListFromDb(String id, Object paramObject) {
		try {
			MybatisManager.getSession().clearCache();
			List list = MybatisManager.getSession().selectList(id, paramObject);
			return list;
		} catch (Exception e) {
			throw new RuntimeException("queryForListFromDb", e);
		}
	}
}
