package com.campify.reservationservice.domain.entities;


import com.campify.reservationservice.models.Inventaire;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder @ToString
public class ReservationEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int inventaire_id;
    @Temporal(TemporalType.DATE)
    private Date pickTime;
    @Temporal(TemporalType.DATE)
    private Date dropTime;
    private String pickUp;
    private String dropOff;
    private String name;
    private String lastName;
    private String phone;
    private int age;
    private String email;
    private String address;
    private String city;
    private String zipcode;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    private Integer totalAmount;
    @Transient
    private Inventaire inventaire;

}
