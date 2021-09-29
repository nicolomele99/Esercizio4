package it.apulia.gestionevolo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.apulia.gestionevolo.model.Ticket;
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Query(value = "SELECT SUM(flight.price) FROM flight INNER JOIN ticket ON flight.flight_id = ticket.flight_id WHERE ticket.ticket_id = :ticketId", nativeQuery = true)
    Long findPriceTickets(Long ticketId);

    @Query(value = "SELECT COUNT(*) FROM flight INNER JOIN ticket ON flight.flight_id = ticket.flight_id INNER JOIN passengers_tickets ON ticket.ticket_id=  passengers_tickets.ticket_id INNER JOIN passenger ON passengers_tickets.passenger_id = passenger.passenger_id  WHERE flight.flight_id = :flightId", nativeQuery = true)
    Long findNumberReservedSeats(Long flightId);
}
