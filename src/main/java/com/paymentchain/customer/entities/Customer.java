package com.paymentchain.customer.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nombre")
    private String name;

    @Column(name = "Telefono")
    private String phone;
}
