package com.konasl.livescore.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public class LiveScoreRequest extends PageRequest {
    private String title;
}
