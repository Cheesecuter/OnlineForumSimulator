package com.j2eeprac.Servlet.Utils;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.j2eeprac.Account.LoginOperator;
import com.j2eeprac.Dao.I_ArticleDao;
import com.j2eeprac.Dao.I_UserDao;
import com.j2eeprac.Entities.Article.Article;
import com.j2eeprac.Utils.Dao;
import com.j2eeprac.Utils.JLogger;

@SuppressWarnings("serial")
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		JLogger logger = new JLogger();
		LoginOperator operator = (LoginOperator) logger.bind(new LoginOperator());

		Dao dao = new Dao();
		I_UserDao userDao = dao.getUserDao();
		I_ArticleDao articleDao = dao.getArticleDao();
		HttpSession session = request.getSession();

		String userName = request.getParameter("userName");
		String userKey = request.getParameter("userKey");
		String searchInput = request.getParameter("searchInput");
		List<Article> homeArticleList = articleDao.selectAll();
		session.setAttribute("homeArticleList", homeArticleList);
		session.setAttribute("sessionSearchInput", searchInput);
		if (userName == null || "".equals(userName.trim())) {
			session.setAttribute("loginUserName", "未登录");
			session.setAttribute("msg", "用户名不能为空");
			session.setAttribute("searchInput", searchInput);
			session.setAttribute("userAuthority", 0);
			request.getRequestDispatcher("LinkHome").forward(request, response);
			return;
		}
		if (userKey == null || "".equals(userKey.trim())) {
			session.setAttribute("loginUserName", "未登录");
			session.setAttribute("msg", "密码不能为空");
			session.setAttribute("searchInput", searchInput);
			session.setAttribute("userAuthority", 0);
			request.getRequestDispatcher("LinkHome").forward(request, response);
			return;
		}
		if (!operator.login(userName, userKey, userDao)) {
			session.setAttribute("loginUserName", "未登录");
			session.setAttribute("msg", "用户登陆失败");
			session.setAttribute("searchInput", searchInput);
			session.setAttribute("userAuthority", 0);
			request.getRequestDispatcher("LinkHome").forward(request, response);
			return;
		} else {
			session.setAttribute("loginUserName", userName);
			session.setAttribute("msg", "");
			session.setAttribute("searchInput", searchInput);
			if (userDao.findByUserName(userName).getAuthority() == 1) {
				session.setAttribute("adminFlag", "1");
			} else {
				session.setAttribute("adminFlag", "0");
			}
			session.setAttribute("reLogged", "true");
			request.getRequestDispatcher("LinkHome").forward(request, response);
			return;
		}
	}
}
