package com.donotpanic.airport.domain.airport;

import com.donotpanic.airport.domain.Engine.ObjectType;

public class PlaneParkingPlace extends PlaneAirportLocation {

    PlaneParkingPlace(Airport airport, int number){
        super(airport, "PlaneParking #" + (number) + " (" + airport.getAirportName() + ")", ObjectType.PLANE_PARKING);
    }
}
