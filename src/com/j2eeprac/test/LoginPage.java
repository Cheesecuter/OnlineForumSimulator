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
		String p_usex = "男";
		int p_uage = 21;
		int p_authority = 1;
		UserManager userRegister = new UserManager();
		String uKey = "123";
		user = userRegister.registerUser(p_uid, uKey, p_uname, p_usex, p_uage, p_authority);
		//user.setUserInfo(p_uid, p_uname, p_usex, p_uage, p_authority);

		/* database operation */
		userDao.insert(user);
//
//		System.out.println("用户表中共有" + userDao.countAll() + "条信息");
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
//		String p_aname = "html文字大小自动适应宽度,三种字体大小自适应容器宽度的方法";
//		String p_author = "Admin";
//		String p_content = "\r\n"
//				+ "\r\n"
//				+ "618项目中遇到过这样一个问题，移动端各种机型屏幕宽度，各页面中的标题字数是不定的，设计师根据375宽的屏设计的字体大小为20px，在iPhone5中320的屏宽下某些页面由于标题文字长了些就出现了标题折行。所以提出一个问题，可否实现字体大小根据容器宽度自适应，即在小屏幕下由于标题字数多出现折行的话，就减小字体大小，从而达到不折行的效果。\r\n"
//				+ "\r\n"
//				+ "首先是想从CSS方向上解决，然而搜索一番没有找到解决方案，要想实现需要计算协助，css计算目前只有calc()方法，然而calc()不能解决这个问题。所以这里借助js来从三个方向上解决问题。\r\n"
//				+ "\r\n"
//				+ "方向一 动态计算字体大小\r\n"
//				+ "\r\n"
//				+ "通过计算原有font-size下文案所撑起的宽度，除以容器宽度(这里设定容器宽度为屏幕宽度)，得出需要缩放的系数，将系数换算成百分比赋值给font-size即可。\r\n"
//				+ "\r\n"
//				+ "代码如下：\r\n"
//				+ "\r\n"
//				+ "这个标题很长哦可能一行放不下的怎么办呢\r\n"
//				+ "\r\n"
//				+ "var hdWidth = document.body.getBoundingClientRect().width;\r\n"
//				+ "\r\n"
//				+ "var textWidth = document.getElementsByClassName('hd-text')[0].offsetWidth;\r\n"
//				+ "\r\n"
//				+ "var scale = hdWidth / textWidth;\r\n"
//				+ "\r\n"
//				+ "document.getElementsByClassName('hd-text')[0].style.fontSize = scale * 100 + '%';\r\n"
//				+ "\r\n"
//				+ "方向二 缩放容器\r\n"
//				+ "\r\n"
//				+ "该方案类似方案一，只是缩放的不是font-size，而是通过缩放容器。使用transform的scale或zoom，其中transform的scale需要设定transform-origin: 0 0;且只对block元素有效(inline-block也有效)\r\n"
//				+ "\r\n"
//				+ "代码如下：\r\n"
//				+ "\r\n"
//				+ "这个标题很长哦可能一行放不下的怎么办呢\r\n"
//				+ "\r\n"
//				+ "var hdWidth = document.body.getBoundingClientRect().width;\r\n"
//				+ "\r\n"
//				+ "var textWidth = document.getElementsByClassName('hd-text')[0].offsetWidth;\r\n"
//				+ "\r\n"
//				+ "var scale = hdWidth / textWidth;\r\n"
//				+ "\r\n"
//				+ "document.getElementsByClassName('hd-text')[0].style.zoom = scale;\r\n"
//				+ "\r\n"
//				+ "方向三 svg视口和视图框\r\n"
//				+ "\r\n"
//				+ "最近在学习SVG，所以想到了SVG视口和视图框。同样也是需要计算指定font-size下的文字宽度，然后就是应用视口和视图框的原理，将宽度赋值给viewBox。\r\n"
//				+ "\r\n"
//				+ "代码如下：\r\n"
//				+ "\r\n"
//				+ "xmlns:xlink=\"http://www.w3.org/1999/xlink\">\r\n"
//				+ "\r\n"
//				+ "这个标题很长哦可能一行放不下的怎么办呢\r\n"
//				+ "\r\n"
//				+ "var textWidth = document.getElementsByClassName('svg-text')[0].getBoundingClientRect().width;\r\n"
//				+ "\r\n"
//				+ "document.getElementsByClassName('hd3')[0].setAttribute('viewBox', '0 0 ' + textWidth+ ' 50');\r\n"
//				+ "\r\n"
//				+ "\r\n"
//				+ "618项目中遇到过这样一个问题，移动端各种机型屏幕宽度，各页面中的标题字数是不定的，设计师根据375宽的屏设计的字体大小为20px，在iPhone5中320的屏宽下某些页面由于标题文字长了些就出现了标题折行。所以提出一个问题，可否实现字体大小根据容器宽度自适应，即在小屏幕下由于标题字数多出现折行的话，就减小字体大小，从而达到不折行的效果。\r\n"
//				+ "\r\n"
//				+ "首先是想从CSS方向上解决，然而搜索一番没有找到解决方案，要想实现需要计算协助，css计算目前只有calc()方法，然而calc()不能解决这个问题。所以这里借助js来从三个方向上解决问题。\r\n"
//				+ "\r\n"
//				+ "方向一 动态计算字体大小\r\n"
//				+ "\r\n"
//				+ "通过计算原有font-size下文案所撑起的宽度，除以容器宽度(这里设定容器宽度为屏幕宽度)，得出需要缩放的系数，将系数换算成百分比赋值给font-size即可。\r\n"
//				+ "\r\n"
//				+ "代码如下：\r\n"
//				+ "\r\n"
//				+ "这个标题很长哦可能一行放不下的怎么办呢\r\n"
//				+ "\r\n"
//				+ "var hdWidth = document.body.getBoundingClientRect().width;\r\n"
//				+ "\r\n"
//				+ "var textWidth = document.getElementsByClassName('hd-text')[0].offsetWidth;\r\n"
//				+ "\r\n"
//				+ "var scale = hdWidth / textWidth;\r\n"
//				+ "\r\n"
//				+ "document.getElementsByClassName('hd-text')[0].style.fontSize = scale * 100 + '%';\r\n"
//				+ "\r\n"
//				+ "方向二 缩放容器\r\n"
//				+ "\r\n"
//				+ "该方案类似方案一，只是缩放的不是font-size，而是通过缩放容器。使用transform的scale或zoom，其中transform的scale需要设定transform-origin: 0 0;且只对block元素有效(inline-block也有效)\r\n"
//				+ "\r\n"
//				+ "代码如下：\r\n"
//				+ "\r\n"
//				+ "这个标题很长哦可能一行放不下的怎么办呢\r\n"
//				+ "\r\n"
//				+ "var hdWidth = document.body.getBoundingClientRect().width;\r\n"
//				+ "\r\n"
//				+ "var textWidth = document.getElementsByClassName('hd-text')[0].offsetWidth;\r\n"
//				+ "\r\n"
//				+ "var scale = hdWidth / textWidth;\r\n"
//				+ "\r\n"
//				+ "document.getElementsByClassName('hd-text')[0].style.zoom = scale;\r\n"
//				+ "\r\n"
//				+ "方向三 svg视口和视图框\r\n"
//				+ "\r\n"
//				+ "最近在学习SVG，所以想到了SVG视口和视图框。同样也是需要计算指定font-size下的文字宽度，然后就是应用视口和视图框的原理，将宽度赋值给viewBox。\r\n"
//				+ "\r\n"
//				+ "代码如下：\r\n"
//				+ "\r\n"
//				+ "xmlns:xlink=\"http://www.w3.org/1999/xlink\">\r\n"
//				+ "\r\n"
//				+ "这个标题很长哦可能一行放不下的怎么办呢\r\n"
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
		//System.out.println("作品表中共有" + articleDao.countAll() + "条信息");
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
