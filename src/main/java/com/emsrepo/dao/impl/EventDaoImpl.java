package com.emsrepo.dao.impl;

import java.util.List;

import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.emsrepo.dao.EventDao;
import com.emsrepo.domain.Event;

@SuppressWarnings("deprecation")
@Repository
public class EventDaoImpl implements EventDao {

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
	public List<Event> getAllEventList() {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Event.class);
		return (List<Event>) criteria.list();
	}

	@Override
	public void batchUpdateEventStatus(List<Integer> eidList, String status) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();

		ScrollableResults events = session.createCriteria(Event.class).setCacheMode(CacheMode.IGNORE)
				.scroll(ScrollMode.FORWARD_ONLY);

		int count = 0;
		while (events.next()) {
			Event e = (Event) events.get(0);
			if (eidList.contains(e.getEid()))
				e.setStatus(status);
			if (++count % 20 == 0) {
				session.flush();
				session.clear();
			}
		}
		tx.commit();
	}

	@Override
	public void saveEvent(Event event) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(event);
	}

	@Override
	public Event getEvent(int eid) {
		// TODO Auto-generated method stub
		return (Event) sessionFactory.getCurrentSession().get(Event.class, eid);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Event> getEventsByCategory(String category) {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Event.class);
		criteria.add(Expression.like("category", category)).addOrder(Order.desc("createDate"));
		return (List<Event>) criteria.list();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Event> getLatestNEvents(int n) {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Event.class);
		criteria.addOrder(Order.desc("createDate")).setMaxResults(n);
		return (List<Event>) criteria.list();
	}

	@Override
	public void deleteEvent(Event event) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete((Object) event);
	}

	@Override
	public void updateEvent(Event oldEvent, Event newEvent) {
		// TODO Auto-generated method stub
		if (newEvent.getImageUrl() == null && newEvent.getAudioUrl() == null) {
			oldEvent.setEventName(newEvent.getEventName());
			oldEvent.setLocation(newEvent.getLocation());
			oldEvent.setStartDate(newEvent.getStartDate());
			oldEvent.setEndDate(newEvent.getEndDate());
			oldEvent.setCapacity(newEvent.getCapacity());
			oldEvent.setFees(newEvent.getFees());
			oldEvent.setCategory(newEvent.getCategory());
			oldEvent.setDescription(newEvent.getDescription());
		} else {
			if (newEvent.getImageUrl() != null) {
				oldEvent.setImageUrl(newEvent.getImageUrl());
			}
			if (newEvent.getAudioUrl() != null) {
				oldEvent.setAudioUrl(newEvent.getAudioUrl());
			}
		}
		
		sessionFactory.getCurrentSession().update(oldEvent);
	}

	
	/*
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
			list = c.list();
		} catch (Exception e) {
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

				ScrollableResults events = session.createCriteria(Event.class).setCacheMode(CacheMode.IGNORE)
						.scroll(ScrollMode.FORWARD_ONLY);

				int count = 0;
				while (events.next()) {
					Event e = (Event) events.get(0);
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

	// ++++++++++++++++
	@Override
	public void saveEvent(Event event) {
		Session session = null;
		try {
			session = getSession();
			session.save(event);
//			session.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public Event getEvent(int eid) {
		Session session = null;
		Event event = null;

		try {
			session = getSession();
			Criteria criteria = session.createCriteria(Event.class);
			criteria.add(Expression.like("eid", eid));
			event = (Event) criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return event;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Event> getEventsByCategory(String category) {
		Session session = null;
		List<Event> list = new ArrayList<>();

		try {
			session = getSession();
			list = (List<Event>) session.createCriteria(Event.class).add(Expression.like("category", category))
					.addOrder(Order.desc("createDate")).list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return list;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Event> getLatestNEvents(int n) {
		Session session = null;
		List<Event> list = new ArrayList<>();

		try {
			session = getSession();
			list = (List<Event>) session.createCriteria(Event.class).addOrder(Order.desc("createDate")).setMaxResults(n)
					.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return list;
	}

	@Override
	public void deleteEvent(Event event) {
		Session session = null;
		try {
			session = getSession();
			session.delete(event);
			session.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public void updateEvent(Event oldEvent, Event newEvent) {

		if (newEvent.getImageUrl() == null && newEvent.getAudioUrl() == null) {
			oldEvent.setEventName(newEvent.getEventName());
			oldEvent.setLocation(newEvent.getLocation());
			oldEvent.setStartDate(newEvent.getStartDate());
			oldEvent.setEndDate(newEvent.getEndDate());
			oldEvent.setCapacity(newEvent.getCapacity());
			oldEvent.setFees(newEvent.getFees());
			oldEvent.setCategory(newEvent.getCategory());
			oldEvent.setDescription(newEvent.getDescription());
		} else {
			if (newEvent.getImageUrl() != null) {
				oldEvent.setImageUrl(newEvent.getImageUrl());
			}
			if (newEvent.getAudioUrl() != null) {
				oldEvent.setAudioUrl(newEvent.getAudioUrl());
			}
		}

		Session session = null;
		try {
			session = getSession();
			session.update(oldEvent);
			session.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	*/

}
