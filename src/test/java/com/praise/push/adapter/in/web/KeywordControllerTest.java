package com.praise.push.adapter.in.web;


import com.praise.push.common.monitoring.MonitoringProvider;
import com.praise.push.application.port.in.KeywordUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = KeywordController.class)
class KeywordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private KeywordUseCase keywordUseCase;

    @MockBean
    private MonitoringProvider monitoringProvider;

    @DisplayName("[성공] 칭찬 키워드 랜덤 추천 테스트")
    @Test
    void getRecommendationKeywordsTest() throws Exception {
        // Given
        String requestURI = "/praise-up/api/v1/keywords/recommendation";
        String queryParamKey = "size";
        String queryParamValue = "5";

        given(keywordUseCase.getRandomRecommendationKeywords(Integer.parseInt(queryParamValue))).willReturn(anyList());

        // When
        ResultActions resultActions = mockMvc.perform(get(requestURI).param(queryParamKey, queryParamValue));

        // Then
        resultActions
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}