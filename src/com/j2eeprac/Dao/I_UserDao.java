package com.j2eeprac.Dao;

import java.util.List;
import com.j2eeprac.Entities.User.User;

public interface I_UserDao {
	public int insert(User user);

	public int delete(String UID);

	public int update(User user);
	
	public int updateUserInfo(User user);

	public int countAll();
	
	public List<User> selectAll();

	public User findByUserID(String UID);

	public User findByUserName(String uName);
}
