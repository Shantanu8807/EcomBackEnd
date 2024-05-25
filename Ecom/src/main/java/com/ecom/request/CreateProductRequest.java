package com.ecom.request;

import java.util.HashSet;
import java.util.Set;

import com.ecom.model.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {
	
	private String title;
	private String description;
	private String price;
	private int discountedPrice;
	private int discountPercent;
	private int quantity;
	private String brand;
	private String color;
	private Set<Size> size = new HashSet<>();
	private String imageUrl;
	private String topLevelCategory;
	private String secondLevelCategory;
	private String thirdLevelCategory;
	
	
	
}
