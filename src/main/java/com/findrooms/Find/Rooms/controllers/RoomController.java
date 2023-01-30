package com.findrooms.Find.Rooms.controllers;

import com.findrooms.Find.Rooms.dtos.RoomDto;
import com.findrooms.Find.Rooms.services.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @PostMapping("/register/{ownerId}")
    public ResponseEntity<RoomDto> registerRoom(
            @Valid
            @PathVariable("ownerId") Integer ownerId,
            @RequestBody RoomDto roomDto
    ){
        RoomDto savedRoom = roomService.registerRoom(roomDto,ownerId);
        return new ResponseEntity<RoomDto>(savedRoom, HttpStatus.CREATED);
    }

    @GetMapping("/get-room/{roomId}")
    public ResponseEntity<RoomDto> getRoom(@Valid @PathVariable("roomId") Integer roomId){
        RoomDto roomDto = roomService.getRoomById(roomId);
        return new ResponseEntity<RoomDto>(roomDto,HttpStatus.OK);
    }

    @GetMapping("/search-room/{price}")
    public ResponseEntity<List<RoomDto>> searchRoomByPrice(@Valid @PathVariable("price") Integer price ){
        List<RoomDto> roomDtoList = roomService.getRoomByRentPrice(price);
        return new ResponseEntity<List<RoomDto>>(roomDtoList,HttpStatus.OK);
    }

    @GetMapping("/search-pincode/{pinCode}")
    public ResponseEntity<List<RoomDto>> searchRoomByPinCode(@Valid @PathVariable("pinCode") String pinCode ){
        List<RoomDto> roomDtoList = roomService.getRoomByPinCode(pinCode);
        return new ResponseEntity<List<RoomDto>>(roomDtoList,HttpStatus.OK);
    }

    @GetMapping("/search-city/{city}")
    public ResponseEntity<List<RoomDto>> searchRoomByCity(@Valid @PathVariable("city") String city ){
        List<RoomDto> roomDtoList = roomService.getRoomByCity(city);
        return new ResponseEntity<List<RoomDto>>(roomDtoList,HttpStatus.OK);
    }

    @GetMapping("/search-place/{place}")
    public ResponseEntity<List<RoomDto>> searchRoomByPlace(
            @Valid @PathVariable("place") String place
    ){
        List<RoomDto> roomDtoList = roomService.getRoomByPlace(place);
        return new ResponseEntity<List<RoomDto>>(roomDtoList,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RoomDto>> getAllRooms(){
        List<RoomDto> roomDtoList = roomService.getAllRooms();
        return new ResponseEntity<List<RoomDto>>(roomDtoList,HttpStatus.OK);
    }

    @PutMapping("/{roomId}")
    public ResponseEntity<RoomDto> updateRoom(
            @Valid
            @RequestBody RoomDto roomDto,
            @PathVariable("roomId") Integer roomId
    ){
        RoomDto updatedRoom = roomService.updateRoom(roomDto,roomId);
        return new ResponseEntity<RoomDto>(updatedRoom,HttpStatus.OK);
    }

    @GetMapping("/verify/{roomId}")
    public ResponseEntity<String> verifyRoom(@PathVariable("roomId") Integer roomId){
        roomService.verifyRoom(roomId);
        return new ResponseEntity<String>("room is verified",HttpStatus.OK);
    }

    @GetMapping("/book/{roomId}/room/{customerId}")
    public ResponseEntity<String> bookRoom(
            @PathVariable("roomId") Integer roomId,
            @PathVariable("customerId") Integer customerId
    ){
        roomService.bookRoom(roomId,customerId);
        return new ResponseEntity<String>("room is booked",HttpStatus.OK);
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<String> deleteRoom(@PathVariable("roomId") Integer roomId){
        roomService.deleteRoom(roomId);
        return new ResponseEntity<String>("room is deleted",HttpStatus.OK);
    }
}
