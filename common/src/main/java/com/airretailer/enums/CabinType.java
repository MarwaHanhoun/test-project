package com.airretailer.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum CabinType {

    Business("Business"),
    First("First"),
    PremiumEconomy("Premium Economy"),
    Economy("Economy");

    public final String displayName ;


}
