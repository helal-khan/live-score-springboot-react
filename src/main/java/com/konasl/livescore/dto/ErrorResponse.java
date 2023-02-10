package com.konasl.livescore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {
    private String code;
    private int status;
    private String errorCode;
    private String message; // reason
    private List<String> errors;
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();
}