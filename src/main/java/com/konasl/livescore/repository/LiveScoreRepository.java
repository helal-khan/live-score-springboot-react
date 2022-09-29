package com.konasl.livescore.repository;

import com.konasl.livescore.entity.LiveScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LiveScoreRepository extends JpaRepository<LiveScore, Long> {

    Optional<LiveScore> findLiveScoreByLinkAndUri(String link, String uri);
}