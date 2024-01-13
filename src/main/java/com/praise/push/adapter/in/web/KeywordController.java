package com.praise.push.adapter.in.web;

import com.praise.push.application.port.in.dto.KeywordResponseDto;
import com.praise.push.common.model.ResponseDto;
import com.praise.push.application.port.in.KeywordUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/praise-up/api/v1")
@Tag(name = "keywords", description = "Keyword API")
@CrossOrigin(origins = "*", allowedHeaders = "*")
class KeywordController {

    private final KeywordUseCase keywordUseCase;

    /**
     * get recommendation keywords as many as size
     */
    @Operation(summary = "칭찬 키워드 조회")
    @ApiResponse(responseCode = "200", description = "칭찬 키워드 조회 성공")
    @GetMapping("/keywords/recommendation")
    ResponseEntity<List<KeywordResponseDto>> recommendationKeywords(@RequestParam("size") Integer size) {
        return ResponseDto.ok(keywordUseCase.getRandomRecommendationKeywords(size));
    }
}
