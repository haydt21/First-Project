package com.poly.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.bean.Product;
import com.poly.bean.User;
import com.poly.repository.UserRepository;
import com.poly.service.ProductService;
import com.poly.service.UserService;

@Controller
public class AdminController {

	@Autowired
	UserService udao;

	@Autowired
	ProductService pdao;
	@RequestMapping("/admin/index")
	public String index() {
		return "admin/index";
	}

	@RequestMapping("/admin/manage/users")
	public String users(Model model, @RequestParam("p") Optional<Integer> p) {
		// Pageable pageable;
		// try {
		// pageable = PageRequest.of(p.orElse(0), 5);
		// } catch (Exception e) {
		// pageable = PageRequest.of(0, 5);
		// }
		// Page<User> list = udao.findAll(pageable);
		List<User> list = udao.findActiveUsers();
		User entity = new User();
		model.addAttribute("list", list);
		model.addAttribute("user", entity);
		return "admin/manage/users";
	}

	@RequestMapping("/admin/manage/products")
	public String products(Model model, @RequestParam("p") Optional<Integer> p) {
		// Pageable pageable;
		// try {
		// pageable = PageRequest.of(p.orElse(0), 5);
		// } catch (Exception e) {
		// pageable = PageRequest.of(0, 5);
		// }
		// Page<User> list = udao.findAll(pageable);
		List<Product> list = pdao.findAll();
		Product entity = new Product();
		model.addAttribute("list", list);
		model.addAttribute("user", entity);
		return "admin/manage/products";
	}

	@RequestMapping("/admin/manage/bills")
	public String bills() {
		return "admin/manage/bills";
	}

}
