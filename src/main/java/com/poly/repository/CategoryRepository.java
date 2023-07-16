package com.poly.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.bean.Category;

public interface  CategoryRepository extends JpaRepository<Category, String> {
	
}
