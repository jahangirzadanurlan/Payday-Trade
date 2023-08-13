package com.example.demo1.service.Impl;

import com.example.demo1.exception.ApplicationException;
import com.example.demo1.model.entity.Stock;
import com.example.demo1.model.enums.Exceptions;
import com.example.demo1.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class ScheduledTaskService {
    private final StockRepository stockRepository;

    @Scheduled(initialDelay = 40 * 60 * 1000, fixedRate = 40 * 60 * 1000) //40 minute
    public void updateDatabase() throws IOException {
        List<Stock> stocks = stockRepository.findAll();

        for (Stock stock : stocks){
            Optional<Stock> _product = stockRepository.findById(stock.getId());

            int randomPrice= ThreadLocalRandom.current().nextInt(100,800);
            _product.orElseThrow(()-> new ApplicationException(Exceptions.STOCK_NOT_FOUND_EXCEPTION)).setPrice(randomPrice);

            stockRepository.save(_product.orElseThrow());
        }
    }


}
