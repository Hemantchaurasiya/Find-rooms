package com.findrooms.Find.Rooms.services;

import com.findrooms.Find.Rooms.dtos.CustomerDto;
import com.findrooms.Find.Rooms.dtos.OwnerDto;

import java.util.List;

public interface OwnerService {
    OwnerDto registerOwner(OwnerDto ownerDto);

    OwnerDto loginOwner(String email,String password);
    OwnerDto getOwnerById(Integer id);
    List<OwnerDto> getAllOwners();
    OwnerDto updateOwner(OwnerDto ownerDto ,Integer id);
    void deleteOwner(Integer id);
}
