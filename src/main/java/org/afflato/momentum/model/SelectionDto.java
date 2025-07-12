package org.afflato.momentum.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SelectionDto {
    private String selectedTab;
    private String trackIndex;

    private String compareIndex1;
    private String compareIndex2;
}
