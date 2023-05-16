package com.j2eeprac.Entities.User;

public class UserManagerProxy implements I_UserManager{

	private UserManager userManager;
	
	public UserManagerProxy(UserManager userManager) {
		this.userManager=userManager;
	}
	
	@Override
	public User registerUser(String uid, String uKey, String uName, String uSex, int uAge, int authority) {
		System.out.println("registering user");
		userManager.registerUser(uid, uKey, uName, uSex, uAge, authority);
		return null;
	}

	@Override
	public void deleteUser(String uid) {
		System.out.println("deleting user");
		userManager.deleteUser(uid);
	}
	
}
