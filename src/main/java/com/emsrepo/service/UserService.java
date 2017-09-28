package com.emsrepo.service;

import com.emsrepo.entity.User;

public interface UserService {

	public void addUser(User user);
	
	public User getUser(int uid);
}
