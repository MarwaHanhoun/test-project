package com.airretailer.service;

import com.airretailer.dto.SeatAvailabilityForm;
import com.airretailer.dto.SeatAvailabilityProjection;
import com.airretailer.entity.SeatAvailability;
import com.airretailer.enums.CabinType;
import com.airretailer.exptions.DuplicatedFlight;
import com.airretailer.exptions.NoSeatAvailable;
import com.airretailer.exptions.SeatAvailabilityNotExist;
import com.airretailer.repositry.SeatAvailabilityRepository;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class SeatAvailabilityServiceImpl implements SeatAvailabilityService{
    private final SeatAvailabilityRepository repo;
    private final AmenitiesService amenitiesService;
    @Override
    public SeatAvailabilityProjection addSeatAvailability(SeatAvailabilityForm form) throws DuplicatedFlight {
        if(repo.existsByFlightNumberAndCabinType(form.getFlightNumber(), form.getCabinType())){
            throw new DuplicatedFlight();
        }
        var seat = new SeatAvailability(UUID.randomUUID(),
                form.getFlightNumber(),
                form.getAirline(),
                form.getSeats(),
                form.getCabinType()
                );
        return asProjection(repo.save(seat));
    }

    @Override
    public SeatAvailabilityProjection updateSeatAvailability(UUID id, Integer seatNumber) throws SeatAvailabilityNotExist {
        var seat = repo.findById(id).orElseThrow(SeatAvailabilityNotExist::new);
        seat.setSeats(seatNumber);
        return asProjection(repo.save(seat));
    }

    @Override
    public SeatAvailabilityProjection deleteSeatAvailability(UUID id) throws SeatAvailabilityNotExist {
        var seat = repo.findById(id).orElseThrow(SeatAvailabilityNotExist::new);
        repo.delete(seat);
        return asProjection(seat);
    }

    @Override
    public SeatAvailabilityProjection getSeatAvailability(UUID id) throws SeatAvailabilityNotExist {
       return repo.findById(id).map(this::asProjection).orElseThrow(SeatAvailabilityNotExist::new);
    }

    @Override
    public Page<SeatAvailabilityProjection> list(CabinType cabinType, String flightNumber, String airline, Pageable pageable) {
        return repo.search(cabinType,flightNumber,airline,pageable).map(this::asProjection);
    }

    @Override
    public ObjectNode search(CabinType cabinType, String flightNumber) throws NoSeatAvailable {
        return (ObjectNode) repo.findByFlightNumberAndCabinType(flightNumber, cabinType)
                .filter(x->x.getSeats() >0)
                .map(x-> amenitiesService.findAmenitiesFor(x.getCabinType()))
                .orElseThrow(NoSeatAvailable::new);
    }

    private SeatAvailabilityProjection asProjection(SeatAvailability seatAvailability){
        return new SeatAvailabilityProjection() {
            @Override
            public UUID getId() {
                return seatAvailability.getId();
            }
            @Override
            public String getFlightNumber() {
                return seatAvailability.getFlightNumber();
            }

            @Override
            public String getAirline() {
                return seatAvailability.getAirline();
            }

            @Override
            public Integer getSeats() {
                return seatAvailability.getSeats();
            }

            @Override
            public CabinType getCabinType() {
                return seatAvailability.getCabinType();
            }
        };
    }
}
