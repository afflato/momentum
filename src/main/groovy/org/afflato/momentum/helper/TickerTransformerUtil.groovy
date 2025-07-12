package org.afflato.momentum.helper

import org.afflato.momentum.model.StockWrapper
import org.json.JSONObject
import org.springframework.stereotype.Component

@Component("tickerTransformer")
class TickerTransformerUtil {

    StockWrapper transformTickerResponse(body) {
        JSONObject ticker = new JSONObject(body)
        StockWrapper response = []
        response.prevClose = ticker?.chart?.result?[0]?.meta?.previousClose
        response.fiftyTwoWeekHigh = ticker?.chart?.result?[0]?.meta?.fiftyTwoWeekHigh
        response.fiftyTwoWeekLow = ticker?.chart?.result?[0]?.meta?.fiftyTwoWeekLow

        return response
    }
}
