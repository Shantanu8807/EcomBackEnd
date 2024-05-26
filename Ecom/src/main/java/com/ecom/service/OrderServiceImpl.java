package com.ecom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.exception.OrderException;
import com.ecom.model.Addres;
import com.ecom.model.Order;
import com.ecom.model.User;
import com.ecom.repository.CartRepo;


@Service
public class OrderServiceImpl implements OrderService{
    
	@Autowired
	private CartRepo cartRepo;
	
	@Autowired
	private CartService cartItemService;
	
	@Autowired
	private ProductService productService;
	
	
	@Override
	public Order createOrder(User user, Addres shippingAdres) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order findOrderBuId(Long orderid) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> userOrderHistory(Long userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order placedOrder(Long orderid) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order confirmedOrder(Long orderid) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order shippedOrder(Long orderid) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order deliveredOrder(Long orderid) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order cancledOrder(Long orderid) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getAllOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteOrder(Long orderid) throws OrderException {
		// TODO Auto-generated method stub
		
	}

}
