package com.emsrepo.dao.impl;

import com.emsrepo.entity.Event;
import com.emsrepo.entity.Logger;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.emsrepo.dao.LoggerDao;

@Repository
public class LoggerDaoImpl implements LoggerDao {

	@Autowired
	private SessionFactory sessionFactory;
	
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
			list =  c.list();
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

}
