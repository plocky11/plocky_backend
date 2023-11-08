package com.plocky.domain.trashcan.entity;

import com.plocky.global.entity.BaseTimeEntity;
import com.plocky.global.entity.Location;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Transactional
@EntityListeners(AuditingEntityListener.class)
public class Trashcan extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="trashcan_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="location_id")
    private Location location;
}
