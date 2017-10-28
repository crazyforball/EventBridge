package com.emsrepo.service;

import java.util.List;
import java.util.Map;

import com.emsrepo.domain.User;
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

	// ++++++++
	public boolean registerUser(User user);

	public boolean loginUser(String username, String password);

	public User getUser(String username);

	public boolean isExistingUser(User user);

	public boolean updateUser(User user, Map<String, String> userInfo);

}
