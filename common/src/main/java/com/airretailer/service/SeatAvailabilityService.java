package com.airretailer.service;

import com.airretailer.dto.SeatAvailabilityForm;
import com.airretailer.dto.SeatAvailabilityProjection;
import com.airretailer.enums.CabinType;
import com.airretailer.exptions.DuplicatedFlight;
import com.airretailer.exptions.NoSeatAvailable;
import com.airretailer.exptions.SeatAvailabilityNotExist;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface SeatAvailabilityService {
    SeatAvailabilityProjection addSeatAvailability(SeatAvailabilityForm form) throws DuplicatedFlight;
    SeatAvailabilityProjection updateSeatAvailability(UUID id, Integer seatNumber) throws SeatAvailabilityNotExist;
    SeatAvailabilityProjection deleteSeatAvailability(UUID id) throws SeatAvailabilityNotExist;
    SeatAvailabilityProjection getSeatAvailability(UUID id) throws SeatAvailabilityNotExist;
    Page<SeatAvailabilityProjection> list(CabinType cabinType, String flightNumber, String airline, Pageable pageable);
    ObjectNode search(CabinType cabinType, String flightNumber) throws NoSeatAvailable;


}
