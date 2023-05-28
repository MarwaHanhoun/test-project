package com.airretailer.entity;

import com.airretailer.enums.CabinType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeatAvailability {
    @Id
    private UUID id = UUID.randomUUID();
    private String flightNumber;
    private String airline;
    private Integer seats;
    @Enumerated(EnumType.STRING)
    private CabinType cabinType;

}
