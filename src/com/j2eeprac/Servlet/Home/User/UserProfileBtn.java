package com.j2eeprac.Servlet.Home.User;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.j2eeprac.Dao.I_UserDao;
import com.j2eeprac.Entities.User.User;
import com.j2eeprac.Utils.Dao;

@SuppressWarnings("serial")
@WebServlet("/UserProfileBtn")
public class UserProfileBtn extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Dao dao = new Dao();
		I_UserDao userDao = dao.getUserDao();
		HttpSession session = request.getSession();

		int adminFlag = 0;
		String userProfileName = request.getParameter("userProfileName");
		String reLogged = (String) session.getAttribute("reLogged");

		if (userProfileName == null || userProfileName.equals("Î´µÇÂ¼")) {
			session.setAttribute("userProfile", null);
			session.setAttribute("userReleases", null);
			session.setAttribute("userList", null);
			session.setAttribute("articleList", null);
			session.setAttribute("reLogged", "false");
		} else {
			User user = userDao.findByUserName(userProfileName);
			session.setAttribute("userProfile", user);
			session.setAttribute("userReleases", null);
			session.setAttribute("userList", null);
			session.setAttribute("articleList", null);
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
