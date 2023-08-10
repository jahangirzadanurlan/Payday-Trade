package com.example.paydaytrade.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BuySellRequestDto {
    @NotBlank(message = "Symbol is required")
    private String symbol;

    @NotBlank(message = "Username is required")
    private String username;

    @Min(value = 0, message = "Money must be a positive value")
    private int offerPrice;
}
