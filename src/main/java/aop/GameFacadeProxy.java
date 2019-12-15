package aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author xiaobaobao
 * @date 2019/12/15，0:16
 */
public class GameFacadeProxy implements InvocationHandler {

	private Object target;

	/**
	 * 绑定业务对象并返回一个代理类
	 */
	public Object bind(Object target) {

		this.target = target;

		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}

	/**
	 * 包装调用方法：进行预处理、调用后处理
	 */
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		Object result;

		System.out.println("预处理操作——————");
		result = method.invoke(target, args);
		System.out.println("调用后处理——————");

		return result;
	}

}