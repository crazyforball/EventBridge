package com.emsrepo.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.emsrepo.domain.Event;
import com.emsrepo.service.CategoryService;
import com.emsrepo.service.EventService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private EventService eventService;

	/**
	 * Simply selects the home view to render by returning its name.
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request) throws Exception {
		List<Event> events = eventService.retrieveLatestNEvents(5);

		request.getSession().setAttribute("events", events);
		request.getSession().setAttribute("categories", categoryService.getCategoryList());
		return "home";
	}

	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String contact() {
		return "contact";
	}

}
