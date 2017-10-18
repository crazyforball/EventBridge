package com.emsrepo.dao;

import java.util.List;

import com.emsrepo.entity.User;
import com.emsrepo.vo.UserVO;

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
	
	public List<User> getAllUserList();
	
	public List<User> getGeneralUserList();
	
	public void updateUser(User u);

	void batchUpdateUserStatus(List<Integer> uidList, String status);
}
