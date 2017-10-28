package com.emsrepo.dao;

import java.util.List;
import java.util.Map;

import com.emsrepo.domain.User;

public interface UserDao {

	/**
	 * Add user
	 * 
	 * @param user
	 */
	public void addUser(User user);

	/**
	 * Get user
	 * 
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

	public List<User> getAllUserList();

	public List<User> getGeneralUserList();

	public void updateUser(User u);

	void batchUpdateUserStatus(List<Integer> uidList, String status);

	// ++++++++++++++++
	public void saveUser(User user);

	public User getUser(String username);

	public boolean updateUser(User user, Map<String, String> userInfo);
}
