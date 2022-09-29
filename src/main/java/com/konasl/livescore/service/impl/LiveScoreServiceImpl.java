package com.konasl.livescore.service.impl;

import com.konasl.livescore.entity.LiveScore;
import com.konasl.livescore.repository.LiveScoreRepository;
import com.konasl.livescore.service.LiveScoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LiveScoreServiceImpl implements LiveScoreService {

    private final LiveScoreRepository liveScoreRepository;

    @Override
    public void saveLiveScore(final LiveScore liveScore) {
        final LiveScore existingLiveScore = liveScoreRepository.findLiveScoreByLinkAndUri(liveScore.getLink(), liveScore.getUri()).orElse(null);
        if (existingLiveScore != null) {
            log.info("Updated score :: {}", existingLiveScore.getTitle());
            liveScore.setId(existingLiveScore.getId());
            liveScore.setTitle(existingLiveScore.getTitle());
            liveScore.setDescription(existingLiveScore.getDescription());
        } else {
            log.info("New score :: {}", liveScore.getTitle());
        }
        liveScoreRepository.save(liveScore);
    }
}