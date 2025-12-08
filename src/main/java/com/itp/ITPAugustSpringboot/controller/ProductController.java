package com.itp.ITPAugustSpringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itp.ITPAugustSpringboot.entity.Product;
import com.itp.ITPAugustSpringboot.entity.Rating;
import com.itp.ITPAugustSpringboot.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@PostMapping("/add-product")
	public String addProduct()
	{
		Product product1=Product.builder()
				.title("Mobile")
				.price(1000.0)
				.description("One Plus 15 16GB RAM 1TB Storage")
				.category("Electronics")
				.image("https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_t.png")
				.rating(Rating.builder().rate(3.9).count(120).build())
				.build();
		
		productService.addProduct(product1);
		
		return "product addedd Successfully";
	}
	
	@PostMapping("/add-product-by-requestparam")
	public String addProductByRequestParam(@RequestParam("productTitle") String pTitle,
			@RequestParam("b") double pPrice,
			@RequestParam("c") String pDesc,
			@RequestParam("d") String pCat,
			@RequestParam("e") String pImg,
			@RequestParam("f") double ratRate,
			@RequestParam("g") int ratCount
			)
	{
		Product product1=Product.builder()
				.title(pTitle)
				.price(pPrice)
				.description(pDesc)
				.category(pCat)
				.image(pImg)
				.rating(Rating.builder().rate(ratRate).count(ratCount).build())
				.build();
		
		productService.addProduct(product1);
		
		return "product addedd Successfully";
	}

	
	@PostMapping("/add-product-by-pathvariable/{a}/{b}/{c}/{d}/{e}/{f}/{g}")
	public Product addProductByPathVariable(@PathVariable("a") String pTitle,
			@PathVariable("b") double pPrice,
			@PathVariable("c") String pDesc,
			@PathVariable("d") String pCat,
			@PathVariable("e") String pImg,
			@PathVariable("f") double ratRate,
			@PathVariable("g") int ratCount
			)
	{
		Product product1=Product.builder()
				.title(pTitle)
				.price(pPrice)
				.description(pDesc)
				.category(pCat)
				.image(pImg)
				.rating(Rating.builder().rate(ratRate).count(ratCount).build())
				.build();
		
		return productService.addProduct(product1);
		
		//return "product addedd Successfully";
	}

	
	@PostMapping("/add-product-by-requestbody")
	public Product addProductByRequestBody(@RequestBody Product product1)
	{
		return productService.addProduct(product1);
	}

}

/*
 {
    "title": "TV",
    "price": 4000.0,
    "description": "LG LED TV",
    "category": "Electronics",
    "image": "myImage2.png",
    "rating": {
        "rid": 3,
        "rate": 4.8,
        "count": 5000
    }
}
*/
