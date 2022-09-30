package com.konasl.livescore.repository;

import com.konasl.livescore.dto.LiveScoreQuery;
import com.konasl.livescore.entity.LiveScore;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LiveScoreRepositoryCustom {

    Page<LiveScore> findLiveScores(final LiveScoreQuery liveScoreQuery, final Pageable pageable);
}
