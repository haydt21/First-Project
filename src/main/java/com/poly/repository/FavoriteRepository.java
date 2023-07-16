package com.poly.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poly.bean.Favorite;
import com.poly.bean.Product;
import com.poly.bean.User;

public interface  FavoriteRepository extends JpaRepository<Favorite, Long> {
	@Query("SELECT c FROM Favorite c WHERE c.user.id =?1")
	List<Favorite> findByUserId(String userId);
	
	@Query(value = "SELECT COUNT(*) FROM Favorites f WHERE f.UserID = :userID AND f.ProductID = :productID", nativeQuery = true)
	Integer isProductLikedByUser(@Param("userID") String userID, @Param("productID") String productID);
	
	Optional<Favorite> findByUserAndProduct(User user, Product product);
}
