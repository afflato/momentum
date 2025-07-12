package org.afflato.momentum.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoadIndices {

    private List<String> indices;
    SelectionDto selectionDto;
}
