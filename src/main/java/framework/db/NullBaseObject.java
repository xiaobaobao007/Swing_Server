package main.java.framework.db;

/**
 * @author xiaobaobao
 * @date 2019/12/14，21:13
 */
public class NullBaseObject extends BaseObject {

	private String key;

	public NullBaseObject(int userId, String key) {
		this.userId = userId;
		this.key = key;
	}

	@Override
	public String getBaseObjectKey() {
		return key;
	}
}
