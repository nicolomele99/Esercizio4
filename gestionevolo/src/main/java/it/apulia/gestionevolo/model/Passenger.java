package it.apulia.gestionevolo.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;


@Entity
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long passengerId;
    private String firstName;
    private String lastName;
    private String address;
    @Size( min = 10 )
    private String phoneNumber;
    private String email;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User userId;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "passengers_tickets", joinColumns = { @JoinColumn(name = "passenger_id") }, inverseJoinColumns = {
            @JoinColumn(name = "ticket_id") })
    private Set<Ticket> tickets;

    public void addTicket(Ticket ticket) {
        if (this.tickets == null) {
            this.tickets = new HashSet<>();
        }
        this.tickets.add(ticket);
    }

    public Passenger() {
    }

    public Passenger(Long passengerId, String firstName, String lastName, String address, String phoneNumber,
                     String email, User userId, Set<Ticket> tickets) {
        this.passengerId = passengerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.userId = userId;
        this.tickets = tickets;
    }

    public Long getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Long passengerId) {
        this.passengerId = passengerId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }
}
