package com.plocky.domain.trashcan.entity;

import com.plocky.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Transactional
public class Trashcan extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="trashcan_id")
    private Long id;
    private String coordinate;
}
