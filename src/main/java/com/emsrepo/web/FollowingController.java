package com.emsrepo.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.emsrepo.domain.Event;
import com.emsrepo.domain.Following;
import com.emsrepo.domain.User;
import com.emsrepo.service.EventService;
import com.emsrepo.service.FollowingService;

@Controller
@RequestMapping(value = "/following")
public class FollowingController {

	@Autowired
	private EventService eventService;

	@Autowired
	private FollowingService followingService;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String viewMyFollowings(Map<String, Object> model, HttpServletRequest request) {
		if ((Boolean) request.getAttribute("hasLoggedIn")) {
			if ((Boolean) request.getAttribute("authorized")) {
				User user = (User) request.getSession().getAttribute("user");
				List<Following> followings = followingService.retrieveFollowings(user);
				request.setAttribute("followings", followings);
				return "my_followings";
			}
			return null;
		}
		return "user_login";
	}

	@RequestMapping(value = "/make", method = RequestMethod.GET)
	public String processFollowing(Model model, HttpServletRequest request) {
		int eventId = 0;
		Following following = new Following();
		try {
			eventId = Integer.parseInt(request.getParameter("eventId"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (eventId != 0) {
			Event event = eventService.retrieveEvent(eventId);
			User creator = (User) request.getSession().getAttribute("user");
			following.setCreator(creator);
			following.setEvent(event);
		}

		if (followingService.registerFollowing(following)) {
			return "following_make_success";
		}
		return "following_make_failure";
	}

	@RequestMapping(value = "/cancel", method = RequestMethod.GET)
	public String deleteFollowing(Map<String, Object> model, HttpServletRequest request) {
		if ((Boolean) request.getAttribute("hasLoggedIn")) {
			if ((Boolean) request.getAttribute("authorized")) {
				User user = (User) request.getSession().getAttribute("user");
				int fid = 0;
				try {
					fid = Integer.parseInt(request.getParameter("followingId"));
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (fid != 0) {
					followingService.cancelFollowing(fid);
					return "redirect: home?username=" + user.getUsername();
				}
			}
			return null;
		}
		return "user_login";
	}

}
