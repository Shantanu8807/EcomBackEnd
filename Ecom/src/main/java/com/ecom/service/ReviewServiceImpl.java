package com.ecom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ecom.exception.ProductException;
import com.ecom.model.Product;
import com.ecom.model.Review;
import com.ecom.model.User;
import com.ecom.repository.ReviewRepo;
import com.ecom.request.ReviewRequest;

public class ReviewServiceImpl implements ReviewService{
     
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ReviewRepo reviewRepo;
	@Override
	public Review createReview(ReviewRequest req, User user) throws ProductException {
		Product product=productService.findProductById(req.getProductId());
		Review review= new Review();
		review.setProduct(product);
		review.setUser(user);
		review.setReview(req.getReview());
		return reviewRepo.save(review);
	}

	@Override
	public List<Review> getAllProductReview(Long productId) {
		return reviewRepo.getAllProductsReview(productId);
	}

}
