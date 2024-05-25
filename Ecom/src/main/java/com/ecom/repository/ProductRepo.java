package com.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecom.model.Product;


@Repository
public interface ProductRepo extends JpaRepository<Product, Long>{
    
	
	@Query("select p from Product p " +
		       "where (p.category.name = :category or :category = '') " +
		       "and ((:minPrice is null and :maxPrice is null) or " +
		       "(p.discountedPrice between :minPrice and :maxPrice)) " +
		       "and (:minDiscount is null or p.discountPresent >= :minDiscount) " +
		       "order by " +
		       "case when :sort = 'price_low' then p.discountedPrice end asc, " +
		       "case when :sort = 'price_high' then p.discountedPrice end desc")
	public List<Product> filterProducts(@Param("category")String category,
			@Param("minPrice")Integer minPrice,
			@Param("maxPrice")Integer maxPrice,
			@Param("minDiscount")Integer minDiscount,
			@Param("sort")String sort
			);
	
	
}
