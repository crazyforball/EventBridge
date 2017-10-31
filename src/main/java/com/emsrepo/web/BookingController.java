package com.emsrepo.web;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.emsrepo.domain.Booking;
import com.emsrepo.domain.Event;
import com.emsrepo.domain.User;
import com.emsrepo.service.BookingService;
import com.emsrepo.service.EventService;
import com.emsrepo.utils.DateTimeUtil;

@Controller
@RequestMapping(value = "/booking")
public class BookingController {

	@Autowired
	private EventService eventService;

	@Autowired
	private BookingService bookingService;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String viewMyBookings(Map<String, Object> model, HttpServletRequest request) {
		if ((Boolean) request.getAttribute("hasLoggedIn")) {
			if ((Boolean) request.getAttribute("authorized")) {
				User user = (User) request.getSession().getAttribute("user");
				List<Booking> bookings = bookingService.retrieveBookings(user);
				
				for (Iterator<Booking> iterator=bookings.iterator(); iterator.hasNext();) {
					Booking booking = iterator.next();
					Event event = booking.getEvent();
					Date endDate = DateTimeUtil.toDate(event.getEndDate());
					System.out.println("endDate: " + endDate);
					if (endDate != null) {
						if (endDate.before(new Date())) {
							event.setStatus("FINISHED");
						}
					}
				}
				
				request.setAttribute("bookings", bookings);
				return "my_bookings";
			}
			return null;
		}
		return "user_login";
	}

	@RequestMapping(value = "/make", method = RequestMethod.GET)
	public String viewBooking(Map<String, Object> model, HttpServletRequest request) {
		if ((Boolean) request.getAttribute("hasLoggedIn")) {
			int eventId = 0;
			try {
				eventId = Integer.parseInt(request.getParameter("eventId"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (eventId != 0) {
				request.setAttribute("event", eventService.retrieveEvent(eventId));
				Booking booking = new Booking();
				model.put("booking", booking);
				return "booking_make";
			}
		}
		return "user_login";
	}

	@RequestMapping(value = "/make", method = RequestMethod.POST)
	public String processBooking(@ModelAttribute("booking") Booking booking, HttpServletRequest request) {
		int eventId = 0;
		try {
			eventId = Integer.parseInt(request.getParameter("eventId"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (eventId != 0) {
			Event event = eventService.retrieveEvent(eventId);
			User creator = (User) request.getSession().getAttribute("user");
			booking.setCreator(creator);
			booking.setEvent(event);
		}

		if (bookingService.registerBooking(booking)) {
			return "booking_make_success";
		}
		return "booking_make_failure";
	}

	@RequestMapping(value = "/cancel", method = RequestMethod.GET)
	public String deleteBooking(Map<String, Object> model, HttpServletRequest request) {
		if ((Boolean) request.getAttribute("hasLoggedIn")) {
			if ((Boolean) request.getAttribute("authorized")) {
				User user = (User) request.getSession().getAttribute("user");
				int bid = 0;
				try {
					bid = Integer.parseInt(request.getParameter("bookingId"));
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (bid != 0) {
					bookingService.cancelBooking(bid);
					return "redirect: home?username=" + user.getUsername();
				}
			}
			return null;
		}
		return "user_login";
	}

}
