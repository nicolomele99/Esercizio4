package it.apulia.gestionevolo.controllers.dto;

import it.apulia.gestionevolo.model.Flight;

public class FindTicketIdAndPassengerDto {

    private Long ticketId;
    private String firstName;
    private String lastName;
    private Flight flightId;
    private String departure;
    private String destination;
    private String address;
    private String phoneNumber;
    private Float price;

    public FindTicketIdAndPassengerDto(Long ticketId, String firstName, String lastName, Flight flightId,
                                       String departure, String destination, String address, String phoneNumber, Float price) {
        this.ticketId = ticketId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.flightId = flightId;
        this.departure = departure;
        this.destination = destination;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.price = price;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Flight getFlightId() {
        return flightId;
    }

    public void setFlightId(Flight flightId) {
        this.flightId = flightId;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

}
