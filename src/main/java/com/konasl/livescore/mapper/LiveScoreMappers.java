package com.konasl.livescore.mapper;

import com.konasl.livescore.configuration.mapper.Mapper;
import com.konasl.livescore.configuration.mapper.MapperRegistry;
import com.konasl.livescore.dto.LiveScoreQuery;
import com.konasl.livescore.dto.LiveScoreRequest;
import com.konasl.livescore.dto.LiveScoreResponse;
import com.konasl.livescore.entity.LiveScore;
import com.rometools.rome.feed.synd.SyndEntry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class LiveScoreMappers {

    private final MapperRegistry mapperRegistry;

    @PostConstruct
    private void registerMappers() {
        mapperRegistry.addMapper(SyndEntry.class, LiveScore.class, entryToLiveScoreMapper());
        mapperRegistry.addMapper(LiveScoreRequest.class, LiveScoreQuery.class, liveScoreRequestToLiveScoreQueryMapper());
        mapperRegistry.addMapper(LiveScore.class, LiveScoreResponse.class, liveScoreToLiveScoreResponseMapper());
    }

    private Mapper<SyndEntry, LiveScore> entryToLiveScoreMapper() {
        return entry -> LiveScore.builder()
                .title(entry.getTitle())
                .link(entry.getLink())
                .description(entry.getDescription().getValue())
                .uri(entry.getUri())
                .build();
    }

    private Mapper<LiveScoreRequest, LiveScoreQuery> liveScoreRequestToLiveScoreQueryMapper() {
        return search -> LiveScoreQuery.builder()
                .title(search.getTitle())
                .build();
    }

    private Mapper<LiveScore, LiveScoreResponse> liveScoreToLiveScoreResponseMapper() {
        return score -> LiveScoreResponse.builder()
                .id(score.getId())
                .title(score.getTitle())
                .link(score.getLink())
                .description(score.getDescription())
                .uri(score.getUri())
                .build();
    }
}
