package main.java.framework.manager;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class MybatisManager {

	private static String CONFIG_FILE = "config/mybatis/mybatis-config.xml";

	private static SqlSessionFactory factory = null;

	private static ThreadLocal<SqlSession> localSession = new ThreadLocal<>();

	public static void init() throws IOException {
		init(CONFIG_FILE);
	}

	public static void init(String fileName) throws IOException {
		Reader reader = Resources.getResourceAsReader(fileName);
		factory = new SqlSessionFactoryBuilder().build(reader);
	}

	public static SqlSession getSession() {
		SqlSession session = localSession.get();
		if (session == null) {
			session = factory.openSession(ExecutorType.BATCH, false);
			localSession.set(session);
		}
		return session;
	}

	public static SqlSession getSession(boolean autoCommit) {
		SqlSession session = localSession.get();
		if (session == null) {
			session = factory.openSession(true);
			localSession.set(session);
		}
		return session;
	}

	public static void commit(boolean close) {
		SqlSession session = localSession.get();
		if (session != null) {
			session.commit();
			if (close) {
				closeAll();
			}
		}
	}

	public static void closeAll() {
		if (localSession.get() != null) {
			try {
				localSession.get().close();
				localSession.remove();
			} catch (Exception e) {
				GameLogManager.logger.error("localSession关闭异常", e);
			}
		}
	}

}
