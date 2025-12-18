package com.itp.ITPAugustSpringboot.dto;

import com.itp.ITPAugustSpringboot.util.Category;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class ProductRequestDTO {
	@NotBlank(message = "Title is required")
	@Size(min = 3, max = 100, message = "Title must be between 6 and 100 characters") 
    private String title;
	
	@Min(value = 1, message = "Price must be greater than 0")
	@Max(value = 10000, message = "Price must be less than 10000")
    private double price;
	
	@NotBlank(message = "Title is required")
	@Size(min = 3, max = 300, message = "Title must be between 3 and 300 characters") 
    private String description;
	
    private Category category;
    private String image;
    private RatingRequestDTO rating;
}


/*
@NotBlank rejects
null
"" (empty string) 
"    " (whitespace-only string)
 */
