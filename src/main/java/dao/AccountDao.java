package main.java.dao;

import main.java.constant.Constant;
import main.java.db.Account;
import main.java.framework.dao.BaseGSDao;
import main.java.framework.db.BaseObject;
import main.java.framework.db.NullBaseObject;

/**
 * @author xiaobaobao
 * @date 2019/12/14，20:54
 */
public class AccountDao extends BaseGSDao {

	public static AccountDao dao = new AccountDao();

	public Account getAccount(String accountId) {
		BaseObject baseObject = getCacheObject(Constant.CACHE_TYPE_ACCOUNT, Account.PREFIX + accountId);
		if (baseObject == null) {
			baseObject = (Account) queryForObjectFromDb("Account.getAccount", accountId);
			if (baseObject != null) {
				insertCache(baseObject);
			} else {
				insertCache(new NullBaseObject(Constant.CACHE_TYPE_ACCOUNT, Account.PREFIX + accountId));
				return null;
			}
		} else if (baseObject instanceof NullBaseObject) {
			return null;
		}
		return (Account) baseObject;
	}

	public void insertAccount(Account account) {
		super.insert("Account.insertAccount", account);
	}

	public void updateAccount(Account account) {
		super.update("Account.updateAccount", account);
	}
}
