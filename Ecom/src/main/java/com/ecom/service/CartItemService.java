package com.ecom.service;

import com.ecom.exception.CartItemException;
import com.ecom.exception.UserException;
import com.ecom.model.Cart;
import com.ecom.model.CartItem;
import com.ecom.model.Product;

public interface CartItemService {
	
	public CartItem createCartItem(CartItem cartItem);
	
	public CartItem updateCartItem(Long userId,Long id,CartItem cartItem) throws CartItemException,UserException;

    public CartItem isCartItemExists(Cart cart,Product product,String size,Long userid);

    public void removeCartItem(Long userid,Long cartItemId) throws CartItemException,UserException;
    
    public CartItem findCartItemById(Long cartItemid) throws CartItemException;

}
