package com.j2eeprac.Servlet.Utils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.j2eeprac.Dao.I_UserDao;
import com.j2eeprac.Entities.Article.ArticleReleaser;
import com.j2eeprac.Entities.Article.ArticleReleaserProxy;
import com.j2eeprac.Entities.User.User;
import com.j2eeprac.Utils.Dao;

@SuppressWarnings("serial")
@WebServlet("/UserArticleReleaser")
public class UserArticleReleaser extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Dao dao = new Dao();
		I_UserDao userDao = dao.getUserDao();
		HttpSession session = request.getSession();

		int adminFlag = 0;
		String userProfileName = request.getParameter("userProfileName");

		if (userProfileName == null || userProfileName.equals("Î´µÇÂ¼")) {
			session.setAttribute("userProfile", null);
			session.setAttribute("userReleases", null);
			session.setAttribute("userList", null);
			session.setAttribute("articleList", null);
			request.getRequestDispatcher("LinkUserHome").forward(request, response);
		} else {
			String userAuthorName = userProfileName;
			User user = userDao.findByUserName(userAuthorName);

			ArticleReleaser articleReleaser = new ArticleReleaser();
			ArticleReleaserProxy articleReleaserProxy = new ArticleReleaserProxy(articleReleaser);
			user.setArticleReleaser(articleReleaserProxy);
			String tempAID = Integer.toString((int) (10001 + Math.random() * (99999 - 10001 + 1)));

			session.setAttribute("releaseFlag", "true");
			session.setAttribute("userAuthority", user.getAuthority());
			session.setAttribute("articleAID", tempAID);
			session.setAttribute("articleTitle", "");
			session.setAttribute("articleAuthor", userAuthorName);
			session.setAttribute("articleContent", "");
			adminFlag = userDao.findByUserName(request.getParameter("userProfileName")).getAuthority();

			if (adminFlag == 0) {
				request.getRequestDispatcher("LinkArticleEditor").forward(request, response);
			} else {
				request.getRequestDispatcher("LinkArticleEditor").forward(request, response);
			}
		}
		return;
	}
}
