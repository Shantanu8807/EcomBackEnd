package com.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecom.model.Review;

@Repository
public interface ReviewRepo extends JpaRepository<Review,Long>{
  
	@Query("select * from review r where r.product.id=:productId")
	public List<Review> getAllProductsReview(@Param("producId")Long productId);
}
