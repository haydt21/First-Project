package com.poly.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.OrderDetail;

public interface  OrderDetailRepository extends JpaRepository<OrderDetail, String> {

	@Query("SELECT o.product.id, o.product.name, COUNT(o.id) AS soLuong FROM OrderDetail o GROUP BY o.product.id, o.product.name")
	List<Object[]> getTop10SPBanChay(Pageable page);
	
	@Query("SELECT o.product.name , SUM(o.order.total * o.product.price) AS tongTien FROM OrderDetail o GROUP BY o.product.name")
	List<Object[]> getDoanhThu(Pageable page);
}
