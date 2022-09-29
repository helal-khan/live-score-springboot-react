package com.konasl.livescore.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
@EnableScheduling
@RequiredArgsConstructor
public class ScheduledTasks {

    private static final String DATE_TIME_FORMAT = "y-MM-d hh:mm:ss a";

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);

    @Scheduled(fixedDelay = 1000 * 60 * 5)
    public void fetchLiveScores() {
        log.info("Scheduled task completed at :: {}", dateTimeFormatter.format(LocalDateTime.now()));
    }

}
