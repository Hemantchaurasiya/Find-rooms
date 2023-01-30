package com.findrooms.Find.Rooms.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddressDto {
    private Integer id;
    private String pinCode;
    private String place;
    private String city;
    private String state;
    private String country;
}
