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
import com.j2eeprac.Entities.User.UserManager;
import com.j2eeprac.Utils.Dao;

@SuppressWarnings("serial")
@WebServlet("/UserProfileEditor")
public class UserProfileEditor extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Dao dao = new Dao();
		I_UserDao userDao = dao.getUserDao();
		HttpSession session = request.getSession();
		User user;

		String userUID = request.getParameter("userProfileIptUID");
		String userName = request.getParameter("userProfileIptUname");
		String userKey = request.getParameter("userProfileIptUkey");
		String userSex = request.getParameter("userProfileIptUsex");
		int userAge = Integer.parseInt(request.getParameter("userProfileIptUage"));
		int userAuthority = userDao.findByUserID(userUID).getAuthority();
		UserManager userManager = new UserManager();

		user = userManager.registerUser(userUID, userKey, userName, userSex, userAge, userAuthority);
		userDao.update(user);

		dao.sessionCommit();

		session.setAttribute("userProfile", user);
		session.setAttribute("userReleases", null);
		session.setAttribute("userList", null);
		session.setAttribute("articleList", null);
		request.getRequestDispatcher("LinkAdminHome").forward(request, response);
		return;
	}
}
