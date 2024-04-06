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

import com.learning.model.User;
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
	
	@GetMapping({"/login",  "/"})
	public String showLogin() {
		return "loginform";
	}
	//try with @pathvariable
	@PostMapping("/checklogin")
	public String checkLogin(ModelMap model, @RequestParam("username") String username,
			@RequestParam("password") String password , HttpSession session)  {
		if (userService.checkLogin(username, password)) {
			if(userService.authorization(username, password)) {
//				System.out.println("login thanh cong");
				//Authorization here
				//true la admin
				return "admin/home";
			}
			else {
//			System.out.println("login thanh cong");
//			day la user/home
			return "user/index";
			}
		}
		else {
			System.out.println("login that bai");
			model.addAttribute("ERROR", "Username or Password not exist");
			return "loginform";
		}
		
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("username");
		return "login";
	}
	
}
