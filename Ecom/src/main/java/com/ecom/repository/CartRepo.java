package com.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecom.model.Cart;

@Repository
public interface CartRepo extends JpaRepository<Cart, Long> {
    
	
	@Query("slect c from cart c where c.user.id=:userId")
	public Cart findByUserId(@Param("userId") Long userId);
}
