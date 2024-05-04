package com.learning.controller;

import com.learning.model.Role;
import com.learning.model.User;
import com.learning.service.ProductService;
import com.learning.service.RoleService;
import com.learning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    ProductService productService;
//    @GetMapping("/register")
//    public String register(ModelMap model) {
//        User u = new User();
////		u.setUsername("");
//        model.addAttribute("USER", u);
//        model.addAttribute("ACTION", "/saveOrUpdate");
//        return "register-user";
//    }

    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostMapping("/saveOrUpdate")
    public String saveOrUpdate(@ModelAttribute("USER") User user) {
//		UserDAO dao = new UserDAO();
//		dao.save(user);
//        int role_id = 2;
//        Role role = roleService.findById(2).get();
//		user.setRole(role);
//        userService.save(user);

        System.out.println("Da vao dang ky");
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setFullname(user.getFullname());
        newUser.setMail(user.getMail());
        newUser.setRoles(user.getRoles());
        newUser.setFullname(user.getFullname());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));


        userService.registerDefaultUser(newUser);
        System.out.println("Dang Ky Thanh cong");
        return "log_regform";
    }
}

