package org.afflato.momentum.controller;

import org.afflato.momentum.model.IndexDto;
import org.afflato.momentum.model.MomentumDto;
import org.afflato.momentum.model.SecurityDto;
import org.afflato.momentum.model.StrategyIndexDto;
import org.afflato.momentum.service.IndexDataService;
import org.afflato.momentum.service.MarketService;
import org.afflato.momentum.service.MomentumRules;
import org.afflato.momentum.service.StrategyIndexService;
import org.afflato.momentum.utils.StrategyIndices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class MomentumRestController {

    @Autowired
    MomentumRules rules;

    @Autowired
    StrategyIndexService niftyService;
    @Autowired
    MarketService marketService;
    @Autowired
    IndexDataService indexDataService;


    @GetMapping(value = "v1/momentum", produces = MediaType.APPLICATION_JSON_VALUE)
    public MomentumDto getLatestMomentum(@RequestParam(name = "cat") String category) {
        return rules.loadIndex(category);
    }

    @GetMapping(value = "v1/alpha", produces = MediaType.APPLICATION_JSON_VALUE)
    public MomentumDto getLatestAlpha(String category) {
        return rules.loadIndex("ALPHA50");
    }

    @GetMapping(value = "v1/compare", produces = MediaType.APPLICATION_JSON_VALUE)
    public MomentumDto getComparison(@RequestParam(name = "cat1") String cat1, @RequestParam(name = "cat2") String cat2) {
        return rules.compareIndices(cat1, cat2);
    }

    @GetMapping(value = "v1/indices", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getIndices() {
        return Arrays.asList("NIFTY500", "MIDCAP150", "ALPHA50", "VALUE50");
    }


    @GetMapping("v1/strategy")
    public List<IndexDto> getStrategyIndexData(@RequestParam("indexName") String indexName) {
        List<IndexDto> alphaRestListDl = niftyService.getStrategyIndexData(indexName);
        return alphaRestListDl;
    }

    @GetMapping("v1/nifty")
    public SecurityDto getNiftyIndexData(@RequestParam("indexName") String indexName) {
        SecurityDto liveData = marketService.getNiftyIndexData(indexName);
        return liveData;
    }

    @GetMapping("v1/indexData")
    public String getIndexData(@RequestParam("index") StrategyIndices index) {
        return indexDataService.fetchDataForIndex(index);
    }

    @GetMapping(value = "v1/indexData/names", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StrategyIndexDto> getStrategyIndices() {
        return indexDataService.getAllIndices();

    }
}