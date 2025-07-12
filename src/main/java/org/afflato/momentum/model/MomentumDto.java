package org.afflato.momentum.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MomentumDto {
    List<Security> incoming;
    List<Security> outgoing;
    List<Security> common;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Security{
        private String symbol;
        private String subCategory;
        private BigDecimal prevClose;
        private BigDecimal fiftyTwoWeekHigh;
        private BigDecimal fiftyTwoWeekLow;
    }
}
