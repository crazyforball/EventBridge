package com.emsrepo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emsrepo.dao.UserDao;
import com.emsrepo.entity.User;
import com.emsrepo.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		userDao.addUser(user);
	}

	@Override
	public User getUser(int uid) {
		// TODO Auto-generated method stub
		return userDao.getUserById(uid);
	}
}
