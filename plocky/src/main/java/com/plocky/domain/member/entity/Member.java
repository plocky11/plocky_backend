package com.plocky.domain.member.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.AccessLevel;

import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Transactional
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    @Column(unique = true)
    private String kakaoId;
    private String refreshToken;
    private String nickname;
    private float totalDistance;
    private Integer totalQuantity;

//    @
//    List<Plogging> ploggingList = new ArrayList<>();

//    @OneToOne
//    private Pet pet;
}
