package com.firstProjectDemo.first_api;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class MarketDataService {
    private final RestClient restClient;

    public MarketDataService(){
        this.restClient=RestClient.builder()
                .baseUrl("https://open.er-api.com")
                .build();
    }
    @Cacheable("exchangeRates")
    public ExchangeRateResponse exchangeRates(){
        return restClient.get()
                .uri("/v6/latest/USD")
                .retrieve()
                .body(ExchangeRateResponse.class);
    }

    @Scheduled(fixedRate = 30000)
    @CacheEvict(value = "exchangeRates",allEntries = true)
    public void refreshMarketData(){
        System.out.println("clearing old data");
    }
}
