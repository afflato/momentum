package org.afflato.momentum.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SecurityDto(
        int declines,
        String indexType,
        List<SecurityDetailsDto> data,
        String trdVolumesum,
        List<LatestDataDto> latestData,
        int advances,
        int unchanged,
        String trdValueSumMil,
        String time,
        String trdVolumesumMil,
        String trdValueSum
) {
}
