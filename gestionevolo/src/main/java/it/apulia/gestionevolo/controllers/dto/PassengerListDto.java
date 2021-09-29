package it.apulia.gestionevolo.controllers.dto;

import java.util.List;

import it.apulia.gestionevolo.model.Passenger;

public class PassengerListDto {

    List<Passenger> passengers;

    public void addPassenger(Passenger passenger) {
        this.passengers.add(passenger);
    }

    public PassengerListDto(List<Passenger> passengers) {
        this.passengers = passengers;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }





}
