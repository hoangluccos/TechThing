package com.learning.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
	
	@GetMapping("/admin")
	public String adminHome(HttpSession session)
	{
		UserDetails userDetails = (UserDetails) session.getAttribute("userDetails");
		System.out.println(userDetails.getUsername());
		return "admin/home";
	}
}
