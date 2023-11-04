package com.plocky.domain.pet.entity;

import com.plocky.domain.member.entity.Member;
import com.plocky.domain.trashCategory.entity.TrashCategory;
import com.plocky.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@Transactional
public class Pet extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id")
    private Long id;
    private String petName;
    @Enumerated(EnumType.STRING)
    private PetKind petKind;
    private int level;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Long memberId;
}