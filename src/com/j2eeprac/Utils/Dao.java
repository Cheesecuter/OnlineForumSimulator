package com.j2eeprac.Utils;

import java.io.IOException;
import org.apache.ibatis.session.SqlSession;
import com.j2eeprac.Dao.I_ArticleDao;
import com.j2eeprac.Dao.I_UserDao;

public class Dao {

	private I_UserDao userDao;
	private I_ArticleDao articleDao;
	private Session sessionFactory;
	private SqlSession session;

	public Dao() throws IOException {
		sessionFactory = new Session();
		session = sessionFactory.getSession();
		userDao = session.getMapper(I_UserDao.class);
		articleDao = session.getMapper(I_ArticleDao.class);
	}

	public I_UserDao getUserDao() {
		return this.userDao;
	}

	public I_ArticleDao getArticleDao() {
		return this.articleDao;
	}

	public void sessionCommit() {
		session.commit();
	}

	public void sessionClose() {
		session.close();
	}
}
