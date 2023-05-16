package com.j2eeprac.Servlet.Utils;

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
@WebServlet("/UserRegister")
public class UserRegister extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Dao dao = new Dao();
		I_UserDao userDao = dao.getUserDao();
		HttpSession session = request.getSession();
		User user;

		String userUID = "";
		String userName = request.getParameter("userProfileIptUname");
		String userKey = request.getParameter("userProfileIptUkey");
		String userSex = "";
		int userAge = 0;
		try {
			userSex = request.getParameter("userProfileIptUsex");
			userAge = Integer.parseInt(request.getParameter("userProfileIptUage"));
		} catch (Exception e) {
		}
		int userAuthority = 0;
		UserManager userManager = new UserManager();
		user = userManager.registerUser(userUID, userKey, userName, userSex, userAge, userAuthority);
		userDao.insert(user);

		dao.sessionCommit();
		session.setAttribute("loginUserName", userName);
		session.setAttribute("msg", "");
		session.setAttribute("searchInput", "");
		session.setAttribute("adminFlag", "0");
		request.getRequestDispatcher("LinkHome").forward(request, response);
		return;
	}
}
