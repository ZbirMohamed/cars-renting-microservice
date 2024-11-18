package com.campify.reservationservice.domain.dto;

import com.campify.reservationservice.domain.entities.PaymentMethod;
import com.campify.reservationservice.models.Inventaire;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
public class ReservationDto {

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
