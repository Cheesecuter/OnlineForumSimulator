package com.j2eeprac.Utils;

import java.io.IOException;
import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Session {
	private SqlSession session;

	public Session() throws IOException {
		String resource = "com/j2eeprac/Utils/MyBatis-Configuration.xml";
		Reader reader = Resources.getResourceAsReader(resource);
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(reader);
		session = factory.openSession();
	}

	public SqlSession getSession() {
		return session;
	}

	public void clearSession() {
		session.clearCache();
	}

	public void closeSession() {
		session.close();
	}
}
