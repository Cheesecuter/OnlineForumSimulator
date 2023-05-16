package com.j2eeprac.test;

import java.io.IOException;

import com.j2eeprac.Account.LoginOperator;
import com.j2eeprac.Dao.I_UserDao;
import com.j2eeprac.Entities.User.User;
import com.j2eeprac.Entities.User.UserManager;
import com.j2eeprac.Utils.Dao;

public class CommentTest {
	public static void main(String[] args) throws IOException {
		String p_uid = "10001";
		String uKey = "1pw";
		Dao dao = new Dao();
		UserManager userManager = new UserManager();
		User user1;
		user1 = userManager.registerUser(p_uid, uKey, "Admin", "male", 21, 1);
		I_UserDao userDao = dao.getUserDao();
		LoginOperator loginOperator = new LoginOperator();
		loginOperator.login(p_uid, uKey, userDao);
	}
}
