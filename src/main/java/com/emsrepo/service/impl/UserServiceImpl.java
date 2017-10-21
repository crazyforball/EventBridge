package com.emsrepo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emsrepo.dao.LoggerDao;
import com.emsrepo.dao.UserDao;
import com.emsrepo.entity.Logger;
import com.emsrepo.entity.User;
import com.emsrepo.enums.LogTypeEnum;
import com.emsrepo.enums.UserTypeEnum;
import com.emsrepo.service.UserService;
import com.emsrepo.utils.CollectionUtils;
import com.emsrepo.utils.DateTimeUtil;
import com.emsrepo.vo.UserVO;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private LoggerDao loggerDao;
	
	@Override
	public void addUser(User user) {
		userDao.addUser(user);
	}

	@Override
	public User getUser(int uid) {
		return userDao.getUserById(uid);
	}

	@Override
	public User getUserByUsername(String username) {
		return userDao.getUserByUsername(username);
	}

	@Override
	public UserVO getUserVOById(int uid) {
		User u = getUser(uid);
		
		if (null == u) {
			return null;
		}
		
		return convertToUserVO(u);
	}

	@Override
	public List<UserVO> getUserVOList() {
		List<User> userList = userDao.getAllUserList();
		if (userList != null) {
			List<UserVO> userVOList = new ArrayList<>();
			for (User user : userList) {
				if (user != null) {
					userVOList.add(convertToUserVO(user));
				}
				
			}
			return userVOList;
		}
		return null;
	}
	
	public UserVO convertToUserVO(User user) {
		UserVO vo = new UserVO();
		vo.setUid(user.getUid());
		vo.setFirstName(user.getFirstName());
		vo.setLastName(user.getLastName());
		vo.setUsername(user.getUsername());
		String utype = "Invalid User";
		for (UserTypeEnum ut : UserTypeEnum.values()) {
			if (user.getUtype() == ut.utype)
				utype = ut.desc;
		}
		vo.setUtype(utype);
		vo.setPhoneNum(user.getPhoneNum());
		vo.setEmail(user.getEmail());
		vo.setStatus(user.getStatus());
		return vo;
	}

	@Override
	@Transactional
	public void updateStatus(int uid, String status) {
		User u = userDao.getUserById(uid);
		u.setStatus(status);
		userDao.updateUser(u);
	}
	
	@Override
	@Transactional
	public void batchUpdateUserStatus(List<Integer> uidList, String status) {
		userDao.batchUpdateUserStatus(uidList, status);
		addLog(uidList, status, 5);
	}

	@Override
	public List<UserVO> getGeneralUserVOList() {
		List<User> userList = userDao.getGeneralUserList();
		if (userList != null) {
			List<UserVO> userVOList = new ArrayList<>();
			for (User user : userList) {
				if (user != null) {
					userVOList.add(convertToUserVO(user));
				}
				
			}
			return userVOList;
		}
		return null;
	}
	
	public void addLog(List<Integer> uidList, String status, int adminId) {
		Logger log = new Logger();
		log.setAdminId(adminId);
		log.setUid(CollectionUtils.convertListToString(uidList));
		log.setLogType(status + " " + LogTypeEnum.OPT_USER);
		log.setLogDate(DateTimeUtil.getNowadayMillsTime());
		loggerDao.addLog(log);
	}
}
