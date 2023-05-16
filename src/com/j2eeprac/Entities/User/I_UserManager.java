package com.j2eeprac.Entities.User;

public interface I_UserManager {
	public User registerUser(String uid, String uKey, String uName, String uSex, int uAge, int authority);
	public void deleteUser(String uid);
}
