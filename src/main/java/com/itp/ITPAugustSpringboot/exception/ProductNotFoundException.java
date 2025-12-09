package com.itp.ITPAugustSpringboot.exception;

public class ProductNotFoundException extends RuntimeException
{
	
	public ProductNotFoundException (String message)
	{
		super(message);
	}
}
