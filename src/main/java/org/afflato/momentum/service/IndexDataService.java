package org.afflato.momentum.service;


import org.afflato.momentum.configuration.StrategyProperties;
import org.afflato.momentum.model.StrategyIndexDto;
import org.afflato.momentum.utils.StrategyIndices;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import jakarta.annotation.PostConstruct; // For Spring Boot 3.x+; use javax.annotation.PostConstruct for older versions

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IndexDataService {

    private final StrategyProperties strategyProperties;

    @Value("${strategy.csv.baseUrl}")
    private String csvBaseUrl;

    @Value("${strategy.json.baseUrl}")
    private String jsonBaseUrl;


    public List<StrategyIndexDto> getAllIndices() {
        return Arrays.stream(StrategyIndices.values())
                .map(index -> new StrategyIndexDto(index.getName(), index.getType()))
                .collect(Collectors.toList());
    }

    public IndexDataService(StrategyProperties strategyProperties) {
        this.strategyProperties = strategyProperties;
    }

    public String getStrategyUrl(StrategyIndices index) {
        String baseUrl;
        String fileName;

        if ("csv".equals(index.getType())) {
            baseUrl = csvBaseUrl;
            fileName = strategyProperties.getCsv().get(index.getName());
        } else if ("json".equals(index.getType())) {
            baseUrl = jsonBaseUrl;
            fileName = strategyProperties.getJson().get(index.getName());
            if (fileName != null && fileName.startsWith("\"") && fileName.endsWith("\"")) {
                fileName = fileName.substring(1, fileName.length() - 1);
            }
        } else {
            throw new IllegalArgumentException("Unsupported strategy type: " + index.getType());
        }

        if (baseUrl == null || fileName == null) {
            throw new IllegalStateException("Could not determine URL for strategy: " + index.getName());
        }

        String cleanedBaseUrl = baseUrl.endsWith("/") ? baseUrl : baseUrl + "/";
        return cleanedBaseUrl + fileName;
    }

    @PostConstruct
    public void init() {
        System.out.println("CSV Base URL: " + csvBaseUrl);
        System.out.println("JSON Base URL: " + jsonBaseUrl);
        System.out.println("NIFTYALPHA50 CSV file: " + strategyProperties.getCsv().get("NIFTYALPHA50"));
        System.out.println("NIFTY50 JSON file: " + strategyProperties.getJson().get("NIFTY50"));
    }

    // Example of how you might use it in another method
    public String fetchDataForIndex(StrategyIndices index) {
        String url = getStrategyUrl(index);
        System.out.println("Fetching data from: " + url);

        return url;
    }
}