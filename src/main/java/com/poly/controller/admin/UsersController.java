package com.poly.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.poly.bean.User;
import com.poly.repository.UserRepository;
import com.poly.service.UserService;

@Controller
public class UsersController {
    
    @Autowired
    UserService udao;

    @RequestMapping("/admin/manage/user/getform/{id}")
		public String getForm(Model model, @ModelAttribute("user") User entity, @PathVariable("id") String id,
				@RequestParam("p") Optional<Integer> p) {
			// Pageable pageable;
			// try {
			// 	pageable = PageRequest.of(p.orElse(0), 5);
			// } catch (Exception e) {
			// 	pageable = PageRequest.of(0, 5);
			// }
			entity = udao.findById(id);
			model.addAttribute("user", entity);
			// Page<User> list = dao.findAll(pageable);
            List<User> list = udao.findActiveUsers();
			model.addAttribute("list", list);
			return "admin/manage/users";
		}
		
    @RequestMapping("/admin/manage/user/update/{id}")
    public String update(Model model, @ModelAttribute("user") User entity) {
    	udao.update(entity);
    	return "redirect:/admin/manage/users";
    }
}
