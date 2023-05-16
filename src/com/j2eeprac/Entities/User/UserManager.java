package com.j2eeprac.Entities.User;

public class UserManager implements I_UserManager {

	@Override
	public User registerUser(String uid, String uKey, String uName, String uSex, int uAge, int authority) {
		return new User(uid, uKey, uName, uSex, uAge, authority);
	}

	@Override
	public void deleteUser(String uid) {
		
	}

}
