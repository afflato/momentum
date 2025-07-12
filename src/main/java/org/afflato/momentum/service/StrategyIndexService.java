package org.afflato.momentum.service;


import org.afflato.momentum.configuration.StrategyProperties;
import org.afflato.momentum.model.IndexDto;
import org.afflato.momentum.utils.MappingHelper;
import org.afflato.momentum.utils.StrategyIndices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.Duration;
import java.util.List;

import static org.afflato.momentum.utils.MappingHelper.parseCsvContent;

@Service
public class StrategyIndexService {
    private static final Logger logger = LoggerFactory.getLogger(StrategyIndexService.class);

    private final String baseUrl;

    private final RestClient restClient;
    private final StrategyProperties strategyProperties;

    public StrategyIndexService(@Value("${strategy.csv.baseUrl}") String baseUrl, StrategyProperties strategyProperties
    ) {
        this.baseUrl = baseUrl;
        JdkClientHttpRequestFactory requestFactory = new JdkClientHttpRequestFactory();
        requestFactory.setReadTimeout(Duration.ofSeconds(5));
        this.restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .defaultHeaders(MappingHelper::initHeaders)
                //.defaultHeader("Accept", "application/json", "text/plain", "*/*")
                .defaultHeader(HttpHeaders.ACCEPT,
                        MediaType.TEXT_HTML_VALUE + ",application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
                .requestFactory(requestFactory)
                .build();
        this.strategyProperties = strategyProperties;
    }


    public List<IndexDto> getStrategyIndexData(String indexName) {
        StrategyIndices index = StrategyIndices.fromName(indexName);
        String fileName = strategyProperties.getCsv().get(index.getName());
        logger.info("Attempting to access {}{}", baseUrl, fileName);
        String csvData = restClient.get()
                .uri(fileName)
                .retrieve()
                .body(String.class);
        return parseCsvContent(csvData);
    }



}
