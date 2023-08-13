package com.example.demo1.controller;

import static org.mockito.Mockito.when;

import com.example.demo1.model.dto.request.SellRequestDto;
import com.example.demo1.model.entity.Stock;
import com.example.demo1.service.IStockService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {StockController.class})
@ExtendWith(SpringExtension.class)
class StockControllerTest {
    @MockBean
    private IStockService iStockService;

    @Autowired
    private StockController stockController;

    @Test
    void testShowStockData() throws Exception {
        when(iStockService.findAllStocks()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/stocks");
        MockMvcBuilders.standaloneSetup(stockController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testShowStockData2() throws Exception {
        when(iStockService.findAllStocks()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/stocks");
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(stockController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testStockById() throws Exception {
        when(iStockService.sellStock(Mockito.<SellRequestDto>any())).thenReturn(1);

        SellRequestDto sellRequestDto = new SellRequestDto();
        sellRequestDto.setOfferPrice(1);
        sellRequestDto.setSymbol("Symbol");
        String content = (new ObjectMapper()).writeValueAsString(sellRequestDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/stock/sell")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(stockController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("1"));
    }

    @Test
    void testStockBySymbol() throws Exception {
        Stock stock = new Stock();
        stock.setCountry("GB");
        stock.setCurrency("GBP");
        stock.setExchange("Exchange");
        stock.setId(1L);
        stock.setMic_code("Mic code");
        stock.setName("Name");
        stock.setPrice(1);
        stock.setSymbol("Symbol");
        stock.setType("Type");
        when(iStockService.findStockBySymbol(Mockito.<String>any())).thenReturn(stock);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/stock").param("symbol", "foo");
        MockMvcBuilders.standaloneSetup(stockController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"symbol\":\"Symbol\",\"name\":\"Name\",\"currency\":\"GBP\",\"exchange\":\"Exchange\",\"mic_code\":\"Mic"
                                        + " code\",\"country\":\"GB\",\"type\":\"Type\",\"price\":1}"));
    }
}

