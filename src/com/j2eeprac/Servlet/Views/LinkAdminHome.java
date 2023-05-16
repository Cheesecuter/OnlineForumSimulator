package com.j2eeprac.Servlet.Views;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/LinkAdminHome")
public class LinkAdminHome extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String reLogged = (String) session.getAttribute("reLogged");

		if (reLogged != null) {
			if (reLogged.equals("true")) {
				session.setAttribute("userProfile", null);
				session.setAttribute("userReleases", null);
				session.setAttribute("userList", null);
				session.setAttribute("articleList", null);
				session.setAttribute("reLogged", "false");
			}
		}
		request.getRequestDispatcher("/view/Home-Admin.jsp").forward(request, response);
		return;
	}
}
