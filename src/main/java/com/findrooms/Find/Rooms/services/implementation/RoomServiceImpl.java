package com.findrooms.Find.Rooms.services.implementation;

import com.findrooms.Find.Rooms.dtos.OwnerDto;
import com.findrooms.Find.Rooms.dtos.RoomDto;
import com.findrooms.Find.Rooms.entities.Address;
import com.findrooms.Find.Rooms.entities.Customer;
import com.findrooms.Find.Rooms.entities.Owner;
import com.findrooms.Find.Rooms.entities.Room;
import com.findrooms.Find.Rooms.exceptions.ResourceNotFoundException;
import com.findrooms.Find.Rooms.repositories.AddressRepository;
import com.findrooms.Find.Rooms.repositories.CustomerRepository;
import com.findrooms.Find.Rooms.repositories.OwnerRepository;
import com.findrooms.Find.Rooms.repositories.RoomRepository;
import com.findrooms.Find.Rooms.services.RoomService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.sasl.SaslServer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RoomDto registerRoom(RoomDto roomDto,Integer ownerId) {
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(()->new ResourceNotFoundException("Owner Not Found!"));
        Room room = modelMapper.map(roomDto,Room.class);
        room.setOwner(owner);
        roomRepository.save(room);
        return modelMapper.map(room,RoomDto.class);
    }

    @Override
    public RoomDto getRoomById(Integer id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Room not Found!"));
        OwnerDto ownerDto = modelMapper.map(room.getOwner(),OwnerDto.class);
        RoomDto roomDto = modelMapper.map(room,RoomDto.class);
        roomDto.setOwner(ownerDto);
        return roomDto;
    }

    @Override
    public List<RoomDto> getAllRooms() {
        List<Room> roomList = roomRepository.findAll();
        List<RoomDto> roomDtoList = roomList.stream()
                .map(room-> {
                    OwnerDto ownerDto = modelMapper.map(room.getOwner(),OwnerDto.class);
                    RoomDto roomDto = modelMapper.map(room,RoomDto.class);
                    roomDto.setOwner(ownerDto);
                    return roomDto;
                }).collect(Collectors.toList());
        return roomDtoList;
    }

    @Override
    public List<RoomDto> getRoomByRentPrice(Integer rent) {
        List<Room> roomList = roomRepository.findByRent(rent);
        List<RoomDto> roomDtoList = roomList
                .stream()
                .map((room)-> {
                    OwnerDto ownerDto = modelMapper.map(room.getOwner(),OwnerDto.class);
                    RoomDto roomDto = modelMapper.map(room,RoomDto.class);
                    roomDto.setOwner(ownerDto);
                    return roomDto;
                }).collect(Collectors.toList());
        return roomDtoList;
    }

    @Override
    public List<RoomDto> getRoomByCity(String city) {
        List<Address> roomAddressList = addressRepository.findByCity(city);
        List<Room> roomList = new ArrayList<>();
        for (Address roomAddress:roomAddressList) {
            List<Room> rooms = roomRepository.findByAddress(roomAddress.getId());
            for (Room room:rooms){
                roomList.add(room);
            }
        }
        List<RoomDto> roomDtoList = roomList
                .stream()
                .map((room)-> {
                    OwnerDto ownerDto = modelMapper.map(room.getOwner(),OwnerDto.class);
                    RoomDto roomDto = modelMapper.map(room,RoomDto.class);
                    roomDto.setOwner(ownerDto);
                    return roomDto;
                }).collect(Collectors.toList());
        return roomDtoList;
    }

    @Override
    public List<RoomDto> getRoomByPinCode(String location) {
        List<Address> roomAddressList = addressRepository.findByPinCode(location);
        List<Room> roomList = new ArrayList<>();
        for (Address roomAddress:roomAddressList) {
            List<Room> rooms = roomRepository.findByAddress(roomAddress.getId());
            for (Room room:rooms){
                roomList.add(room);
            }
        }
        List<RoomDto> roomDtoList = roomList
                .stream()
                .map((room)-> {
                    OwnerDto ownerDto = modelMapper.map(room.getOwner(),OwnerDto.class);
                    RoomDto roomDto = modelMapper.map(room,RoomDto.class);
                    roomDto.setOwner(ownerDto);
                    return roomDto;
                }).collect(Collectors.toList());
        return roomDtoList;
    }

    @Override
    public List<RoomDto> getRoomByPlace(String place) {
        List<Address> roomAddressList = addressRepository.findByPlace(place);
        List<Room> roomList = new ArrayList<>();
        if (roomAddressList!=null){
            for (Address roomAddress:roomAddressList) {
                List<Room> rooms = roomRepository.findByAddress(roomAddress.getId());
                for (Room room:rooms){
                    roomList.add(room);
                }

            }
            List<RoomDto> roomDtoList = roomList
                    .stream()
                    .map((room)-> {
                        OwnerDto ownerDto = modelMapper.map(room.getOwner(),OwnerDto.class);
                        RoomDto roomDto = modelMapper.map(room,RoomDto.class);
                        roomDto.setOwner(ownerDto);
                        return roomDto;
                    }).collect(Collectors.toList());
            return roomDtoList;
        }
        return new ArrayList<>();
    }

    @Override
    public RoomDto updateRoom(RoomDto roomDto, Integer id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Room not Found!"));

        if (roomDto.getRent()!=0){
            room.setRent(roomDto.getRent());
        }
        if (room.getAddress()==null){
            Address address = new Address();
            address.setPinCode(roomDto.getAddress().getPinCode());
            address.setPlace(roomDto.getAddress().getPlace());
            address.setCity(roomDto.getAddress().getCity());
            address.setState(roomDto.getAddress().getState());
            address.setCountry(roomDto.getAddress().getCountry());
            room.setAddress(address);
        } else if (roomDto.getAddress()!=null) {
            Address address = addressRepository.findById(room.getAddress().getId())
                    .orElseThrow(()->new ResourceNotFoundException("Address not found!"));
            address.setPinCode(roomDto.getAddress().getPinCode());
            address.setPlace(roomDto.getAddress().getPlace());
            address.setCity(roomDto.getAddress().getCity());
            address.setState(roomDto.getAddress().getState());
            address.setCountry(roomDto.getAddress().getCountry());
            room.setAddress(address);
        }
        if (roomDto.getAbout()!=null){
            room.setAbout(roomDto.getAbout());
        }
        if (roomDto.getDescription()!=null){
            room.setDescription(roomDto.getDescription());
        }
        if (roomDto.getImages()!=null){
            room.setImages(roomDto.getImages());
        }

        Room savedRoom = roomRepository.save(room);
        OwnerDto ownerDto = modelMapper.map(savedRoom.getOwner(),OwnerDto.class);
        RoomDto roomDto1 = modelMapper.map(savedRoom,RoomDto.class);
        roomDto1.setOwner(ownerDto);
        return roomDto1;
    }

    @Override
    public void verifyRoom(Integer id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Room not Found!"));
        if (room.isVerified()==false){
            room.setVerified(true);
            roomRepository.save(room);
        }
    }

    @Override
    public void bookRoom(Integer roomId, Integer customerId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(()->new ResourceNotFoundException("Room not Found!"));
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(()->new ResourceNotFoundException("Customer not Found!"));
        if (room.isVerified()==true){
            room.setCustomer(customer);
            room.setTrack(true);
            roomRepository.save(room);
        }
    }

    @Override
    public void deleteRoom(Integer id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Room not Found!"));
        roomRepository.delete(room);
    }
}
