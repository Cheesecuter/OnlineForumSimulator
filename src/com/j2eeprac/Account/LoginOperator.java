package com.j2eeprac.Account;

import com.j2eeprac.Dao.I_UserDao;

public class LoginOperator {
	public Boolean login(String uName, String uKey, I_UserDao dao) {
		if (dao.findByUserName(uName) == null) {
			System.out.println("�û�������");
			return false;
		} else {
			String password = dao.findByUserName(uName).getUkey();
			if (uKey.equals(password)) {
				System.out.println("��½�ɹ�");
				return true;
			} else {
				System.out.println("�������");
				return false;
			}
		}
	}
}
