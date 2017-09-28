package com.emsrepo.dao;

import com.emsrepo.entity.User;

public interface UserDao {

	/**
	 * Add user
	 * @param user
	 */
	public void addUser(User user);
	
	/**
	 * Get user
	 * @param uid
	 * @return
	 */
	public User getUserById(int uid);
	
	/**
	 * 
	 * @param username
	 * @return
	 */
	public User getUserByUsername(String username);
}
