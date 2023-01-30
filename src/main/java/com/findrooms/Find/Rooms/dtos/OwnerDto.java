package com.findrooms.Find.Rooms.dtos;

import com.findrooms.Find.Rooms.entities.Address;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OwnerDto extends UserDto{
    private String identity;
    private String image;
    private AddressDto address;
}
