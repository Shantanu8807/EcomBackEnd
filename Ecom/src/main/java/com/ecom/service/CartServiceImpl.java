package com.ecom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.exception.ProductException;
import com.ecom.model.Cart;
import com.ecom.model.CartItem;
import com.ecom.model.Product;
import com.ecom.model.User;
import com.ecom.repository.CartRepo;
import com.ecom.request.AddItemRequest;

@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	private CartRepo cartRepo;
	@Autowired
	private CartItemService cartItemService;
	@Autowired
	private ProductService productService;
	
	

	@Override
	public Cart createCart(User user) {
		Cart cart = new Cart();
		cart.setUser(user);
		
		return cartRepo.save(cart);
		
	}

	@Override
	public String addCartItem(Long userid, AddItemRequest req) throws ProductException {
		Cart cart=cartRepo.findByUserId(userid);
		
		Product product=productService.findProductById(req.getProductId());
		CartItem isPresent=cartItemService.isCartItemExists(cart, product, req.getSize(), userid);
	    
		if(isPresent==null)
		{
			CartItem cartItem=new CartItem();
			cartItem.setProduct(product);
			cartItem.setCart(cart);
			cartItem.setQuantity(req.getQuantity());
			cartItem.setUserid(userid);
			int price=req.getQuantity()*product.getDiscountedPrice();
			
			cartItem.setPrice(price);
			cartItem.setSize(req.getSize());
			
			CartItem createdCartItem= cartItemService.createCartItem(cartItem);
			
			cart.getCartItems().add(createdCartItem);
		}
		
		return "Item added Succesfully";
	
	}

	@Override
	public Cart findUserCart(Long userId) {
		Cart cart=cartRepo.findByUserId(userId);
		
		int totalPrice=0;
		int totalDiscountedPrice=0;
		int totalItem=0;
		for(CartItem c:cart.getCartItems())
		{
			totalPrice+=c.getPrice();
			totalDiscountedPrice+=c.getDicountedPrice();
			totalItem+=c.getQuantity();
		}
		
		cart.setTotalDicountedPrice(totalDiscountedPrice);
		cart.setTotalPrice(totalPrice);
		cart.setTotalItem(totalItem);
		cart.setDiscount(totalPrice-totalDiscountedPrice);
		return cartRepo.save(cart);
	}

}
