package com.digitalLibraryManagementSystem.service;



import java.util.List;

import com.digitalLibraryManagementSystem.dao.UserDao;
import com.digitalLibraryManagementSystem.entity.User;



public class  UserService {

	UserDao userDao = new UserDao();


	public User checkLogin(String username, String password) {
		return userDao.checkLogin(username, password);
	}

	public boolean emailExists(String email) {
		return userDao.emailExists(email);
	}


	public boolean addUser(User user) {
		return userDao.addUser(user);
	}



	public List<User> getAllUserList() {
		return userDao.getAllUserList();
	}


	public User getUserById(long userId) {
		return userDao.getUserById(userId);
	}


	public boolean updateUser(User user) {
		return userDao.updateUser(user);
	}

	public boolean deletedUserById(long user) {
		return userDao.deletedUserById(user);
	}


	public boolean hardDeleteUserById(long userId) {
	    return userDao.hardDeleteUserById(userId);
	}

}

