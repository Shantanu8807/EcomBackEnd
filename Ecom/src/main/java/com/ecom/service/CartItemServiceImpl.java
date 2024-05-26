package com.ecom.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.exception.CartItemException;
import com.ecom.exception.UserException;
import com.ecom.model.Cart;
import com.ecom.model.CartItem;
import com.ecom.model.Product;
import com.ecom.model.User;
import com.ecom.repository.CartItemRepo;
import com.ecom.repository.CartRepo;

@Service
public class CartItemServiceImpl implements CartItemService {

	@Autowired
	private CartItemRepo cartItemRepo;
	@Autowired
	private UserService userService;
	@Autowired
	private CartRepo cartRepo;

	@Override
	public CartItem createCartItem(CartItem cartItem) {
		cartItem.setQuantity(1);
		cartItem.setPrice(Integer.parseInt(cartItem.getProduct().getPrice()) * cartItem.getQuantity());
		cartItem.setDicountedPrice(cartItem.getProduct().getDiscountedPrice() * cartItem.getQuantity());

		CartItem createdCartItem = cartItemRepo.save(cartItem);
		return createdCartItem;
	}

	@Override
	public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
		CartItem item = findCartItemById(id);
		User user = userService.findUserById(item.getUserid());

		if (user.getId().equals(userId)) {
			item.setQuantity(cartItem.getQuantity());
			item.setPrice(item.getPrice() * Integer.parseInt(item.getProduct().getPrice()));
			item.setDicountedPrice(item.getProduct().getDiscountedPrice() * item.getQuantity());
		}

		return cartItemRepo.save(item);

	}

	@Override
	public CartItem isCartItemExists(Cart cart, Product product, String size, Long userid) {
		CartItem cartItem = cartItemRepo.isCartItemExists(cart, product, size, userid);

		return cartItem;
	}

	@Override
	public void removeCartItem(Long userid, Long cartItemId) throws CartItemException, UserException {
		
		CartItem cartItem = findCartItemById(cartItemId);
		User user=userService.findUserById(cartItem.getUserid());
		
		User reqUser=userService.findUserById(userid);
		
		if(reqUser.getId().equals(user.getId()))
		{
			cartItemRepo.deleteById(cartItemId);
		}
		else
		{
			throw new UserException("Item not found");
		}
		

	}

	@Override
	public CartItem findCartItemById(Long cartItemid) throws CartItemException {
		Optional<CartItem> opt= cartItemRepo.findById(cartItemid);
		
		if(opt.isPresent())
		{
			return opt.get();
		}
	throw new CartItemException("cartItem not foumd with id: "+cartItemid);
	}

}
