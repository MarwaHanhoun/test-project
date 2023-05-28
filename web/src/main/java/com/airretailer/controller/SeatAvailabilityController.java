package com.airretailer.controller;

import com.airretailer.advice.AppError;
import com.airretailer.dto.SeatAvailabilityForm;
import com.airretailer.dto.SeatAvailabilityProjection;
import com.airretailer.dto.UpdateSeats;
import com.airretailer.enums.CabinType;
import com.airretailer.exptions.DuplicatedFlight;
import com.airretailer.exptions.NoSeatAvailable;
import com.airretailer.exptions.SeatAvailabilityNotExist;
import com.airretailer.service.SeatAvailabilityService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/v1/flight/seats")
@RequiredArgsConstructor
public class SeatAvailabilityController {
    private final SeatAvailabilityService service;

    @GetMapping
    public Page<SeatAvailabilityProjection> list(@RequestParam(required = false) String airline,
                                                 @RequestParam(required = false) String flightNumber,
                                                 @RequestParam(required = false) CabinType cabinType, Pageable pageable) {
        return service.list(cabinType, flightNumber, airline, pageable);
    }

    @PostMapping
    public SeatAvailabilityProjection add(@RequestBody SeatAvailabilityForm form) throws DuplicatedFlight {
        return service.addSeatAvailability(form);
    }

    @PutMapping("/{id}")
    public SeatAvailabilityProjection update(@PathVariable UUID id, @RequestBody UpdateSeats seats) throws SeatAvailabilityNotExist {
        return service.updateSeatAvailability(id, seats.getSeats());
    }

    @DeleteMapping("/{id}")
    public SeatAvailabilityProjection update(@PathVariable UUID id) throws SeatAvailabilityNotExist {
        return service.deleteSeatAvailability(id);
    }

    @GetMapping("/search")
    public ObjectNode search(@RequestParam CabinType cabinType, @RequestParam String flightNumber)  {
        return service.search(cabinType, flightNumber);
    }



}
