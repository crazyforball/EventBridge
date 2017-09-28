package com.emsrepo.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.emsrepo.entity.User;
import com.emsrepo.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		    
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		User u = userService.getUser(1);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("account", u.getEmail() );
		model.addAttribute("serverTime", formattedDate );
		return "home";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String toLogin(Model model) {
		
		return "login";
	}
	
	@RequestMapping(value="/login_fail", method=RequestMethod.GET)
	public String loginFail(Model model) {
		
		model.addAttribute("login_error", "username or password is incorrect, please re-enter!");
		return "login";
	}
	
	@RequestMapping(value="/login_success", method=RequestMethod.GET)
	public String loginSuccess(Model model) {
		return "home";
	}
}
