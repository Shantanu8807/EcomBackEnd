package com.ecom.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ecom.exception.ProductException;
import com.ecom.model.Category;
import com.ecom.model.Product;
import com.ecom.repository.CategoryRepo;
import com.ecom.repository.ProductRepo;
import com.ecom.request.CreateProductRequest;


@Service
public class ProductServiceImpl implements ProductService {
     
	@Autowired
	private ProductRepo productRepo;
	
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private UserService userService;
	
	
	@Override
	public Product createProduct(CreateProductRequest req) {
		
		Category topLevel=categoryRepo.findByName(req.getTopLevelCategory());
		if(topLevel==null)
		{
			Category topLevelCategory=new Category();
			topLevelCategory.setName(req.getTopLevelCategory());;
			topLevelCategory.setLevel(1);
			topLevel=categoryRepo.save(topLevelCategory);
		}
		
		Category secondLevel=categoryRepo.findByNameAndParent(req.getSecondLevelCategory(),topLevel.getName());
		if(secondLevel==null)
		{
			Category secondLevelCategory=new Category();
			secondLevelCategory.setName(req.getTopLevelCategory());;
			secondLevelCategory.setLevel(1);
			secondLevel=categoryRepo.save(secondLevelCategory);
		}
		
		Category thirdLevel=categoryRepo.findByNameAndParent(req.getThirdLevelCategory(),secondLevel.getName());
		if(thirdLevel==null)
		{
			Category thirdLevelCategory=new Category();
			thirdLevelCategory.setName(req.getTopLevelCategory());;
			thirdLevelCategory.setLevel(1);
			thirdLevel=categoryRepo.save(thirdLevelCategory);
		}
		
		Product product = new Product();
		product.setTitle(req.getTitle());
		product.setColor(req.getColor());
		product.setBrand(req.getBrand());
		product.setCategory(thirdLevel);
		product.setCreatedAt(LocalDateTime.now());
		product.setQuantity(req.getQuantity());
		product.setSizes(req.getSize());
		product.setImageUrl(req.getImageUrl());
		product.setPrice(req.getPrice());
		product.setDiscountedPrice(req.getDiscountedPrice());
		product.setDesription(req.getDescription());
		product.setDiscountPresent(req.getDiscountPercent());
		
		
		Product savedProduct=productRepo.save(product);
		
		
		
		return savedProduct;
	}

	@Override
	public String deleteProduct(Long productId) throws ProductException {
		Product product=findProductById(productId);
	   
			product.getSizes().clear();
			productRepo.delete(product);
			return "Product deleted successfully";
	
	}

	@Override
	public Product updateProduct(Long productId, Product req) throws ProductException {
		Product product=findProductById(productId);
		if(req.getQuantity()!=0)
		{
			product.setQuantity(req.getQuantity());
		}
		return productRepo.save(product);
	}

	@Override
	public Product findProductById(Long id) throws ProductException {
	  
		
		Optional<Product> product= productRepo.findById(id);
		if(product.isPresent())
		{
			return product.get();
		}
		throw new ProductException("Product not found with id :"+id);
	}

	@Override
	public List<Product> findProductByCategory(String category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Product> getALlProduct(String category, List<String> colors, List<String> sizes, Integer minPrice,
			Integer maxPrice, Integer minDiscount, String sort, String stock,Integer pageNumber, Integer pageSize) {
		
		Pageable pageble=PageRequest.of(pageNumber, pageSize);
		
		List<Product> products=productRepo.filterProducts(category, minPrice, maxPrice, minDiscount, sort);
		
		if(!colors.isEmpty())
		{
			products=products.stream().filter(p->colors.stream().anyMatch(c->c.equalsIgnoreCase(p.getColor()))).collect(Collectors.toList());
		}
		
		if(stock!=null)
		{
			if(stock.equals("in_stock"))
			{
				products=products.stream().filter(p->p.getQuantity()>0).collect(Collectors.toList());
				
			}
			else if(stock.equals("out_of_stock"))
			{
				products=products.stream().filter(p->p.getQuantity()<1).collect(Collectors.toList());
				
			}
		}
		
		int startIndex=(int)pageble.getOffset();
		int endIndex=Math.min(startIndex + pageble.getPageSize(),products.size());
		List<Product> pageContent=products.subList(startIndex, endIndex);
		
		Page<Product> filterProducts= new PageImpl<>(pageContent,pageble,products.size());
		
		
		
		return null;
	}

}
