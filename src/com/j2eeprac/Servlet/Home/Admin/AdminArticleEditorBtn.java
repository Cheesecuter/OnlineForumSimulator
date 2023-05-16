package com.j2eeprac.Servlet.Home.Admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.j2eeprac.Dao.I_ArticleDao;
import com.j2eeprac.Dao.I_UserDao;
import com.j2eeprac.Entities.Article.Article;
import com.j2eeprac.Utils.Dao;

@SuppressWarnings("serial")
@WebServlet("/AdminArticleEditorBtn")
public class AdminArticleEditorBtn extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Dao dao = new Dao();
		I_ArticleDao articleDao = dao.getArticleDao();
		I_UserDao userDao = dao.getUserDao();
		HttpSession session = request.getSession();

		int adminFlag = 0;
		String userProfileName = request.getParameter("userProfileName");

		if (userProfileName == null || userProfileName.equals("Î´µÇÂ¼")) {
			session.setAttribute("userProfile", null);
			session.setAttribute("userReleases", null);
			session.setAttribute("userList", null);
			session.setAttribute("articleList", null);
		} else {
			List<Article> articleList = articleDao.selectAll();
			session.setAttribute("userProfile", null);
			session.setAttribute("userReleases", null);
			session.setAttribute("userList", null);
			session.setAttribute("articleList", articleList);
			adminFlag = userDao.findByUserName(request.getParameter("userProfileName")).getAuthority();
		}

		if (adminFlag == 0) {
			request.getRequestDispatcher("LinkUserHome").forward(request, response);
		} else {
			request.getRequestDispatcher("LinkAdminHome").forward(request, response);
		}
		return;
	}
}
