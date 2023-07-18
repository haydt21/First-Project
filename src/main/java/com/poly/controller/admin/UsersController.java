package com.poly.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.bean.User;
import com.poly.service.SessionService;
import com.poly.service.UserService;

@Controller
public class UsersController {

	@Autowired
	UserService udao;

	@Autowired
	SessionService session;

	@RequestMapping("/admin/manage/users")
	public String users(Model model, @RequestParam("p") Optional<Integer> p,
			@RequestParam(defaultValue = "id", name = "sortField") String sortField,
			@RequestParam(defaultValue = "asc", name = "sortDir") String sortDir) {
		Sort sort = sortDir.equals("desc") ? Sort.by(sortField).descending() : Sort.by(sortField).ascending();
		Pageable pageable;
		try {
			pageable = PageRequest.of(p.orElse(0), 5, sort);
		} catch (Exception e) {
			pageable = PageRequest.of(0, 5, sort);
		}
		Page<User> list = udao.findActiveUsers(pageable);
		// List<User> list = udao.findActiveUsers();
		User entity = new User();
		System.out.println(sortField + ", " + sortDir);
		model.addAttribute("sortField", sortField);
		model.addAttribute("list", list);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
		model.addAttribute("user", entity);
		return "admin/manage/users";
	}

	@RequestMapping("/admin/manage/user/getform/{id}")
	public String getForm(Model model, @ModelAttribute("user") User entity, @PathVariable("id") String id,
			@RequestParam("p") Optional<Integer> p, Pageable pageable) {
		try {
			pageable = PageRequest.of(p.orElse(0), 5);
		} catch (Exception e) {
			pageable = PageRequest.of(0, 5);
		}
		entity = udao.findById(id);
		model.addAttribute("user", entity);
		Page<User> list = udao.findActiveUsers(pageable);
		// List<User> list = udao.findActiveUsers();
		model.addAttribute("list", list);
		return "admin/manage/users";
	}

	@RequestMapping("/admin/manage/user/update/{id}")
	public String update(Model model, @ModelAttribute("user") User entity, @PathVariable("id") String id) {
		User user = udao.findById(id);
		entity.setPassword(user.getPassword());
		entity.setActive(user.getActive());
		udao.update(entity);
		return "redirect:/admin/manage/users";
	}

	@RequestMapping("/admin/manage/user/delete/{id}")
	public String delete(Model model, @ModelAttribute("user") User entity, @PathVariable("id") String id) {
		User user = udao.findById(id);
		entity.setPassword(user.getPassword());
		entity.setActive(false);
		udao.delete(entity);
		return "redirect:/admin/manage/users";
	}

	@PostMapping("/admin/manage/user/save")
	public String saveNew(Model model, @Validated @ModelAttribute("user") User users, Errors errors) {
		if (errors.hasErrors()) {
			return "admin/manage/users";
		}
		users.setPassword("1");
		users.setActive(true);
		users.setRole(true);
		udao.create(users);
		return "redirect:/admin/manage/users";
	}

	@RequestMapping("/admin/manage/users/search")
	public String seachProduct(Model model, @RequestParam("keyword") Optional<String> name,
			@RequestParam("p") Optional<Integer> p, @ModelAttribute("user") User entity) {
		String findName;
		if (session.getAttribute("keyword") == null) {
			findName = name.orElse("");
		} else {
			findName = name.orElse(session.getAttribute("keyword"));
		}
		session.setAttribute("keyword", findName);
		Pageable pageable = PageRequest.of(p.orElse(0), 5);
		Page<User> list = udao.findUserByNamePage("%" + findName + "%", pageable);
		model.addAttribute("list", list);
		return "admin/manage/users";
	}
}
