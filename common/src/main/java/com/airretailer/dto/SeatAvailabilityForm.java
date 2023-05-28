package com.airretailer.dto;

import com.airretailer.enums.CabinType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class SeatAvailabilityForm {
    private final String flightNumber;
    private final String airline;
    private final Integer seats;
    private final CabinType cabinType;
}
