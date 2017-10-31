package com.emsrepo.web;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.emsrepo.domain.Comment;
import com.emsrepo.domain.Event;
import com.emsrepo.domain.User;
import com.emsrepo.service.CategoryService;
import com.emsrepo.service.CommentService;
import com.emsrepo.service.EventService;
import com.emsrepo.service.UserService;
import com.emsrepo.utils.RatingUtil;

@Controller
@RequestMapping(value = "/event")
public class EventController {

	@Autowired
	@Qualifier("eventService")
	private EventService eventService;

	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@Autowired
	@Qualifier("categoryService")
	private CategoryService categoryService;

	@Autowired
	@Qualifier("commentService")
	private CommentService commentService;

	@RequestMapping(value = "/post", method = RequestMethod.GET)
	public String viewPost(Map<String, Object> model, HttpServletRequest request) {
		if ((Boolean) request.getAttribute("hasLoggedIn")) {
			Event eventForm = new Event();
			model.put("eventForm", eventForm);
			model.put("categoryNames", categoryService.getCategoryNames());
			return "event_post";
		}
		return "user_login";
	}

	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public String processPost(@ModelAttribute("eventForm") Event event, HttpServletRequest request) {

		event.setCreator((User) request.getSession().getAttribute("user"));
		if (eventService.postEvent(event)) {
			request.setAttribute("eventId", event.getEid());
			return "event_post_edit_success";
		}
		return "event_post_edit_failure";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String viewEvent(HttpServletRequest request) {
		int eventId = 0;
		try {
			eventId = Integer.parseInt(request.getParameter("eventId"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (eventId != 0) {
			Event event = eventService.retrieveEvent(eventId);
			request.setAttribute("event", event);

			String locationInURL = null;
			locationInURL = event.getLocation().trim().replace(" ", "+");
			System.out.print("location in URL:" + locationInURL);
			if (locationInURL != null) {
				request.setAttribute("locationInURL", locationInURL);
			}

			List<Comment> comments = commentService.retrieveComments(event);

			Map<String, Double> rates = RatingUtil.calRates(comments);

			if (rates != null) {
				request.setAttribute("positiveRate", rates.get("positiveRate"));
			}

			return "event_home";
		}
		return null;
	}

	@RequestMapping(value = "/category", method = RequestMethod.GET)
	public String viewEventsByCategory(HttpServletRequest request) {
		String category = request.getParameter("category");
		if (category != null) {
			List<Event> events = eventService.retrieveEventsByCategory(category);
			request.setAttribute("category", category);
			request.setAttribute("events", events);
			return "events_by_category";
		}
		return null;
	}

	@RequestMapping(value = "/myPosts", method = RequestMethod.GET)
	public String viewMyPosts(Map<String, Object> model, HttpServletRequest request) {
		if ((Boolean) request.getAttribute("hasLoggedIn")) {
			if ((Boolean) request.getAttribute("authorized")) {
				String username = request.getParameter("username");
				List<Event> events = new ArrayList<Event>(userService.getUser(username).getCreatedEvents());
				request.setAttribute("events", events);
				return "my_posts";
			}
			return null;
		}
		return "user_login";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editPost(Map<String, Object> model, HttpServletRequest request) {
		if ((Boolean) request.getAttribute("hasLoggedIn")) {
			if ((Boolean) request.getAttribute("authorized")) {
				int eventId = 0;
				try {
					eventId = Integer.parseInt(request.getParameter("eventId"));
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (eventId != 0) {
					Event oldEvent = eventService.retrieveEvent(eventId);
					Event newEvent = new Event();
					List<String> categoryNames = categoryService.getCategoryNames();
					categoryNames.remove(oldEvent.getCategory());

					System.out.println("old: " + oldEvent);

					model.put("oldEvent", oldEvent);
					model.put("newEvent", newEvent);
					model.put("categoryNames", categoryNames);

					return "event_edit";
				}
			}
			return null;
		}
		return "user_login";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String processEdit(@ModelAttribute("newEvent") Event newEvent, HttpServletRequest request) {
		int eventId = 0;
		try {
			eventId = Integer.parseInt(request.getParameter("eventId"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (eventId != 0) {
			Event oldEvent = eventService.retrieveEvent(eventId);

			eventService.updateEvent(oldEvent, newEvent);
			request.setAttribute("update", true);
			request.setAttribute("eventId", eventId);
			return "event_post_edit_success";
		}
		return "event_post_edit_failure";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deletePost(Map<String, Object> model, HttpServletRequest request) {
		if ((Boolean) request.getAttribute("hasLoggedIn")) {
			if ((Boolean) request.getAttribute("authorized")) {
				int eventId = 0;
				try {
					eventId = Integer.parseInt(request.getParameter("eventId"));
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (eventId != 0) {
					Event eventToDelete = eventService.retrieveEvent(eventId);
					String username = eventToDelete.getCreator().getUsername();
					eventService.deleteEvent(eventToDelete);
					return "redirect: myPosts?username=" + username;
				}
			}
			return null;
		}
		return "user_login";
	}

	@RequestMapping(value = "/media/upload", method = RequestMethod.GET)
	public String viewUpload(Map<String, Object> model, HttpServletRequest request) {
		if ((Boolean) request.getAttribute("hasLoggedIn")) {
			if ((Boolean) request.getAttribute("authorized")) {
				request.setAttribute("eventId", request.getParameter("eventId"));
				return "media_upload";
			}
			return null;
		}
		return "user_login";
	}

	@RequestMapping(value = "/media/upload", method = RequestMethod.POST)
	public String processUpload(@RequestParam(value = "file", required = false) MultipartFile[] files,
			HttpServletRequest request) throws Exception {
		if ((Boolean) request.getAttribute("hasLoggedIn")) {
			if ((Boolean) request.getAttribute("authorized")) {
				int eventId = 0;
				try {
					eventId = Integer.parseInt(request.getParameter("eventId"));
				} catch (Exception e) {
					e.printStackTrace();
				}

				String pathRoot = request.getSession().getServletContext().getRealPath("");
				String path = "";

				if (eventId != 0) {
					Event oldEvent = eventService.retrieveEvent(eventId);
					Event newEvent = new Event();
					for (MultipartFile file : files) {
						if (!file.isEmpty()) {
							String uuid = UUID.randomUUID().toString().replaceAll("-", "");
							String contentType = file.getContentType();
							System.out.println(contentType);
							String fileName = contentType.substring(contentType.indexOf("/") + 1);

							if (fileName.equals("mpeg")) {
								fileName = "mp3";
							}

							if (contentType.contains("image")) {
								path = "/resources/static/images/" + uuid + "." + fileName;
								newEvent.setImageUrl(path);
							}
							if (contentType.contains("audio")) {
								path = "/resources/static/audios/" + uuid + "." + fileName;
								newEvent.setAudioUrl(path);
							}

							File location = new File(pathRoot + path);
							if (!location.exists()) {
								location.mkdirs();
							}
							file.transferTo(location);
						}
					}
					eventService.updateEvent(oldEvent, newEvent);
					request.setAttribute("eventId", eventId);
					return "media_upload_success";
				}
			}
			return null;
		}
		return "user_login";
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(@RequestParam("keyword") String keyword, HttpServletRequest request) throws Exception {
		System.out.println("start searching...");
		request.setAttribute("events", eventService.retrieveEventsByKeyword(keyword));
		return "search_result";
	}

	@RequestMapping(value = "/comments", method = RequestMethod.GET)
	public String viewComments(Model model, HttpServletRequest request) {
		int eventId = 0;
		int option = 2;

		try {
			eventId = Integer.parseInt(request.getParameter("eventId"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (eventId != 0) {
			Event event = eventService.retrieveEvent(eventId);
			List<Comment> comments = commentService.retrieveComments(event);
			List<Comment> positiveComments = commentService.retrievePositiveComments(comments);
			List<Comment> neutralComments = commentService.retrieveNeutralComments(comments);
			List<Comment> negativeComments = commentService.retrieveNegativeComments(comments);

			Map<String, Double> rates = RatingUtil.calRates(comments);

			if (rates != null) {
				request.setAttribute("positiveRate", rates.get("positiveRate"));
				
				System.out.println(rates.get("neutralRate"));
				
				request.setAttribute("neutralRate", rates.get("neutralRate"));
				request.setAttribute("negativeRate", rates.get("negativeRate"));
			}

			try {
				option = Integer.parseInt(request.getParameter("option"));
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (option != 2) {
				switch (option) {
				case 1:
					request.setAttribute("comments", positiveComments);
					break;
				case 0:
					request.setAttribute("comments", neutralComments);
					break;
				case -1:
					request.setAttribute("comments", negativeComments);
					break;
				}
			} else {
				request.setAttribute("comments", comments);
			}
			request.setAttribute("event", event);
			return "event_comments";
		}

		return null;
	}

}
