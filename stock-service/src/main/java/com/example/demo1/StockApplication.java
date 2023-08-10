package com.example.demo1;

import com.example.demo1.model.entity.Stock;
import com.example.demo1.repository.StockRepository;
import com.example.demo1.service.Impl.StockApiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
@EnableScheduling
@RequiredArgsConstructor
public class StockApplication /*implements CommandLineRunner*/ {
//    private final StockRepository stockRepository;
//    private final StockApiClient stockApiClient;

    public static void main(String[] args) {
        SpringApplication.run(StockApplication.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        List<Stock> stocks = stockApiClient.getAllProduct();
//
//        for (Stock stock : stocks){
//            int randomPrice= ThreadLocalRandom.current().nextInt(100,800);
//            stock.setPrice(randomPrice);
//
//            Stock stockEntity = Stock.builder()
//                    .country(stock.getCountry())
//                    .name(stock.getName())
//                    .price(stock.getPrice())
//                    .symbol(stock.getSymbol())
//                    .type(stock.getType())
//                    .currency(stock.getCurrency())
//                    .mic_code(stock.getMic_code())
//                    .exchange(stock.getExchange())
//                    .build();
//            stockRepository.save(stockEntity);
//        }
//
//    }
}
