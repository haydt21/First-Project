package com.poly.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.poly.entity.Order;

@Repository
public interface  OrderRepository extends JpaRepository<Order, String> {
	
//	@Query("SELECT o FROM Order o")
//	List<Order> findAll();
}
