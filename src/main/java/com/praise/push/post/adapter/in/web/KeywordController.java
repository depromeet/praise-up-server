package com.praise.push.post.adapter.in.web;

import com.praise.push.common.dto.ResponseDto;
import com.praise.push.post.application.port.in.KeywordUseCase;
import com.praise.push.post.domain.Keyword;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/praise-up/api/v1")
class KeywordController {

    private final KeywordUseCase keywordUseCase;

    /**
     * get recommendation keywords as many as size
     */
    @GetMapping("/keywords/recommendation")
    public ResponseEntity<List<Keyword>> recommendationKeywords(@RequestParam Integer size) {
        return ResponseDto.ok(keywordUseCase.getRandomRecommendationKeywords(size));
    }
}
