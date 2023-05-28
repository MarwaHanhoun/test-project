package com.airretailer.repositry;

import com.airretailer.dto.SeatAvailabilityProjection;
import com.airretailer.entity.SeatAvailability;
import com.airretailer.enums.CabinType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface SeatAvailabilityRepository extends JpaRepository<SeatAvailability, UUID> {
    @Query(value = "from SeatAvailability where (:cabinType is null or cabinType =:cabinType)" +
            " and (:airline is null or airline =:airline)" +
            " and (:flightNumber is null or flightNumber =:flightNumber)",
    countQuery = "from SeatAvailability where (:cabinType is null or cabinType =:cabinType)" +
            " and (:airline is null or airline =:airline)" +
            " and (:flightNumber is null or flightNumber =:flightNumber)")
    Page<SeatAvailability> search(CabinType cabinType, String airline, String flightNumber, Pageable pageable);

    Boolean existsByFlightNumberAndCabinType(String flightNumber, CabinType cabinType);

    Optional<SeatAvailabilityProjection> findByFlightNumberAndCabinType(String flightNumber, CabinType cabinType);


}
