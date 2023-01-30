package com.findrooms.Find.Rooms.services;

import com.findrooms.Find.Rooms.dtos.AdminDto;

public interface AdminService {
    AdminDto registerAdmin(AdminDto adminDto);
    AdminDto loginAdmin(String email,String password);
}
