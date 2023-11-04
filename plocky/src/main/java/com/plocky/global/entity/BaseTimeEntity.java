package com.plocky.global.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
public class BaseTimeEntity {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
