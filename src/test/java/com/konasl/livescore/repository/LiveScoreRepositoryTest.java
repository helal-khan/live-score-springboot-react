package com.konasl.livescore.repository;

import com.konasl.livescore.entity.LiveScore;
import com.konasl.livescore.shared.MockResource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class LiveScoreRepositoryTest {

    @Autowired
    LiveScoreRepository liveScoreRepository;

    private final LiveScore LIVE_SCORE_BD_VS_IN = MockResource.getMockLiveScoreBDvsIN();
    private final LiveScore LIVE_SCORE_IN_VS_PK = MockResource.getMockLiveScoreINvsPK();

    @BeforeEach
    void setUp() {
        liveScoreRepository.save(LIVE_SCORE_BD_VS_IN);
    }

    @AfterEach
    void tearDown() {
        liveScoreRepository.deleteAll();
    }

    @Test
    void shouldReturnOptionalLiveScore_whenLinkAndUriGiven() {
        Optional<LiveScore> returnedLiveScore = liveScoreRepository.findLiveScoreByLinkAndUri(LIVE_SCORE_BD_VS_IN.getLink(), LIVE_SCORE_BD_VS_IN.getUri());
        assertThat(returnedLiveScore.isPresent()).isTrue();

    }

    @Test
    void shouldNotReturnOptionalLiveScore_whenLinkAndUriGiven() {
        Optional<LiveScore> returnedLiveScore = liveScoreRepository.findLiveScoreByLinkAndUri(LIVE_SCORE_IN_VS_PK.getTitle(), LIVE_SCORE_IN_VS_PK.getUri());
        assertThat(returnedLiveScore.isEmpty()).isTrue();

    }
}