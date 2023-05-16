package com.j2eeprac.Account;

import com.j2eeprac.Dao.I_UserDao;

public class LoginOperator {
	public Boolean login(String uName, String uKey, I_UserDao dao) {
		if (dao.findByUserName(uName) == null) {
			System.out.println("用户不存在");
			return false;
		} else {
			String password = dao.findByUserName(uName).getUkey();
			if (uKey.equals(password)) {
				System.out.println("登陆成功");
				return true;
			} else {
				System.out.println("密码错误");
				return false;
			}
		}
	}
}
