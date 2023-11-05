package com.plocky.domain.auth.controller;

import com.plocky.domain.auth.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthService authService;

    @GetMapping("oauth/kakao/login/uri")
    public String kakaoLogin(){
        return authService.login();
    }

    @PostMapping("oauth/kakao/login/access")
    public void kakaoAccess(@RequestBody String request){
        authService.access(request);
    }

    // access token 발급 테스트용 임시 컨트롤러
    @GetMapping("oauth/kakao/login/redirect")
    public String redirectView(){
        return "redirect";
    }
}
