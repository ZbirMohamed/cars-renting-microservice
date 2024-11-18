package com.campify.reservationservice.service;

import com.campify.reservationservice.domain.entities.ReservationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface ReservationService {


    ReservationEntity save(ReservationEntity reservation);

    Optional<ReservationEntity> findByid(int id);

    Page<ReservationEntity> findAll(Pageable pageable);

    boolean isExisting(int id);

    void delete(int id);
}
