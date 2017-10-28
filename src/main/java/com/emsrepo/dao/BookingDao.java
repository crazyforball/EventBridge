package com.emsrepo.dao;

import java.util.List;

import com.emsrepo.domain.Booking;

public interface BookingDao {
	public void saveBooking(Booking booking);

	public Booking getBooking(int uid, int eid);

	public Booking getBooking(int bookingId);

	public List<Booking> getBookings(int uid);

	public void deleteBooking(Booking booking);
}
