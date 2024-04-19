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

import com.learning.model.Product;
import com.learning.model.User;
import com.learning.service.ProductService;
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
	
	@Autowired
    private ProductService productService;

	@GetMapping("/admin/user")
	public String listUser(Model model)
	{
		model.addAttribute("users", userService.findAll());
		return "admin/user";

	}
	
	/*
	 * @GetMapping("/user/user_info/edit/{id}") public String
	 * userEditInfo(@PathVariable String id, Model model) { model.addAttribute(id,
	 * model) }
	 */
	
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
	
	@GetMapping("/admin/user/delete/{id}")
	public String deleteUser(@PathVariable String id) {
		userService.deleteById(id);
		return "redirect:/admin/user";
	}
	
}
