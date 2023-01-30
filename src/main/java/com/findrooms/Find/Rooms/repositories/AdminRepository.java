package com.findrooms.Find.Rooms.repositories;

import com.findrooms.Find.Rooms.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {
}
