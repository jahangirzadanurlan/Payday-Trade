package com.example.demo1.service.Impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo1.model.dto.request.SellRequestDto;
import com.example.demo1.model.dto.response.StockResponseDto;
import com.example.demo1.model.entity.Stock;
import com.example.demo1.repository.StockRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {StockService.class})
@ExtendWith(SpringExtension.class)
class StockServiceTest {
    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private StockRepository stockRepository;

    @Autowired
    private StockService stockService;

    @Test
    void testFindStockByName() {
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
        Optional<Stock> ofResult = Optional.of(stock);
        when(stockRepository.findStockByName(Mockito.<String>any())).thenReturn(ofResult);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Object>>any()))
                .thenThrow(new RuntimeException("Stock doesn't find"));
        assertThrows(RuntimeException.class, () -> stockService.findStockByName("Name"));
        verify(stockRepository).findStockByName(Mockito.<String>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Object>>any());
    }

    @Test
    void testFindStockByName2() {
        Stock stock = mock(Stock.class);
        doNothing().when(stock).setCountry(Mockito.<String>any());
        doNothing().when(stock).setCurrency(Mockito.<String>any());
        doNothing().when(stock).setExchange(Mockito.<String>any());
        doNothing().when(stock).setId(Mockito.<Long>any());
        doNothing().when(stock).setMic_code(Mockito.<String>any());
        doNothing().when(stock).setName(Mockito.<String>any());
        doNothing().when(stock).setPrice(anyInt());
        doNothing().when(stock).setSymbol(Mockito.<String>any());
        doNothing().when(stock).setType(Mockito.<String>any());
        stock.setCountry("GB");
        stock.setCurrency("GBP");
        stock.setExchange("Exchange");
        stock.setId(1L);
        stock.setMic_code("Mic code");
        stock.setName("Name");
        stock.setPrice(1);
        stock.setSymbol("Symbol");
        stock.setType("Type");
        Optional<Stock> ofResult = Optional.of(stock);
        when(stockRepository.findStockByName(Mockito.<String>any())).thenReturn(ofResult);
        StockResponseDto stockResponseDto = new StockResponseDto("Symbol", "Name", "GBP", "Exchange", "Mic code", "GB",
                "Type", 1);

        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Object>>any())).thenReturn(stockResponseDto);
        assertSame(stockResponseDto, stockService.findStockByName("Name"));
        verify(stockRepository).findStockByName(Mockito.<String>any());
        verify(stock).setCountry(Mockito.<String>any());
        verify(stock).setCurrency(Mockito.<String>any());
        verify(stock).setExchange(Mockito.<String>any());
        verify(stock).setId(Mockito.<Long>any());
        verify(stock).setMic_code(Mockito.<String>any());
        verify(stock).setName(Mockito.<String>any());
        verify(stock).setPrice(anyInt());
        verify(stock).setSymbol(Mockito.<String>any());
        verify(stock).setType(Mockito.<String>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Object>>any());
    }

    @Test
    void testFindStockByName3() {
        Stock stock = mock(Stock.class);
        doNothing().when(stock).setCountry(Mockito.<String>any());
        doNothing().when(stock).setCurrency(Mockito.<String>any());
        doNothing().when(stock).setExchange(Mockito.<String>any());
        doNothing().when(stock).setId(Mockito.<Long>any());
        doNothing().when(stock).setMic_code(Mockito.<String>any());
        doNothing().when(stock).setName(Mockito.<String>any());
        doNothing().when(stock).setPrice(anyInt());
        doNothing().when(stock).setSymbol(Mockito.<String>any());
        doNothing().when(stock).setType(Mockito.<String>any());
        stock.setCountry("GB");
        stock.setCurrency("GBP");
        stock.setExchange("Exchange");
        stock.setId(1L);
        stock.setMic_code("Mic code");
        stock.setName("Name");
        stock.setPrice(1);
        stock.setSymbol("Symbol");
        stock.setType("Type");
        Optional<Stock> ofResult = Optional.of(stock);
        when(stockRepository.findStockByName(Mockito.<String>any())).thenReturn(ofResult);
        when(modelMapper.map(Mockito.<Object>any(), Mockito.<Class<Object>>any())).thenReturn(null);
        assertThrows(RuntimeException.class, () -> stockService.findStockByName("Name"));
        verify(stockRepository).findStockByName(Mockito.<String>any());
        verify(stock).setCountry(Mockito.<String>any());
        verify(stock).setCurrency(Mockito.<String>any());
        verify(stock).setExchange(Mockito.<String>any());
        verify(stock).setId(Mockito.<Long>any());
        verify(stock).setMic_code(Mockito.<String>any());
        verify(stock).setName(Mockito.<String>any());
        verify(stock).setPrice(anyInt());
        verify(stock).setSymbol(Mockito.<String>any());
        verify(stock).setType(Mockito.<String>any());
        verify(modelMapper).map(Mockito.<Object>any(), Mockito.<Class<Object>>any());
    }

    @Test
    void testFindAllStocks() {
        when(stockRepository.findAll()).thenReturn(new ArrayList<>());
        assertThrows(RuntimeException.class, () -> stockService.findAllStocks());
        verify(stockRepository).findAll();
    }

    @Test
    void testFindAllStocks2() {
        Stock stock = new Stock();
        stock.setCountry("GB");
        stock.setCurrency("GBP");
        stock.setExchange("Stock List is empty!");
        stock.setId(1L);
        stock.setMic_code("Stock List is empty!");
        stock.setName("Stock List is empty!");
        stock.setPrice(1);
        stock.setSymbol("Stock List is empty!");
        stock.setType("Stock List is empty!");

        ArrayList<Stock> stockList = new ArrayList<>();
        stockList.add(stock);
        when(stockRepository.findAll()).thenReturn(stockList);
        List<Stock> actualFindAllStocksResult = stockService.findAllStocks();
        assertSame(stockList, actualFindAllStocksResult);
        assertEquals(1, actualFindAllStocksResult.size());
        verify(stockRepository).findAll();
    }

    @Test
    void testFindAllStocks3() {
        when(stockRepository.findAll()).thenThrow(new RuntimeException("Stock List is empty!"));
        assertThrows(RuntimeException.class, () -> stockService.findAllStocks());
        verify(stockRepository).findAll();
    }

    @Test
    void testFindStockBySymbol() {
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
        Optional<Stock> ofResult = Optional.of(stock);
        when(stockRepository.findStockBySymbol(Mockito.<String>any())).thenReturn(ofResult);
        assertSame(stock, stockService.findStockBySymbol("Symbol"));
        verify(stockRepository).findStockBySymbol(Mockito.<String>any());
    }

    @Test
    void testFindStockBySymbol2() {
        when(stockRepository.findStockBySymbol(Mockito.<String>any())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> stockService.findStockBySymbol("Symbol"));
        verify(stockRepository).findStockBySymbol(Mockito.<String>any());
    }

    @Test
    void testFindStockBySymbol3() {
        when(stockRepository.findStockBySymbol(Mockito.<String>any()))
                .thenThrow(new RuntimeException("Stock not found! "));
        assertThrows(RuntimeException.class, () -> stockService.findStockBySymbol("Symbol"));
        verify(stockRepository).findStockBySymbol(Mockito.<String>any());
    }

    @Test
    void testSellStock() {
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
        Optional<Stock> ofResult = Optional.of(stock);
        when(stockRepository.findStockBySymbol(Mockito.<String>any())).thenReturn(ofResult);
        assertEquals(1, stockService.sellStock(new SellRequestDto("Symbol", 1)));
        verify(stockRepository).findStockBySymbol(Mockito.<String>any());
    }

    @Test
    void testSellStock2() {
        Stock stock = mock(Stock.class);
        when(stock.getPrice()).thenReturn(0);
        doNothing().when(stock).setCountry(Mockito.<String>any());
        doNothing().when(stock).setCurrency(Mockito.<String>any());
        doNothing().when(stock).setExchange(Mockito.<String>any());
        doNothing().when(stock).setId(Mockito.<Long>any());
        doNothing().when(stock).setMic_code(Mockito.<String>any());
        doNothing().when(stock).setName(Mockito.<String>any());
        doNothing().when(stock).setPrice(anyInt());
        doNothing().when(stock).setSymbol(Mockito.<String>any());
        doNothing().when(stock).setType(Mockito.<String>any());
        stock.setCountry("GB");
        stock.setCurrency("GBP");
        stock.setExchange("Exchange");
        stock.setId(1L);
        stock.setMic_code("Mic code");
        stock.setName("Name");
        stock.setPrice(1);
        stock.setSymbol("Symbol");
        stock.setType("Type");
        Optional<Stock> ofResult = Optional.of(stock);
        when(stockRepository.findStockBySymbol(Mockito.<String>any())).thenReturn(ofResult);
        assertEquals(0, stockService.sellStock(new SellRequestDto("Symbol", 1)));
        verify(stockRepository).findStockBySymbol(Mockito.<String>any());
        verify(stock).getPrice();
        verify(stock).setCountry(Mockito.<String>any());
        verify(stock).setCurrency(Mockito.<String>any());
        verify(stock).setExchange(Mockito.<String>any());
        verify(stock).setId(Mockito.<Long>any());
        verify(stock).setMic_code(Mockito.<String>any());
        verify(stock).setName(Mockito.<String>any());
        verify(stock).setPrice(anyInt());
        verify(stock).setSymbol(Mockito.<String>any());
        verify(stock).setType(Mockito.<String>any());
    }

    @Test
    void testSellStock3() {
        when(stockRepository.findStockBySymbol(Mockito.<String>any())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> stockService.sellStock(new SellRequestDto("Symbol", 1)));
        verify(stockRepository).findStockBySymbol(Mockito.<String>any());
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testSellStock4() {
        Stock stock = mock(Stock.class);
        when(stock.getPrice()).thenReturn(1);
        doNothing().when(stock).setCountry(Mockito.<String>any());
        doNothing().when(stock).setCurrency(Mockito.<String>any());
        doNothing().when(stock).setExchange(Mockito.<String>any());
        doNothing().when(stock).setId(Mockito.<Long>any());
        doNothing().when(stock).setMic_code(Mockito.<String>any());
        doNothing().when(stock).setName(Mockito.<String>any());
        doNothing().when(stock).setPrice(anyInt());
        doNothing().when(stock).setSymbol(Mockito.<String>any());
        doNothing().when(stock).setType(Mockito.<String>any());
        stock.setCountry("GB");
        stock.setCurrency("GBP");
        stock.setExchange("Exchange");
        stock.setId(1L);
        stock.setMic_code("Mic code");
        stock.setName("Name");
        stock.setPrice(1);
        stock.setSymbol("Symbol");
        stock.setType("Type");
        Optional<Stock> ofResult = Optional.of(stock);
        when(stockRepository.findStockBySymbol(Mockito.<String>any())).thenReturn(ofResult);
        stockService.sellStock(null);
    }
}

