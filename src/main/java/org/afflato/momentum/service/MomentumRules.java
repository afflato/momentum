package org.afflato.momentum.service;

import org.afflato.momentum.helper.ConcurrencyUtil;
import org.afflato.momentum.model.IndexDto;
import org.afflato.momentum.model.MomentumDto;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static org.afflato.momentum.utils.MappingHelper.getSubCategory;

@Component
public class MomentumRules {
    private static final Logger logger = LoggerFactory.getLogger(MomentumRules.class);


    public Function<String, MomentumDto.Security> SECURITY_FUNCTION = e -> MomentumDto.Security.builder()
            .symbol(e)
            .subCategory(getSubCategory(e))
            .build();

    @Autowired
    StrategyIndexService niftyService;

    @Autowired
    ConcurrencyUtil concurrencyUtil;

    @Autowired
    private TickerService tickerService;

    public MomentumDto loadIndex(final String category) {
        List<IndexDto> strategy = niftyService.getStrategyIndexData(category);
        List<String> common = strategy.stream().map(IndexDto::symbol).toList() ;
        return getMomentumDto(new ArrayList<>(), new ArrayList<>(), common);
    }

    public MomentumDto compareIndices(final String category1, String category2) {

        List<IndexDto> strategy1 = niftyService.getStrategyIndexData(category1);
        List<IndexDto> strategy2 = niftyService.getStrategyIndexData(category2);

        List<String> i1 = strategy1.stream().map(IndexDto::symbol).toList() ;
        List<String> i2 = strategy2.stream().map(IndexDto::symbol).toList() ;

        List<String> added = CollectionUtils.removeAll(i2, i1).stream().toList();
        List<String> removed = CollectionUtils.removeAll(i1, i2).stream().toList();
        List<String> common = CollectionUtils.intersection(i1, i2).stream().toList();

        return getMomentumDto(added, removed, common);
    }



    private MomentumDto getMomentumDto(List<String> added, List<String> removed, List<String> common) {
        return concurrencyUtil.getMomentumDto(added, removed, common, tickerService);
    }
}
