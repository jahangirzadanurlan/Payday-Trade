package com.example.paydaytrade.service.impl;

import com.example.paydaytrade.model.dto.request.BuySellRequestDto;
import com.example.paydaytrade.model.entity.Stock;
import com.example.paydaytrade.model.entity.UserStock;
import com.example.paydaytrade.repository.UserStockRepository;
import com.example.paydaytrade.service.IWalletService;
import com.example.paydaytrade.service.StockServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduledTaskService {
    private final UserStockRepository userStockRepository;
    private final StockServiceClient stockServiceClient;
    private final IWalletService walletService;
    private final MailSenderService mailSenderService;


    @Scheduled(initialDelay = 40 * 60 * 1000, fixedRate = 40 * 60 *1000) //40 minute
    public void checkBuyingProcess(){
        List<UserStock> stocks = userStockRepository.findByBuyStatusFalse();
        if (!stocks.isEmpty()){
            for (UserStock stock : stocks){
                Stock stockBySymbol = stockServiceClient.findStockBySymbol(stock.getSymbol());

                if (stock.getBuyPrice() >= stockBySymbol.getPrice()){
                    stock.setBuyStatus(true);
                    stock.setBuyPrice(stockBySymbol.getPrice());
                    userStockRepository.save(stock);

                    BuySellRequestDto buySellRequestDto = BuySellRequestDto.builder()
                            .username(stock.getUser().getUsername())
                            .symbol(stock.getSymbol())
                            .offerPrice(stock.getBuyPrice())
                            .build();

                    walletService.decreaseBalance(stockBySymbol.getPrice(),stock.getUser());
                    mailSenderService.sendBuyMessage(stock.getUser(),buySellRequestDto,stockBySymbol.getPrice());

                }
            }

        }

    }

    @Scheduled(initialDelay = 50 * 60 * 1000, fixedRate = 50 * 60 *1000) //50 minute
    public void checkSellingProcess(){
        List<UserStock> stocks = userStockRepository.findBySellRequestTrue();
        if (!stocks.isEmpty()){
            for (UserStock stock : stocks){
                Stock stockBySymbol = stockServiceClient.findStockBySymbol(stock.getSymbol());

                if (stock.getSellPrice() <= stockBySymbol.getPrice()){
                    stock.setSellStatus(true);
                    stock.setSellRequest(false);
                    stock.setSellPrice(stockBySymbol.getPrice());
                    userStockRepository.save(stock);

                    BuySellRequestDto buySellRequestDto = BuySellRequestDto.builder()
                            .username(stock.getUser().getUsername())
                            .symbol(stock.getSymbol())
                            .offerPrice(stock.getBuyPrice())
                            .build();

                    walletService.decreaseBalance(stockBySymbol.getPrice(),stock.getUser());
                    mailSenderService.sendSellMessage(stock.getUser(),buySellRequestDto,stockBySymbol.getPrice());

                }
            }

        }

    }

}
