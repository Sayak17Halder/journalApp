package com.sayak.journalApp.service;

import com.sayak.journalApp.WeatherEntity.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class WeatherService {
    private static final String apiKey = "c0249bbcb9c8c9fad5456b6bf66b5ea5";

    private static final String API = "https://quotes.rest/quote/random.json?api_key=API_KEY&query=CITY";
    @Autowired
    private RestTemplate restTemplate;
    public WeatherResponse getWeather(String city){
        String finalAPI = API.replace("CITY", city).replace("API_KEY", apiKey);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse body = response.getBody();
        return body;
    }
}
