package com.findrooms.Find.Rooms.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer roomId;

    @Column(nullable = false)
    private Integer rent;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id",referencedColumnName = "id")
    private Address address;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "owner_id",referencedColumnName = "id")
    private Owner owner;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "customer_id",referencedColumnName = "id")
    private Customer customer;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String about;

    @Column(nullable = false)
    private List<String> images;

    private boolean track = false;
    private boolean verified = false;
}