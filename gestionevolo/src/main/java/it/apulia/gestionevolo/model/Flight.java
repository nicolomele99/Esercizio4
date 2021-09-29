package it.apulia.gestionevolo.model;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long flightId;

    private Timestamp departureDate;
    @NotEmpty(message = "Inserire luogo di partenza!")
    private String departure;
    @NotEmpty(message = "Inserire destinazione!")
    private String destination;
    @NotNull(message = "Inserire capienza!")
    @Min(value=0,message = "Inserire valore maggiore o uguale a 0!")
    private int seats;
    @NotNull(message = "Inserire prezzo!")
    @Min(value=0,message = "Inserire valore maggiore o uguale a 0!")
    private Float price;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Ticket> tickets;

    public Flight(){
    }

    public Flight(Long flightId, Timestamp departureDate, String departure, String destination, int seats, Float price,
                  Set<Ticket> tickets) {
        this.flightId = flightId;
        this.departureDate = departureDate;
        this.departure = departure;
        this.destination = destination;
        this.seats = seats;
        this.price = price;
        this.tickets = tickets;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public Timestamp getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Timestamp departureDate) {
        this.departureDate = departureDate;
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


    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }
}
