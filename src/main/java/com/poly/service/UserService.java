package com.poly.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.poly.bean.User;
import com.poly.repository.UserRepository;
@Service
public class UserService {
	@Autowired
	UserRepository udao;
	
    public List<User> findActiveUsers(){
    	return udao.findAll();
    }
	
    public Page<User> findActiveUsers(Pageable pageable){
    	return udao.findAll(pageable);
    }
    
    public User findById(String id) {
    	return udao.getOne(id);
    }
    
    public User update(User entity) {
    	return udao.saveAndFlush(entity);
    }
    
    public User delete(User entity) {
    	return udao.saveAndFlush(entity);
    }
    public List<String> getAllEmails(){
    	return udao.getAllEmails();
    }
	
    public List<String> getAllPhones(){
    	return udao.getAllPhones();
    }
	
    public Page<User> findUserByNamePage(String name, Pageable pageable){
		return udao.findUserByNamePage(name, pageable);
	}
    
    public User create(User entity) {
    	return udao.saveAndFlush(entity);
    }
}
