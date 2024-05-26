package com.ecom.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecom.exception.ProductException;
import com.ecom.model.Review;
import com.ecom.model.User;
import com.ecom.request.ReviewRequest;

@Service
public interface ReviewService {
    
	public Review createReview(ReviewRequest req,User user) throws ProductException;
	
	
	public List<Review> getAllProductReview(Long productId);
	
}
