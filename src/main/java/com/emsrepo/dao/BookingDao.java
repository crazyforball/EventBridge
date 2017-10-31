package com.emsrepo.dao;

import java.util.List;

import com.emsrepo.domain.Booking;
import com.emsrepo.domain.Event;
import com.emsrepo.domain.User;

public interface BookingDao {
	public void saveBooking(Booking booking);

	public Booking getBooking(User creator, Event event);

	public Booking getBooking(int bookingId);

	public List<Booking> getBookings(User creator);

	public void deleteBooking(Booking booking);
}
