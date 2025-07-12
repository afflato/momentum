package org.afflato.momentum.service;


import org.afflato.momentum.configuration.StrategyProperties;
import org.afflato.momentum.model.SecurityDto;
import org.afflato.momentum.utils.StrategyIndices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.net.http.HttpClient;
import java.time.Duration;

import static org.afflato.momentum.utils.MappingHelper.getSecurityDto;

@Service
public class MarketService {
    private static final Logger logger = LoggerFactory.getLogger(MarketService.class);


    private final String baseUrl;

    private final RestClient restClient;
    private final StrategyProperties strategyProperties;

    public MarketService(@Value("${strategy.json.baseUrl}") String baseUrl, StrategyProperties strategyProperties) {
        this.baseUrl = baseUrl;
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(10))
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();

        JdkClientHttpRequestFactory requestFactory = new JdkClientHttpRequestFactory(httpClient);

        this.restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("Accept", "*/*")
                .defaultHeader("User-Agent", "curl/8.11.1")
                .requestFactory(requestFactory)
                .requestInterceptor((request, body, execution) -> {
                    logger.info("Outgoing request to {}:", request.getURI());
                    request.getHeaders().forEach((name, values) -> logger.info("  {}: {}", name, values));
                    return execution.execute(request, body);
                })
                .build();
        this.strategyProperties = strategyProperties;

    }

    public SecurityDto getNiftyIndexData(String indexName) {
        StrategyIndices index = StrategyIndices.valueOf(indexName);
        String fileName = strategyProperties.getJson().get(index.getName());
        if (fileName != null && fileName.startsWith("\"") && fileName.endsWith("\"")) {
            fileName = fileName.substring(1, fileName.length() - 1);
        }
        String cleanedBaseUrl = baseUrl.endsWith("/") ? baseUrl : baseUrl + "/";
        String fullUri = cleanedBaseUrl + fileName;
        logger.info("Attempting to access full URI (before encoding): {}", fullUri);

        SecurityDto securityDto = null;
        String body = restClient.get()
                .uri(fileName)
                .accept(MediaType.APPLICATION_JSON, MediaType.ALL)
                .retrieve()
                .body(String.class);
        logger.info(body);

        securityDto = getSecurityDto(body);


        return securityDto;
    }
}
