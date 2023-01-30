package com.findrooms.Find.Rooms.services;

import com.findrooms.Find.Rooms.dtos.RoomDto;

import java.util.List;

public interface RoomService {
    RoomDto registerRoom(RoomDto roomDto,Integer ownerId);
    RoomDto getRoomById(Integer id);
    List<RoomDto> getAllRooms();
    List<RoomDto> getRoomByRentPrice(Integer rent);
    List<RoomDto> getRoomByCity(String city);
    List<RoomDto> getRoomByPinCode(String location);

    List<RoomDto> getRoomByPlace(String place);
    RoomDto updateRoom(RoomDto roomDto, Integer id);

    void verifyRoom(Integer id);

    void bookRoom(Integer roomId,Integer customerId);
    void deleteRoom(Integer id);
}
