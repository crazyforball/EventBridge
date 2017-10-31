package com.emsrepo.aop;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.emsrepo.domain.Booking;
import com.emsrepo.domain.Comment;
import com.emsrepo.domain.Event;
import com.emsrepo.domain.Following;
import com.emsrepo.domain.User;
import com.emsrepo.service.BookingService;
import com.emsrepo.service.CommentService;
import com.emsrepo.service.EventService;
import com.emsrepo.service.FollowingService;

//@Aspect
//@Component
public class AuthenticationInterceptor {
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private FollowingService followingService;
	
	@Autowired
	private CommentService commentService;

	@Autowired
	private EventService eventService;

	public void authenticate(Object model, HttpServletRequest request) {
		System.out.println("authenticate");
		int eventId = 0;
		int bookingId = 0;
		int followingId = 0;
		int commentId = 0;
		
		String adminName = request.getParameter("adminName");
		String username = request.getParameter("username");
		User user = (User) request.getSession().getAttribute("user");

		if (user != null) {
			request.setAttribute("hasLoggedIn", true);
			System.out.println("hasLoggedIn");
			
			try {
				String eventIdStr = request.getParameter("eventId");
				if (eventIdStr != null && eventIdStr.length() > 0) {
					eventId = Integer.parseInt(eventIdStr);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				String bookingIdStr = request.getParameter("bookingId");
				if (bookingIdStr != null && bookingIdStr.length() > 0) {
					bookingId = Integer.parseInt(bookingIdStr);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				String followingIdStr = request.getParameter("followingId");
				if (followingIdStr != null && followingIdStr.length() > 0) {
					followingId = Integer.parseInt(followingIdStr);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				String commentIdStr = request.getParameter("commentId");
				if (commentIdStr != null && commentIdStr.length() > 0) {
					commentId = Integer.parseInt(commentIdStr);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if (eventId != 0) {
				Event event = eventService.retrieveEvent(eventId);
				if (event != null) {
					if (user.getUid().compareTo(event.getCreator().getUid()) == 0) {
						request.setAttribute("authorized", true);
						System.out.println("authorized");
						return;
					}
				}
			}
			if (bookingId != 0) {
				Booking booking = bookingService.retrieveBooking(bookingId);
				if (booking != null) {
					if (user.getUid().compareTo(booking.getCreator().getUid()) == 0) {
						request.setAttribute("authorized", true);
						System.out.println("authorized");
						return;
					}
				}
			}
			if (followingId != 0) {
				Following following = followingService.retrieveFollowing(followingId);
				if (following != null) {
					if (user.getUid().compareTo(following.getCreator().getUid()) == 0) {
						request.setAttribute("authorized", true);
						System.out.println("authorized");
						return;
					}
				}
			}
			if (commentId != 0) {
				Comment comment = commentService.retrieveComment(commentId);
				if (comment != null) {
					if (user.getUid().compareTo(comment.getCreator().getUid()) == 0) {
						request.setAttribute("authorized", true);
						System.out.println("authorized");
						return;
					}
				}
			}
			
			if (username != null) {
				if (user.getUsername().equals(username)) {
					request.setAttribute("authorized", true);
					System.out.println("authorized");
					request.setAttribute("username", username);
					return;
				}
			}
			if (adminName != null) {
				if (user.getUsername().equals(adminName) && user.getUtype().compareTo(2) == 0) {
					request.setAttribute("isAdmin", true);
					System.out.println("isAdmin");
					return;
				}
			}
		} else {
			request.setAttribute("hasLoggedIn", false);
		}
		request.setAttribute("authorized", false);
		request.setAttribute("isAdmin", false);
	}
}
