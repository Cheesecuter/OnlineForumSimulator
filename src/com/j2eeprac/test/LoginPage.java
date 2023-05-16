package com.j2eeprac.test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.j2eeprac.Account.LoginOperator;
import com.j2eeprac.Dao.I_ArticleDao;
import com.j2eeprac.Dao.I_UserDao;
import com.j2eeprac.Entities.Article.Article;
import com.j2eeprac.Entities.Article.ArticleReleaser;
import com.j2eeprac.Entities.Article.ArticleReleaserProxy;
import com.j2eeprac.Entities.User.User;
import com.j2eeprac.Entities.User.UserManager;
import com.j2eeprac.Utils.Session;

public class LoginPage {

	public static void main(String[] args) throws IOException {
		Session sessionFactory = new Session();
		SqlSession session = sessionFactory.getSession();
		I_UserDao userDao = session.getMapper(I_UserDao.class);
		I_ArticleDao articleDao = session.getMapper(I_ArticleDao.class);

		/* init user */
		User user;
		String p_uid = "10001";
		String p_uname = "Admin2";
		String p_usex = "��";
		int p_uage = 21;
		int p_authority = 1;
		UserManager userRegister = new UserManager();
		String uKey = "123";
		user = userRegister.registerUser(p_uid, uKey, p_uname, p_usex, p_uage, p_authority);
		//user.setUserInfo(p_uid, p_uname, p_usex, p_uage, p_authority);

		/* database operation */
		userDao.insert(user);
//
//		System.out.println("�û����й���" + userDao.countAll() + "����Ϣ");
//		List<User> listU = userDao.selectAll();
//		System.out.println("+----------------------------------------------------------+");
//		System.out.println("|user table                                                |");
//		System.out.println("+-----------+-------------+----------+----------+----------+");
//		System.out.println("|user id    |user name    |user sex  |user age  |authority |");
//		System.out.println("+-----------+-------------+----------+----------+----------+");
//		for (int i = 0; i < listU.size(); i++) {
//			User u = listU.get(i);
//			System.out.printf("|%-11s|%-13s|%-10s|%-10d|%-10d|\n", u.getUID(), u.getUname(), u.getUsex(), u.getUage(),
//					u.getAuthority());
//			System.out.println("+-----------+-------------+----------+----------+----------+");
//		}

		/* init article */
//		String p_aid = "10016";
//		String p_aname = "html���ִ�С�Զ���Ӧ���,���������С����Ӧ������ȵķ���";
//		String p_author = "Admin";
//		String p_content = "\r\n"
//				+ "\r\n"
//				+ "618��Ŀ������������һ�����⣬�ƶ��˸��ֻ�����Ļ��ȣ���ҳ���еı��������ǲ����ģ����ʦ����375�������Ƶ������СΪ20px����iPhone5��320��������ĳЩҳ�����ڱ������ֳ���Щ�ͳ����˱������С��������һ�����⣬�ɷ�ʵ�������С���������������Ӧ������С��Ļ�����ڱ���������������еĻ����ͼ�С�����С���Ӷ��ﵽ�����е�Ч����\r\n"
//				+ "\r\n"
//				+ "���������CSS�����Ͻ����Ȼ������һ��û���ҵ����������Ҫ��ʵ����Ҫ����Э����css����Ŀǰֻ��calc()������Ȼ��calc()���ܽ��������⡣�����������js�������������Ͻ�����⡣\r\n"
//				+ "\r\n"
//				+ "����һ ��̬���������С\r\n"
//				+ "\r\n"
//				+ "ͨ������ԭ��font-size���İ�������Ŀ�ȣ������������(�����趨�������Ϊ��Ļ���)���ó���Ҫ���ŵ�ϵ������ϵ������ɰٷֱȸ�ֵ��font-size���ɡ�\r\n"
//				+ "\r\n"
//				+ "�������£�\r\n"
//				+ "\r\n"
//				+ "�������ܳ�Ŷ����һ�зŲ��µ���ô����\r\n"
//				+ "\r\n"
//				+ "var hdWidth = document.body.getBoundingClientRect().width;\r\n"
//				+ "\r\n"
//				+ "var textWidth = document.getElementsByClassName('hd-text')[0].offsetWidth;\r\n"
//				+ "\r\n"
//				+ "var scale = hdWidth / textWidth;\r\n"
//				+ "\r\n"
//				+ "document.getElementsByClassName('hd-text')[0].style.fontSize = scale * 100 + '%';\r\n"
//				+ "\r\n"
//				+ "����� ��������\r\n"
//				+ "\r\n"
//				+ "�÷������Ʒ���һ��ֻ�����ŵĲ���font-size������ͨ������������ʹ��transform��scale��zoom������transform��scale��Ҫ�趨transform-origin: 0 0;��ֻ��blockԪ����Ч(inline-blockҲ��Ч)\r\n"
//				+ "\r\n"
//				+ "�������£�\r\n"
//				+ "\r\n"
//				+ "�������ܳ�Ŷ����һ�зŲ��µ���ô����\r\n"
//				+ "\r\n"
//				+ "var hdWidth = document.body.getBoundingClientRect().width;\r\n"
//				+ "\r\n"
//				+ "var textWidth = document.getElementsByClassName('hd-text')[0].offsetWidth;\r\n"
//				+ "\r\n"
//				+ "var scale = hdWidth / textWidth;\r\n"
//				+ "\r\n"
//				+ "document.getElementsByClassName('hd-text')[0].style.zoom = scale;\r\n"
//				+ "\r\n"
//				+ "������ svg�ӿں���ͼ��\r\n"
//				+ "\r\n"
//				+ "�����ѧϰSVG�������뵽��SVG�ӿں���ͼ��ͬ��Ҳ����Ҫ����ָ��font-size�µ����ֿ�ȣ�Ȼ�����Ӧ���ӿں���ͼ���ԭ������ȸ�ֵ��viewBox��\r\n"
//				+ "\r\n"
//				+ "�������£�\r\n"
//				+ "\r\n"
//				+ "xmlns:xlink=\"http://www.w3.org/1999/xlink\">\r\n"
//				+ "\r\n"
//				+ "�������ܳ�Ŷ����һ�зŲ��µ���ô����\r\n"
//				+ "\r\n"
//				+ "var textWidth = document.getElementsByClassName('svg-text')[0].getBoundingClientRect().width;\r\n"
//				+ "\r\n"
//				+ "document.getElementsByClassName('hd3')[0].setAttribute('viewBox', '0 0 ' + textWidth+ ' 50');\r\n"
//				+ "\r\n"
//				+ "\r\n"
//				+ "618��Ŀ������������һ�����⣬�ƶ��˸��ֻ�����Ļ��ȣ���ҳ���еı��������ǲ����ģ����ʦ����375�������Ƶ������СΪ20px����iPhone5��320��������ĳЩҳ�����ڱ������ֳ���Щ�ͳ����˱������С��������һ�����⣬�ɷ�ʵ�������С���������������Ӧ������С��Ļ�����ڱ���������������еĻ����ͼ�С�����С���Ӷ��ﵽ�����е�Ч����\r\n"
//				+ "\r\n"
//				+ "���������CSS�����Ͻ����Ȼ������һ��û���ҵ����������Ҫ��ʵ����Ҫ����Э����css����Ŀǰֻ��calc()������Ȼ��calc()���ܽ��������⡣�����������js�������������Ͻ�����⡣\r\n"
//				+ "\r\n"
//				+ "����һ ��̬���������С\r\n"
//				+ "\r\n"
//				+ "ͨ������ԭ��font-size���İ�������Ŀ�ȣ������������(�����趨�������Ϊ��Ļ���)���ó���Ҫ���ŵ�ϵ������ϵ������ɰٷֱȸ�ֵ��font-size���ɡ�\r\n"
//				+ "\r\n"
//				+ "�������£�\r\n"
//				+ "\r\n"
//				+ "�������ܳ�Ŷ����һ�зŲ��µ���ô����\r\n"
//				+ "\r\n"
//				+ "var hdWidth = document.body.getBoundingClientRect().width;\r\n"
//				+ "\r\n"
//				+ "var textWidth = document.getElementsByClassName('hd-text')[0].offsetWidth;\r\n"
//				+ "\r\n"
//				+ "var scale = hdWidth / textWidth;\r\n"
//				+ "\r\n"
//				+ "document.getElementsByClassName('hd-text')[0].style.fontSize = scale * 100 + '%';\r\n"
//				+ "\r\n"
//				+ "����� ��������\r\n"
//				+ "\r\n"
//				+ "�÷������Ʒ���һ��ֻ�����ŵĲ���font-size������ͨ������������ʹ��transform��scale��zoom������transform��scale��Ҫ�趨transform-origin: 0 0;��ֻ��blockԪ����Ч(inline-blockҲ��Ч)\r\n"
//				+ "\r\n"
//				+ "�������£�\r\n"
//				+ "\r\n"
//				+ "�������ܳ�Ŷ����һ�зŲ��µ���ô����\r\n"
//				+ "\r\n"
//				+ "var hdWidth = document.body.getBoundingClientRect().width;\r\n"
//				+ "\r\n"
//				+ "var textWidth = document.getElementsByClassName('hd-text')[0].offsetWidth;\r\n"
//				+ "\r\n"
//				+ "var scale = hdWidth / textWidth;\r\n"
//				+ "\r\n"
//				+ "document.getElementsByClassName('hd-text')[0].style.zoom = scale;\r\n"
//				+ "\r\n"
//				+ "������ svg�ӿں���ͼ��\r\n"
//				+ "\r\n"
//				+ "�����ѧϰSVG�������뵽��SVG�ӿں���ͼ��ͬ��Ҳ����Ҫ����ָ��font-size�µ����ֿ�ȣ�Ȼ�����Ӧ���ӿں���ͼ���ԭ������ȸ�ֵ��viewBox��\r\n"
//				+ "\r\n"
//				+ "�������£�\r\n"
//				+ "\r\n"
//				+ "xmlns:xlink=\"http://www.w3.org/1999/xlink\">\r\n"
//				+ "\r\n"
//				+ "�������ܳ�Ŷ����һ�зŲ��µ���ô����\r\n"
//				+ "\r\n"
//				+ "var textWidth = document.getElementsByClassName('svg-text')[0].getBoundingClientRect().width;\r\n"
//				+ "\r\n"
//				+ "document.getElementsByClassName('hd3')[0].setAttribute('viewBox', '0 0 ' + textWidth+ ' 50');\r\n";
		/* release article */
//		ArticleReleaser articleReleaser = new ArticleReleaser();
//		ArticleReleaserProxy articleReleaserProxy = new ArticleReleaserProxy(articleReleaser);
//		user.setArticleReleaser(articleReleaserProxy);
//		user.getArticleReleaser().releaseArticle(p_aid, p_aname, p_author, p_content);

		/* database operation */
		//Article article = user.getArticleReleaser().getArticle();
		//articleDao.insert(article);
		//System.out.println("��Ʒ���й���" + articleDao.countAll() + "����Ϣ");
//		List<Article> listA = articleDao.selectAll();
//		System.out.println("+------------------------------------+");
//		System.out.println("|article table                       |");
//		System.out.println("+-----------+-------------+----------+");
//		System.out.println("|article id |article name |author    |");
//		System.out.println("+-----------+-------------+----------+");
//		for (int i = 0; i < listA.size(); i++) {
//			Article a = listA.get(i);
//			System.out.printf("|%-11s|%-13s|%-10s|\n", a.getAID(), a.getAname(), a.getAuthor());
//			System.out.println("+-----------+-------------+----------+");
//			System.out.println(a.getContent());
//		}
//		List<Article> listA = articleDao.findArticles("s");
//		System.out.println("+------------------------------------+");
//		System.out.println("|article table                       |");
//		System.out.println("+-----------+-------------+----------+");
//		System.out.println("|article id |article name |author    |");
//		System.out.println("+-----------+-------------+----------+");
//		for (int i = 0; i < listA.size(); i++) {
//			Article a = listA.get(i);
//			System.out.printf("|%-11s|%-13s|%-10s|\n", a.getAID(), a.getAname(), a.getAuthor());
//			System.out.println("+-----------+-------------+----------+");
//			System.out.println(a.getContent());
//		}
		session.commit();
		session.close();
		System.out.println("\nover");
	}
}
