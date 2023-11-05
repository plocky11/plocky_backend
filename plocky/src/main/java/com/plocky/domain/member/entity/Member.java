package com.plocky.domain.member.entity;

import com.plocky.global.entity.BaseTimeEntity;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.AccessLevel;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Transactional
@EntityListeners(AuditingEntityListener.class)
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    @Column(unique = true)
    private String kakaoId;
    private String refreshToken;
    @Column(unique = true)
    private String nickname;
    private float totalDistance;
    private Integer totalQuantity;

    public void updateNickname(String nickname){
        this.nickname = nickname;
    }

//    @
//    List<Plogging> ploggingList = new ArrayList<>();

//    @OneToOne
//    private Pet pet;
}
