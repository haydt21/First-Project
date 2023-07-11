package com.poly.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Category;
import com.poly.entity.Product;
	
public interface  ProductRepository extends JpaRepository<Product, String> {

	@Query(value = "SELECT TOP 8 * FROM Products ORDER BY NEWID()", nativeQuery = true)
    List<Product> findRandomProducts();
	
	@Query("SELECT p FROM Product p WHERE p.quantity > 0")
	List<Product> findAll();
	
	@Query("SELECT p FROM Product p WHERE p.quantity > 0")
	Page<Product> findAll(Pageable pageable);
	
	@Query("UPDATE Product p SET p.quantity = 0 WHERE p.id =?1")
	Object entity(Product id);
	
	@Query("SELECT p FROM Product p WHERE p.name LIKE ?1")
	Page<Product> findProductByNamePage(String name, Pageable pageable);
	
	
	@Query(value = "SELECT TOP 6 * FROM Products ORDER BY NEWID()", nativeQuery = true)
    List<Product> findRandomRelateProducts();
	

	
	@Query("SELECT p FROM Product p WHERE p.name LIKE ?1")
	Page<Product> findByNamePage(String name, Pageable pageable);
	
	@Query("SELECT p FROM Product p WHERE p.category = ?1")
    Page<Product> findByCategory(Category category,Pageable pageable);
}
