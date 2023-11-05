package com.plocky.global.jwt.service;

import com.plocky.domain.auth.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class JwtService {
    private final AuthService authService;
    public Optional<String> extractAccessToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader("Authorization")).filter(
                accessToken -> accessToken.startsWith("Bearer ")
        ).map(accessToken -> accessToken.replace("Bearer ", ""));
    }

    public Optional<String> extractKakaoId(String accessToken) {
//        String kakaoId = authService.checkIfAccessTokenIsValid(accessToken).getId();
//        return Optional.ofNullable(kakaoId);
        return Optional.ofNullable("");
    }
}
