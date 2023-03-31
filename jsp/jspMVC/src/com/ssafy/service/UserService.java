package com.ssafy.service;

import java.util.List;

import com.ssafy.dto.User;

public interface UserService {
	public List<User> getUserList();
	public User findUserById(String userId);
	public boolean modifyUserById(String userId, User user);
	public boolean deleteUserById(String userId);
	public Boolean joinUser(User user);
}
