package com.emsrepo.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.emsrepo.dao.UserDao;
import com.emsrepo.entity.Users;

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
	public void addUser(Users user) {
		// TODO Auto-generated method stub
		getSession().persist(user);
	}
	
	@Override
	public Users getUserById(int uid) {
		// TODO Auto-generated method stub
		return (Users) getSession().get(Users.class, uid);
	}

}
