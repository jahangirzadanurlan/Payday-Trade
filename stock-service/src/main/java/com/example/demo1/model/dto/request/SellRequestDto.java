package com.example.demo1.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellRequestDto {
    private String symbol;
    private int offerPrice;

}
