package com.emsrepo.service;

import com.emsrepo.entity.Users;

public interface UserService {

	public void addUser(Users user);
	
	public Users getUser(int uid);
}
