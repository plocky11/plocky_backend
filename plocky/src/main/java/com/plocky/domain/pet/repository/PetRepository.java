package com.plocky.domain.pet.repository;

import com.plocky.domain.member.entity.Member;
import com.plocky.domain.pet.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

}
