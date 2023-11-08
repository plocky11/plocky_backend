package com.plocky.domain.trashCategory.repository;

import com.plocky.domain.trashCategory.entity.TrashCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrashCategoryRepository extends JpaRepository<TrashCategory, Long> {
}
