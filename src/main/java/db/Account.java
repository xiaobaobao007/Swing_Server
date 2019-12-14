package main.java.db;

import main.java.framework.db.BaseObject;

import java.util.Date;

/**
 * 账户信息
 *
 * @author xiaobaobao
 * @date 2019/12/14，16:53
 */
public class Account extends BaseObject {

	public static String PREFIX = "Account_";

	private String account;
	private String password;
	private String email;
	private Date createTime;
	private String registIp;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRegistIp() {
		return registIp;
	}

	public void setRegistIp(String registIp) {
		this.registIp = registIp;
	}

	@Override
	public String getBaseObjectKey() {
		return PREFIX + userId;
	}
}
