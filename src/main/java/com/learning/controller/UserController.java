package com.learning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.learning.model.User;
import com.learning.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;

import java.lang.ProcessBuilder.Redirect;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {
	@Autowired
	UserService userService;

	@GetMapping("/user")
	public String listUser(Model model)
	{
		return "admin/user";
	}
	
//	@GetMapping("/register")
//	public String addOrEdit(ModelMap model) {
//		User u = new User();
//		model.addAttribute("USER", u);
//		model.addAttribute("ACTION", "/saveOrUpdate");
//		return "register-user";
//	}

//	@PostMapping("/saveOrUpdate")
//	public String saveOrUpdate(ModelMap model, @ModelAttribute("USER") User user) {
////		UserDAO dao = new UserDAO();
////		dao.save(user);
////		System.out.println("Thanh cong");
//		user.setRole_id(2);
//		userService.save(user);
//		return "register-user";
//	}

	
}
