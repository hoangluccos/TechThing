package com.learning.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.learning.service.UserService;

@Controller
public class AdminController {
	@Autowired
	UserService userService;
	@GetMapping("/admin")
	public String adminHome(HttpSession session)
	{
		UserDetails userDetails = (UserDetails) session.getAttribute("userDetails");
		System.out.println(userDetails.getUsername());
		return "admin/homeAdmin";
	}
	@GetMapping("/admin/user")
	public String listUser(Model model)
	{
		model.addAttribute("users", userService.findAll());
		return "admin/user";

	}
	@GetMapping("/admin/user/delete/{id}")
	public String deleteUser(@PathVariable String id) {
		userService.deleteById(id);
		return "redirect:/admin/user";
	}
}
