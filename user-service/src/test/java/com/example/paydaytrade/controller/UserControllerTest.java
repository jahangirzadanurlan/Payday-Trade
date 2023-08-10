package com.example.paydaytrade.controller;

import com.example.paydaytrade.model.dto.request.BuySellRequestDto;
import com.example.paydaytrade.model.dto.request.DepositRequestDto;
import com.example.paydaytrade.model.dto.response.ResponseDto;
import com.example.paydaytrade.service.IStockService;
import com.example.paydaytrade.service.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.view.RedirectView;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserControllerTest {

    @Mock
    private IStockService stockService;

    @Mock
    private IUserService userService;

    private UserController userController;
    private CommonController commonController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userController = new UserController(stockService, userService);
        commonController=new CommonController();
    }

    @Test
    void testBuyStock() {
        BuySellRequestDto requestDto = new BuySellRequestDto();
        ResponseDto response = new ResponseDto("Success");

        when(stockService.buyStock(requestDto)).thenReturn(response);

        ResponseEntity<ResponseDto> result = userController.buyStock(requestDto);

        assertEquals(response, result.getBody());
    }

    @Test
    void testSellStock() {
        BuySellRequestDto requestDto = new BuySellRequestDto();
        ResponseDto response = new ResponseDto("Success");

        when(stockService.sellStock(requestDto)).thenReturn(response);

        ResponseEntity<ResponseDto> result = userController.sellStock(requestDto);

        assertEquals(response, result.getBody());
    }

    @Test
    void testDepositMoney() {
        DepositRequestDto requestDto = new DepositRequestDto();
        ResponseDto response = new ResponseDto("Success");

        when(userService.depositMoney(requestDto)).thenReturn(ResponseEntity.ok().body(response));

        ResponseEntity<ResponseDto> result = userController.depositMoney(requestDto);

        assertEquals(response, result.getBody());
    }

    @Test
    void testRedirectToYouTubeChannel() {
        RedirectView redirectView = commonController.redirectToYouTubeChannel();
        assertEquals("https://www.youtube.com/@NurlanJahangirzada", redirectView.getUrl());
    }

    @Test
    void testRedirectYouTubeChannel() {
        RedirectView redirectView = commonController.redirectYouTubeChannel();
        assertEquals("https://www.youtube.com/watch?v=AnwgxRtWXLI&list=PLhfrWIlLOoKMe1Ue0IdeULQvEgCgQ3a1Bhttps://www.youtube.com/watch?v=AnwgxRtWXLI&list=PLhfrWIlLOoKMe1Ue0IdeULQvEgCgQ3a1B", redirectView.getUrl());
    }
}