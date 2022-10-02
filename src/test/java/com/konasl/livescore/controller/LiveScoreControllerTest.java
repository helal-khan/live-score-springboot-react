package com.konasl.livescore.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.konasl.livescore.dto.LiveScoreRequest;
import com.konasl.livescore.dto.LiveScoreResponse;
import com.konasl.livescore.service.LiveScoreService;
import com.konasl.livescore.shared.MockResource;
import com.konasl.livescore.shared.WithMockAuthUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LiveScoreControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    LiveScoreService liveScoreService;

    private final LiveScoreResponse LIVE_SCORE_RESPONSE = MockResource.getMockLiveScoreResponse();
    private final LiveScoreRequest LIVE_SCORE_REQUEST = MockResource.getMockLiveScorePageRequest();
    private final String API_URL_PREFIX = "/api/liveScores";
    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @WithMockAuthUser
    void shouldReturnPageOfLiveScores() throws Exception {
        String liveScoreRequest = mapper.writeValueAsString(LIVE_SCORE_REQUEST);
        Page<LiveScoreResponse> liveScoreResponse = new PageImpl<>(List.of(LIVE_SCORE_RESPONSE));
        when(liveScoreService.findLiveScores(LIVE_SCORE_REQUEST)).thenReturn(liveScoreResponse);

        mockMvc.perform(post(API_URL_PREFIX + "/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(liveScoreRequest)
                )
                .andExpect(status().isOk());
    }
}