package com.ecom.service;

import java.util.List;

import com.ecom.exception.OrderException;
import com.ecom.model.Addres;
import com.ecom.model.Order;
import com.ecom.model.User;

public interface OrderService {
	
	
	public Order createOrder(User user,Addres shippingAdres);
	
	public Order findOrderBuId(Long orderid) throws OrderException;
    
	public List<Order> userOrderHistory(Long userid);
	
	Order placedOrder(Long orderid) throws OrderException;
	
	public Order confirmedOrder(Long orderid) throws OrderException;
	
	public Order shippedOrder(Long orderid) throws OrderException;
	
	public Order deliveredOrder(Long orderid) throws OrderException;
	
	public Order cancledOrder(Long orderid) throws OrderException;
	
	public List<Order> getAllOrders();
	
	public void deleteOrder(Long orderid) throws OrderException;
	
	
	

}
