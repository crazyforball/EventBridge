package com.emsrepo.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.emsrepo.dao.UserDao;
import com.emsrepo.entity.User;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {  
        this.sessionFactory = sessionFactory;  
    }  
	
	public Session getSession() {
		return this.sessionFactory.openSession();
	}
	
	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		getSession().persist(user);
	}
	
	@Override
	public User getUserById(int uid) {
		// TODO Auto-generated method stub
		return (User) getSession().get(User.class, uid);
	}

	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return (User)getSession().get(User.class, username);
	}

}
