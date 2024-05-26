package com.ecom.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
	
	
	@Id
	@ManyToOne
	private Order order;
	
	@ManyToOne 
	private Product product;
	
	
	private String size;
	
	private int quantity;
	
	private Integer price;
	
	private Integer discounterPrice;
	
	private Long userId;
	
	private LocalDateTime deliveryDate;
	
	
	
	
	
	

}
