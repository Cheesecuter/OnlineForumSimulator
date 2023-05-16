package com.j2eeprac.test;

import java.io.IOException;
import com.j2eeprac.Account.LoginOperator;
import com.j2eeprac.Dao.I_UserDao;
import com.j2eeprac.Entities.User.User;
import com.j2eeprac.Entities.User.UserManager;
import com.j2eeprac.Utils.Dao;

public class LoginTest {
	public static void main(String[] args) throws IOException {
		Dao dao = new Dao();
		I_UserDao userDao = dao.getUserDao();
		String p_uid = "10001";
		String uKey = "1pw";
		LoginOperator loginOperator = new LoginOperator();
		loginOperator.login(p_uid, uKey, userDao);
	}
}
