package com.itp.ITPAugustSpringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class ProductResponseDTO {
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;
    private RatingResponseDTO rating;
}
