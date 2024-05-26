package com.ecom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.exception.ProductException;
import com.ecom.model.Product;
import com.ecom.model.Rating;
import com.ecom.model.User;
import com.ecom.repository.RatingRepo;
import com.ecom.request.RatingRequest;


@Service
public class RatingServiceImpl implements RatingService {
     
	@Autowired
	private RatingRepo ratingRepo;
	
	@Autowired
	private ProductService productService;
	
	@Override
	public Rating createRating(RatingRequest req, User user) throws ProductException {
		Product product=productService.findProductById(req.getProductId());
		Rating rating= new Rating();
		rating.setProduct(product);
		rating.setUser(user);
		rating.setRating(req.getRating());
		return ratingRepo.save(rating);
	}

	@Override
	public List<Rating> getProductsRating(Long productId) {
		return ratingRepo.getAllRatings(productId);
	}

	
}
