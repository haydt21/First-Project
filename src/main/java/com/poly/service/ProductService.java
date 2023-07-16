package com.poly.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.poly.bean.Category;
import com.poly.bean.Product;
import com.poly.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository pdao;

	public List<Product> findRandomProducts() {
		return pdao.findRandomProducts();
	}

	public List<Product> findAll() {
		return pdao.findAll();
	}

	public Page<Product> findAll(Pageable pageable) {
		return pdao.findAll(pageable);
	}

	public Product findById(String id) {
		return pdao.getOne(id);
	}

	public Object entity(Product id) {
		return pdao.entity(id);
	}

	public Page<Product> findProductByNamePage(String name, Pageable pageable) {
		return pdao.findProductByNamePage(name, pageable);
	}

	public List<Product> findRandomRelateProducts() {
		return pdao.findRandomRelateProducts();
	}

	public Page<Product> findByNamePage(String name, Pageable pageable) {
		return pdao.findByNamePage(name, pageable);
	}

	public Page<Product> findByCategory(Category category, Pageable pageable) {
		return pdao.findByCategory(category, pageable);
	}

	public Product create(Product entity) {
		return pdao.saveAndFlush(entity);
	}
	
	public Product update(Product entity) {
		return pdao.saveAndFlush(entity);
	}
	
}