package com.konasl.livescore.scheduler;

import com.konasl.livescore.configuration.mapper.MapperRegistry;
import com.konasl.livescore.entity.LiveScore;
import com.konasl.livescore.service.LiveScoreService;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
@EnableScheduling
@RequiredArgsConstructor
public class ScheduledTasks {

    @Value("${live-score.source-url}")
    private String LIVE_SCORE_URL;
    private static final String DATE_TIME_FORMAT = "y-MM-d hh:mm:ss a";
    private final MapperRegistry mapperRegistry;
    private final LiveScoreService liveScoreService;
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

    @Scheduled(fixedDelay = 1000 * 60 * 5) //every 5 min interval
    public void fetchLiveScores() {
        try {
            log.info("Scheduled task started at :: {}", dateTimeFormatter.format(LocalDateTime.now()));
            URL feedSource = new URL(LIVE_SCORE_URL);
            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedSource));
            feed.getEntries().forEach(entry -> liveScoreService.saveLiveScore(mapperRegistry.getMapper(SyndEntry.class, LiveScore.class).map(entry)));
            log.info("Scheduled task completed at :: {}", dateTimeFormatter.format(LocalDateTime.now()));
        } catch (Exception e) {
            log.info("Error happened. Reason: {}", e.getMessage());
        }
    }
}
