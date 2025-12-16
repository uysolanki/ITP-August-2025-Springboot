package com.itp.ITPAugustSpringboot.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.itp.ITPAugustSpringboot.entity.Product;
import com.itp.ITPAugustSpringboot.service.ProductService;

@Controller
@RequestMapping("/flipkart")
public class ProductControllerFE {
	
	@Autowired
	ProductService productService;
	
	private static final Logger logger=Logger.getLogger(ProductControllerFE.class);
	
	@RequestMapping("/csk")
	public String demo1(Model model)
	{
		return "mahi";
	}
	
	@RequestMapping("/allProducts")
	public String allProducts(Model model)
	{
		List<Product> products=productService.allProducts();
		model.addAttribute("products",products);
		return "all-products";
	}
	
	@RequestMapping("/delete-product/{pid}")
	public String deleteProduct(@PathVariable int pid)
	{
		logger.info("Request received in controller to delete product ID " +pid);
		productService.deleteProduct(pid);
		return "redirect:/flipkart/allProducts";
	}
	
	@RequestMapping("/add-product-form")
	public ModelAndView addProductForm()
	{
		ModelAndView mav=new ModelAndView();
		mav.setViewName("add-product-form");
		Product product= new Product();
		mav.addObject("product", product);
		return mav;
	}
	
	@PostMapping("/add-product")
	public String addProductByRequestBody(@ModelAttribute Product product1)
	{
		productService.addProduct(product1);
		return "redirect:/flipkart/allProducts";
	}
	
	@RequestMapping("/update-product-form/{pid}")
	public ModelAndView updateProductForm(@PathVariable int pid)
	{
		ModelAndView mav=new ModelAndView();
		mav.setViewName("update-product-form");
		Product product= productService.singleProduct(pid);
		mav.addObject("product", product);
		return mav;
	}
	
	@PostMapping("/update-product/{prodId}")
	public String updateProduct(@PathVariable int prodId,@ModelAttribute Product newValues)
	{
		
		productService.updateProduct(prodId,newValues);
		return "redirect:/flipkart/allProducts";
	}
}

