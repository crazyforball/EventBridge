package com.emsrepo.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.emsrepo.domain.User;
import com.emsrepo.service.UserService;

@Controller
@RequestMapping(value = "/account")
public class AccountController {

	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String viewRegistration(Map<String, Object> model) {
		User userForm = new User();
		model.put("userForm", userForm);
		return "user_registration";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String processRegistration(@ModelAttribute("userForm") User user, HttpServletRequest request) {
		if (userService.registerUser(user)) {
			User current_user = userService.getUser(user.getUsername());
			request.getSession().setAttribute("user", current_user);
			return "user_registration_success";
		}
		return "user_registration_failure";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String viewLogin() {
		return "user_login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String processLogin(@RequestParam("username") String username, @RequestParam("password") String password,
			HttpServletRequest request) {
		if (userService.loginUser(username, password)) {
			User current_user = userService.getUser(username);
			request.getSession().setAttribute("user", current_user);
			return "user_login_success";
		}
		return "user_login_failure";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String viewMyAccount(Map<String, Object> model, HttpServletRequest request) {
		if ((Boolean) request.getAttribute("hasLoggedIn")) {
			return "account_home";
		}
		return "user_login";
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String viewUpdate(Map<String, Object> model, HttpServletRequest request) {
		if ((Boolean) request.getAttribute("hasLoggedIn")) {
			if ((Boolean) request.getAttribute("authorized")) {
				return "account_update";
			}
			return null;
		}
		return "user_login";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String processUpdate(@RequestParam("password") String password,
			@RequestParam("newPassword1") String newPassword1, @RequestParam("newPassword2") String newPassword2,
			@RequestParam("phone") String phone, @RequestParam("email") String email, HttpServletRequest request) {
		Map<String, String> userInfo = new HashMap<String, String>();
		userInfo.put("password", password);
		userInfo.put("newPassword1", newPassword1);
		userInfo.put("newPassword2", newPassword2);
		userInfo.put("phone", phone);
		userInfo.put("email", email);

		String username = request.getParameter("username");
		User user = null;
		if (username != null) {
			user = userService.getUser(username);
		}
		if (user != null) {
			if (userService.updateUser(user, userInfo)) {
				request.getSession().setAttribute("user", user);
				return "account_home";
			}
			return "account_update_failure";
		}
		return null;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logOut(HttpServletRequest request) {
		request.getSession().removeAttribute("user");
		return "user_login";
	}
}
