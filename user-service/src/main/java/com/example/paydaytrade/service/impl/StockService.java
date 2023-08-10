package com.example.paydaytrade.service.impl;

import com.example.paydaytrade.exception.ApplicationException;
import com.example.paydaytrade.model.dto.request.BuySellRequestDto;
import com.example.paydaytrade.model.dto.request.SellRequestDto;
import com.example.paydaytrade.model.dto.response.ResponseDto;
import com.example.paydaytrade.model.entity.Stock;
import com.example.paydaytrade.model.entity.UserStock;
import com.example.paydaytrade.model.entity.User;
import com.example.paydaytrade.model.enums.Exceptions;
import com.example.paydaytrade.repository.UserStockRepository;
import com.example.paydaytrade.repository.UserRepository;
import com.example.paydaytrade.service.IStockService;
import com.example.paydaytrade.service.IWalletService;
import com.example.paydaytrade.service.StockServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StockService implements IStockService {
    private final UserStockRepository userStockRepository;
    private final StockServiceClient stockServiceClient;
    private final UserRepository userRepository;
    private final IWalletService walletService;
    private final MailSenderService mailSenderService;

    @Override
    public ResponseDto buyStock(BuySellRequestDto buySellRequestDto) {
        Optional<User> user = userRepository.findUserByUsernameOrEmail(buySellRequestDto.getUsername());
        UserStock userStockBySymbol = userStockRepository.findUserStockBySymbol(buySellRequestDto.getSymbol());

        SellRequestDto request = SellRequestDto.builder()
                .symbol(buySellRequestDto.getSymbol())
                .offerPrice(buySellRequestDto.getOfferPrice())
                .build();
        int price = stockServiceClient.sellStock(request);

        if (price == 0){
            if (walletService.checkBalance(buySellRequestDto.getOfferPrice(),user.orElseThrow(() -> new ApplicationException(Exceptions.USER_NOT_FOUND_EXCEPTION)))){
                if (userStockBySymbol != null){
                    saveAlreadyHaveStock(userStockBySymbol, request.getOfferPrice(), user,true);
                    walletService.decreaseBalance(request.getOfferPrice(), user.orElseThrow());
                }else {
                    saveStock(buySellRequestDto.getSymbol(), request.getOfferPrice(), user,true);
                    walletService.decreaseBalance(request.getOfferPrice(), user.orElseThrow());
                }
                mailSenderService.sendBuyMessage(user.orElseThrow(),buySellRequestDto, request.getOfferPrice());
                return new ResponseDto("Buying is successfully!");
            }else {
                throw new ApplicationException(Exceptions.WALLET_NOT_ENOUGH_EXCEPTION);
            }
        }else {
            saveStock(buySellRequestDto.getSymbol(), request.getOfferPrice(), user,false);

            throw new ApplicationException(Exceptions.HIGH_PRICE_EXCEPTION);
        }

    }

    private void saveStock(String symbol, int offerPrice, Optional<User> user,boolean buyStatus) {
        UserStock userStock = getUserStock(symbol, user, offerPrice,buyStatus);
        List<UserStock> userStocks = user.orElseThrow(() -> new ApplicationException(Exceptions.USER_NOT_FOUND_EXCEPTION)).getUserStock();
        userStocks.add(userStock);
        userRepository.save(user.orElseThrow());
    }

    private void saveAlreadyHaveStock(UserStock _userStock,int offerPrice,Optional<User> user,boolean buyStatus){
        UserStock userStock = getAlreadyHaveUserStock(_userStock, user, offerPrice,buyStatus);
        List<UserStock> userStocks = user.orElseThrow(() -> new ApplicationException(Exceptions.USER_NOT_FOUND_EXCEPTION)).getUserStock();
        userStocks.add(userStock);
        userRepository.save(user.orElseThrow());
    }

    private UserStock getAlreadyHaveUserStock(UserStock userStock, Optional<User> user,int offerPrice,boolean buyStatus) {
        userStock.setBuyStatus(buyStatus);
        userStock.setBuyPrice(offerPrice);
        userStockRepository.save(userStock);
        return userStock;
    }
    private UserStock getUserStock(String symbol, Optional<User> user,int offerPrice,boolean buyStatus) {
        Stock stock = stockServiceClient.findStockBySymbol(symbol);
        if (stock != null){
            UserStock userStock=UserStock.builder()
                    .user(user.orElseThrow(() -> new RuntimeException("User not found!")))
                    .country(stock.getCountry())
                    .name(stock.getName())
                    .currency(stock.getCurrency())
                    .exchange(stock.getExchange())
                    .mic_code(stock.getMic_code())
                    .buyPrice(offerPrice)
                    .symbol(stock.getSymbol())
                    .type(stock.getType())
                    .buyStatus(buyStatus)
                    .build();
            userStockRepository.save(userStock);
            return userStock;
        }else {
            throw new ApplicationException(Exceptions.STOCK_NOT_FOUND_EXCEPTION);
        }
    }

    @Override
    public ResponseDto sellStock( BuySellRequestDto buySellRequestDto) {
        Optional<User> user = userRepository.findUserByUsernameOrEmail(buySellRequestDto.getUsername());
        UserStock stock = userStockRepository.findUserStockBySymbol(buySellRequestDto.getSymbol());
        if (stock == null){
            throw new ApplicationException(Exceptions.STOCK_NOT_FOUND_EXCEPTION);
        }

        if (stock.isBuyStatus() && !stock.isSellStatus()){
            SellRequestDto request = SellRequestDto.builder()
                    .symbol(buySellRequestDto.getSymbol())
                    .offerPrice(buySellRequestDto.getOfferPrice())
                    .build();
            int price = stockServiceClient.sellStock(request);

            if (price != 0){
                if (walletService.checkBalance(buySellRequestDto.getOfferPrice(),user.orElseThrow(() -> new ApplicationException(Exceptions.USER_NOT_FOUND_EXCEPTION)))){
                    stock.setSellStatus(true);
                    stock.setSellRequest(false);
                    stock.setSellPrice(price);
                    userStockRepository.save(stock);

                    walletService.increaseBalance(price,user.orElseThrow(() -> new RuntimeException("User not found!")));
                    mailSenderService.sendSellMessage(user.orElseThrow(),buySellRequestDto,price);
                    return new ResponseDto("Sell is successfully." + buySellRequestDto.getUsername() + " gain " + price +" dollar");
                }else {
                    throw  new ApplicationException(Exceptions.WALLET_NOT_ENOUGH_EXCEPTION);
                }
            }else {
                stock.setSellRequest(true);
                stock.setSellPrice(buySellRequestDto.getOfferPrice());
                userStockRepository.save(stock);
                return new ResponseDto("The stock price is higher than your bid("+request.getOfferPrice()+"). However, " +
                        "we have tracked your stock(" + request.getSymbol() + "). If the stock's value falls below your bid, it will be taken automatically.");
            }
        }else {
            throw new ApplicationException(Exceptions.STOCK_STATUS_IS_FALSE_EXCEPTION);
        }

    }
}
