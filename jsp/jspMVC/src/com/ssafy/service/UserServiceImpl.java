package com.ssafy.service;

import java.util.List;

import com.ssafy.dao.UserDAO;
import com.ssafy.dao.UserDAOImpl;
import com.ssafy.dto.User;

public class UserServiceImpl implements UserService{
	UserDAO userDAO = UserDAOImpl.getInstance();
	private static UserService instance = new UserServiceImpl();
	private UserServiceImpl() {
		super();
	}
	public static UserService getInstance() {
		return instance;
	}
	@Override
	public List<User> getUserList() {
		List<User> userList = userDAO.selectUserList();
		return userList;
	}
	@Override
	public User findUserById(String userId) {
		// TODO Auto-generated method stub
		User user = userDAO.selectUserById(userId);
		return user;
	}
	@Override
	public boolean modifyUserById(String userId, User user) {
		//
		boolean isSuccessUpdate = userDAO.updateUserById(userId, user);
		return isSuccessUpdate;
	}
	@Override
	public boolean deleteUserById(String userId) {
		// TODO Auto-generated method stub
		boolean isSuccessDelete = userDAO.deleteUserById(userId);
		return isSuccessDelete;
	}
	@Override
	public Boolean joinUser(User user) {
		// TODO Auto-generated method stub
		boolean isSuccessInsert = userDAO.insertUser(user);
		return isSuccessInsert;
	}


}
