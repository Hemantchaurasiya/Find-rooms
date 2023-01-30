package com.findrooms.Find.Rooms.repositories;

import com.findrooms.Find.Rooms.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner,Integer> {
    Owner findByEmail(String email);
}
