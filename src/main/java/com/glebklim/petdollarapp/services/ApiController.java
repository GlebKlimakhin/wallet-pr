package com.glebklim.petdollarapp.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glebklim.petdollarapp.dto.ConvertApiResponse;
import com.glebklim.petdollarapp.dto.LatestApiResponse;
import com.glebklim.petdollarapp.dto.SymbolsResponse;
import lombok.SneakyThrows;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ApiController {

    @Autowired
    private RestTemplate restTemplate;

    private final String uri = "http://api.exchangeratesapi.io/v1/";

    private static ObjectMapper objectMapper = new ObjectMapper();


//    @Value("${api.key}")
//    private final String accessKey = "7c73487cf644a0a699dff3a53697fa4a";

    @GetMapping("/convert?access_key={accessKey}&from={from}&to={to}&amount={amount}")
    public HttpEntity<ConvertApiResponse> convert(@PathVariable String from, @PathVariable String to, @PathVariable Integer amount) {
        HttpEntity<?> entity = new HttpEntity(prepareHeaders());
        Map<String, Object> uriParams = new HashMap<>();
//        String uriTemplate = UriComponentsBuilder.fromHttpUrl(uri)
//                        .queryParam("access_key", "{accessKey}")
//                                .queryParam("from", "{from}")
//                                        .queryParam("to", "{to}")
//                                                .queryParam("amount", "{amount}")
//                                                        .encode().toUriString();
        uriParams.put("access_key", "7c73487cf644a0a699dff3a53697fa4a");
        uriParams.put("from", from);
        uriParams.put("to", to);
        uriParams.put("amount", amount);
        HttpEntity<ConvertApiResponse> response = restTemplate.exchange("http://api.exchangeratesapi.io/v1/convert?access_key={accessKey}&from={from}&to={to}&amount={amount}",
                HttpMethod.GET,
                entity,
                ConvertApiResponse.class,
                uriParams
                );
        return response;
    }

    @GetMapping("/symbols")
    @SneakyThrows
    public HttpEntity<SymbolsResponse> symbols() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<SymbolsResponse> httpEntity = new HttpEntity<>(headers);
        return restTemplate.exchange("http://api.exchangeratesapi.io/v1/symbols?access_key={accessKey}",
                HttpMethod.GET,
                httpEntity,
                SymbolsResponse.class,
                "7c73487cf644a0a699dff3a53697fa4a");
    }

    @GetMapping("/latest?base={base}&symbols={symbols}")
    public HttpEntity<?> latest(@PathVariable(required = false) String base, @PathVariable(required = false) String symbols) {
        HttpEntity<LatestApiResponse> httpEntity = new HttpEntity<>(prepareHeaders());
        Map<String, String> uriParams = new HashMap<>();
        uriParams.put("access_key", "7c73487cf644a0a699dff3a53697fa4a");
        uriParams.put("base", base);
        uriParams.put("symbols", symbols);
        return restTemplate.exchange("https://api.exchangeratesapi.io/v1/latest?access_key={accessKey}&base={base}&symbols={symbols}",
                HttpMethod.GET,
                httpEntity,
                LatestApiResponse.class,
                uriParams
                );
    }

    private HttpHeaders prepareHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }


    @GetMapping("/a")
    public String abs() {
        return "abc";
    }

//    https://api.exchangeratesapi.io/v1/latest
//            ? access_key = API_KEY
//    & base = USD
//    & symbols = GBP,JPY,EUR latest rates endpoint
}
