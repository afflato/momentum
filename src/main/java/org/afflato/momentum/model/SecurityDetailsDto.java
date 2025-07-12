package org.afflato.momentum.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SecurityDetailsDto(
        @JsonProperty("ptsC") String ptsC,
        @JsonProperty("wklo") String wklo,
        @JsonProperty("iislPtsChange") String iislPtsChange,
        String symbol,
        @JsonProperty("cAct") String cAct,
        @JsonProperty("mPC") String mPC,
        @JsonProperty("wkhi") String wkhi,
        @JsonProperty("wklocm_adj") String wklocmAdj,
        @JsonProperty("trdVolM") String trdVolM,
        @JsonProperty("wkhicm_adj") String wkhicmAdj,
        @JsonProperty("mVal") String mVal,
        @JsonProperty("ltP") String ltP,
        @JsonProperty("xDt") String xDt,
        @JsonProperty("trdVol") String trdVol,
        @JsonProperty("ntP") String ntP,
        @JsonProperty("yPC") String yPC,
        String previousClose,
        String dayEndClose,
        String high,
        @JsonProperty("iislPercChange") String iislPercChange,
        String low,
        String per,
        String open
) {
}
