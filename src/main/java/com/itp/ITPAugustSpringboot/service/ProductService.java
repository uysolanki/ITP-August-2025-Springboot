package com.itp.ITPAugustSpringboot.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itp.ITPAugustSpringboot.entity.Product;
import com.itp.ITPAugustSpringboot.exception.ProductNotFoundException;
import com.itp.ITPAugustSpringboot.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	public Product addProduct(Product product1) {
		return productRepository.save(product1);
		
	}

	public List<Product> addMultipleProducts(List<Product> products) {
		return productRepository.saveAll(products);
	}

	public List<Product> allProducts() {
		return productRepository.findAll();
	}

	public Product singleProduct(int pid) 
	{
	System.out.println("Request received in controller for product id " + pid);
	Optional<Product> optionalProduct=productRepository.findById(pid);
	Product product=null;
	if(optionalProduct.isPresent())
	{
		product=optionalProduct.get();
		return product;
	}
	throw new RuntimeException("Product you are searching is not in stock");
	}
	
	
	public Product singleProduct2(int pid) 
	{
	System.out.println("Request received in service for product id " + pid);
	Optional<Product> optionalProduct=productRepository.findById(pid);
	Product product=null;
	try
	{
		product=optionalProduct.get();
		return product;
	}
	catch(NoSuchElementException ex1)
	{
		System.out.println(ex1.getMessage() +  " " +pid);
		throw new ProductNotFoundException("Product you are searching is not in stock");
	}
	
	}

	public List<Product> filterProductByCategory(String category) {
	return productRepository.findByCategoryContaining(category);
	}

	public List<Product> filterProductByPriceGreaterThan(double price) {
		return productRepository.findByPriceGreaterThanEqual(price);
	}
	
	
	
}



/*
try
{
	product=optionalProduct.get();
	return product;
}
throw new RuntimeException("Product you are searching is not in stock");
*/