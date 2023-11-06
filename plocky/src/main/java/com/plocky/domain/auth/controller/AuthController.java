package com.plocky.domain.auth.controller;

import com.plocky.domain.auth.service.AuthService;
import com.plocky.global.jwt.service.JwtService;
import com.plocky.global.utils.SecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthService authService;
    private final JwtService jwtService;

    @GetMapping("oauth/kakao/login/uri")
    public String kakaoLogin(){
        return authService.login();
    }

    @PostMapping("oauth/kakao/login/access")
    public void kakaoAccess(@RequestParam(value = "code", required = true) String kakaoCode){
        authService.access(kakaoCode);
    }

    // access token 발급 테스트용 임시 컨트롤러
    @GetMapping("oauth/kakao/login/redirect")
    public String redirectView(){
        return "redirect";
    }

    @GetMapping("/hello")
    public String hello(HttpServletRequest request) {
        if (SecurityUtil.getLoginedUserName().equals(jwtService.extractKakaoId(jwtService.extractAccessToken(request).orElseThrow()))) {
            return "Hello";
        }
        else {
            log.info("UNAUTHORIZED 403");
            return "403";
        }
    }
}
