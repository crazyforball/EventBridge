package com.emsrepo.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.emsrepo.domain.Category;
import com.emsrepo.domain.User;
import com.emsrepo.service.CategoryService;

@Controller
@RequestMapping(value = "/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String viewMyBookings(Map<String, Object> model, HttpServletRequest request) {
		if ((Boolean) request.getAttribute("hasLoggedIn")) {
			if ((Boolean) request.getAttribute("isAdmin")) {
				List<Category> categoryList = categoryService.getCategoryList();
				System.out.println(categoryList.size());
				request.setAttribute("categoryList", categoryList);
				return "category_home";
			}
			return null;
		}
		return "user_login";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String viewBooking(Map<String, Object> model, HttpServletRequest request) {
		if ((Boolean) request.getAttribute("hasLoggedIn")) {
			if ((Boolean) request.getAttribute("isAdmin")) {
				Category category = new Category();
				model.put("category", category);
				return "category_add";
			}
			return null;
		}
		return "user_login";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processBooking(@ModelAttribute("category") Category category, HttpServletRequest request) {
		if (categoryService.registerCategory(category)) {
			User user = (User) request.getSession().getAttribute("user");
			return "redirect: home?adminName=" + user.getUsername();
		}
		return "category_add_failure";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteBooking(Map<String, Object> model, HttpServletRequest request) {
		if ((Boolean) request.getAttribute("hasLoggedIn")) {
			if ((Boolean) request.getAttribute("isAdmin")) {
				User user = (User) request.getSession().getAttribute("user");
				int cid = 0;
				try {
					cid = Integer.parseInt(request.getParameter("categoryId"));
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (cid != 0) {
					Category categoryToDelete = categoryService.getCategory(cid);
					categoryService.deleteCategory(categoryToDelete);
					return "redirect: home?adminName=" + user.getUsername();
				}
			}
			return null;
		}
		return "user_login";
	}

}
