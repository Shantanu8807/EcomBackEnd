package com.ecom.service;

import org.springframework.stereotype.Service;

import com.ecom.exception.ProductException;
import com.ecom.model.Cart;
import com.ecom.model.User;
import com.ecom.request.AddItemRequest;

@Service
public interface CartService {
	
	public Cart createCart(User user);
	
	public String addCartItem(Long userid,AddItemRequest req) throws ProductException;
	
	public Cart findUserCart(Long userId);
	
	
	

}
