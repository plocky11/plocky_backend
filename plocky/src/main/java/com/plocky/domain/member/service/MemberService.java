package com.plocky.domain.member.service;

import com.plocky.domain.auth.dto.KakaoInfoDto;
import com.plocky.domain.auth.dto.TokenResponse;
import com.plocky.domain.member.entity.Member;
import com.plocky.domain.member.repository.MemberRepository;
import com.plocky.domain.pet.entity.Pet;
import com.plocky.domain.pet.service.PetService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;
    private final PetService petService;
    private final Random random;
    @Value("${plocky.nickname.first}")
    private List<String> firstNick;
    @Value("${plocky.nickname.second}")
    private List<String> secondNick;

    // 랜덤한 닉네임 생성
    private String createRandomNickname(){
        int randomFirstIndex = random.nextInt(firstNick.size());
        int randomSecondIndex = random.nextInt(secondNick.size());
        return firstNick.get(randomFirstIndex) + secondNick.get(randomSecondIndex);
    }

    // 닉네임 중복 검사
    private void isValidNickname(Member member){
        while(true){
            if (memberRepository.findByNickname(member.getNickname()).orElse(null) == null) return ;
            else member.updateNickname(createRandomNickname());
        }
    }

    // 회원가입
    public void signup(KakaoInfoDto kakaoInfoDto, TokenResponse token) {
        // 닉네임 생성 및 검증
        String nickname = createRandomNickname();
        // member 생성
        Member member = Member.builder()
                .kakaoId(kakaoInfoDto.getKakaoId().toString())
                .refreshToken(token.getRefreshToken())
                .nickname(nickname)
                .totalDistance(0)
                .totalQuantity(0)
                .build();
        // 닉네임 중복 검사
        isValidNickname(member);
        memberRepository.save(member);
        // 펫 생성
        petService.createPet(member);
    }
}
