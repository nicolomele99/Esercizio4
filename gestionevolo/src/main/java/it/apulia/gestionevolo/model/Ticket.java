package it.apulia.gestionevolo.model;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ticketId;

    @ManyToOne
    @JoinColumn(name="flightId" , referencedColumnName="flightId")
    private Flight flightId;

    @ManyToMany(mappedBy = "tickets")
    private Set<Passenger> passengers = new HashSet<>();

    public Ticket() {

    }

    public Ticket(Long ticketId, Flight flightId, Set<Passenger> passengers) {
        this.ticketId = ticketId;
        this.flightId = flightId;
        this.passengers = passengers;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Flight getFlightId() {
        return flightId;
    }

    public void setFlightId(Flight flightId) {
        this.flightId = flightId;
    }

    public Set<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(Set<Passenger> passengers) {
        this.passengers = passengers;
    }
}
