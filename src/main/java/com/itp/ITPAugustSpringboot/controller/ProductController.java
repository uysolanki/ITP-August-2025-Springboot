package com.itp.ITPAugustSpringboot.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itp.ITPAugustSpringboot.entity.Product;
import com.itp.ITPAugustSpringboot.entity.Rating;
import com.itp.ITPAugustSpringboot.exception.ProductNotFoundException;
import com.itp.ITPAugustSpringboot.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	private static final Logger logger=Logger.getLogger(ProductController.class);
	
	@PostMapping("/add-product")
	public ResponseEntity<String>  addProduct()
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
		
		return new ResponseEntity<String>("product addedd Successfully",HttpStatus.OK);
	}
	
	@PostMapping("/add-product-by-requestparam")
	public ResponseEntity<String> addProductByRequestParam(@RequestParam("productTitle") String pTitle,
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
		
		return new ResponseEntity<String>("product addedd Successfully",HttpStatus.OK);
	}

	
	@PostMapping("/add-product-by-pathvariable/{a}/{b}/{c}/{d}/{e}/{f}/{g}")
	public ResponseEntity<Product> addProductByPathVariable(@PathVariable("a") String pTitle,
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
		
		return new ResponseEntity<Product>(productService.addProduct(product1),HttpStatus.CREATED);
		
		//return "product addedd Successfully";
	}

	
	@PostMapping("/add-product-by-pathvariable1/{pTitle}/{pPrice}/{pDesc}/{pCat}/{pImg}/{ratRate}/{ratCount}")
	public ResponseEntity<Product> addProductByPathVariable1(@PathVariable String pTitle,
			@PathVariable double pPrice,
			@PathVariable String pDesc,
			@PathVariable String pCat,
			@PathVariable String pImg,
			@PathVariable double ratRate,
			@PathVariable int ratCount
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
		
		return new ResponseEntity<Product>(productService.addProduct(product1),HttpStatus.CREATED);
		
		//return "product addedd Successfully";
	}

	
	
	@PostMapping("/add-product-by-requestbody")
	public ResponseEntity<Product> addProductByRequestBody(@RequestBody Product product1)
	{
		return new ResponseEntity<Product>(productService.addProduct(product1),HttpStatus.CREATED);
	}
	
	@PostMapping("/add-multiple-products-by-requestbody")
	public ResponseEntity<List<Product>> addMultipleProductsByRequestBody(@RequestBody List<Product> products)
	{
		return new ResponseEntity<List<Product>>(productService.addMultipleProducts(products),HttpStatus.CREATED);
	}
	
	@GetMapping("/all-products")
	public ResponseEntity<List<Product>> allProducts()
	{
		return new ResponseEntity<List<Product>>(productService.allProducts(),HttpStatus.OK);
	}
	
	@GetMapping("/single-product/{pid}")
	public ResponseEntity<Product> singleProduct(@PathVariable int pid)
	{	
		return new ResponseEntity<Product>(productService.singleProduct(pid),HttpStatus.OK);
	}
	
	@GetMapping("/single-product1/{pid}")
	public ResponseEntity<?> singleProduct1(@PathVariable int pid)
	{
		System.out.println("Request received in controller for product id " + pid);
		try
		{
		return new ResponseEntity<Product>(productService.singleProduct(pid),HttpStatus.OK);
		}
		catch(RuntimeException ex1)
		{
			return new ResponseEntity<String>(ex1.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping("/single-product2/{pid}")
	public ResponseEntity<?> singleProduct2(@PathVariable int pid)
	{
		System.out.println("Request received in controller for product id " + pid);
		try
		{
		return new ResponseEntity<Product>(productService.singleProduct2(pid),HttpStatus.OK);
		}
		catch(ProductNotFoundException ex1)
		{
			return new ResponseEntity<String>(ex1.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/single-product3/{pid}")
	public ResponseEntity<Product> singleProduct3(@PathVariable int pid)
	{
		return new ResponseEntity<Product>(productService.singleProduct2(pid),HttpStatus.OK);
	}
	
	
	@GetMapping("/filter-product-by-category/{category}")
	public ResponseEntity<List<Product>> filterProductByCategory(@PathVariable String category)
	{
		return new ResponseEntity<List<Product>>(productService.filterProductByCategory(category),HttpStatus.OK);
	}
	
	
	@GetMapping("/filter-product-by-price-greaterthan/{price}")
	public ResponseEntity<List<Product>> filterProductByPriceGreaterThan(@PathVariable double price)
	{
		return new ResponseEntity<List<Product>>(productService.filterProductByPriceGreaterThan(price),HttpStatus.OK);
	}
	
	
	@DeleteMapping("/delete-product/{pid}")
	public ResponseEntity<String> deleteProduct(@PathVariable int pid)
	{
		logger.info("Request received in controller to delete product ID " +pid);
		productService.deleteProduct(pid);
		return new ResponseEntity<String>("Product Deleted with ID "+pid,HttpStatus.OK);
	}
	
	@PutMapping("/update-product/{pid}")
	public ResponseEntity<Product> updateProduct(@PathVariable int pid, @RequestBody Product newValues)
	{
		return new ResponseEntity<Product>(productService.updateProduct(pid,newValues),HttpStatus.OK);
	}
	//aop aspect oriented progamming
	
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
