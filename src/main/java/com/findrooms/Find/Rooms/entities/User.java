package com.findrooms.Find.Rooms.entities;

import com.findrooms.Find.Rooms.dtos.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "firstname" ,nullable = false)
    private String firstname;

    @Column(name = "lastname" ,nullable = false)
    private String lastname;

    @Column(name = "email" ,nullable = false)
    private String email;

    @Column(name = "password" ,nullable = false)
    private String password;

    @Column(name = "phone" ,nullable = false)
    private String phone;

    @Column(name = "role" ,nullable = false)
    private Role role;

    private boolean activate = false;
}