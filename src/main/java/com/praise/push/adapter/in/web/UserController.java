package com.praise.push.adapter.in.web;

import com.praise.push.application.port.in.dto.UserPostStateResponseDto;
import com.praise.push.application.port.out.UserResponse;
import com.praise.push.application.service.UserService;
import com.praise.push.common.model.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/praise-up/api/v1")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Users", description = "User API")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    private final UserService userService;

    @Operation(summary = "유저 조회")
    @ApiResponse(responseCode = "200", description = "유저 조회 성공")
    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("id") Long id) {
        var response = userService.getUserById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "닉네임 변경")
    @ApiResponse(responseCode = "200", description = "닉네임 변경 성공")
    @PatchMapping("/user/{id}/nickname")
    public ResponseEntity<UserResponse> changeNickname(@PathVariable("id") Long id, @RequestParam("nickname") String nickname) {
        var response = userService.changeNickname(id, nickname);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "유저 탈퇴")
    @ApiResponse(responseCode = "200", description = "유저 탈퇴 성공")
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id, @RequestParam("reason") String reason) {
        userService.deleteUser(id, reason);
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build();
    }

    @Operation(summary = "글 작성 유무 조회")
    @ApiResponse(responseCode = "200", description = "글 작성 유무 조회 성공")
    @GetMapping("/user/{id}/post-state")
    public ResponseEntity<UserPostStateResponseDto> getUserPostState(@PathVariable("id") Long id) {
        UserPostStateResponseDto userPostStateResponseDto = userService.getUserPostStatus(id);

        return ResponseDto.ok(userPostStateResponseDto);
    }
}
