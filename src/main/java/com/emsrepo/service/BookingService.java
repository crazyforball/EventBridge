package com.emsrepo.service;

import java.util.List;

import com.emsrepo.domain.Booking;
import com.emsrepo.domain.Event;
import com.emsrepo.domain.User;

public interface BookingService {

	public boolean registerBooking(Booking booking);

	public boolean isExistingBooking(Booking booking);

	public Booking retrieveBooking(int bookingId);

	public Booking retrieveBooking(User creator, Event event);

	public List<Booking> retrieveBookings(User creator);

	public void cancelBooking(int bid);

}
