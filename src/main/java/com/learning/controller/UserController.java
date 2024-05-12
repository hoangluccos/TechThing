package com.learning.controller;

import com.learning.model.InvoiceDetail;
import com.learning.model.Invoices;
import com.learning.service.InvoicesDetailService;
import com.learning.service.InvoicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
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
import java.util.*;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	
	@Autowired
    private ProductService productService;

	//---- user home
	@GetMapping("/user/home")
	public String userHome(Model model, HttpSession session)
	{
//		UserDetails userDetails = (UserDetails) session.getAttribute("userDetails");
//		System.out.println(userDetails.getUsername());
//		model.addAttribute("products", productService.findAll());
//		return "user/index";


		//---phan trang - pagination
		String keyword = "";
		return viewPage(model, 1, "id", "asc", keyword, session);

	}
	//---- begin phan trang - pagination
	@RequestMapping("/page/{pageNum}")
	public String viewPage(Model model, @PathVariable(name = "pageNum")int pageNum,
						   @Param("sortField") String sortField,
						   @Param("sortDir")String sortDir,
						   @Param("keyword")String keyword, HttpSession session){
		UserDetails userDetails = (UserDetails) session.getAttribute("userDetails");
		System.out.println(userDetails.getUsername());

		//recieve page from service
		Page<Product> page =  productService.listAll(pageNum,sortField, sortDir,keyword);

		//get value from page.getContent()
		List<Product> listProducts = page.getContent();

		// Get image sources for each product
		Map<Integer, List<String>> imageSrcsByProductId = new HashMap<>();
		for (Product product : listProducts) {
			List<String> imageSrcs = productService.getImageSrcsByProductId(product.getProduct_id());
			imageSrcsByProductId.put(product.getProduct_id(), imageSrcs);
		}

		model.addAttribute("imageSrcsByProductId", imageSrcsByProductId);
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listProducts", listProducts);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("keyword", keyword);


		//reverse asc : tang dan ; desc giam dan; ascending and descending
		String reverseDir = sortDir.equals("asc") ? "desc" : "asc";
		model.addAttribute("reverseDir", reverseDir);

		return "user/index";
	}

	//end - pagination

	//---- category laptop
	@GetMapping("/user/category/laptop/{pageNum}")
	public String category_laptop(Model model, @PathVariable(name = "pageNum")int pageNum,
								  @Param("keyword")String keyword)
	{
		return category(model, pageNum, keyword, "1");
	}

	public String category (Model model, @PathVariable(name = "pageNum")int pageNum,
							@Param("keyword")String keyword, String id){
		String sortDir = "asc";
		String sortField = "id";
//		String id = "1";
		//recieve page from service
		Page<Product> page =  productService.listAllByCategory(pageNum,sortField, sortDir,id);

		//get value from page.getContent()
		List<Product> listProducts = page.getContent();
		Map<Integer, List<String>> imageSrcsByProductId = new HashMap<>();
		for (Product product : listProducts) {
			List<String> imageSrcs = productService.getImageSrcsByProductId(product.getProduct_id());
			imageSrcsByProductId.put(product.getProduct_id(), imageSrcs);
		}

		model.addAttribute("imageSrcsByProductId", imageSrcsByProductId);

		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listProducts", listProducts);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("keyword", keyword);


		//reverse asc : tang dan ; desc giam dan; ascending and descending
		String reverseDir = sortDir.equals("asc") ? "desc" : "asc";
		model.addAttribute("reverseDir", reverseDir);

		return "user/index";


	}
	@GetMapping("/user/category/phone/{pageNum}")
	public String category_phone(Model model, @PathVariable(name = "pageNum")int pageNum,
								 @Param("keyword")String keyword)
	{
		return category(model, pageNum, keyword, "2");

	}
	@GetMapping("/user/category/tablet/{pageNum}")
	public String category_tablet(Model model, @PathVariable(name = "pageNum")int pageNum,
								  @Param("keyword")String keyword)
	{
		return category(model, pageNum, keyword, "3");

	}
	@GetMapping("/user/category/watch/{pageNum}")
	public String category_watch(Model model, @PathVariable(name = "pageNum")int pageNum,
								 @Param("keyword")String keyword)
	{
		return category(model, pageNum, keyword, "4");

	}
	@GetMapping("/user/category/pc/{pageNum}")
	public String category_pc(Model model, @PathVariable(name = "pageNum")int pageNum,
							  @Param("keyword")String keyword)
	{
		return category(model, pageNum, keyword, "5");

	}




	//--------------------Belong to Admin--------------------
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
	
	@Autowired
	InvoicesService invoicesService;
	@Autowired
	InvoicesDetailService invoicesDetailService;
	@GetMapping("/user/user_info")
	public String userInfo(Model model , HttpSession session)
	{
		//user info
		UserDetails userDetails = (UserDetails) session.getAttribute("userDetails");
		String name = userDetails.getUsername();

		//lsu don hang
		List<Invoices> dh = invoicesService.findByName(name);
		List<InvoiceDetail> detaildh = new ArrayList<>();
		if (dh != null) {
			for (Invoices invoice : dh) {
				List<InvoiceDetail> details = invoicesDetailService.findByInvoiceID(invoice.getInvoice_id());
				detaildh.addAll(details);
			}
		}
		else{
			detaildh = new  ArrayList<>();
		}

		model.addAttribute("users", userService.findByUsername(name));

		model.addAttribute("lsdh", detaildh);
		return "user/user_info";
	}
	@GetMapping("/user/user_info/edit")
	public String editProfile(Model model, HttpSession session){
		UserDetails userDetails = (UserDetails) session.getAttribute("userDetails");


		model.addAttribute("user", userService.findByUsername(userDetails.getUsername()));
		return "user/edit";
	}
	@PostMapping("/user/save")
	public String saveUser(@ModelAttribute ("user") User user ){
		userService.save(user);
		return "redirect:/user/user_info";
	}
}
