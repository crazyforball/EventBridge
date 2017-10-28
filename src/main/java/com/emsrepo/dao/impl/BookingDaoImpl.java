package com.emsrepo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.emsrepo.dao.BookingDao;
import com.emsrepo.domain.Booking;

@SuppressWarnings("deprecation")
@Repository
public class BookingDaoImpl implements BookingDao {

	@Autowired
	private SessionFactory sessionFactory;

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
	public Booking getBooking(int uid, int eid) {
		Session session = null;
		Booking booking = null;

		try {
			session = getSession();
			Criteria criteria = session.createCriteria(Booking.class);
			criteria.add(Expression.like("uid", uid)).add(Expression.like("eid", eid));
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
	public List<Booking> getBookings(int uid) {
		Session session = null;
		List<Booking> list = new ArrayList<>();

		try {
			session = getSession();
			Criteria criteria = session.createCriteria(Booking.class);
			criteria.add(Expression.like("uid", uid)).addOrder(Order.desc("bookingDate"));
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

}
