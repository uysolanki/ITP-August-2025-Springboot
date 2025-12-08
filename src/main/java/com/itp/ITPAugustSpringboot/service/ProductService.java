package com.itp.ITPAugustSpringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itp.ITPAugustSpringboot.entity.Product;
import com.itp.ITPAugustSpringboot.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	public Product addProduct(Product product1) {
		return productRepository.save(product1);
		
	}
}
