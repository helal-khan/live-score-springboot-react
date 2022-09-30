package com.konasl.livescore.repository;

import com.konasl.livescore.dto.LiveScoreQuery;
import com.konasl.livescore.entity.LiveScore;
import com.konasl.livescore.entity.QLiveScore;
import com.konasl.livescore.util.QuerydslPageUtils;
import com.querydsl.core.BooleanBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import static java.util.Objects.requireNonNull;

@Repository("liveScoreRepositoryCustom")
public class LiveScoreRepositoryCustomImpl extends QuerydslRepositorySupport implements LiveScoreRepositoryCustom {

    public LiveScoreRepositoryCustomImpl() {
        super(LiveScore.class);
    }

    @Override
    public Page<LiveScore> findLiveScores(final LiveScoreQuery liveScoreQuery, final Pageable pageable) {
        QLiveScore qLiveScore = QLiveScore.liveScore;
        BooleanBuilder predicate = new BooleanBuilder();
        if (StringUtils.isNotBlank(liveScoreQuery.getTitle())) {
            predicate.and(qLiveScore.title.containsIgnoreCase(liveScoreQuery.getTitle()));
        }
        return QuerydslPageUtils.createPage(
                requireNonNull(getQuerydsl()),
                from(qLiveScore).where(predicate).orderBy(qLiveScore.updatedAt.desc()).select(qLiveScore),
                pageable
        );
    }
}
