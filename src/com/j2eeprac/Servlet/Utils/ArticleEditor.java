package com.j2eeprac.Servlet.Utils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.j2eeprac.Dao.I_ArticleDao;
import com.j2eeprac.Dao.I_UserDao;
import com.j2eeprac.Entities.Article.Article;
import com.j2eeprac.Entities.Article.ArticleReleaser;
import com.j2eeprac.Entities.Article.ArticleReleaserProxy;
import com.j2eeprac.Entities.User.User;
import com.j2eeprac.Utils.Dao;

@SuppressWarnings("serial")
@WebServlet("/ArticleEditor")
public class ArticleEditor extends HttpServlet {
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Dao dao = new Dao();
		I_ArticleDao articleDao = dao.getArticleDao();
		I_UserDao userDao = dao.getUserDao();
		HttpSession session = request.getSession();
		ArticleReleaser articleReleaser = new ArticleReleaser();
		ArticleReleaserProxy articleReleaserProxy = new ArticleReleaserProxy(articleReleaser);

		String userName = (String) session.getAttribute("loginUserName");
		String msg = (String) session.getAttribute("msg");
		String keyword = (String) session.getAttribute("searchInput");

		int deleteFlag = Integer.parseInt(request.getParameter("articleEditorDeleteFlag"));
		String releaseFlag = (String) request.getParameter("releaseFlag");

		String articleAID = (String) session.getAttribute("articleAID");
		String articleTitle = (String) request.getParameter("articleTitle");
		String articleAuthor = (String) session.getAttribute("articleAuthor");
		String content = (String) request.getParameter("articleContent");

		User user = userDao.findByUserName(userName);
		user.setArticleReleaser(articleReleaserProxy);
		user.getArticleReleaser().releaseArticle(articleAID, articleTitle, articleAuthor, content);
		Article article = user.getArticleReleaser().getArticle();

		if (deleteFlag == 0) {
			if (releaseFlag.equals("true")) {
				// System.out.println("insert");
				articleDao.insert(article);
			} else {
				// System.out.println("update");
				articleDao.update(article);
			}
			dao.sessionCommit();
			session.setAttribute("userAuthority", user.getAuthority());
			session.setAttribute("loginUserName", userName);
			session.setAttribute("msg", msg);
			session.setAttribute("searchInput", keyword);
			session.setAttribute("articleTitle", article.getAname());
			session.setAttribute("articleAuthor", article.getAuthor());
			session.setAttribute("articleContent", article.getContent());
			request.getRequestDispatcher("LinkArticlePage").forward(request, response);
		} else {
			articleDao.delete(articleAID);
			dao.sessionCommit();
			session.setAttribute("userAuthority", user.getAuthority());
			session.setAttribute("loginUserName", userName);
			session.setAttribute("msg", msg);
			session.setAttribute("searchInput", keyword);
			request.getRequestDispatcher("LinkHome").forward(request, response);
		}
		return;
	}
}
