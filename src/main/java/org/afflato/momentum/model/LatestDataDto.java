package org.afflato.momentum.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LatestDataDto(
        @JsonProperty("yHigh") String yHigh,
        String date,
        String high,
        @JsonProperty("yLow") String yLow,
        String low,
        String ch,
        String indexName,
        String ltp,
        @JsonProperty("yCls") String yCls,
        String per,
        String open,
        @JsonProperty("mCls") String mCls
) {
}
