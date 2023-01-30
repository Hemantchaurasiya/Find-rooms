package com.findrooms.Find.Rooms.controllers;

import com.findrooms.Find.Rooms.dtos.LoginDto;
import com.findrooms.Find.Rooms.dtos.OwnerDto;
import com.findrooms.Find.Rooms.services.OwnerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/owners")
public class OwnerController {
    @Autowired
    private OwnerService ownerService;

    @PostMapping("/register")
    public ResponseEntity<OwnerDto> registerCustomer(@Valid @RequestBody OwnerDto ownerDto){
        OwnerDto savedOwner = ownerService.registerOwner(ownerDto);
        return new ResponseEntity<OwnerDto>(savedOwner, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<OwnerDto> loginCustomer(@Valid @RequestBody LoginDto loginDto){
        String email = loginDto.getEmail();
        String password = loginDto.getPassword();
        OwnerDto ownerDto = ownerService.loginOwner(email,password);
        return new ResponseEntity<OwnerDto>(ownerDto, HttpStatus.OK);
    }

    @GetMapping("/{ownerId}")
    public ResponseEntity<OwnerDto> getCustomer(@PathVariable("ownerId") Integer ownerId){
        OwnerDto ownerDto = ownerService.getOwnerById(ownerId);
        return new ResponseEntity<OwnerDto>(ownerDto,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<OwnerDto>> getAllOwners(){
        List<OwnerDto> ownerDtoList  = ownerService.getAllOwners();
        return new ResponseEntity<List<OwnerDto>>(ownerDtoList,HttpStatus.OK);
    }

    @PutMapping("/{ownerId}")
    public ResponseEntity<OwnerDto> updateOwner(
            @Valid
            @RequestBody OwnerDto ownerDto,
            @PathVariable("ownerId") Integer ownerId
    ){
        OwnerDto updatedOwner = ownerService.updateOwner(ownerDto,ownerId);
        return new ResponseEntity<OwnerDto>(updatedOwner,HttpStatus.OK);
    }

    @DeleteMapping("/{ownerId}")
    public ResponseEntity<String> deleteOwner(@PathVariable("ownerId") Integer ownerId){
        ownerService.deleteOwner(ownerId);
        return new ResponseEntity<String>("owner is deleted",HttpStatus.OK);
    }
}
