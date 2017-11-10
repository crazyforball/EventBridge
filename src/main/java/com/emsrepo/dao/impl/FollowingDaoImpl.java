package com.emsrepo.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.emsrepo.dao.FollowingDao;
import com.emsrepo.domain.Event;
import com.emsrepo.domain.Following;
import com.emsrepo.domain.User;

@SuppressWarnings("deprecation")
@Repository
public class FollowingDaoImpl implements FollowingDao {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void saveFollowing(Following following) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(following);
	}

	@Override
	public Following getFollowing(User creator, Event event) {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Following.class);
		criteria.add(Expression.like("creator", creator)).add(Expression.like("event", event));
		return (Following) criteria.uniqueResult();
	}

	@Override
	public Following getFollowing(int followingId) {
		// TODO Auto-generated method stub
		return (Following) sessionFactory.getCurrentSession().get(Following.class, followingId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Following> getFollowings(User creator) {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Following.class);
		criteria.add(Expression.like("creator", creator)).addOrder(Order.desc("followingDate"));
		return (List<Following>) criteria.list();
	}

	@Override
	public void deleteFollowing(Following following) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete((Object) following);
	}

	/*
	public Session getSession() {
		return this.sessionFactory.openSession();
	}

	@Override
	public void saveFollowing(Following following) {
		Session session = null;
		try {
			session = getSession();
			session.save(following);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public Following getFollowing(User creator, Event event) {
		Session session = null;
		Following following = null;

		try {
			session = getSession();
			Criteria criteria = session.createCriteria(Following.class);
			criteria.add(Expression.like("creator", creator)).add(Expression.like("event", event));
			following = (Following) criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return following;

	}

	@Override
	public Following getFollowing(int bid) {
		Session session = null;
		Following following = null;

		try {
			session = getSession();
			following = (Following) session.get(Following.class, bid);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return following;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Following> getFollowings(User creator) {
		Session session = null;
		List<Following> list = new ArrayList<>();

		try {
			session = getSession();
			Criteria criteria = session.createCriteria(Following.class);
			criteria.add(Expression.like("creator", creator)).addOrder(Order.desc("followingDate"));
			list = (List<Following>) criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return list;
	}

	@Override
	public void deleteFollowing(Following following) {
		Session session = null;
		try {
			session = getSession();
			session.delete(following);
			session.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	*/

}
