package com.findrooms.Find.Rooms.controllers;

import com.findrooms.Find.Rooms.dtos.CustomerDto;
import com.findrooms.Find.Rooms.dtos.LoginDto;
import com.findrooms.Find.Rooms.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<CustomerDto> registerCustomer(@Valid @RequestBody CustomerDto customerDto){
        CustomerDto savedCustomer = customerService.registerCustomer(customerDto);
        return new ResponseEntity<CustomerDto>(savedCustomer, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<CustomerDto> loginCustomer(@Valid @RequestBody LoginDto loginDto){
        String email = loginDto.getEmail();
        String password = loginDto.getPassword();
        CustomerDto customer = customerService.loginCustomer(email,password);
        return new ResponseEntity<CustomerDto>(customer, HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable("customerId") Integer customerId){
        CustomerDto customerDto = customerService.getCustomerById(customerId);
        return new ResponseEntity<CustomerDto>(customerDto,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers(){
        List<CustomerDto> customerDtoList = customerService.getAllCustomers();
        return new ResponseEntity<List<CustomerDto>>(customerDtoList,HttpStatus.OK);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerDto> updateCustomer(
            @Valid
            @RequestBody CustomerDto customerDto,
            @PathVariable("customerId") Integer customerId
    ){
        CustomerDto updatedCustomer = customerService.updateCustomer(customerDto,customerId);
        return new ResponseEntity<CustomerDto>(updatedCustomer,HttpStatus.OK);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("customerId") Integer customerId){
        customerService.deleteCustomer(customerId);
        return new ResponseEntity<String>("customer is deleted",HttpStatus.OK);
    }
}
