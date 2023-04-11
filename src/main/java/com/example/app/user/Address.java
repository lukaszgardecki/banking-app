package com.example.app.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String street;
    private String city;
    private String zipCode;
    @OneToMany(mappedBy = "address")
//    @JoinColumn(name = "address_id")
    private List<User> user = new ArrayList<>();
}
