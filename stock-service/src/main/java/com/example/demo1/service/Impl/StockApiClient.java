package com.example.demo1.service.Impl;

import com.example.demo1.model.entity.Stock;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.asynchttpclient.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
@RequiredArgsConstructor
public class StockApiClient {
    private final ObjectMapper objectMapper;

    @Value("${rapidapi.key}")
    private String apiKey;

    public String fetchStockData() throws IOException {
        AsyncHttpClient client = new DefaultAsyncHttpClient();
        String url = "https://twelve-data1.p.rapidapi.com/stocks?exchange=NASDAQ&format=json";

        String responseBody = client.prepare("GET", url)
                .setHeader("X-RapidAPI-Key", apiKey)
                .setHeader("X-RapidAPI-Host", "twelve-data1.p.rapidapi.com")
                .execute()
                .toCompletableFuture()
                .thenApply(Response::getResponseBody)
                .join();

        client.close();
        return responseBody;
    }

    public List<Stock> getAllProduct() throws IOException {

        String json=fetchStockData();
        JSONObject jsonObject=new JSONObject(json);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);

        List<Stock> stockList = objectMapper.readValue(jsonObject.getJSONArray("data").toString(),objectMapper.getTypeFactory().constructCollectionType(List.class, Stock.class));


        for (Stock stock : stockList) {
            int randomPrice = ThreadLocalRandom.current().nextInt(100, 700);
            stock.setPrice(randomPrice);
        }

        return stockList;
    }
}

