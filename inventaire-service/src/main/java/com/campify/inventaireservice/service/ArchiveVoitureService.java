package com.campify.inventaireservice.service;

import com.campify.inventaireservice.domain.entities.ArchiveVoitureEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ArchiveVoitureService {


    ArchiveVoitureEntity save(ArchiveVoitureEntity voiture);

    Optional<ArchiveVoitureEntity> findByid(Long id);

    Page<ArchiveVoitureEntity> findAll(Pageable pageable);

    List<ArchiveVoitureEntity> findArchivedCarsByCarid(int id);

    List<ArchiveVoitureEntity> findCarByDisponible(int id);

    boolean isExisting(Long id);

    void delete(Long id);


}
