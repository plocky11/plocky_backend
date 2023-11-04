package com.plocky.domain.auth.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AuthService {
    private final RestTemplate restTemplate;
    @Value("${kakao.client.id}")
    private String REST_API_KEY;
    @Value("${kakao.client.redirect_uri}")
    private String REDIRECT_URI;
    @Value("${kakao.uri.authorization_uri}")
    private String AUTH_URI;
    @Value("${kakao.uri.token_uri}")
    private String TOKEN_URI;
    @Value("${kakao.uri.user-info-uri}")
    private String USER_URI;

    // 로그인 요청 시 카카오 로그인 주소 반환
    public String login() {
        return AUTH_URI + "?client_id=" + REST_API_KEY + "&redirect_uri=" + REDIRECT_URI + "&response_type=code";
    }

    // 카카오 서버에 accessToken 요청
    private ResponseEntity<AccessTokenResponse> requestAccessToken(String auth_uri) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        String requestBody = "{\"key\": \"value\"}";
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        return restTemplate.exchange(auth_uri, HttpMethod.POST, requestEntity, AccessTokenResponse.class);
    }

    // 카카오 서버에 user 정보 요청
    private ResponseEntity<String> requestUserInfo(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        headers.set("Authorization", "Bearer "+token);
        HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
        return restTemplate.exchange(USER_URI, HttpMethod.GET, requestEntity, String.class);
    }

    // 인가코드 받아서 accessToken 발급
    public void access(String request) {
        String[] parts = request.split("code=");
        if (parts.length > 1) {
            String code = parts[1];
            String auth_uri = TOKEN_URI + "?grant_type=authorization_code&client_id=" + REST_API_KEY +
                    "&redirect_uri=" + REDIRECT_URI + "&code=" + code;
            // access token 발급
            ResponseEntity<AccessTokenResponse> responseAccessEntity = requestAccessToken(auth_uri);
            AccessTokenResponse responseAccessBody = responseAccessEntity.getBody();
            log.info(responseAccessBody.getAccessToken());

            // user 정보 조회
            ResponseEntity<String> responseUserEntity = requestUserInfo(responseAccessBody.getAccessToken());
            String responseUserBody = responseUserEntity.getBody();
            log.info(responseUserBody);
        }


    }
}
