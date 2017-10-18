package com.emsrepo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
		Session session = null;
		try {
			session = getSession();
			if (null != session ) {
				getSession().persist(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	@Override
	public User getUserById(int uid) {
		Session session = null;
		User u = null;
		try {
			session = getSession();
			if (session != null)
				u = (User) session.get(User.class, uid);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return u;
	}

	@Override
	public User getUserByUsername(String username) {
		Session session = null;
		User u = null;
		try {
			session = getSession();
			if (session != null)
				u = (User) session.get(User.class, username);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return u;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUserList() {
		Session session = null;
		List<User> list = new ArrayList<>();
		try {
			session = getSession();
			Criteria c = session.createCriteria(User.class);
			list =  c.list();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public void updateUser(User u) {
		Session session = null;
		try {
			session = getSession();
			if (session != null)
				session.update(u);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	@Override
	public void batchUpdateUserStatus(List<Integer> uidList, String status) {
		Session session = null;
		
		try {
			session = getSession();
			Transaction tx = session.beginTransaction();
			
			ScrollableResults users = session.createCriteria(User.class)
					.setCacheMode(CacheMode.IGNORE)
					.scroll(ScrollMode.FORWARD_ONLY);
			
			int count=0;
			while (users.next()) {
				User u = (User)users.get(0);
				if (uidList.contains(u.getUid()))
					u.setStatus(status);
				if (++count % 20 == 0) {
					session.flush();
					session.clear();
				}
			}
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getGeneralUserList() {
		Session session = null;
		
		List<User> list = null;
		
		try {
			session = getSession();
			list = session.createQuery("from User user where user.utype = 1")
					.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return list;
	}

}
