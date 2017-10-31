package com.emsrepo.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emsrepo.domain.Logger;
import com.emsrepo.service.EventService;
import com.emsrepo.service.LoggerService;
import com.emsrepo.service.UserService;
import com.emsrepo.vo.EventVO;
import com.emsrepo.vo.MsgVO;
import com.emsrepo.vo.UserVO;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class AdminController {

	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@Autowired
	@Qualifier("eventService")
	private EventService eventService;

	@Autowired
	@Qualifier("loggerService")
	private LoggerService loggerService;

	@RequestMapping("/admin")
	public String toAdmin(Model model, HttpServletRequest request) {
		if ((Boolean) request.getAttribute("hasLoggedIn")) {
			if ((Boolean) request.getAttribute("isAdmin")) {
				return "admin";
			}
			return null;
		}
		return "user_login";
	}

	@RequestMapping(value = "/admin/{uid}", method = RequestMethod.GET)
	@ResponseBody
	public String getAdminUserById(@PathVariable Integer uid, HttpServletRequest request) throws Exception {
//		if ((Boolean) request.getAttribute("hasLoggedIn")) {
//			if ((Boolean) request.getAttribute("isAdmin")) {
				UserVO admin = userService.getUserVOById(uid);
				ObjectMapper mapper = new ObjectMapper();
				String json = "";
				json = mapper.writeValueAsString(admin);
				return json;
//			}
//			return null;
//		}
//		return "user_login";
	}

	@RequestMapping("/admin/user-mgmt")
	public String toUserMgmt(Model model, HttpServletRequest request) {
		if ((Boolean) request.getAttribute("hasLoggedIn")) {
			if ((Boolean) request.getAttribute("isAdmin")) {
				return "user-mgmt";
			}
			return null;
		}
		return "user_login";
	}

	@RequestMapping(value = "/admin/getUserList", method = RequestMethod.GET)
	@ResponseBody
	public String getUserVOList(Model model, HttpServletRequest request) throws Exception {
//		if ((Boolean) request.getAttribute("hasLoggedIn")) {
//			if ((Boolean) request.getAttribute("isAdmin")) {
				List<UserVO> userList = userService.getGeneralUserVOList();
				ObjectMapper mapper = new ObjectMapper();
				String json = "";
				json = mapper.writeValueAsString(userList);
				return json;
//			}
//			return null;
//		}
//		return "user_login";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/admin/denyUserStatus", method = RequestMethod.POST)
	@ResponseBody
	public String denyUserStatus(@RequestBody String param) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, Integer.class);
		List<Integer> uidList = (List<Integer>) mapper.readValue(param, javaType);

		String status = "fail";

		try {
			if (CollectionUtils.isEmpty(uidList)) {

				System.out.println("--- AdminController updateUserStatus wrong parameters fail ---");
			} else {
				userService.batchUpdateUserStatus(uidList, "DENY");
				status = "ok";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			status = "fail";
			System.out.println("--- AdminController updateUserStatus exception fail ---");
		}

		MsgVO msg = new MsgVO();
		msg.setStatus(status);

		String json = "";
		json = mapper.writeValueAsString(msg);
		return json;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/admin/passUserStatus", method = RequestMethod.POST)
	@ResponseBody
	public String passUserStatus(@RequestBody String param) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, Integer.class);
		List<Integer> uidList = (List<Integer>) mapper.readValue(param, javaType);

		String status = "fail";

		try {
			if (CollectionUtils.isEmpty(uidList)) {

				System.out.println("--- AdminController updateUserStatus wrong parameters fail ---");
			} else {
				userService.batchUpdateUserStatus(uidList, "PASS");
				status = "ok";
			}
		} catch (Exception e) {
			e.printStackTrace();
			status = "fail";
			System.out.println("--- AdminController updateUserStatus exception fail ---");
		}

		MsgVO msg = new MsgVO();
		msg.setStatus(status);

		String json = "";
		json = mapper.writeValueAsString(msg);
		return json;
	}

	@RequestMapping(value = "/admin/event-mgmt", method = RequestMethod.GET)
	public String toEventMgmt(Model model, HttpServletRequest request) {
		if ((Boolean) request.getAttribute("hasLoggedIn")) {
			if ((Boolean) request.getAttribute("isAdmin")) {
				return "event-mgmt";
			}
			return null;
		}
		return "user_login";
	}

	@RequestMapping(value = "/admin/getEventList", method = RequestMethod.GET)
	@ResponseBody
	public String getEventVOList(Model model, HttpServletRequest request) throws Exception {
//		if ((Boolean) request.getAttribute("hasLoggedIn")) {
//			if ((Boolean) request.getAttribute("isAdmin")) {
				List<EventVO> voList = eventService.getAllEventList();
				ObjectMapper mapper = new ObjectMapper();
				String json = "";
				json = mapper.writeValueAsString(voList);
				return json;
//			}
//			return null;
//		}
//		return "user_login";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/admin/approveEvent", method = RequestMethod.POST)
	@ResponseBody
	public String approveEvent(@RequestBody String param) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, Integer.class);
		List<Integer> eidList = (List<Integer>) mapper.readValue(param, javaType);

		String status = "fail";

		try {
			if (CollectionUtils.isEmpty(eidList)) {

				System.out.println("--- AdminController updateUserStatus wrong parameters fail ---");
			} else {
				eventService.batchUpdateEventStatus(eidList, "APPROVED");
				status = "ok";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			status = "fail";
			System.out.println("--- AdminController updateUserStatus exception fail ---");
		}

		MsgVO msg = new MsgVO();
		msg.setStatus(status);

		String json = "";
		json = mapper.writeValueAsString(msg);
		return json;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/admin/denyEvent", method = RequestMethod.POST)
	@ResponseBody
	public String denyEvent(@RequestBody String param) throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, Integer.class);
		List<Integer> eidList = (List<Integer>) mapper.readValue(param, javaType);

		String status = "fail";

		try {
			if (CollectionUtils.isEmpty(eidList)) {

				System.out.println("--- AdminController updateUserStatus wrong parameters fail ---");
			} else {
				eventService.batchUpdateEventStatus(eidList, "DENIED");
				status = "ok";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			status = "fail";
			System.out.println("--- AdminController updateUserStatus exception fail ---");
		}

		MsgVO msg = new MsgVO();
		msg.setStatus(status);

		String json = "";
		json = mapper.writeValueAsString(msg);
		return json;
	}

	@RequestMapping("/admin/log-list")
	public String toLogList(Model model, HttpServletRequest request) {
		if ((Boolean) request.getAttribute("hasLoggedIn")) {
			if ((Boolean) request.getAttribute("isAdmin")) {
				return "log-list";
			}
			return null;
		}
		return "user_login";
	}

	@RequestMapping(value = "/admin/getLogList", method = RequestMethod.GET)
	@ResponseBody
	public String getLogList(Model model, HttpServletRequest request) throws Exception {
		List<Logger> logList = loggerService.getLoggerDetailList();
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		json = mapper.writeValueAsString(logList);
		return json;
	}
}
