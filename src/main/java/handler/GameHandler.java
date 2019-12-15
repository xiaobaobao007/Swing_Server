package handler;

import aop.GameFacade;

/**
 * @author xiaobaobao
 * @date 2019/12/15，0:16
 */
public class GameHandler implements GameFacade {

	@Override
	public void getInfo(String a) {
		System.out.println("增加图书方法。。。" + a);
	}

}
