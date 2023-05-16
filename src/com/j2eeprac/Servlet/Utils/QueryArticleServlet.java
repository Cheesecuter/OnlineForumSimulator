package com.j2eeprac.Servlet.Utils;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.j2eeprac.Dao.I_ArticleDao;
import com.j2eeprac.Entities.Article.Article;
import com.j2eeprac.Utils.Dao;

@SuppressWarnings("serial")
@WebServlet("/QueryArticleServlet")
public class QueryArticleServlet extends HttpServlet {
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Dao dao = new Dao();
		I_ArticleDao articleDao = dao.getArticleDao();
		HttpSession session = request.getSession();
		String keyword = request.getParameter("searchInput");
		if (articleDao.findArticles(keyword) == null) {
			session.setAttribute("searchInput", keyword);
		} else {
			List<Article> articles = articleDao.findArticles(keyword);
			session.setAttribute("searchInput", keyword);
			session.setAttribute("homeArticleList", articles);
		}
		request.getRequestDispatcher("LinkHome").forward(request, response);
		return;
	}
}
