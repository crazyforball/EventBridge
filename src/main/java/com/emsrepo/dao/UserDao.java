package com.emsrepo.dao;

import com.emsrepo.entity.Users;

public interface UserDao {

	/**
	 * Add user
	 * @param user
	 */
	public void addUser(Users user);
	
	/**
	 * Get user
	 * @param uid
	 * @return
	 */
	public Users getUserById(int uid);
}
