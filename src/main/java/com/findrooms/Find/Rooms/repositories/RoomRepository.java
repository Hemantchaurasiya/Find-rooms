package com.findrooms.Find.Rooms.repositories;

import com.findrooms.Find.Rooms.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room,Integer> {
    List<Room> findByRent(Integer rent);

    @Query(value = "SELECT * FROM ROOM WHERE address_id=?",nativeQuery = true)
    List<Room> findByAddress(Integer id);
}
