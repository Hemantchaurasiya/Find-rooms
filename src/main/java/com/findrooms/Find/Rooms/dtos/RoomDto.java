package com.findrooms.Find.Rooms.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class RoomDto {
    private Integer roomId;
    private Integer rent;
    private AddressDto address;
    private String description;
    private String about;
    private OwnerDto owner;
    private List<String> images;
}
