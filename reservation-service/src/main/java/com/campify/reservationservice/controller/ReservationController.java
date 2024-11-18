package com.campify.reservationservice.controller;

import com.campify.reservationservice.clients.InventaireRestClient;
import com.campify.reservationservice.domain.dto.ReservationDto;
import com.campify.reservationservice.domain.entities.ReservationEntity;
import com.campify.reservationservice.mappers.Mapper;
import com.campify.reservationservice.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reservation")

public class ReservationController {


    @Value("${stripe.api.key}")
    private String stripeApiKey;

    private ReservationService reservationService;
    private InventaireRestClient inventaireRestClient;
    private Mapper<ReservationEntity, ReservationDto> reservationMapper;

    public ReservationController(ReservationService reservationService, InventaireRestClient inventaireRestClient, Mapper<ReservationEntity, ReservationDto> reservationMapper) {
        this.reservationService = reservationService;
        this.inventaireRestClient = inventaireRestClient;
        this.reservationMapper = reservationMapper;
    }

    @GetMapping
    public List<ReservationDto> reservationList(@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ReservationEntity> reservationPage = reservationService.findAll(pageable);

        return reservationPage.getContent().stream()
                .map(reservationMapper::mapTo)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDto> getReservationById(@PathVariable int id){
        Optional<ReservationEntity> reservation = reservationService.findByid(id);
        return reservation.map(reservationEntity -> {
            ReservationDto reservationDto  =reservationMapper.mapTo(reservationEntity);
            return new ResponseEntity<>(reservationDto , HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)) ;
    }
    @PostMapping
    public ResponseEntity<ReservationDto> createReservation(@Valid @RequestBody ReservationDto reservation){

        ReservationEntity reservationEntity = reservationMapper.mapFrom(reservation);
        ReservationEntity savedReservationEntity = reservationService.save(reservationEntity);
        //inventaireRestClient.updateInventaireDispo((long) reservation.getInventaire_id());
        return new ResponseEntity<>(reservation, HttpStatus.CREATED);

    }
    @PutMapping("/{id}")
    public ResponseEntity<ReservationDto> updateReservation(
            @PathVariable("id") int id,
            @RequestBody ReservationDto reservationDto)
    {
        if(!reservationService.isExisting(id))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        reservationDto.setId(id);
        ReservationEntity reservation = reservationMapper.mapFrom(reservationDto);
        ReservationEntity savedReservation = reservationService.save(reservation);

        return new ResponseEntity<>(reservationMapper.mapTo(savedReservation),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCar(
            @PathVariable("id") int id
    ){
        reservationService.delete(id);
        return new ResponseEntity("Pizza id :"+id+" deleted with success",HttpStatus.NO_CONTENT);
    }
}
