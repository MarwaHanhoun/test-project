package com.airretailer.dto;

import com.airretailer.enums.CabinType;

import java.util.UUID;

public interface SeatAvailabilityProjection {

     UUID getId();
     String getFlightNumber();
     String getAirline();
     Integer getSeats();
     CabinType getCabinType();
}
