package com.emsrepo.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.emsrepo.domain.Comment;
import com.emsrepo.domain.Event;
import com.emsrepo.domain.User;
import com.emsrepo.service.CommentService;
import com.emsrepo.service.EventService;

@Controller
@RequestMapping(value = "/comment")
public class CommentController {

	@Autowired
	private EventService eventService;

	@Autowired
	private CommentService commentService;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String viewMyComments(Map<String, Object> model, HttpServletRequest request) {
		if ((Boolean) request.getAttribute("hasLoggedIn")) {
			if ((Boolean) request.getAttribute("authorized")) {
				User user = (User) request.getSession().getAttribute("user");
				List<Comment> comments = commentService.retrieveComments(user);
				request.setAttribute("comments", comments);
				return "my_comments";
			}
			return null;
		}
		return "user_login";
	}

	@RequestMapping(value = "/make", method = RequestMethod.GET)
	public String viewComment(Map<String, Object> model, HttpServletRequest request) {
		if ((Boolean) request.getAttribute("hasLoggedIn")) {
			int eventId = 0;
			try {
				eventId = Integer.parseInt(request.getParameter("eventId"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (eventId != 0) {
				request.setAttribute("event", eventService.retrieveEvent(eventId));
				Comment comment = new Comment();
				model.put("comment", comment);
				return "comment_make";
			}
		}
		return "user_login";
	}

	@RequestMapping(value = "/make", method = RequestMethod.POST)
	public String processComment(@ModelAttribute("comment") Comment comment, HttpServletRequest request) {
		int eventId = 0;
		try {
			eventId = Integer.parseInt(request.getParameter("eventId"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (eventId != 0) {
			Event event = eventService.retrieveEvent(eventId);
			User creator = (User) request.getSession().getAttribute("user");
			comment.setCreator(creator);
			comment.setEvent(event);
		}

		if (commentService.registerComment(comment)) {
			return "comment_make_success";
		}
		return "comment_make_failure";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteComment(Map<String, Object> model, HttpServletRequest request) {
		if ((Boolean) request.getAttribute("hasLoggedIn")) {
			if ((Boolean) request.getAttribute("authorized")) {
				User user = (User) request.getSession().getAttribute("user");
				int cid = 0;
				try {
					cid = Integer.parseInt(request.getParameter("commentId"));
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (cid != 0) {
					commentService.deleteComment(cid);
					return "redirect: home?username=" + user.getUsername();
				}
			}
			return null;
		}
		return "user_login";
	}

}
