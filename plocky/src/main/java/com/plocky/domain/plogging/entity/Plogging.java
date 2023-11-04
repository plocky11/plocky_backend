package com.plocky.domain.plogging.entity;

import com.plocky.domain.member.entity.Member;
import com.plocky.domain.trashCategory.entity.TrashCategory;
import com.plocky.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class Plogging extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ploggin_id")
    private Long id;
    private float distance;
    private int amount;
    private String route;
    private String startedWhere;
    private LocalDateTime startedWhen;
    private String endedWhere;
    private LocalDateTime endedWhen;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="trash_category_id")
    private TrashCategory trashCategory;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;
}
