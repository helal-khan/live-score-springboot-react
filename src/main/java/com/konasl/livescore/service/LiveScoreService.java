package com.konasl.livescore.service;

import com.konasl.livescore.dto.LiveScoreRequest;
import com.konasl.livescore.dto.LiveScoreResponse;
import com.konasl.livescore.entity.LiveScore;
import org.springframework.data.domain.Page;

public interface LiveScoreService {

    void saveLiveScore(final LiveScore liveScore);

    Page<LiveScoreResponse> findLiveScores(final LiveScoreRequest liveScoreRequest);
}
