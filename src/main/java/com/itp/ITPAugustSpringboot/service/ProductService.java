package com.itp.ITPAugustSpringboot.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itp.ITPAugustSpringboot.controller.ProductController;
import com.itp.ITPAugustSpringboot.dto.ProductRequestDTO;
import com.itp.ITPAugustSpringboot.dto.ProductResponseDTO;
import com.itp.ITPAugustSpringboot.entity.Product;
import com.itp.ITPAugustSpringboot.exception.ProductNotFoundException;
import com.itp.ITPAugustSpringboot.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ModelMapper modelMapper;

	private static final Logger logger=Logger.getLogger(ProductService.class);
	
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
		logger.warn(ex1.getMessage() +  " " +pid);
		throw new ProductNotFoundException("Product you are searching is not in stock");
	}
	
	}

	public List<Product> filterProductByCategory(String category) {
	return productRepository.findByCategory(category);
	}

	public List<Product> filterProductByPriceGreaterThan(double price) {
		return productRepository.findByPriceGreaterThanEqual(price);
	}

	public void deleteProduct(int pid) {
		logger.info("Request received in service to delete product ID " +pid);
		if(!productRepository.existsById(pid))
		{
			throw new ProductNotFoundException("Product with ID "+ pid + " does not exist in the Database");
		}
		productRepository.deleteById(pid);
		
	}

	public Product updateProduct(int pid, Product newValues) {
		if(!productRepository.existsById(pid))
		{
			throw new ProductNotFoundException("Product with ID "+ pid + " does not exist in the Database");
		}
		
		Product productFromDB=singleProduct(pid);
		productFromDB.setCategory(newValues.getCategory());
		productFromDB.setDescription(newValues.getDescription());
		productFromDB.setImage(newValues.getImage());
		productFromDB.setPrice(newValues.getPrice());
		productFromDB.setTitle(newValues.getTitle());
		if(newValues.getRating()!=null)
		{
		productFromDB.getRating().setCount(newValues.getRating().getCount());
		productFromDB.getRating().setRate(newValues.getRating().getRate());
		}
		return productRepository.save(productFromDB);
	}

	public ProductResponseDTO addProductDTO(ProductRequestDTO productReqDTO) 
	{
		Product product = modelMapper.map(productReqDTO, Product.class);
		Product savedProduct=productRepository.save(product);
		return modelMapper.map(savedProduct, ProductResponseDTO.class);
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