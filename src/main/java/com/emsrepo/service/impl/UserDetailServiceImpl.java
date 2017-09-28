package com.emsrepo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.emsrepo.dao.UserDao;
import com.emsrepo.entity.User;
import com.emsrepo.service.UserDetailService;

public class UserDetailServiceImpl implements UserDetailService {

	@Autowired
	private UserDao userDao;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 User user = userDao.getUserByUsername(username);
		 if (user == null || user.getUtype() < 1)
			 throw new UsernameNotFoundException(username+" not exist!");
		 List<GrantedAuthority> authList = new ArrayList<>();
		 authList.add(new SimpleGrantedAuthority(user.getUtype().toString()));
         boolean accountNonExpired = true;
         boolean credentialsNonExpired = true;
         boolean accountNonLocked = true;
 
         UserDetails userdetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user
                 .getPassword(), true, accountNonExpired, credentialsNonExpired, accountNonLocked, authList);
 
         return userdetails;	
	}
}
