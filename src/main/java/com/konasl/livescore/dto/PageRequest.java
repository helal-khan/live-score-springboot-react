package com.konasl.livescore.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Getter
@Setter
public class PageRequest {
    private Integer pageNumber;
    private Integer pageSize;
}