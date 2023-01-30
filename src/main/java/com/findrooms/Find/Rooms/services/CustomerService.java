package com.findrooms.Find.Rooms.services;

import com.findrooms.Find.Rooms.dtos.CustomerDto;

import java.util.List;

public interface CustomerService {
    CustomerDto registerCustomer(CustomerDto customerDto);
    CustomerDto loginCustomer(String email,String password);
    CustomerDto getCustomerById(Integer id);
    List<CustomerDto> getAllCustomers();
    CustomerDto updateCustomer(CustomerDto customerDto ,Integer id);
    void deleteCustomer(Integer id);
}
