package com.konasl.livescore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LiveScoreResponse {
    private Long id;
    private String title;
    private String link;
    private String description;
    private String uri;
}
