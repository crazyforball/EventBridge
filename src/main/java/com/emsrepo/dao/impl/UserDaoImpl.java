package com.emsrepo.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Expression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.emsrepo.dao.UserDao;
import com.emsrepo.domain.User;

@SuppressWarnings("deprecation")
@Repository("userDao")
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().persist(user);;
	}

	@Override
	public User getUserById(int uid) {
		// TODO Auto-generated method stub
		return (User) sessionFactory.getCurrentSession().get(User.class, uid);
	}

	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
		criteria.add(Expression.like("username", username));
		return (User) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUserList() {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
		return (List<User>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getGeneralUserList() {
		// TODO Auto-generated method stub
		return (List<User>) sessionFactory.getCurrentSession().createQuery("from User user where user.utype = 1").list();
	}

	@Override
	public void updateUser(User u) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(u);
	}

	@Override
	public void batchUpdateUserStatus(List<Integer> uidList, String status) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();

		ScrollableResults users = session.createCriteria(User.class).setCacheMode(CacheMode.IGNORE)
				.scroll(ScrollMode.FORWARD_ONLY);

		int count = 0;
		while (users.next()) {
			User u = (User) users.get(0);
			if (uidList.contains(u.getUid()))
				u.setStatus(status);
			if (++count % 20 == 0) {
				session.flush();
				session.clear();
			}
		}

		tx.commit();
	}

	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
		criteria.add(Expression.like("username", username));
		return (User) criteria.uniqueResult();
	}

	@Override
	public boolean updateUser(User user, Map<String, String> userInfo) {
		// TODO Auto-generated method stub
		user.setPassword(userInfo.get("newPassword2"));
		user.setPhoneNum(userInfo.get("phone"));
		user.setEmail(userInfo.get("email"));
		
		boolean result = false;
		
		sessionFactory.getCurrentSession().update(user);
		result = true;
		
		return result;
	}
	
	

	/*
	public Session getSession() {
		return this.sessionFactory.openSession();
	}

	@Override
	public void addUser(User user) {
		Session session = null;
		try {
			session = getSession();
			if (null != session) {
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
		} catch (Exception e) {
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
		} catch (Exception e) {
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
			list = c.list();
		} catch (Exception e) {
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

			ScrollableResults users = session.createCriteria(User.class).setCacheMode(CacheMode.IGNORE)
					.scroll(ScrollMode.FORWARD_ONLY);

			int count = 0;
			while (users.next()) {
				User u = (User) users.get(0);
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
			list = session.createQuery("from User user where user.utype = 1").list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return list;
	}

	// ++++++++++++++++++++++++++

	@Override
	public void saveUser(User user) {
		Session session = null;
		try {
			session = getSession();
			session.save(user);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public User getUser(String username) {
		Session session = null;
		User user = null;
		
		try {
			session = getSession();
			Criteria criteria = session.createCriteria(User.class);
			criteria.add(Expression.like("username", username));
			user = (User) criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return user;
	}

	@Override
	public boolean updateUser(User user, Map<String, String> userInfo) {
		// TODO Auto-generated method stub
		user.setPassword(userInfo.get("newPassword2"));
		user.setPhoneNum(userInfo.get("phone"));
		user.setEmail(userInfo.get("email"));
		
		Session session = null;
		boolean result = false;
		
		try {
			session = getSession();
			session.update(user);
			session.flush();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return result;
	}
	*/

}
