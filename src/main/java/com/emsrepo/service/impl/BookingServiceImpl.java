package com.emsrepo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emsrepo.dao.BookingDao;
import com.emsrepo.domain.Booking;
import com.emsrepo.domain.Event;
import com.emsrepo.domain.User;
import com.emsrepo.service.BookingService;

@Service("bookingService")
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingDao bookingDao;

	@Override
	public boolean registerBooking(Booking booking) {
		if (!isExistingBooking(booking)) {
			bookingDao.saveBooking(booking);
			return true;
		}
		return false;
	}

	@Override
	public boolean isExistingBooking(Booking booking) {
		return bookingDao.getBooking(booking.getCreator(), booking.getEvent()) != null;
	}

	@Override
	public Booking retrieveBooking(int bid) {
		return bookingDao.getBooking(bid);
	}

	@Override
	public Booking retrieveBooking(User creator, Event event) {
		return bookingDao.getBooking(creator, event);
	}

	@Override
	public List<Booking> retrieveBookings(User creator) {
		return bookingDao.getBookings(creator);
	}

	@Override
	public void cancelBooking(int bid) {
		bookingDao.deleteBooking(bookingDao.getBooking(bid));
	}
}
