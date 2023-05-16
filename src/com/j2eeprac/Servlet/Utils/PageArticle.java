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
import com.j2eeprac.Entities.User.User;
import com.j2eeprac.Utils.Dao;

@SuppressWarnings("serial")
@WebServlet("/PageArticle")
public class PageArticle extends HttpServlet {
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Dao dao = new Dao();
		I_UserDao userDao = dao.getUserDao();
		I_ArticleDao articleDao = dao.getArticleDao();
		HttpSession session = request.getSession();

		String userName = (String) session.getAttribute("loginUserName");
		String msg = (String) session.getAttribute("msg");
		String keyword = (String) session.getAttribute("searchInput");
		String aricleAID = request.getParameter("articleAID");

		User user = userDao.findByUserName(userName);
		Article article = articleDao.findByArticleID(aricleAID);

		session.setAttribute("loginUserName", userName);
		session.setAttribute("msg", msg);
		session.setAttribute("searchInput", keyword);

		if (user != null) {
			session.setAttribute("userAuthority", user.getAuthority());
		} else {
			session.setAttribute("userAuthority", 0);
		}
		session.setAttribute("articleAID", article.getAID());
		session.setAttribute("articleTitle", article.getAname());
		session.setAttribute("articleAuthor", article.getAuthor());
		session.setAttribute("articleContent", article.getContent());
		request.getRequestDispatcher("LinkArticlePage").forward(request, response);
		return;
	}
}