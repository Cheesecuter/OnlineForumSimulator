package com.j2eeprac.Servlet.Home.Admin;

import java.io.IOException;
import java.util.List;

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
@WebServlet("/AdminUserEditor")
public class AdminUserEditor extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Dao dao = new Dao();
		I_UserDao userDao = dao.getUserDao();
		HttpSession session = request.getSession();

		String userUID = request.getParameter("userEditedUID");
		int deleteFlag = Integer.parseInt(request.getParameter("userEditorDeleteFlag"));

		if (deleteFlag == 0) {
			String userName = request.getParameter("userEditedName");
			String userSex = request.getParameter("userEditedSex");
			int userAge = Integer.parseInt(request.getParameter("userEditedAge"));
			int userAuthority = Integer.parseInt(request.getParameter("userEditedAuthority"));
			User user;
			UserManager userManager = new UserManager();
			user = userManager.registerUser(userUID, "", userName, userSex, userAge, userAuthority);
			System.out.println(userUID + " " + userName + " " + userSex + " " + userAge + " " + userAuthority);
			userDao.updateUserInfo(user);
		} else {
			userDao.delete(userUID);
		}

		dao.sessionCommit();
		List<User> userList = userDao.selectAll();

		session.setAttribute("userProfile", null);
		session.setAttribute("userReleases", null);
		session.setAttribute("userList", userList);
		session.setAttribute("articleList", null);
		request.getRequestDispatcher("LinkAdminHome").forward(request, response);
		return;
	}
}
