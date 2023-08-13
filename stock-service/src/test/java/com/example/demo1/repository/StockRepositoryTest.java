package com.example.demo1.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.demo1.model.entity.Stock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {StockRepository.class})
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.example.demo1.model.entity"})
@DataJpaTest(properties = {"spring.main.allow-bean-definition-overriding=true"})
class StockRepositoryTest {
    @Autowired
    private StockRepository stockRepository;

    @Test
    void testFindStockByName() {
        Stock stock = new Stock();
        stock.setCountry("GB");
        stock.setCurrency("GBP");
        stock.setExchange("Exchange");
        stock.setMic_code("Mic code");
        stock.setName("Name");
        stock.setPrice(1);
        stock.setSymbol("Symbol");
        stock.setType("Type");

        Stock stock2 = new Stock();
        stock2.setCountry("GBR");
        stock2.setCurrency("USD");
        stock2.setExchange("com.example.demo1.model.entity.Stock");
        stock2.setMic_code("com.example.demo1.model.entity.Stock");
        stock2.setName("com.example.demo1.model.entity.Stock");
        stock2.setPrice(0);
        stock2.setSymbol("com.example.demo1.model.entity.Stock");
        stock2.setType("com.example.demo1.model.entity.Stock");
        stockRepository.save(stock);
        stockRepository.save(stock2);
        assertTrue(stockRepository.findStockByName("Name").isPresent());
    }

    @Test
    void testFindStockBySymbol() {
        Stock stock = new Stock();
        stock.setCountry("GB");
        stock.setCurrency("GBP");
        stock.setExchange("Exchange");
        stock.setMic_code("Mic code");
        stock.setName("Name");
        stock.setPrice(1);
        stock.setSymbol("Symbol");
        stock.setType("Type");

        Stock stock2 = new Stock();
        stock2.setCountry("GBR");
        stock2.setCurrency("USD");
        stock2.setExchange("com.example.demo1.model.entity.Stock");
        stock2.setMic_code("com.example.demo1.model.entity.Stock");
        stock2.setName("com.example.demo1.model.entity.Stock");
        stock2.setPrice(0);
        stock2.setSymbol("com.example.demo1.model.entity.Stock");
        stock2.setType("com.example.demo1.model.entity.Stock");
        stockRepository.save(stock);
        stockRepository.save(stock2);
        assertTrue(stockRepository.findStockBySymbol("Symbol").isPresent());
    }
}

