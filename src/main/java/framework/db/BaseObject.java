package main.java.framework.db;

import java.io.Serializable;

/**
 * 数据库和缓存基类
 *
 * @author xiaobaobao
 * @date 2019/12/14，17:08
 */
public abstract class BaseObject implements Cloneable, Serializable {

	private static final long serialVersionUID = 1L;

	protected int userId;

	public abstract String getBaseObjectKey();

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public BaseObject clone() {
		try {
			return (BaseObject) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException("拷贝出错", e);
		}
	}
}
