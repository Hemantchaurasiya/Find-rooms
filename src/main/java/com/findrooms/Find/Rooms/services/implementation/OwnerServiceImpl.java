package com.findrooms.Find.Rooms.services.implementation;

import com.findrooms.Find.Rooms.dtos.OwnerDto;
import com.findrooms.Find.Rooms.entities.Address;
import com.findrooms.Find.Rooms.entities.Owner;
import com.findrooms.Find.Rooms.exceptions.ResourceNotFoundException;
import com.findrooms.Find.Rooms.repositories.AddressRepository;
import com.findrooms.Find.Rooms.repositories.OwnerRepository;
import com.findrooms.Find.Rooms.services.OwnerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnerServiceImpl implements OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public OwnerDto registerOwner(OwnerDto ownerDto) {
        Owner owner = modelMapper.map(ownerDto,Owner.class);
        Owner savedOwner = ownerRepository.save(owner);
        return modelMapper.map(savedOwner,OwnerDto.class);
    }

    @Override
    public OwnerDto loginOwner(String email, String password) {
        Owner owner = ownerRepository.findByEmail(email);
        if (!owner.getPassword().equals(password)){
            return null;
        }
        return modelMapper.map(owner, OwnerDto.class);
    }

    @Override
    public OwnerDto getOwnerById(Integer id) {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Owner Not Found!"));
        return modelMapper.map(owner,OwnerDto.class);
    }

    @Override
    public List<OwnerDto> getAllOwners() {
        List<Owner> ownerList = ownerRepository.findAll();
        List<OwnerDto> ownerDtoList = ownerList.stream()
                .map(owner -> modelMapper.map(owner,OwnerDto.class)).collect(Collectors.toList());
        return ownerDtoList;
    }

    @Override
    public OwnerDto updateOwner(OwnerDto ownerDto, Integer id) {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Owner Not Found!"));

        if (ownerDto.getIdentity()!=null){
            owner.setIdentity(ownerDto.getIdentity());
        }
        if (owner.getAddress()==null){
            Address address = new Address();
            address.setPinCode(ownerDto.getAddress().getPinCode());
            address.setPlace(ownerDto.getAddress().getPlace());
            address.setCity(ownerDto.getAddress().getCity());
            address.setState(ownerDto.getAddress().getState());
            address.setCountry(ownerDto.getAddress().getCountry());
            owner.setAddress(address);
        } else if (ownerDto.getAddress()!=null) {
            Address address = addressRepository.findById(owner.getAddress().getId())
                    .orElseThrow(()->new ResourceNotFoundException("Address not found!"));
            address.setPinCode(ownerDto.getAddress().getPinCode());
            address.setPlace(ownerDto.getAddress().getPlace());
            address.setCity(ownerDto.getAddress().getCity());
            address.setState(ownerDto.getAddress().getState());
            address.setCountry(ownerDto.getAddress().getCountry());
            owner.setAddress(address);
        }
        if (ownerDto.getImage()!=null){
            owner.setImage(ownerDto.getImage());
        }

        Owner savedOwner = ownerRepository.save(owner);
        return modelMapper.map(savedOwner, OwnerDto.class);
    }

    @Override
    public void deleteOwner(Integer id) {
        Owner owner = ownerRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Owner Not Found!"));
        ownerRepository.delete(owner);
    }
}
