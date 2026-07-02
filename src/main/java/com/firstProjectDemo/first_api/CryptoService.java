package com.firstProjectDemo.first_api;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CryptoService {
    private final RestTemplate restTemplate;

    public CryptoService(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }

    @Cacheable("bitcoin")
    public String getBitcoin(){
        System.out.println(  "Fetching data from coindesk API");
        String url = "https://blockchain.info/ticker";
        return restTemplate.getForObject(url, String.class);
    }

    @Scheduled(fixedRate = 30000)
    @CacheEvict(value = "bitcoin",allEntries = true)
    public void clearBitcoinCache(){
        System.out.println("Clearing bitcoin cache at it accordingly");

    }
}
