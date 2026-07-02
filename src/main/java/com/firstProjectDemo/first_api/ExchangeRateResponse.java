package com.firstProjectDemo.first_api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public record ExchangeRateResponse(

        @JsonProperty("rates")
        Map<String,Double> rates
) {
}
