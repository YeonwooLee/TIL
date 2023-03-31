package com.ssafy.dao;

import java.util.List;

import com.ssafy.dto.User;

public interface UserDAO {
	public void crtUserTbl();
	public List<User> selectUserList();
	public User selectUserById(String userId);
	public boolean updateUserById(String userId, User user);
	public boolean deleteUserById(String userId);
	boolean insertUser(User user);
}
