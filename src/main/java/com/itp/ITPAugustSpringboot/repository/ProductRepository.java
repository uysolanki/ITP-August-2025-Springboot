package com.itp.ITPAugustSpringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itp.ITPAugustSpringboot.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>
{

}
