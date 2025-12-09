package com.itp.ITPAugustSpringboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itp.ITPAugustSpringboot.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>
{

	List<Product> findByCategoryContaining(String a);
	
	List<Product> findByPriceGreaterThanEqual(double a);
}
