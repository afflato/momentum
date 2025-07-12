package org.afflato.momentum.service;

import org.afflato.momentum.helper.TickerTransformerUtil;
import org.afflato.momentum.model.StockWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class TickerService {
    private RestClient restClient;

    private ConcurrentMap<String, StockWrapper> symbolMap = new ConcurrentHashMap<>();

    @Autowired
    private TickerTransformerUtil tickerTransformer;

    public TickerService() {
        restClient = RestClient.builder()
                .baseUrl("https://query1.finance.yahoo.com")
                .build();
    }

    public StockWrapper getQuote(String symbol) {
        StockWrapper wrapper = symbolMap.get(symbol);
        if(Objects.nonNull(wrapper)) {
            return wrapper;
        }

        String body = restClient.get()
                .uri(String.format("/v8/finance/chart/%s.NS", symbol))
                .retrieve()
                .body(String.class);

        StockWrapper stockWrapper = tickerTransformer.transformTickerResponse(body);
        stockWrapper.setSymbol(symbol);
        symbolMap.put(symbol, stockWrapper);

        return stockWrapper;
    }
}
