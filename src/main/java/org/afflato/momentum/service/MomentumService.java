package org.afflato.momentum.service;

import org.afflato.momentum.model.LoadIndices;
import org.afflato.momentum.model.MomentumDto;
import org.afflato.momentum.model.SelectionDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class MomentumService {
    @Autowired
    MomentumRules rules;


    public LoadIndices init() {
        return LoadIndices.builder().indices(Arrays.asList("ALPHA50", "NIFTY500")).selectionDto(SelectionDto.builder().build()).build();
    }

    public MomentumDto process(SelectionDto selection) {
        String category = selection.getTrackIndex();
        if (StringUtils.isNotBlank(category)) {
            MomentumDto response = rules.loadIndex("NIFTY500");
            return response;
        }
        else {
            String index1 = selection.getCompareIndex1();
            String index2 = selection.getCompareIndex2();
            if(StringUtils.isNotBlank(index1) && StringUtils.isNotBlank(index2)) {
                MomentumDto response = rules.compareIndices(index1, index2);
                return response;
            } else {
                MomentumDto error = null;
                return error;
            }
        }
    }
}
