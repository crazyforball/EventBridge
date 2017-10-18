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
import com.emsrepo.dao.EventDao;
import com.emsrepo.entity.Event;

@Repository
public class EventDaoImpl implements EventDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession() {
		return this.sessionFactory.openSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Event> getAllEventList() {

		Session session = null;
		List<Event> list = new ArrayList<>();
		try {
			session = getSession();
			Criteria c = session.createCriteria(Event.class);
			list =  c.list();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		System.out.println(list.toString());
		return list;
	}
	
	@Override
	public void batchUpdateEventStatus(List<Integer> eidList, String status) {
		Session session = null;
		
		try {
			session = getSession();
			if (session != null) {
				Transaction tx = session.beginTransaction();
				
				ScrollableResults events = session.createCriteria(Event.class)
						.setCacheMode(CacheMode.IGNORE)
						.scroll(ScrollMode.FORWARD_ONLY);
				
				int count=0;
				while (events.next()) {
					Event e = (Event)events.get(0);
					if (eidList.contains(e.getEid()))
						e.setStatus(status);
					if (++count % 20 == 0) {
						session.flush();
						session.clear();
					}
				}
				tx.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
}
