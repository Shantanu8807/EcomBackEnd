package com.ecom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecom.model.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long>{

	public Category findByName(String name);
	
	@Query("SELECT c FROM Category c WHERE c.name = :name AND c.parentCategory.name = :parentCategoryName")
	public Category findByNameAndParent(@Param("name")String name,@Param("parentCategoryName")String parentCategoryName);
}
