package com.konasl.livescore.controller;

import com.konasl.livescore.dto.LiveScoreRequest;
import com.konasl.livescore.dto.LiveScoreResponse;
import com.konasl.livescore.service.LiveScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("api/liveScores")
@RequiredArgsConstructor
public class LiveScoreController {

    private final LiveScoreService liveScoreService;

    @PostMapping(value = "/list", produces = APPLICATION_JSON_VALUE)
    public Page<LiveScoreResponse> findLiveScores(@RequestBody LiveScoreRequest liveScoreRequest) {
        return liveScoreService.findLiveScores(liveScoreRequest);
    }
}
