package com.findrooms.Find.Rooms.services.implementation;

import com.findrooms.Find.Rooms.dtos.CustomerDto;
import com.findrooms.Find.Rooms.entities.Address;
import com.findrooms.Find.Rooms.entities.Customer;
import com.findrooms.Find.Rooms.exceptions.ResourceNotFoundException;
import com.findrooms.Find.Rooms.repositories.AddressRepository;
import com.findrooms.Find.Rooms.repositories.CustomerRepository;
import com.findrooms.Find.Rooms.services.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CustomerDto registerCustomer(CustomerDto customerDto) {
        Customer savedCustomer = customerRepository.save(modelMapper.map(customerDto,Customer.class));
        return modelMapper.map(savedCustomer,CustomerDto.class);
    }

    @Override
    public CustomerDto loginCustomer(String email, String password) {
        Customer customer = customerRepository.findByEmail(email);
        if (!customer.getPassword().equals(password)){
            return null;
        }
        return modelMapper.map(customer,CustomerDto.class);
    }

    @Override
    public CustomerDto getCustomerById(Integer id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Customer Not Found!"));
        return modelMapper.map(customer,CustomerDto.class);
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDto> customerDtoList = customers.stream()
                .map(customer -> modelMapper.map(customer,CustomerDto.class)).collect(Collectors.toList());
        return customerDtoList;
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto, Integer id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Customer Not Found!"));
        if (customerDto.getFirstname()!=null){
            customer.setFirstname(customerDto.getFirstname());
        }
        if (customerDto.getLastname()!=null){
            customer.setLastname(customerDto.getLastname());
        }
        if (customerDto.getEmail()!=null){
            customer.setEmail(customerDto.getEmail());
        }
        if (customerDto.getPhone()!=null){
            customer.setPhone(customerDto.getPhone());
        }
        if (customerDto.getIdentity()!=null){
            customer.setIdentity(customerDto.getIdentity());
        }
        if (customer.getAddress()==null){
            Address address = new Address();
            address.setPinCode(customerDto.getAddress().getPinCode());
            address.setPlace(customerDto.getAddress().getPlace());
            address.setCity(customerDto.getAddress().getCity());
            address.setState(customerDto.getAddress().getState());
            address.setCountry(customerDto.getAddress().getCountry());
            customer.setAddress(address);
        } else if (customerDto.getAddress()!=null) {
            Address address = addressRepository.findById(customer.getAddress().getId())
                    .orElseThrow(()->new ResourceNotFoundException("Address not found!"));
            address.setPinCode(customerDto.getAddress().getPinCode());
            address.setPlace(customerDto.getAddress().getPlace());
            address.setCity(customerDto.getAddress().getCity());
            address.setState(customerDto.getAddress().getState());
            address.setCountry(customerDto.getAddress().getCountry());
            customer.setAddress(address);
        }
        if (customerDto.getImage()!=null){
            customer.setImage(customerDto.getImage());
        }
        Customer updatedCustomer = customerRepository.save(customer);
        return modelMapper.map(updatedCustomer, CustomerDto.class);
    }

    @Override
    public void deleteCustomer(Integer id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Customer Not Found!"));
        customerRepository.delete(customer);
    }
}
