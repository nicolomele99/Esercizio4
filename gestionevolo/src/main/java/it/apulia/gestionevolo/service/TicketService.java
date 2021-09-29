package it.apulia.gestionevolo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.apulia.gestionevolo.model.Ticket;
import it.apulia.gestionevolo.repository.TicketRepository;

@Service
public class TicketService {
    @Autowired
    private TicketRepository repo;

    public void save(Ticket ticket) {
        repo.save(ticket);
    }

    public Long findPriceTickets(Long ticketId) {
        Long tickets = repo.findPriceTickets(ticketId);
        return tickets;
    }

    public Long findNumberReservedSeats(Long flightId) {
        Long seats = repo.findNumberReservedSeats(flightId);
        return seats;
    }
}
