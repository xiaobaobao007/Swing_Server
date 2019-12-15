package handler;

import aop.GameFacade;
import aop.GameFacadeProxy;
import org.testng.annotations.Test;

/**
 * @author xiaobaobao
 * @date 2019/12/14，23:57
 */
public class test {

	@Test
	public void test() {
		GameHandler gameHandler = new GameHandler();
		GameFacadeProxy proxy = new GameFacadeProxy();
		GameFacade bookfacade = (GameFacade) proxy.bind(gameHandler);
		bookfacade.getInfo("11");
	}
}
