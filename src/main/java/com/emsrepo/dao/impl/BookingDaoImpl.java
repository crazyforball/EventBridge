package com.emsrepo.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.emsrepo.dao.BookingDao;
import com.emsrepo.domain.Booking;
import com.emsrepo.domain.Event;
import com.emsrepo.domain.User;

@SuppressWarnings("deprecation")
@Repository
public class BookingDaoImpl implements BookingDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void saveBooking(Booking booking) {
		sessionFactory.getCurrentSession().save(booking);
	}

	@Override
	public Booking getBooking(User creator, Event event) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Booking.class);
		criteria.add(Expression.like("creator", creator)).add(Expression.like("event", event));
		return (Booking) criteria.uniqueResult();
	}

	@Override
	public Booking getBooking(int bid) {
		return (Booking) sessionFactory.getCurrentSession().get(Booking.class, bid);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Booking> getBookings(User creator) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Booking.class);
		criteria.add(Expression.like("creator", creator)).addOrder(Order.desc("bookingDate"));
		return (List<Booking>) criteria.list();
	}

	@Override
	public void deleteBooking(Booking booking) {
		sessionFactory.getCurrentSession().delete((Object) booking);
	}

	/*
	public Session getSession() {
		return this.sessionFactory.openSession();
	}

	@Override
	public void saveBooking(Booking booking) {
		Session session = null;
		try {
			session = getSession();
			session.save(booking);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public Booking getBooking(User creator, Event event) {
		Session session = null;
		Booking booking = null;

		try {
			session = getSession();
			Criteria criteria = session.createCriteria(Booking.class);
			criteria.add(Expression.like("creator", creator)).add(Expression.like("event", event));
			booking = (Booking) criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return booking;

	}

	@Override
	public Booking getBooking(int bid) {
		Session session = null;
		Booking booking = null;

		try {
			session = getSession();
			booking = (Booking) session.get(Booking.class, bid);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return booking;
	}

	@SuppressWarnings("unchecked")

	@Override
	public List<Booking> getBookings(User creator) {
		Session session = null;
		List<Booking> list = new ArrayList<>();

		try {
			session = getSession();
			Criteria criteria = session.createCriteria(Booking.class);
			criteria.add(Expression.like("creator", creator)).addOrder(Order.desc("bookingDate"));
			list = (List<Booking>) criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}

		return list;
	}

	@Override
	public void deleteBooking(Booking booking) {
		Session session = null;
		try {
			session = getSession();
			session.delete(booking);
			session.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	*/

}
