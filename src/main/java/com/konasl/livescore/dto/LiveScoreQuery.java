package com.konasl.livescore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class LiveScoreQuery {
    private String title;
}
