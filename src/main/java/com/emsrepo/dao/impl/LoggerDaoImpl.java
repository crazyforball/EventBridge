package com.emsrepo.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.emsrepo.dao.LoggerDao;
import com.emsrepo.domain.Logger;

@Repository
public class LoggerDaoImpl implements LoggerDao {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Logger> getAllLoggerList() {
		// TODO Auto-generated method stub
		return (List<Logger>) sessionFactory.getCurrentSession().createCriteria(Logger.class).list();
	}

	@Override
	public void addLog(Logger log) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().persist(log);;
	}

	@Override
	public void saveLog(Logger log) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(log);
	}

	/*
	public Session getSession() {
		return this.sessionFactory.openSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Logger> getAllLoggerList() {

		Session session = null;
		List<Logger> list = new ArrayList<>();

		try {
			session = getSession();
			Criteria c = session.createCriteria(Logger.class);
			list = c.list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public void addLog(Logger log) {
		Session session = null;
		if (log != null) {
			try {
				session = getSession();
				session.persist(log);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				session.close();
			}
		}
	}
	
	//++++++++++++++
	@Override
	public void saveLog(Logger log) {
		Session session = null;
		if (log != null) {
			try {
				session = getSession();
				session.save(log);
				session.flush();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				session.close();
			}
		}
	}
	*/

}
