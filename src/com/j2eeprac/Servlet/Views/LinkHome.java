package com.j2eeprac.Servlet.Views;

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
@WebServlet("/LinkHome")
public class LinkHome extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Dao dao = new Dao();
		I_ArticleDao articleDao = dao.getArticleDao();
		HttpSession session = request.getSession();

		List<Article> homeArticleList = articleDao.selectAll();
		session.setAttribute("homeArticleList", homeArticleList);
		request.getRequestDispatcher("/view/Home.jsp").forward(request, response);
		return;
	}
}
