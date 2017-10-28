package com.emsrepo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emsrepo.dao.BookingDao;
import com.emsrepo.domain.Booking;
import com.emsrepo.service.BookingService;

@Service("bookingService")
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingDao bookingDao;

	// business logic of registering a Person into the database
	@Override
	public boolean registerBooking(Booking booking) {

		// Step 1: check whether this person is already in the database
		// Step 2: if not, save this person into the database
		if (!isExistingBooking(booking)) {
			bookingDao.saveBooking(booking);
			return true;
		}
		return false;
	}

	@Override
	public boolean isExistingBooking(Booking booking) {
		return bookingDao.getBooking(booking.getUid(), booking.getEid()) != null;
	}
	
	@Override
	public Booking retrieveBooking(int bid) {
		return bookingDao.getBooking(bid);
	}

	@Override
	public Booking retrieveBooking(int uid, int eid) {
		return bookingDao.getBooking(uid, eid);
	}

	@Override
	public List<Booking> retrieveBookings(int uid) {
		return bookingDao.getBookings(uid);
	}

	@Override
	public void cancelBooking(int bid) {
		bookingDao.deleteBooking(bookingDao.getBooking(bid));
	}
}
