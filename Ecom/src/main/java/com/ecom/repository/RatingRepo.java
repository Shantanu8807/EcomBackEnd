package com.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecom.model.Rating;


@Repository
public interface RatingRepo extends JpaRepository<Rating,Long>{
   
	@Query("select r from rating r where r.product.id=:productId")
	public List<Rating> getAllRatings(@Param("productId")Long producId);

}
