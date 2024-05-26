package com.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.model.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long>{

}
