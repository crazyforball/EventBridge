package com.emsrepo.service;

import java.util.List;

import com.emsrepo.entity.User;
import com.emsrepo.vo.UserVO;

public interface UserService {

	public void addUser(User user);
	
	public User getUser(int uid);
	
	public User getUserByUsername(String username);
	
	public UserVO getUserVOById(int uid);
	
	public List<UserVO> getUserVOList();
	
	public List<UserVO> getGeneralUserVOList();
	
	public void updateStatus(int uid, String status);

	void batchUpdateUserStatus(List<Integer> uidList, String status);
}
