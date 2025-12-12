package com.itp.ITPAugustSpringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class RatingRequestDTO {
	private double rate;
	private int count;
}
