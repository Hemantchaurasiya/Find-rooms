package com.findrooms.Find.Rooms.repositories;

import com.findrooms.Find.Rooms.entities.Address;
import com.findrooms.Find.Rooms.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository  extends JpaRepository<Address,Integer> {
    List<Address> findByCity(String city);
    List<Address> findByPlace(String place);
    List<Address> findByPinCode(String pinCode);
}
