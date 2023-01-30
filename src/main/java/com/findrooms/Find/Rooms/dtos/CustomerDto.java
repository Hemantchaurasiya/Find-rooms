package com.findrooms.Find.Rooms.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto extends UserDto{
    private String identity;
    private String image;
    private AddressDto address;
}
