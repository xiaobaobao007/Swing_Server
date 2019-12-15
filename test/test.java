import main.java.dao.AccountDao;
import main.java.framework.manager.MybatisManager;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @author xiaobaobao
 * @date 2019/12/14，17:37
 */
public class test {

	@Before
	public void before() throws IOException {
		MybatisManager.init();
	}

	@Test
	public void demo1() {
		AccountDao.dao.getAccount("123");
	}
}
