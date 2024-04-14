package com.learning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.learning.model.Role;
import com.learning.model.User;
import com.learning.service.RoleService;
import com.learning.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

import java.lang.ProcessBuilder.Redirect;
import java.util.List;
import java.util.Optional;

@Controller
public class LoginController {
	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;

	@GetMapping({ "/login", "/" })
	public String showLogin(ModelMap model) {
		User u = new User();
//		u.setUsername("");
		model.addAttribute("USER", u);
		model.addAttribute("ACTION", "/saveOrUpdate");
//		return "loginform";
		return "log_regform";
	}

	@PostMapping("/saveOrUpdate")
	public String saveOrUpdate(ModelMap model, @ModelAttribute("USER") User user) {
//		UserDAO dao = new UserDAO();
//		dao.save(user);	
		int role_id = 2;
		Role role = roleService.findById(2).get();
//		user.setRole(role);
		userService.save(user);
		System.out.println("Thanh cong");
		return "log_regform";
	}

	// try with @pathvariable
	@PostMapping("/checklogin")
	public void checkLogin() {}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("username");
//		return "loginform";
		return "log_regform";
	}

}
