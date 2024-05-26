package com.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecom.model.Cart;
import com.ecom.model.CartItem;
import com.ecom.model.Product;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Long > {

	
	@Query("select ci from cartitem ci where ci.cart=:cart and ci.product=:product and ci.size=:size and ci.userId=:userId")
	public CartItem isCartItemExists(@Param("cart") Cart cart,@Param("product") Product product,@Param("size")String size,@Param("userId")Long userId);
}
