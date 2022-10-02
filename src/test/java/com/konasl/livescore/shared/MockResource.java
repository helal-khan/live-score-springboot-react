package com.konasl.livescore.shared;

import com.konasl.livescore.dto.LiveScoreRequest;
import com.konasl.livescore.dto.LiveScoreResponse;
import com.konasl.livescore.entity.LiveScore;
import com.konasl.livescore.entity.User;

public class MockResource {

    public static User getMockUserIbrahim() {
        return User.builder()
                .fullName("Ibrahim Hossain")
                .username("ibrahim")
                .password("123456")
                .email("ibrahim@gmail.com")
                .build();
    }

    public static User getMockUserShahin() {
        return User.builder()
                .fullName("Shahin Ahmed")
                .username("shahin")
                .password("123456")
                .email("shahin@gmail.com")
                .build();
    }

    public static LiveScoreRequest getMockLiveScorePageRequest() {
        return LiveScoreRequest.builder()
                .title("")
                .pageNumber(0)
                .pageSize(10)
                .build();

    }

    public static LiveScore getMockLiveScoreBDvsIN() {
        return LiveScore.builder()
                .title("BD vs IN")
                .link("http://www.cricinfo.com/ci/engine/match/1330684.html?CMP=OTC-RSS")
                .description("BD vs IN")
                .uri("http://www.cricinfo.com/ci/engine/match/1330684.html")
                .build();
    }

    public static LiveScoreResponse getMockLiveScoreResponse() {
        return LiveScoreResponse.builder()
                .title("BD vs IN")
                .link("http://www.cricinfo.com/ci/engine/match/1330684.html?CMP=OTC-RSS")
                .description("BD vs IN")
                .uri("http://www.cricinfo.com/ci/engine/match/1330684.html")
                .build();
    }

    public static LiveScore getMockLiveScoreINvsPK() {
        return LiveScore.builder()
                .title("IN vs PK")
                .link("http://www.cricinfo.com/ci/engine/match/1334000.html?CMP=OTC-RSS")
                .description("IN vs PK")
                .uri("http://www.cricinfo.com/ci/engine/match/1334000.html")
                .build();
    }
}
